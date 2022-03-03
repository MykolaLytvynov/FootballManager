package ua.mykola.footballmanager.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.mykola.footballmanager.dao.entities.Team;

import javax.transaction.Transactional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Team t SET t.name = ?1, t.city = ?2, t.country = ?3, t.commissionTransfer = ?4 WHERE t.id = ?5")
    void update(String name, String city, String country, float commissionTransfer, int id);

    @Transactional
    @Modifying
    @Query("UPDATE Team t SET t.account = ?1 WHERE t.id = ?2")
    void setAccount(float account, int id);
}
