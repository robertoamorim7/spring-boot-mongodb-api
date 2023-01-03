package com.example.apispringbootmongodb.services;

import com.example.apispringbootmongodb.domain.User;
import com.example.apispringbootmongodb.dto.UserDTO;
import com.example.apispringbootmongodb.repository.UserRepository;
import com.example.apispringbootmongodb.services.exception.ObjectNotFoundException;
import com.sun.jdi.ObjectCollectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj) {
        User newUser = findById(obj.getId());
        updateData(newUser, obj);
        return repository.save(newUser);

    }

    private void updateData(User newUser, User obj) {
        newUser.setName(obj.getName());
        newUser.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO obj) {
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }

}
