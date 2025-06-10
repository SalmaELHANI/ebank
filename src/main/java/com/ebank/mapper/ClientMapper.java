package com.ebank.mapper;

import com.ebank.dto.ClientDTO;
import com.ebank.dto.ClientRequestDTO;
import com.ebank.entity.Client;
import com.ebank.entity.User;

public class ClientMapper {
    public static ClientDTO toClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getUser().getNom());
        dto.setPrenom(client.getUser().getPrenom());
        dto.setEmail(client.getUser().getEmail());
        dto.setAdresse(client.getAdresse());
        dto.setTelephone(client.getTelephone());
        return dto;
    }
    public static Client fromClientRequestDTO(ClientRequestDTO dto, User user) {
        Client client = new Client();
        client.setAdresse(dto.getAdresse());
        client.setTelephone(dto.getTelephone());
        client.setUser(user);
        return client;
    }
}