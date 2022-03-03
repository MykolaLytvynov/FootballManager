package ua.mykola.footballmanager.dao.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    @NonNull
    private int age;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate careerStartDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Team team;
}
