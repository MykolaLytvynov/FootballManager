package ua.mykola.footballmanager.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;

    @NonNull
    private String city;

    @NonNull
    private String country;

    @NonNull
    private float account;

    @NonNull
    @Column(name = "commission_transfer")
    private float commissionTransfer;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Player> players;

}