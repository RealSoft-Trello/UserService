package com.example.realsoft.user_service.service.imp;

import com.example.realsoft.user_service.entity.User;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.model.UserResponse;
import com.example.realsoft.user_service.repository.UserRepository;
import com.example.realsoft.user_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserResponse getUserById(Long userId) throws ResourceNotFound {
        log.info("Getting a user from DB with id {}", userId);
        return modelMapper.map(findUser(userId), UserResponse.class);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFound {
        log.info("Deleting user with id {}", userId);
        userRepository.delete(findUser(userId));
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) throws ResourceNotFound {
        log.info("Editing user info with id {}", userId);
        User user = findUser(userId);
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Getting all users from DB");
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    private User findUser(Long userId) throws ResourceNotFound {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFound("User", "Id", userId.toString()));
    }
}
