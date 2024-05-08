package br.com.devweb.camadas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orcamento {
  
  private Long codigo;
  private String nome_empresa;
  private String descricao;
  private Double valor;
  private String status_pagamento;

}
