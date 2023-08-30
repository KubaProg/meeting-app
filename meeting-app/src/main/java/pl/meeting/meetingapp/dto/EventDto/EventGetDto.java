package pl.meeting.meetingapp.dto.EventDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.meeting.meetingapp.entity.Location;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventGetDto {

    private Integer id;
    private String name;
    private String description;
    private String date;
    private String startTime;
    private String endTime;
    private Location location;

}
