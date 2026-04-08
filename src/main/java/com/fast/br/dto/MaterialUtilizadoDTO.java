package com.fast.br.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialUtilizadoDTO {
    private String nomeMaterial;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;
}
