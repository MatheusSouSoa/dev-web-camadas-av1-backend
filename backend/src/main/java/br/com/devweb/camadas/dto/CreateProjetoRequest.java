package br.com.devweb.camadas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProjetoRequest {
  
  public String nome;
  public String descricao;
  public String data_inicio;
  public String data_termino;
  public String status;
}
