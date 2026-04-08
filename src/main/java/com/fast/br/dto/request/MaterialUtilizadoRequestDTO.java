package com.fast.br.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialUtilizadoRequestDTO {
    private String nomeMaterial;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;
}