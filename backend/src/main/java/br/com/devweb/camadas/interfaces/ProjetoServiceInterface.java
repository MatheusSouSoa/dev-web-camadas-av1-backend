package br.com.devweb.camadas.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.devweb.camadas.dto.CreateProjetoRequest;
import br.com.devweb.camadas.enums.StatusProjeto;
import br.com.devweb.camadas.models.Projeto;

public interface ProjetoServiceInterface {
  ResponseEntity<String> adicionarProjeto(CreateProjetoRequest projeto);
  ResponseEntity<String> removerProjeto(Long codigo);
  ResponseEntity<Projeto> buscarProjetoPorId(Long codigo);
  ResponseEntity<String> editarProjeto(Long codigo, Projeto novoProjeto);
  ResponseEntity<String> atualizarStatusProjeto(Long codigo, StatusProjeto novoStatus);
}
