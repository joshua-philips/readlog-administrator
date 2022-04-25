package com.joshuaphilips.readlogadministrator;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class ReadlogAdministratorApplication {

	public static void main(String[] args) {

		SpringApplication.run(ReadlogAdministratorApplication.class, args);
	}

	@PostConstruct
	public void initialize() throws IOException {
		ClassLoader classLoader = ReadlogAdministratorApplication.class.getClassLoader();
		InputStream serviceAccount = classLoader.getResourceAsStream("serviceAccountKey.json");

		@SuppressWarnings("deprecation")
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		FirebaseApp.initializeApp(options);
	}

}