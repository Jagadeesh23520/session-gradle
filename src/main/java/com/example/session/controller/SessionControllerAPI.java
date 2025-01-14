package com.example.session.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.session.model.UserCredentails;
import com.example.session.model.UserDetails;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/v1")
public interface SessionControllerAPI {

	@PostMapping(value = "/login")
	public ResponseEntity<Object> getLogin(HttpServletRequest request, @RequestBody UserCredentails userCredentails);

	@PostMapping(value = "/register")
	public ResponseEntity<Object> createUser(HttpServletRequest request, @RequestBody UserDetails userDetails);

	@GetMapping(value = "/userlogin")
	public ResponseEntity<String> loginByUserName(HttpServletRequest request);

	@PutMapping(value = "/updateUsers")
	public ResponseEntity<Object> updateUserDetails(@RequestBody UserDetails userDetails);

}
