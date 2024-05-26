package br.com.devweb.camadas.data;

import br.com.devweb.camadas.models.User;
import br.com.devweb.camadas.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private Long codigo = 1L;
    private final List<User> users = new ArrayList<>();

    public void adicionarUser(User user) {
        users.add(user);
    }

    public void incCodigo() {
        this.codigo++;
    }

    public Long genCodigo() {
        return codigo;
    }
    public void removerUser(User user) {
        users.remove(user);
    }

    public List<User> listarUsers() {
        return users;
    }

    public long getId() {
        return users.size();
    }

    public Optional<User> buscarUserPorCodigo(Long codigo) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), codigo))
                .findFirst();
    }

    public Optional<User> buscarUserPorEmail(String email) {
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    public boolean editarUser(Long codigo, User userAtualizado) {
        Optional<User> userOptional = buscarUserPorCodigo(codigo);
        if (userOptional.isPresent()) {
            User userExistente = userOptional.get();
            userExistente.setName(userAtualizado.getName());
            userExistente.setPassword(userAtualizado.getPassword());
            userExistente.setEmail(userAtualizado.getEmail());
            return true;
        }
        return false;
    }
}
