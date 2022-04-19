package com.joshuaphilips.readlogadministrator.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;
import com.joshuaphilips.readlogadministrator.models.UserObject;
import com.joshuaphilips.readlogadministrator.services.UserService;

@RestController
public class UsersController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public List<UserObject> getMethodName() throws FirebaseAuthException {
		return userService.getAllUsers();

	}

}
