package com.fast.br.dto.request;

import java.util.List;

public class CustosTecnicoRequestDTO {
    private String nomeAjudante;
    private CustosDeslocamentoDTO custosDeslocamento;
    private CustosHoraTrabalhadaDTO custosHoraTrabalhada;
    private CustosKmDTO custosKm;
    private List<com.fast.br.dto.MaterialUtilizadoDTO> despesasMateriais;
    private Double valorTotalGeral;

    public String getNomeAjudante() { return nomeAjudante; }
    public void setNomeAjudante(String nomeAjudante) { this.nomeAjudante = nomeAjudante; }

    public CustosDeslocamentoDTO getCustosDeslocamento() { return custosDeslocamento; }
    public void setCustosDeslocamento(CustosDeslocamentoDTO custosDeslocamento) { this.custosDeslocamento = custosDeslocamento; }

    public CustosHoraTrabalhadaDTO getCustosHoraTrabalhada() { return custosHoraTrabalhada; }
    public void setCustosHoraTrabalhada(CustosHoraTrabalhadaDTO custosHoraTrabalhada) { this.custosHoraTrabalhada = custosHoraTrabalhada; }

    public CustosKmDTO getCustosKm() { return custosKm; }
    public void setCustosKm(CustosKmDTO custosKm) { this.custosKm = custosKm; }

    public List<com.fast.br.dto.MaterialUtilizadoDTO> getDespesasMateriais() { return despesasMateriais; }
    public void setDespesasMateriais(List<com.fast.br.dto.MaterialUtilizadoDTO> despesasMateriais) { this.despesasMateriais = despesasMateriais; }

    public Double getValorTotalGeral() { return valorTotalGeral; }
    public void setValorTotalGeral(Double valorTotalGeral) { this.valorTotalGeral = valorTotalGeral; }

    public static class CustosDeslocamentoDTO {
        private String chegadaEmpresa;
        private String saidaEmpresa;
        private String chegadaCliente;
        private String saidaCliente;
        private String totalHoras;
        private Double totalRs;

        public String getChegadaEmpresa() { return chegadaEmpresa; }
        public void setChegadaEmpresa(String chegadaEmpresa) { this.chegadaEmpresa = chegadaEmpresa; }

        public String getSaidaEmpresa() { return saidaEmpresa; }
        public void setSaidaEmpresa(String saidaEmpresa) { this.saidaEmpresa = saidaEmpresa; }

        public String getChegadaCliente() { return chegadaCliente; }
        public void setChegadaCliente(String chegadaCliente) { this.chegadaCliente = chegadaCliente; }

        public String getSaidaCliente() { return saidaCliente; }
        public void setSaidaCliente(String saidaCliente) { this.saidaCliente = saidaCliente; }

        public String getTotalHoras() { return totalHoras; }
        public void setTotalHoras(String totalHoras) { this.totalHoras = totalHoras; }

        public Double getTotalRs() { return totalRs; }
        public void setTotalRs(Double totalRs) { this.totalRs = totalRs; }
    }

    public static class CustosHoraTrabalhadaDTO {
        private String chegadaEmpresa;
        private String saidaEmpresa;
        private String totalHoras;
        private Double totalRs;

        public String getChegadaEmpresa() { return chegadaEmpresa; }
        public void setChegadaEmpresa(String chegadaEmpresa) { this.chegadaEmpresa = chegadaEmpresa; }

        public String getSaidaEmpresa() { return saidaEmpresa; }
        public void setSaidaEmpresa(String saidaEmpresa) { this.saidaEmpresa = saidaEmpresa; }

        public String getTotalHoras() { return totalHoras; }
        public void setTotalHoras(String totalHoras) { this.totalHoras = totalHoras; }

        public Double getTotalRs() { return totalRs; }
        public void setTotalRs(Double totalRs) { this.totalRs = totalRs; }
    }

    public static class CustosKmDTO {
        private String km;
        private Double rsPorKm;
        private Double totalRs;

        public String getKm() { return km; }
        public void setKm(String km) { this.km = km; }

        public Double getRsPorKm() { return rsPorKm; }
        public void setRsPorKm(Double rsPorKm) { this.rsPorKm = rsPorKm; }

        public Double getTotalRs() { return totalRs; }
        public void setTotalRs(Double totalRs) { this.totalRs = totalRs; }
    }
}
