package az.developia.school.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import az.developia.school.entity.CourseEntity;
import az.developia.school.entity.Student;
import az.developia.school.exception.MyException;
import az.developia.school.response.StudentResponse;
import az.developia.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Operation(summary = "получить всех студентов")
	@GetMapping(value = "/get-all")
	public List<StudentResponse> getAllStudent() {
		return studentService.getAllStudent();

	}

	@GetMapping("/get-all-pagination")
	public Page<Student> getAllStudentPaginated(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return studentService.getAllStudentPaginated(PageRequest.of(page, size));
	}

	@GetMapping("/{id}")
	@Operation(summary = "получить по id")
	public Optional<Student> findById(@PathVariable Long id) {
		return Optional.ofNullable(studentService.getStudentById(id)
				.orElseThrow(()->new MyException("Студент с id "  + id+ " не найден")));
	

	}

	@PostMapping("add")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> createStudent(@Valid @RequestBody Student student) {
		studentService.saveStudent(student);
		return ResponseEntity.ok("Студент сохранён");
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}

	@PutMapping("update/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}

	@GetMapping("/search")
	public List<Student> searchStudents(@RequestParam String q) {
		return studentService.searchStudent(q);
	}

	@GetMapping("/{id}/course")
	public Optional<CourseEntity> getStudentCourse(@PathVariable Long id) {
		return studentService.getStudentCourse(id);
	}

}
