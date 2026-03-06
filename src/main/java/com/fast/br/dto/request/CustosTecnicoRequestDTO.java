package com.fast.br.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class CustosTecnicoRequestDTO {
    private String nomeAjudante;
    private CustosDeslocamentoDTO custosDeslocamento;
    private CustosHoraTrabalhadaDTO custosHoraTrabalhada;
    private CustosKmDTO custosKm;
    private List<com.fast.br.dto.MaterialUtilizadoDTO> despesasMateriais;
    private Double valorTotalGeral;
    
    @Data
    public static class CustosDeslocamentoDTO {
        private String chegadaEmpresa;
        private String saidaEmpresa;
        private String chegadaCliente;
        private String saidaCliente;
        private String totalHoras;
        private Double totalRs;
    }
    
    @Data
    public static class CustosHoraTrabalhadaDTO {
        private String chegadaEmpresa;
        private String saidaEmpresa;
        private String totalHoras;
        private Double totalRs;
    }
    
    @Data
    public static class CustosKmDTO {
        private String km;
        private Double rsPorKm;
        private Double totalRs;
    }
}
