package com.example.demo;

import com.example.demo.DAO.InstructorDetailRepository;
import com.example.demo.DAO.InstructorRepository;
import com.example.demo.models.Course;
import com.example.demo.models.Instructor;
import com.example.demo.models.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;

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

			var instructor = instructorRepository.getById(2);
			var courses = instructor.getCourses();
			System.out.println(instructor);
		};
	}

	private static void createInstructor(InstructorRepository instructorRepository) {
		var instructor = new Instructor("Marcos", "Antonio", "marcos_antonio@gmail.com");
		var instructorDetails = new InstructorDetail("https://www.youtube.com/@marcos-antonio", "Developer");

		instructor.setInstructorDetail(instructorDetails);

        var courses = new ArrayList<Course>();
		courses.add(new Course("Math"));
		courses.add(new Course("English"));
		courses.add(new Course("Spanish"));
		courses.add(new Course("History"));

		instructor.setCourses(courses);

		instructorRepository.add(instructor);
	}
}
