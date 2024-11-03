package com.example.demo;

import com.example.demo.DAO.CourseRepository;
import com.example.demo.DAO.InstructorRepository;
import com.example.demo.DAO.StudentRepository;
import com.example.demo.models.Course;
import com.example.demo.models.Instructor;
import com.example.demo.models.InstructorDetail;
import com.example.demo.models.Student;
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
	public CommandLineRunner commandLineRunner(InstructorRepository instructorRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
		return runner -> {
			System.out.println("Hello world");
//			createInstructor(instructorRepository);

			var edward = studentRepository.findById(5);

			try	{
//				var studentByEmail = studentRepository.findByEmail("juanmiguel431@gmail.com"); // Exception - Too many rows
				var studentByEmail = studentRepository.findByEmail("eduar_paulino@gmail.com"); // Success
			} catch (Exception e) {
				System.out.println("More than one student found");
			}

			// Create Student
			var student = new Student("Julian", "Paulino", "julian_paulino@gmail.com");
			studentRepository.add(student);

			// Create Course
//			var course = new Course("Piano");
//			course.addStudent(student);
//			courseRepository.add(course);

//			var courseWithStudents = courseRepository.findCourseAndStudentsById(9);
//			courseRepository.removeStudentFromCourse(9, 9);

			var courseWithStudents = courseRepository.findCourseAndStudentsById(9);

			var instructorCourses = courseRepository.findCoursesByInstructorId(2);
			var instructor2 = instructorRepository.findByIdWithCourses(2);

			this.getCoursesWithTransaction(instructorRepository);
		};
	}


	protected void getCoursesWithTransaction(InstructorRepository instructorRepository) {
		var instructor1 = instructorRepository.findById(2);
		var courses = instructor1.getCourses();
		System.out.println(instructor1);
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
