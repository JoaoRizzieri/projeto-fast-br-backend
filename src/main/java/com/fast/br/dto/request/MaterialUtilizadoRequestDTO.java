package com.fast.br.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialUtilizadoRequestDTO {
    private String nomeMaterial;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}