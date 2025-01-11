package com.example.session.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.session.config.CacheService;
import com.example.session.entity.Usercredentails;
import com.example.session.model.SessionResposne;
import com.example.session.model.UserCredentails;
import com.example.session.repository.UsercredentailsRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController implements SessionControllerAPI {

	@Autowired
	UsercredentailsRepo userRepo;

	@Autowired
	CacheService cacheService;

	@Override
	public void loginByUserName(HttpServletRequest request) {

		String userUID = request.getHeader("userUID");
		String userName = request.getHeader("userName");
		String password = request.getHeader("password");

		HttpSession session = request.getSession();

		String userUID_cache = (String) cacheService.getCache(session.getId(), "USERUID_CACHE");

		System.out.println("userUID input : " + userUID);
		System.out.println("userUID cache : " + userUID_cache);
	}

	@Override
	public ResponseEntity<Object> getLogin(HttpServletRequest request, UserCredentails userCredentails) {

		HttpSession session = request.getSession();
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
			sessionResponse = new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			sessionResponse = new ResponseEntity<Object>("user Not Found", HttpStatus.BAD_REQUEST);
		}

		return sessionResponse;
	}

	@Override
	public void reg(String test) {
		// TODO Auto-generated method stub

	}

}
