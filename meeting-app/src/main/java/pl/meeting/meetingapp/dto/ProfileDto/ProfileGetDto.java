package pl.meeting.meetingapp.dto.ProfileDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.meeting.meetingapp.models.CategoryModelApi;

import javax.validation.Valid;
import java.util.List;

public class ProfileGetDto {

    private Integer id;
    private Integer userId;
    private String sex;
    private List<CategoryModelApi> interests;
    private String job;
    private String school;
    private String description;
    private String photo;
    private String city;
}
