package com.quicktutorialz.learnmicroservices.ToDoMicroservice;

import com.quicktutorialz.learnmicroservices.ToDoMicroservice.daos.ToDoDao;
import com.quicktutorialz.learnmicroservices.ToDoMicroservice.daos.UserDao;
import com.quicktutorialz.learnmicroservices.ToDoMicroservice.entities.ToDo;
import com.quicktutorialz.learnmicroservices.ToDoMicroservice.entities.User;
import com.quicktutorialz.learnmicroservices.ToDoMicroservice.utilities.EncryptionUtils;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@Log
@SpringBootApplication
public class ToDoMicroserviceApplication implements CommandLineRunner {

	@Autowired
	UserDao userDao;  //UserDaoImpl   UserDao userDao = new UserDaoImpl();

	@Autowired
	ToDoDao toDoDao;

	@Autowired
	EncryptionUtils encryptionUtils;




	//private static final Logger log = LoggerFactory.getLogger(ToDoMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ToDoMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception{
		//...
		log.info("lets fill H2 in-memory database");

		//everything here is implemented before our Microservice will be available for HTTP Requests

		String encryptedPwd;
		encryptedPwd = encryptionUtils.encrypt("Hello");
		userDao.save(new User("alex@quicktutorialz.com", "Alessandro Argentieri", encryptedPwd));

		encryptedPwd = encryptionUtils.encrypt("MyPwd");
		userDao.save(new User("franz@quicktutorialz.com", "Franz Leroy", encryptedPwd));

		encryptedPwd = encryptionUtils.encrypt("Belle");
		userDao.save(new User("annabelle@quicktutorialz.com", "Annabelle Sorah", encryptedPwd));


		toDoDao.save( new ToDo(1, "Learn Microservices", new Date(), "high", "alex@quicktutorialz.com"));
		toDoDao.save( new ToDo(null, "Learn Spring boot", null, "low", "alex@quicktutorialz.com"));

		toDoDao.save( new ToDo(3, "Feed Animals", new Date(), "high", "franz@quicktutorialz.com"));
		toDoDao.save( new ToDo(null, "Go to take Jim", null, "low", "franz@quicktutorialz.com"));

		toDoDao.save( new ToDo(5, "By a new Car", new Date(), "high", "annabelle@quicktutorialz.com"));
		toDoDao.save( new ToDo(null, "Go to the Gim", null, "low", "annabelle@quicktutorialz.com"));




		log.info("we've finished to fill our database");

	}
}
