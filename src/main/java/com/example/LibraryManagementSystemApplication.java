package com.example;

import com.example.controllers.LibrarianController;
import com.example.controllers.UserController;
import com.example.entities.Book;
import com.example.entities.Feedback;
import com.example.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryManagementSystemApplication{

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(LibrarianController librarianController, UserController userController){
		return runner -> {

           User aayush = new User();
		   aayush.setName("Aayush");
		   aayush.setPassword("password");

		   User purvesh = new User();
		   purvesh.setName("Purvesh");
		   purvesh.setPassword("password");

			Book java = new Book();
			java.setAuthor("Russel");
			java.setAvailability(true);
			java.setCategory("Programming");
			java.setPublication("Oracle");
			java.setBookName("Java");

			Book ui = new Book();
			java.setAuthor("David");
			java.setAvailability(true);
			java.setCategory("Designing");
			java.setPublication("Code");
			java.setBookName("UI/UX");

			Book devops = new Book();
			java.setAuthor("Sam");
			java.setAvailability(true);
			java.setCategory("Deployment");
			java.setPublication("GFG");
			java.setBookName("Devops");

			Book datascience = new Book();
			java.setAuthor("Martin");
			java.setAvailability(true);
			java.setCategory("ArtificialIntelligence");
			java.setPublication("CodeNinja");
			java.setBookName("DataScience");

			librarianController.addUser(aayush);
			librarianController.addUser(purvesh);

			librarianController.addBook(java);
			librarianController.addBook(ui);
			librarianController.addBook(datascience);
			librarianController.addBook(devops);

			librarianController.displayAllUsers();
			librarianController.displayBooks();
			librarianController.getAllFeedback();

			userController.borrowBook(aayush.getId(), java.getBookId());
			userController.borrowBook(aayush.getId(), devops.getBookId());

			userController.borrowBook(purvesh.getId(), ui.getBookId());
			userController.borrowBook(purvesh.getId(), datascience.getBookId());

			Feedback feedback = new Feedback();
			feedback.setUser(aayush);
			feedback.setMessage("GOOD");
			feedback.setBookId(java.getBookId().toString());
			feedback.setGeneratedAt(LocalDate.now().toString());

			userController.submitFeedback(feedback);

			librarianController.getAllFeedback();

			librarianController.removeUser(purvesh.getId());

			librarianController.displayAllUsers();
		};
	}
}
