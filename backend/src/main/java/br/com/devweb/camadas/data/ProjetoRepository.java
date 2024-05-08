package br.com.devweb.camadas.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.devweb.camadas.interfaces.ProjetoRepositoryInterface;
import br.com.devweb.camadas.models.Projeto;
import lombok.Data;

@Data
@Repository
public class ProjetoRepository implements ProjetoRepositoryInterface{
  
  private List<Projeto> projetos = new ArrayList<>();

  public void adicionarProjeto(Projeto projeto) {
    projetos.add(projeto);
  }

  public void removerProjeto(Projeto projeto) {
    projetos.remove(projeto);
  }

  public List<Projeto> listarProjetos() {
    return projetos;
  }

  public long getId() {
    return projetos.size();
  }
  public Optional<Projeto> buscarProjetoPorCodigo(Long codigo) {
    return projetos.stream()
                  .filter(projeto -> projeto.getCodigo() == codigo)
                  .findFirst();
  }

  public boolean editarProjeto(Long codigo, Projeto projetoAtualizado) {
    Optional<Projeto> projetoOptional = buscarProjetoPorCodigo(codigo);
    if (projetoOptional.isPresent()) {
      Projeto projetoExistente = projetoOptional.get();
      projetoExistente.setNome(projetoAtualizado.getNome());
      projetoExistente.setDescricao(projetoAtualizado.getDescricao());
      projetoExistente.setData_termino(projetoAtualizado.getData_termino());
      projetoExistente.setData_inicio(projetoAtualizado.getData_inicio());
      return true; 
    }
    return false; 
  }

}
