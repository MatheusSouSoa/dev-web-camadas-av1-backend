package br.com.devweb.camadas.validator;

public class ProjetoValidator {
  
  public static boolean isValid(String nome, String descricao, String status) {
    if(nome != "" && descricao != "" && nome != null && descricao != null && status != null && status != ""){
      return true;
    }
    return false;
  }
}
