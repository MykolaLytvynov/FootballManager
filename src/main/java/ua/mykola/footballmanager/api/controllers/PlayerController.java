package ua.mykola.footballmanager.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.mykola.footballmanager.api.dto.PlayerDto;
import ua.mykola.footballmanager.bl.service.PlayerService;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping()
    public ResponseEntity<Object> addPlayer(@Valid @RequestBody PlayerDto playerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("errors", errors);
            return ResponseEntity.badRequest().body(body);
        }
        playerService.save(playerDto);
        return ResponseEntity.ok("Player added successfully");
    }

    @GetMapping()
    public ResponseEntity<List<PlayerDto>> getAll() {
        List<PlayerDto> result = playerService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getById(@PathVariable("id") int id) {
        PlayerDto result = playerService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody PlayerDto playerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("errors", errors);
            return ResponseEntity.badRequest().body(body);
        }
        playerService.update(playerDto);
        return ResponseEntity.ok("Update was successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        playerService.deleteById(id);
        return ResponseEntity.ok("Delete was successful");
    }

}