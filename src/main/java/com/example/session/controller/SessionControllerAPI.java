package com.example.session.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.session.model.UserCredentails;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/v1")
public interface SessionControllerAPI {

	@PostMapping(value = "/login")
	public ResponseEntity<Object> getLogin(HttpServletRequest request, @RequestBody UserCredentails userCredentails);

//	@PostMapping(value = "/register")
//	public ResponseEntity<Object> createUser(HttpServletRequest request, @RequestBody UserCredentails userCredentails);

	@GetMapping(value = "/userlogin")
	public void loginByUserName(HttpServletRequest request);

	@GetMapping(value = "/reg")
	public void reg(@RequestParam(required = true) String test);

}
