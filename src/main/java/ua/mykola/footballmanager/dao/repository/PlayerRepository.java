package ua.mykola.footballmanager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.mykola.footballmanager.dao.entities.Player;
import ua.mykola.footballmanager.dao.entities.Team;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Player p SET p.name = ?1, p.lastName = ?2, p.age = ?3, p.careerStartDate = ?4, p.team = ?5 WHERE p.id = ?6")
    void update(String name, String lastName, int age, LocalDate careerStartDate, Team team, int id);

    @Transactional
    @Modifying
    @Query("UPDATE Player p SET p.team = ?1 WHERE p.id = ?2")
    void changeTeam(Team team, int id);
}
