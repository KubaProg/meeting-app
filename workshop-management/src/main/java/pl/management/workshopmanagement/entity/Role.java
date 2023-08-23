package pl.management.workshopmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_ROLE")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

}
