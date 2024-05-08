package br.com.devweb.camadas.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.devweb.camadas.interfaces.OrcamentoRepositoryInterface;
import br.com.devweb.camadas.models.Orcamento;
import lombok.Data;

@Data
@Repository
public class OrcamentoRepository implements OrcamentoRepositoryInterface {
  private List<Orcamento> orcamentos = new ArrayList<>();

  public void adicionarOrcamento(Orcamento orcamento) {
    orcamentos.add(orcamento);
  }

  public void removerOrcamento(Orcamento orcamento) {
    orcamentos.remove(orcamento);
  }

  public List<Orcamento> listarOrcamentos() {
    return orcamentos;
  }

  public long getId() {
    return orcamentos.size();
  }
  public Optional<Orcamento> buscarOrcamentoPorCodigo(Long codigo) {
    return orcamentos.stream()
                  .filter(orcamento -> orcamento.getCodigo() == codigo)
                  .findFirst();
  }

  public boolean editarOrcamento(Long codigo, Orcamento orcamentoAtualizado) {
    Optional<Orcamento> orcamentoOptional = buscarOrcamentoPorCodigo(codigo);
    if (orcamentoOptional.isPresent()) {
      Orcamento orcamentoExistente = orcamentoOptional.get();
      orcamentoExistente.setNome_empresa(orcamentoAtualizado.getNome_empresa());
      orcamentoExistente.setDescricao(orcamentoAtualizado.getDescricao());
      orcamentoExistente.setValor(orcamentoAtualizado.getValor());
      return true; 
    }
    return false; 
  }
}