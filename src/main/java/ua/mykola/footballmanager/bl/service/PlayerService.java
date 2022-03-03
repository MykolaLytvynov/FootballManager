package ua.mykola.footballmanager.bl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykola.footballmanager.api.dto.PlayerDto;
import ua.mykola.footballmanager.api.dto.TeamDto;
import ua.mykola.footballmanager.dao.entities.Player;
import ua.mykola.footballmanager.dao.entities.Team;
import ua.mykola.footballmanager.exception.NotFoundException;
import ua.mykola.footballmanager.exception.TransferException;
import ua.mykola.footballmanager.dao.repository.PlayerRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements CrudOperations<PlayerDto, Integer> {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamService teamService;

    @Override
    public void save(PlayerDto playerDto) {
        Player newPlayer = PlayerDto.fromPlayerDto(playerDto);
        Team teamNewPlayer = TeamDto.fromTeamDto(teamService.findById(playerDto.getTeamId()));
        newPlayer.setTeam(teamNewPlayer);
        playerRepository.save(newPlayer);
    }

    @Override
    public PlayerDto findById(Integer id) {
        existsById(id);
        Player result = playerRepository.findById(id).get();
        return PlayerDto.fromPlayer(result);
    }

    @Override
    public void update(PlayerDto playerDto) {
        Player player = PlayerDto.fromPlayerDto(playerDto);
        Team teamPlayer = TeamDto.fromTeamDto(teamService.findById(playerDto.getTeamId()));
        player.setTeam(teamPlayer);
        playerRepository.update(player.getName(), player.getLastName(),
                player.getAge(), player.getCareerStartDate(),
                player.getTeam(), player.getId());
    }

    @Override
    public void deleteById(Integer id) {
        existsById(id);
        playerRepository.deleteById(id);
    }

    public List<PlayerDto> getAll() {
        return playerRepository.findAll()
                .stream()
                .map(player -> PlayerDto.fromPlayer(player))
                .collect(Collectors.toList());
    }

    public void playerTransferOperation(int playerId, int teamId) {
        existsById(playerId);
        Player player = playerRepository.findById(playerId).get();
        float transferCostPlayer = getTransferCostPlayer(player);

        TeamDto oldTeamDto = teamService.findById(player.getTeam().getId());
        float commission = transferCostPlayer * oldTeamDto.getCommissionTransfer() / 100;
        float fullCostTransfer = transferCostPlayer + commission;

        TeamDto newTeamDto = teamService.findById(teamId);

        if (oldTeamDto.getId() == newTeamDto.getId()) {
            TransferException transferException = new TransferException("Player is already in this team");
            throw transferException;
        }

        if (fullCostTransfer > newTeamDto.getAccount()) {
            TransferException transferException = new TransferException("Not enough money. Full cost = " + fullCostTransfer + ", " +
                    "Your Account = " + newTeamDto.getAccount());
            throw transferException;
        }

        playerRepository.changeTeam(TeamDto.fromTeamDto(newTeamDto), player.getId());
        teamService.setAccount(oldTeamDto.getAccount() + fullCostTransfer, oldTeamDto.getId());
        teamService.setAccount(newTeamDto.getAccount() - fullCostTransfer, newTeamDto.getId());
    }

    private float getTransferCostPlayer(Player player) {
        Long countMonthExperience = ChronoUnit.MONTHS.between(player.getCareerStartDate(), LocalDate.now());
        float result = (float) countMonthExperience * 100000 / player.getAge();
        return result;
    }

    private void existsById(int id) {
        NotFoundException notFoundException = new NotFoundException("Player not found by id = " + id);
        if (!playerRepository.existsById(id)) throw notFoundException;
    }
}
