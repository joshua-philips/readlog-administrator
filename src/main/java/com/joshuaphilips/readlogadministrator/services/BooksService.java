package com.joshuaphilips.readlogadministrator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.joshuaphilips.readlogadministrator.models.Book;

@Service
public class BooksService {
	private Firestore firestore = FirestoreClient.getFirestore();

	public List<Book> getUsersBooks(String uid) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> future = firestore.collection("user").document(uid).collection("books").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<Book> books = new ArrayList<>();
		for (DocumentSnapshot document : documents) {
			books.add(document.toObject(Book.class));
		}

		return books;
	}

	public List<Book> getReadingList(String uid) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> future = firestore.collection("user").document(uid).collection("readingList").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<Book> books = new ArrayList<>();
		for (DocumentSnapshot document : documents) {
			books.add(document.toObject(Book.class));
		}

		return books;

	}
}
