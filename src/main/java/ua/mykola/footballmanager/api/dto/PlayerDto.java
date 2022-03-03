package ua.mykola.footballmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.mykola.footballmanager.dao.entities.Player;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlayerDto {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private LocalDate careerStartDate;
    private int teamId;

    public static PlayerDto fromPlayer(Player player) {
        PlayerDto result = new PlayerDto(player.getId(),
                player.getName(),
                player.getLastName(),
                player.getAge(),
                player.getCareerStartDate(),
                player.getTeam().getId());
        return result;
    }

    public static Player fromPlayerDto(PlayerDto playerDto) {
        Player result = new Player(playerDto.getName(),
                playerDto.getLastName(),
                playerDto.getAge(),
                playerDto.getCareerStartDate());
        result.setId(playerDto.getId());
        return result;
    }
}