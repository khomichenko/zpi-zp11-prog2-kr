package application.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "clauses")
@EqualsAndHashCode() @NoArgsConstructor @RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Clause {
    @Id @NonNull
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Basic @Column @NonNull private String description;

}
