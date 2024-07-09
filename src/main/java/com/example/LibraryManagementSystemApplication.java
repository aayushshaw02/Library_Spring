package com.example;

import com.example.controllers.LibrarianController;
import com.example.controllers.UserController;
import com.example.entities.Book;
import com.example.entities.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LibraryManagementSystemApplication.class, args);


		LibrarianController controller = context.getBean(LibrarianController.class);
		UserController userController = context.getBean(UserController.class);

		Users users1 = new Users();
		users1.setName("John Doe");
		users1.setPassword("password123");

		Users users2 = new Users();
		users2.setName("Jane Doe");
		users2.setPassword("password456");

		Book book1 = new Book();
		book1.setBookName("Book 1");
		book1.setAuthor("Author 1");
		book1.setPublication("Publication 1");
		book1.setAvailability(true);
		book1.setCategory("Category 1");

		Book book2 = new Book();
		book2.setBookName("Book 2");
		book2.setAuthor("Author 2");
		book2.setPublication("Publication 2");
		book2.setAvailability(true);
		book2.setCategory("Category 2");

		Book book3 = new Book();
		book3.setBookName("Book 3");
		book3.setAuthor("Author 3");
		book3.setPublication("Publication 3");
		book3.setAvailability(true);
		book3.setCategory("Category 3");

		Book book4 = new Book();
		book4.setBookName("Book 4");
		book4.setAuthor("Author 4");
		book4.setPublication("Publication 4");
		book4.setAvailability(true);
		book4.setCategory("Category 4");

		Book book5 = new Book();
		book5.setBookName("Book 5");
		book5.setAuthor("Author 5");
		book5.setPublication("Publication 5");
		book5.setAvailability(true);
		book5.setCategory("Category 5");

		controller.addBook(book1);
		controller.addBook(book2);
		controller.addBook(book3);
		controller.addBook(book4);
		controller.addBook(book5);

		controller.addUser(users1);
		controller.addUser(users2);

		ResponseEntity<Page<Book>> booksResponse = controller.displayBooks(0, 10);
		Page<Book> booksPage = booksResponse.getBody();
		if (booksPage != null) {
			System.out.println("Displayed Books: " + booksPage.getContent());
		} else {
			System.out.println("Displayed Books: No content available");
		}

		// Display all users without pagination (assuming this method doesn't support pagination)
		ResponseEntity<Page<Users>> usersResponse = controller.displayAllUsers(0,10);
		Page<Users> usersList = usersResponse.getBody();
		if (usersList != null) {
			System.out.println("Users total: " + usersList.getContent());
		} else {
			System.out.println("Users total: No content available");
		}

		// Get users by name
		ResponseEntity<Page<Users>> usersByNameResponse = controller.getUsersByName("John Doe",0,10);
		Page<Users> usersByNameList = usersByNameResponse.getBody();
		if (usersByNameList != null) {
			System.out.println("User by Name: " + usersByNameList.getContent());
		} else {
			System.out.println("User by Name: No content available");
		}

		userController.borrowBook(1L, 2L);
	}
}