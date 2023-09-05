package pl.meeting.meetingapp.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {

    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private List<Long> roleIds;

}