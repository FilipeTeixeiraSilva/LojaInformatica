package br.com.api.lojainformatica.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompraDTO {

    private Long clienteId;
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal precoTotal;
}
