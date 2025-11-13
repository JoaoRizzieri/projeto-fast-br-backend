package com.fast.br.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialUtilizadoDTO {
    private String nomeMaterial;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}
