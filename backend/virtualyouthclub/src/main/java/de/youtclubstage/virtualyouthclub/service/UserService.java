package de.youtclubstage.virtualyouthclub.service;

import de.youtclubstage.virtualyouthclub.entity.User;
import de.youtclubstage.virtualyouthclub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<User> getUsers(String search,Pageable pageable){
        if(search == null || search.isEmpty()){
            return userRepository.findAll(pageable);
        }else{
            return userRepository.findAllByEmailContains(search,pageable);
        }
    }
}
