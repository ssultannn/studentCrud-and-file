package az.developia.school.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.school.entity.CourseEntity;
import az.developia.school.exception.MyException;
import az.developia.school.service.CourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/get-all")
	public List<CourseEntity> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/{id}")
	public Optional<CourseEntity> getById(@PathVariable Long id) {
		return Optional.ofNullable(
				courseService.getById(id).orElseThrow(() -> new MyException("Курс с id " + id + "не найден ")));

	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public void addCourse(@Valid @RequestBody CourseEntity courseEntity) {
		courseService.addCourse(courseEntity);

	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void updateCourse(@PathVariable Long id, @RequestBody CourseEntity course) {
		courseService.updateCourse(id, course);
	}

	@DeleteMapping("delete/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void deleteById(@PathVariable Long id) {
		courseService.deleteCourse(id);
	}

}
