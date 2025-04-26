package az.developia.school.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import az.developia.school.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name,String surname);
	
	
	Page<Student> findAll(Pageable pageable);


}
