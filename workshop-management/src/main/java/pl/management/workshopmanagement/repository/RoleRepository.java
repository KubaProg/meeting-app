package pl.management.workshopmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.management.workshopmanagement.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    Optional<Role> findRoleByRoleName(String roleName);

}
