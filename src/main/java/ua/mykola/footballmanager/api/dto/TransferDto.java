package ua.mykola.footballmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferDto {
    private int playerId;
    private int teamId;
}
