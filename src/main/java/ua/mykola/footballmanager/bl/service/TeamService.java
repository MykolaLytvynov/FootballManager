package ua.mykola.footballmanager.bl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykola.footballmanager.api.dto.TeamDto;
import ua.mykola.footballmanager.dao.entities.Team;
import ua.mykola.footballmanager.exception.NotFoundException;
import ua.mykola.footballmanager.dao.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeamService implements CrudOperations<TeamDto, Integer> {
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public void save(TeamDto teamDto) {
        Team team = TeamDto.fromTeamDto(teamDto);
        teamRepository.save(team);
    }

    @Override
    public TeamDto findById(Integer id) {
        existsById(id);
        Team team = teamRepository.findById(id).get();
        return TeamDto.fromTeam(team);
    }

    @Override
    public void update(TeamDto teamDto) {
        teamRepository.update(teamDto.getName(),
                teamDto.getCity(),
                teamDto.getCountry(),
                teamDto.getCommissionTransfer(),
                teamDto.getId());
    }

    @Override
    public void deleteById(Integer id) {
        existsById(id);
        teamRepository.deleteById(id);
    }

    public List<TeamDto> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(team -> TeamDto.fromTeam(team))
                .collect(Collectors.toList());
    }

    public void setAccount(float account, int id) {
        existsById(id);
        teamRepository.setAccount(account, id);
    }

    private void existsById(int id) {
        NotFoundException notFoundException = new NotFoundException("Team not found by id = " + id);
        if (!teamRepository.existsById(id)) throw notFoundException;
    }
}
