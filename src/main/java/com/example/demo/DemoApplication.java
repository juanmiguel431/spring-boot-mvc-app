package com.example.demo;

import com.example.demo.DAO.InstructorDetailRepository;
import com.example.demo.DAO.InstructorRepository;
import com.example.demo.models.Instructor;
import com.example.demo.models.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorRepository instructorRepository, InstructorDetailRepository instructorDetailRepository) {
		return runner -> {
			System.out.println("Hello world");
//			createInstructor(instructorRepository);

			var instructor = instructorRepository.getById(1);
			System.out.println(instructor);
		};
	}

	private static void createInstructor(InstructorRepository instructorRepository) {
		var instructor = new Instructor("Juan Miguel", "Paulino Carpio", "juanmiguel431@gmail.com");
		var instructorDetails = new InstructorDetail("https://www.youtube.com/@juanmiguel431", "Software Engineer");

		instructor.setInstructorDetail(instructorDetails);

		instructorRepository.add(instructor);
	}
}
