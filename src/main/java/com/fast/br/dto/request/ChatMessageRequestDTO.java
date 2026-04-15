package com.fast.br.dto.request;

public class ChatMessageRequestDTO {
    private String mensagem;
    private String remetente;

    public ChatMessageRequestDTO() {}

    public ChatMessageRequestDTO(String mensagem, String remetente) {
        this.mensagem = mensagem;
        this.remetente = remetente;
    }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getRemetente() { return remetente; }
    public void setRemetente(String remetente) { this.remetente = remetente; }
}
