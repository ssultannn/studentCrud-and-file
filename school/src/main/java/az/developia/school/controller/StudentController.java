package az.developia.school.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.school.entity.Student;
import az.developia.school.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("get-all")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@GetMapping("/{id}")
	public Optional<Student> findById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping("add")
	public void createStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}

	@PutMapping("update/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}

	@GetMapping("/search")
	public List<Student> searchStudents(@RequestParam String q) {
		return studentService.searchStudent(q);
	}
}
