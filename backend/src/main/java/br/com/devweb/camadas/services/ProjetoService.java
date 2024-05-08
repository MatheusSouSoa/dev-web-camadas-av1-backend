package br.com.devweb.camadas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.devweb.camadas.dto.CreateProjetoRequest;
import br.com.devweb.camadas.enums.StatusProjeto;
import br.com.devweb.camadas.interfaces.ProjetoRepositoryInterface;
import br.com.devweb.camadas.interfaces.ProjetoServiceInterface;
import br.com.devweb.camadas.models.Projeto;
import br.com.devweb.camadas.validator.ProjetoValidator;

@Service
public class ProjetoService implements ProjetoServiceInterface{

  @Autowired
  private ProjetoRepositoryInterface projetoRepository;


  public ResponseEntity<String> adicionarProjeto(@RequestBody CreateProjetoRequest projeto) {

    if(!ProjetoValidator.isValid(projeto.nome, projeto.descricao, projeto.status)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome, status e descrição são obrigatórios");
    }
    Projeto proj = new Projeto(projetoRepository.getId() + 1, projeto.getNome(), projeto.getDescricao(), projeto.getData_inicio(), projeto.getData_termino(), projeto.status);
    projetoRepository.adicionarProjeto(proj);
    return ResponseEntity.status(HttpStatus.CREATED).body("Projeto adicionado com sucesso");
  }
  
  public ResponseEntity<List<Projeto>> listarProjetos() {
    List<Projeto> projetos = projetoRepository.listarProjetos();
    return ResponseEntity.ok(projetos);
  }

  public ResponseEntity<String> removerProjeto(@PathVariable Long codigo) {
    Optional<Projeto> projeto = projetoRepository.buscarProjetoPorCodigo(codigo);
    if (projeto.isPresent()) {
      projetoRepository.removerProjeto(projeto.get());
      return ResponseEntity.ok("Projeto removido com sucesso");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long codigo) {
    Optional<Projeto> projetoOptional = projetoRepository.buscarProjetoPorCodigo(codigo);
    if (projetoOptional.isPresent()) {
      return ResponseEntity.ok(projetoOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<String> editarProjeto(@PathVariable Long codigo, @RequestBody Projeto novoProjeto) {
    Optional<Projeto> projetoOptional = projetoRepository.buscarProjetoPorCodigo(codigo);

    if (!ProjetoValidator.isValid(novoProjeto.getNome(), novoProjeto.getDescricao(), novoProjeto.getStatus())) 
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome, status e descrição são obrigatórios");

    if (projetoOptional.isPresent()) {
      Projeto projetoExistente = projetoOptional.get();
      if (novoProjeto.getNome() != null) {
        projetoExistente.setNome(novoProjeto.getNome());
      }
      if (novoProjeto.getDescricao() != null) {
        projetoExistente.setDescricao(novoProjeto.getDescricao());
      }
      if (novoProjeto.getData_termino() != null) {
        projetoExistente.setData_termino(novoProjeto.getData_termino());
      }
      if (novoProjeto.getData_inicio() != null) {
        projetoExistente.setData_inicio(novoProjeto.getData_inicio());
      }
      if (novoProjeto.getStatus() != null) {
        projetoExistente.setStatus(novoProjeto.getStatus());
      }
      projetoRepository.editarProjeto(codigo, projetoExistente);
      return ResponseEntity.ok("Projeto editado com sucesso");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<String> atualizarStatusProjeto(@PathVariable Long codigo, @RequestBody StatusProjeto novoStatus) {
    Optional<Projeto> projetoOptional = projetoRepository.buscarProjetoPorCodigo(codigo);
    if (projetoOptional.isPresent()) {
      Projeto projeto = projetoOptional.get();
      projeto.setStatus(novoStatus.toString());
      projetoRepository.editarProjeto(codigo, projeto);
      return ResponseEntity.ok("Status do projeto atualizado com sucesso");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
