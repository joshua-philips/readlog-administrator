package com.joshuaphilips.readlogadministrator.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserMetadata;
import com.google.firebase.auth.UserRecord;
import com.joshuaphilips.readlogadministrator.models.UserObject;

@Service
public class UserService {
	private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

	public List<UserObject> getAllUsers() throws FirebaseAuthException {
		List<UserObject> users = new ArrayList<>();

		ListUsersPage page = firebaseAuth.listUsers(null);

		for (ExportedUserRecord user : page.iterateAll()) {
			UserMetadata metaData = user.getUserMetadata();
			LocalDateTime dateCreated = LocalDateTime.ofInstant(Instant.ofEpochMilli(metaData.getCreationTimestamp()),
					TimeZone.getDefault().toZoneId());
			LocalDateTime lastSignedIn = LocalDateTime.ofInstant(
					Instant.ofEpochMilli(metaData.getLastSignInTimestamp()), TimeZone.getDefault().toZoneId());

			users.add(new UserObject(user.getDisplayName(), user.getEmail(), user.getUid(), user.getPhotoUrl(),
					dateCreated, lastSignedIn));
		}

		return users;
	}

	public UserObject getUser(String uid) throws FirebaseAuthException {
		UserRecord userRecord = firebaseAuth.getUser(uid);
		LocalDateTime dateCreated = LocalDateTime.ofInstant(
				Instant.ofEpochMilli(userRecord.getUserMetadata().getCreationTimestamp()),
				TimeZone.getDefault().toZoneId());

		LocalDateTime lastSignedIn = LocalDateTime.ofInstant(
				Instant.ofEpochMilli(userRecord.getUserMetadata().getLastSignInTimestamp()),
				TimeZone.getDefault().toZoneId());

		UserObject user = new UserObject(userRecord.getDisplayName(), userRecord.getEmail(), userRecord.getUid(),
				userRecord.getPhotoUrl(), dateCreated, lastSignedIn);

		return user;
	}
}
