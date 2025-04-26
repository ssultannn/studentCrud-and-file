package az.developia.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.school.entity.CourseEntity;
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
	
}
