package smm.topScoreRestful.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerRecordNotFoundException extends RuntimeException {

	public PlayerRecordNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
