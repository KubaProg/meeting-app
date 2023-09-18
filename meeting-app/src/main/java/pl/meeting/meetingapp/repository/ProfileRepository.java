package pl.meeting.meetingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.meeting.meetingapp.entity.Profile;

import java.util.Optional;

public interface ProfileRepository  extends JpaRepository<Profile,Long> {
    Optional<Profile> findProfileByUserId(Long userId);

    void deleteProfileByUserId(Long userId);
}
