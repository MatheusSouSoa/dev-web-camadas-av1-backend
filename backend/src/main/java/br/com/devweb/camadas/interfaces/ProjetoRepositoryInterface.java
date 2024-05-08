package br.com.devweb.camadas.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.devweb.camadas.models.Projeto;

public interface ProjetoRepositoryInterface {
  void adicionarProjeto(Projeto projeto);
  void removerProjeto(Projeto projeto);
  List<Projeto> listarProjetos();
  long getId();
  Optional<Projeto> buscarProjetoPorCodigo(Long codigo);
  boolean editarProjeto(Long codigo, Projeto projetoAtualizado);
}
