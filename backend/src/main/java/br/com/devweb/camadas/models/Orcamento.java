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

  public Orcamento(long id, String nome_empresa, String descricao, Double valor, String statusPagamento) {
    this.codigo = id;
    this.nome_empresa = nome_empresa;
    this.descricao = descricao;
    this.valor = valor;
    this.status_pagamento = statusPagamento;
  }

}
