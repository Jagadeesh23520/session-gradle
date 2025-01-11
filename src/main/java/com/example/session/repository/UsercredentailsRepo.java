package com.example.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.session.entity.Usercredentails;


public interface UsercredentailsRepo extends JpaRepository<Usercredentails, Integer> {

	Usercredentails findByLoginID(String loginID);
}
