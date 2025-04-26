package az.developia.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MyException.class)
	public ResponseEntity<String> hanldeMyException(MyException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		StringBuilder erBuilder = new StringBuilder("ошибка валидации: ");
		ex.getBindingResult().getAllErrors().forEach(error -> {
			erBuilder.append(error.getDefaultMessage()).append("; ");
		});
		return ResponseEntity.badRequest().body(erBuilder.toString());
	}
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> hanldeException(Exception ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("eror");
//	}
	
}
