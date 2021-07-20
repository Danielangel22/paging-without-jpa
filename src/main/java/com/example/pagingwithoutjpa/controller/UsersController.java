package com.example.pagingwithoutjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagingwithoutjpa.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping(value = "/users")
	public ResponseEntity<Object> getUsers(@RequestParam int size, @RequestParam int page) {
		return usersService.getUsers(size, page);
	}
}
