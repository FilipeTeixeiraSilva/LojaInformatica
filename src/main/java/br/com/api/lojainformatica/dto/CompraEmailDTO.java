package br.com.api.lojainformatica.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompraEmailDTO {
    private Long idCompra;
    private String nomeCliente;
    private String email;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoTotal;
}
