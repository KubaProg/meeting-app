package pl.meeting.meetingapp.Exception.User;

import org.springframework.http.HttpStatus;
import pl.meeting.meetingapp.Exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("User with %d id does not exist", id));}

    public UserNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, String.format("User with username: %s id does not exist", username));}
}
