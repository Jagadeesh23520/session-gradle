package com.example.session.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.session.config.CacheService;
import com.example.session.entity.Usercredentails;
import com.example.session.model.SessionResposne;
import com.example.session.model.UserCredentails;
import com.example.session.model.UserDetails;
import com.example.session.repository.UsercredentailsRepo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController implements SessionControllerAPI {

	@Autowired
	UsercredentailsRepo userRepo;

	@Autowired
	CacheService cacheService;

	@Override
	public ResponseEntity<String> loginByUserName(HttpServletRequest request) {

		ResponseEntity<String> response = null;
		String userUID = request.getHeader("userUID");
		String userName = request.getHeader("userName");
		String password = request.getHeader("password");

		HttpSession session = request.getSession();

//		String session_userUID = (String) session.getAttribute(session.getId() + "USERUID_CACHE");

		String userUID_cache = (String) cacheService.getCache(session.getId(), "USERUID_CACHE");

		if (userUID.equals(userUID_cache)) {
			response = new ResponseEntity<String>("user login successfully : " + userUID_cache, HttpStatus.ACCEPTED);
		} else {
			response = new ResponseEntity<String>("userUID not match with cache : " + userUID_cache, HttpStatus.OK);
		}
		System.out.println("userUID input : " + userUID);
		System.out.println("userUID cache : " + userUID_cache);
//		System.out.println("session userUID cache : " + session_userUID);

		return response;
	}

	@Override
	public ResponseEntity<Object> getLogin(HttpServletRequest request, HttpServletResponse servletResponse,
			UserCredentails userCredentails) {

		HttpSession session = request.getSession(true);
		Cookie[] cookies = request.getCookies();
		Optional<Cookie> cookie = Arrays.asList(request.getCookies()).stream()
				.filter(c -> "X-KeepAlive".equals(c.getName())).findAny();
		Instant instant = Instant.now();
		Instant timeExtended = instant.plusSeconds(1800);
		if (cookie.isEmpty()) {
			Cookie cookieValue = new Cookie("X-KeepAlive", timeExtended.toString());
			cookieValue.setMaxAge(-1);
			cookieValue.setHttpOnly(false);
			cookieValue.setPath("/");
			servletResponse.addCookie(cookieValue);
		}

		ResponseEntity<Object> sessionResponse = null;

		String loginId = userCredentails.getLoginId();
		Usercredentails userDetails = userRepo.findByLoginID(loginId);

		System.out.println(userDetails);

		if (userDetails != null && userDetails.getPassword().equals(userCredentails.getPassword())) {
			SessionResposne response = new SessionResposne();
			response.setLoginId(userDetails.getLoginID());
			response.setFirstName(userDetails.getFirstName());
			response.setLastName(userDetails.getLastName());
			response.setUserName(userDetails.getUserName());
			response.setAddress(userDetails.getAddress());
			response.setPhoneNumber(userDetails.getPhoneNumber());
			response.setUserUID(userDetails.getUserUID());

			cacheService.setCache(session.getId(), "USERUID_CACHE", response.getUserUID());
			cacheService.setCache(session.getId(), "LOGINID_CACHE", response.getLoginId());

//			session.setAttribute(session.getId() + "USERUID_CACHE", response.getUserUID());

			sessionResponse = new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			sessionResponse = new ResponseEntity<Object>("user Not Found", HttpStatus.BAD_REQUEST);
		}

		return sessionResponse;
	}

	@Override
	public ResponseEntity<Object> createUser(HttpServletRequest request, UserDetails userDetails) {

		ResponseEntity<Object> response = null;

		try {
			Usercredentails userDetail = userRepo.findByLoginID(userDetails.getLoginId());

			if (userDetail != null) {
				response = new ResponseEntity<Object>(
						"This loginID already taken by another user : " + userDetails.getLoginId(),
						HttpStatus.BAD_REQUEST);
			} else {
				userDetail = new Usercredentails();
				userDetail.setUserName(userDetails.getUserName());
				userDetail.setPassword(userDetails.getPassword());
				userDetail.setFirstName(userDetails.getFirstName());
				userDetail.setLastName(userDetails.getLastName());
				userDetail.setLoginID(userDetails.getLoginId());
				userDetail.setPhoneNumber(userDetails.getPhoneNumber());
				userDetail.setAddress(userDetails.getAddress());
				userDetail.setUserUID(UUID.randomUUID().toString());

				Usercredentails saveResponse = userRepo.save(userDetail);

				response = new ResponseEntity<Object>(saveResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Object>("Exception occured : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@Override
	public ResponseEntity<Object> updateUserDetails(UserDetails userDetails) {

		ResponseEntity<Object> response = null;

		try {
			Usercredentails userDetail = userRepo.findByLoginID(userDetails.getLoginId());

			if (userDetail == null) {
				response = new ResponseEntity<Object>("This loginID Not Found : " + userDetails.getLoginId(),
						HttpStatus.BAD_GATEWAY);
			} else {
				userDetail.setUserName(userDetails.getUserName());
				userDetail.setPassword(userDetails.getPassword());
				userDetail.setFirstName(userDetails.getFirstName());
				userDetail.setLastName(userDetails.getLastName());
				userDetail.setPhoneNumber(userDetails.getPhoneNumber());
				userDetail.setAddress(userDetails.getAddress());

				Usercredentails saveResponse = userRepo.saveAndFlush(userDetail);

				response = new ResponseEntity<Object>(saveResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Object>("Exception occured : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return response;

	}

}
