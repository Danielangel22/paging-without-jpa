package com.example.pagingwithoutjpa.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pagingwithoutjpa.Utilities.Util;
import com.example.pagingwithoutjpa.model.Users;

@Service
public class UsersService {

	private static List<String> users = Arrays.asList("Pedro", "Juan", "Arturo", "Pedrito");
	private static List<Integer> usersIds = Arrays.asList(1, 4, 3, 2);
	private static List<Users> usersModel = Arrays.asList(new Users("Pedro", "Solis"), new Users("Juan", "Solis"),
			new Users("Arturo", "Solis"), new Users("Pedrito", "Solis"));

	public ResponseEntity<Object> getUsers(int size, int page) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
		var response = Util.toPage(users, pageable, true, true);
		Util.toPage(usersIds, pageable, true, false);
		Util.toPage(usersModel, pageable, false, false);
		return ResponseEntity.ok(response);
	}

}
