package ua.mykola.footballmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import ua.mykola.footballmanager.dao.entities.Player;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlayerDto {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastName;
    @Range(min=14, message = "Age should be greater than 14")
    private int age;
    @NotNull(message = "Date should not be empty")
    private LocalDate careerStartDate;
    @NotNull(message = "Team should not be empty")
    private Integer teamId;

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