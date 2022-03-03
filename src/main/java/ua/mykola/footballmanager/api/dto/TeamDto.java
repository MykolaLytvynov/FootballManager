package ua.mykola.footballmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.mykola.footballmanager.dao.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TeamDto {
    private int id;
    private String name;
    private String city;
    private String country;
    private float account;
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
