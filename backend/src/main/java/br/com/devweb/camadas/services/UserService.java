package br.com.devweb.camadas.services;

import br.com.devweb.camadas.data.UserRepository;
import br.com.devweb.camadas.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        User checkUser = getUserByEmail(user.getEmail());

        if (checkUser != null) {
            throw new RuntimeException("Usuário já cadastrado com este email.");
        }

        User newUser = new User(userRepository.genCodigo(), user.getName(), user.getEmail(), user.getPassword());
        userRepository.adicionarUser(newUser);
        userRepository.incCodigo();
        return newUser;
    }

//    public List<User> getUsers () {
//        return userRepository.findAll();
//    }

    public User getUserByEmail(String email){
        return userRepository.buscarUserPorEmail(email).orElse(null);
    }

    public User getUserById(Long id){
        var userList = userRepository.buscarUserPorCodigo(id);

        return userList.orElse(null);
    }

}
