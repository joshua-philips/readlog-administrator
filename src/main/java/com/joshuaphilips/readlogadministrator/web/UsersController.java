package com.joshuaphilips.readlogadministrator.web;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.firebase.auth.FirebaseAuthException;
import com.joshuaphilips.readlogadministrator.models.UserObject;
import com.joshuaphilips.readlogadministrator.services.UserService;

@Controller
public class UsersController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String home(Model model) throws FirebaseAuthException {
		List<UserObject> users = userService.getAllUsers();
		model.addAttribute("users", users);

		return "index";
	}

	@GetMapping(value = "/{uid}")
	public String getUserRecord(@PathVariable String uid, Model model) throws FirebaseAuthException {
		UserObject user = userService.getUser(uid);
		model.addAttribute("user", user);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
		String created = user.getDateCreated().format(formatter);
		String signedIn = user.getLastSignedIn().format(formatter);
		model.addAttribute("created", created);
		model.addAttribute("signedIn", signedIn);

		return "profile";
	}

}
