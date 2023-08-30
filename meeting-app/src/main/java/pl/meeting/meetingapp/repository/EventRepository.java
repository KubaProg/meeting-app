package pl.meeting.meetingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.meeting.meetingapp.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>
{

}
