package pl.meeting.meetingapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.meeting.meetingapp.models.CategoryModelApi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
public class Profile {

    @Id
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
