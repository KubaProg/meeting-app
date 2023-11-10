package pl.meeting.meetingapp.Exception.User;

import org.springframework.http.HttpStatus;
import pl.meeting.meetingapp.Exception.BusinessException;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException(String username) {
        super(HttpStatus.BAD_REQUEST, String.format("User with: %s username does already exist", username));
    }
}
