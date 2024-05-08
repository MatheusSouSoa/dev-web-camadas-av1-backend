package br.com.devweb.camadas.models;

import lombok.Data;

@Data
public class Projeto {
  private Long codigo;
  private String nome;
  private String descricao;
  private String data_inicio;
  private String data_termino;
  private String status;

  public Projeto(long id, String nome, String descricao, String dataInicio, String dataFim, String statusProjeto) {
    this.codigo = id;
    this.nome = nome;
    this.descricao = descricao;
    this.data_inicio = dataInicio;
    this.data_termino = dataFim;
    this.status = statusProjeto;
  }

}
