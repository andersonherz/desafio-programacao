package br.com.herz.desafio_luizalabs.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herz.desafio_luizalabs.entity.User;
import br.com.herz.desafio_luizalabs.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

}
