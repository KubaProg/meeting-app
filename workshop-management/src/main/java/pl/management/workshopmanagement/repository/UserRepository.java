package pl.management.workshopmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.management.workshopmanagement.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


}
