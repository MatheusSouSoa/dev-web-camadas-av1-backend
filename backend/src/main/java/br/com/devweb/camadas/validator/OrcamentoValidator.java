package br.com.devweb.camadas.validator;

public class OrcamentoValidator {
  
  public static boolean validar(String nome_empresa, String descricao, Double valor, String status) {
    if(nome_empresa != "" && descricao != "" && nome_empresa != null && descricao != null && valor != null && status != "" && status != null){
      return true;
    }
    return false;
  }
}
