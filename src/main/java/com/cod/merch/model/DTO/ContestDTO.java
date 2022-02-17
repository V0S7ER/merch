package com.cod.merch.model.DTO;

import com.cod.merch.model.Contest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContestDTO {
    private Long id;
    private String name;
    private Long cost;
    List<UserDTO> winners;

    public ContestDTO(Contest contest) {
        id = contest.getId();
        name = contest.getName();
        cost = contest.getCost();
        winners = new ArrayList<>();
        for(var winner : contest.getWinners()) {
            winners.add(new UserDTO(winner));
        }
    }
}
