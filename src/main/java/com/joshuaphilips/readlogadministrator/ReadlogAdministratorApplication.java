package com.joshuaphilips.readlogadministrator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class ReadlogAdministratorApplication {

	public static void main(String[] args) throws IOException {

		ClassLoader classLoader = ReadlogAdministratorApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

		String filePath = file.getAbsolutePath().replace("%20", " "); // Dealing with spaces in the path
		FileInputStream serviceAccount = new FileInputStream(filePath);

		@SuppressWarnings("deprecation")
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(ReadlogAdministratorApplication.class, args);
	}

}
