package ua.mykola.footballmanager.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ua.mykola.footballmanager.api.dto.TeamDto;
import ua.mykola.footballmanager.api.dto.TransferDto;
import ua.mykola.footballmanager.bl.service.PlayerService;
import ua.mykola.footballmanager.bl.service.TeamService;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    @PostMapping()
    public ResponseEntity<Object> addTeam(@Valid @RequestBody TeamDto teamDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("errors", errors);
            return ResponseEntity.badRequest().body(body);
        }
        teamService.save(teamDto);
        return ResponseEntity.ok("Team added successfully");
    }

    @GetMapping()
    public ResponseEntity<List<TeamDto>> getAll() {
        List<TeamDto> result = teamService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getById(@PathVariable("id") int id) {
        TeamDto result = teamService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody TeamDto teamDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("errors", errors);
            return ResponseEntity.badRequest().body(body);
        }
        teamService.update(teamDto);
        return ResponseEntity.ok("Update was successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        if (!teamService.findById(id).getPlayers().isEmpty()) {
            return ResponseEntity.badRequest().body("This team has players. Delete is impossible");
        }
        teamService.deleteById(id);
        return ResponseEntity.ok("Delete was successful");
    }

    @PatchMapping("/transfer")
    public ResponseEntity<Object> playerTransferOperation(@RequestBody TransferDto transferDto) {
        playerService.playerTransferOperation(transferDto.getPlayerId(), transferDto.getTeamId());
        return ResponseEntity.ok("Transfer was successful");
    }
}
