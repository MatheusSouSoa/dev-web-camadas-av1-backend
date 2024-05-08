package br.com.devweb.camadas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devweb.camadas.dto.CreateOrcamentoRequest;
import br.com.devweb.camadas.enums.StatusPagamento;
import br.com.devweb.camadas.interfaces.OrcamentoRepositoryInterface;
import br.com.devweb.camadas.models.Orcamento;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api-v1-0/orcamentos")
public class OrcamentoController {
  
  @Autowired
  private OrcamentoRepositoryInterface orcamentoRepository;

  @PostMapping
  public ResponseEntity<String> adicionarOrcamento(@RequestBody CreateOrcamentoRequest orcamento) {
    Orcamento budget = new Orcamento(orcamentoRepository.getId() + 1, orcamento.getNome_empresa(), orcamento.getDescricao(), orcamento.getValor(), orcamento.getStatus());
    orcamentoRepository.adicionarOrcamento(budget);
    return ResponseEntity.status(HttpStatus.CREATED).body("Orcamento adicionado com sucesso");
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<String> removerOrcamento(@PathVariable Long codigo) {
    Optional<Orcamento> orcamento = orcamentoRepository.buscarOrcamentoPorCodigo(codigo);
    if (orcamento.isPresent()) {
      orcamentoRepository.removerOrcamento(orcamento.get());
      return ResponseEntity.ok("Orcamento removido com sucesso");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Orcamento>> listarOrcamentos() {
    List<Orcamento> orcamentos = orcamentoRepository.listarOrcamentos();
    return ResponseEntity.ok(orcamentos);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Orcamento> buscarOrcamentoPorId(@PathVariable Long codigo) {
    Optional<Orcamento> orcamentoOptional = orcamentoRepository.buscarOrcamentoPorCodigo(codigo);
    if (orcamentoOptional.isPresent()) {
      return ResponseEntity.ok(orcamentoOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<String> editarOrcamento(@PathVariable Long codigo, @RequestBody Orcamento novoOrcamento) {
    Optional<Orcamento> orcamentoOptional = orcamentoRepository.buscarOrcamentoPorCodigo(codigo);
    if (orcamentoOptional.isPresent()) {
      Orcamento orcamentoExistente = orcamentoOptional.get();
      if (novoOrcamento.getNome_empresa() != null) {
        orcamentoExistente.setNome_empresa(novoOrcamento.getNome_empresa());
      }
      if (novoOrcamento.getDescricao() != null) {
        orcamentoExistente.setDescricao(novoOrcamento.getDescricao());
      }
      if (novoOrcamento.getValor() != null) {
        orcamentoExistente.setValor(novoOrcamento.getValor());;
      }
      if (novoOrcamento.getStatus_pagamento() != null) {
        orcamentoExistente.setStatus_pagamento(novoOrcamento.getStatus_pagamento());
      }
      orcamentoRepository.editarOrcamento(codigo, orcamentoExistente);
      return ResponseEntity.ok("Orcamento editado com sucesso");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{codigo}/status")
  public ResponseEntity<String> atualizarStatusOrcamento(@PathVariable Long codigo, @RequestBody StatusPagamento novoStatus) {
    Optional<Orcamento> orcamentoOptional = orcamentoRepository.buscarOrcamentoPorCodigo(codigo);
    if (orcamentoOptional.isPresent()) {
      Orcamento orcamento = orcamentoOptional.get();
      orcamento.setStatus_pagamento(novoStatus.toString());
      orcamentoRepository.editarOrcamento(codigo, orcamento);
      return ResponseEntity.ok("Status do orcamento atualizado com sucesso");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
