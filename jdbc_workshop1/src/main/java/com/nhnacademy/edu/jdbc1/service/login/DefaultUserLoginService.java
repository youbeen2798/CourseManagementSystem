package com.nhnacademy.edu.jdbc1.service.login;

import org.springframework.stereotype.Service;

@Service
 class DefaultUserLoginService implements UserLoginService{

    public final UserRepository userRepository;

    public DefaultUserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isExistUser(String username, String password){
        return userRepository.matches(username, password);
    }

}
