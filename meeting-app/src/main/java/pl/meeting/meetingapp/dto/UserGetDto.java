package pl.meeting.meetingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private List<Long> roleIds;
    private String jwtToken;

}
