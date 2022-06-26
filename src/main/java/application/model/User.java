package application.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode() @NoArgsConstructor @RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Basic @NonNull private String userName;

    @Basic @NonNull private String fio;

    @ManyToMany()
    @JoinTable(
            name = "users_accounts",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "account_id") }
    )
    private List<Account> accounts;

}
