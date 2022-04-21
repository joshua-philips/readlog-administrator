package com.joshuaphilips.readlogadministrator.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserObject {
	private String name;
	private String email;
	private String uid;
	private String photoUrl;
	private LocalDateTime dateCreated;
	private LocalDateTime lastSignedIn;

}
