package pl.meeting.meetingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.meeting.meetingapp.ApiRoutes;
import pl.meeting.meetingapp.MeetingAppApplication;
import pl.meeting.meetingapp.entity.Event;
import pl.meeting.meetingapp.entity.Location;
import pl.meeting.meetingapp.entity.LocationRepository;
import pl.meeting.meetingapp.models.EventModelApi;
import pl.meeting.meetingapp.models.EventPostModelApi;
import pl.meeting.meetingapp.models.LocationModelApi;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = MeetingAppApplication.class)
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    ObjectMapper objectMapper;
    EventPostModelApi eventPostModelApi;

    @BeforeEach
    void init(){
        Location location = new Location();
        location.setLatitude(22.1);
        location.setLongitude(33.2);
        location.setName("Alibaba");
        location.setAddress("Powstancow street");
        locationRepository.save(location);

        eventPostModelApi = new EventPostModelApi()
                .name("Test Event")
                .description("This is a test event")
                .date("2017-01-13")
                .startTime("15:00:00")
                .endTime("17:30:00")
                .locationId(location.getId());
    }

    @Test
    @Transactional
    @WithMockUser
    void createEvent_EventDataToCreateEventGiven_ShouldCreateNewEvent() throws Exception {


        ResultActions postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiRoutes.Base.PATH+ApiRoutes.Event.PATH)
                        .content(objectMapper.writeValueAsString(eventPostModelApi))
                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.containsString("/api/v1/event/")));

        String locationHeader = postResult.andReturn().getResponse().getHeader("Location");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(locationHeader))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(eventPostModelApi.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(eventPostModelApi.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(eventPostModelApi.getDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startTime", Matchers.equalTo(eventPostModelApi.getStartTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endTime", Matchers.equalTo(eventPostModelApi.getEndTime())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.latitude", Matchers.equalTo((22.1))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.longitude", Matchers.equalTo(33.2)));

    }

}