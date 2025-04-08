package com.restadmin.restadmin.service;

import org.springframework.stereotype.Service;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import com.restadmin.restadmin.model.User;
import com.restadmin.restadmin.repository.UserRepository;
import com.restadmin.restadmin.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signin(String name, String pass) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getNom().equals(name)) {
                return "name already exists";
            }
        }
        String hash = hashPass(pass);
        User user = new User();
        user.setNom(name);
        user.setMdp(hash);
        userRepository.save(user);
        return "user " + name + " added";

    }

    public UserDTO login(String nom, String mdp) {
        UserDTO userDTO = null;
        Iterable<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getNom().equals(nom) && verifyHash(mdp, user.getMdp())) {
                userDTO = new UserDTO();
                userDTO.setNom(nom);
                userDTO.setId(user.getId());
                userDTO.setAdmin(user.getAdmin());
                break;
            }
        }
        return userDTO;
    }

    public Iterable<UserDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getNom(),
                    user.getAdmin());
            usersDTO.add(userDTO);
        }

        return usersDTO;
    }

    public String hashPass(String mdp) {
        String hashString;
        Hash hash = Password.hash(mdp).with(bcrypt);
        hashString = hash.getResult();
        return hashString;
    }

    public boolean verifyHash(String mdp, String hash) {
        boolean verified = Password.check(mdp, hash).with(bcrypt);
        return verified;
    }

    public User findUserByName(String name) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (name == user.getNom()) {
                return user;
            }
        }
        return null;
    }
}