package com.fast.br.jwt;

import com.fast.br.dto.TecnicoDTO;

public class TokenResponse {
    private String token;
    private TecnicoDTO tecnico;
    
    public TokenResponse() {}

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TecnicoDTO getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoDTO tecnico) {
        this.tecnico = tecnico;
    }
}
