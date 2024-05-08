package br.com.devweb.camadas.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.devweb.camadas.models.Orcamento;

public interface OrcamentoRepositoryInterface {
  void adicionarOrcamento(Orcamento orcamento);
  void removerOrcamento(Orcamento orcamento);
  List<Orcamento> listarOrcamentos();
  long getId();
  Optional<Orcamento> buscarOrcamentoPorCodigo(Long codigo);
  boolean editarOrcamento(Long codigo, Orcamento orcamentoAtualizado);
}
