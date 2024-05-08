package br.com.devweb.camadas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.devweb.camadas.dto.CreateProjetoRequest;
import br.com.devweb.camadas.enums.StatusProjeto;
import br.com.devweb.camadas.interfaces.ProjetoRepositoryInterface;
import br.com.devweb.camadas.interfaces.ProjetoServiceInterface;
import br.com.devweb.camadas.models.Projeto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api-v1-0/projetos")
public class ProjetoController {

  @Autowired
  private ProjetoServiceInterface projetoService;

  @Autowired
  private ProjetoRepositoryInterface projetoRepository;

  @PostMapping
  public ResponseEntity<String> adicionarProjeto(@RequestBody CreateProjetoRequest projeto) {
    return projetoService.adicionarProjeto(projeto);
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<String> removerProjeto(@PathVariable Long codigo) {
    return projetoService.removerProjeto(codigo);
  }

  @GetMapping
  public ResponseEntity<List<Projeto>> listarProjetos() {
    List<Projeto> projetos = projetoRepository.listarProjetos();
    return ResponseEntity.ok(projetos);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long codigo) {
    return projetoService.buscarProjetoPorId(codigo);
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<String> editarProjeto(@PathVariable Long codigo, @RequestBody Projeto novoProjeto) {
    return projetoService.editarProjeto(codigo, novoProjeto);
  }

  @PutMapping("/{codigo}/status")
  public ResponseEntity<String> atualizarStatusProjeto(@PathVariable Long codigo, @RequestBody StatusProjeto novoStatus) {
    return projetoService.atualizarStatusProjeto(codigo, novoStatus);
  }
}
