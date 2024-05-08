package br.com.devweb.camadas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrcamentoRequest {
  public String nome_empresa;
  public String descricao;
  public Double valor;
  public String status;
}
