package application.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Data
@Table(name = "accounts")
@EqualsAndHashCode() @NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Account {

    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne private User user;

    @ManyToMany()
    @JoinTable(
            name = "accounts_clauses",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "clause_id") }
    )
    private List<Clause> clauses;

    @Basic @Column @NotNull private Double amount;
}
