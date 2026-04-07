package com.fast.br.jwt;

import com.fast.br.dto.TecnicoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private TecnicoDTO tecnico;
    
    public TokenResponse(String token) {
        this.token = token;
    }
}