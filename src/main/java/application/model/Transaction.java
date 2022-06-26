package application.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity @Data
@Table(name = "transactions")
@EqualsAndHashCode() @NoArgsConstructor @RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Transaction {

    @Id
    @ToString.Include @Column(nullable=false, updatable=false) @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp @Column(nullable = false, updatable = false)
    private Date timestamp;

    @OneToOne @NonNull private Account from;
    @OneToOne @NonNull private Account to;

    @OneToOne @NonNull private Clause clause;

    @Basic @NonNull @Column private Double amount;
}
