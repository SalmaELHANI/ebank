package com.ebank.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String login;
    private String oldPassword;
    private String newPassword;
}
