package com.joshuaphilips.readlogadministrator.models;

import java.util.List;

import com.google.cloud.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private String title;
	private List<String> author;
	private int firstPublishYear;
	private String coverImage;
	private String summary;
	private List<String> subject;
	private List<String> place;
	private List<String> time;
	private List<String> person;
	private List<String> publisher;
	private List<Integer> publishYear;
	private List<Link> links;
	private String review;
	private Timestamp dateAdded;
	private String id;

}
