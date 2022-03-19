package ua.mykola.footballmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import ua.mykola.footballmanager.dao.entities.Team;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TeamDto {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "City should not be empty")
    @Size(min = 2, max = 30, message = "City should be between 2 and 30 characters")
    private String city;
    @NotEmpty(message = "Country should not be empty")
    @Size(min = 2, max = 30, message = "Country should be between 2 and 30 characters")
    private String country;
    @Range(min=0, message = "Account should be greater than 0")
    private float account;
    @Range(min=0, max=10, message = "Commission should be between 0 and 10")
    private float commissionTransfer;
    private List<PlayerDto> players;

    public static TeamDto fromTeam(Team team) {
        TeamDto result = new TeamDto(team.getId(),
                team.getName(),
                team.getCity(),
                team.getCountry(),
                team.getAccount(),
                team.getCommissionTransfer(),
                null);
        result.setPlayers(team.getPlayers().stream().map(p -> PlayerDto.fromPlayer(p)).collect(Collectors.toList()));
        return result;
    }

    public static Team fromTeamDto(TeamDto teamDto) {
        Team team = new Team(teamDto.getName(),
                teamDto.getCity(),
                teamDto.getCountry(),
                teamDto.getAccount(),
                teamDto.getCommissionTransfer());
        team.setId(teamDto.getId());
        return team;
    }
}
