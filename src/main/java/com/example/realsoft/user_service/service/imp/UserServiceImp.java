package com.example.realsoft.user_service.service.imp;

import com.example.realsoft.user_service.entity.Role;
import com.example.realsoft.user_service.entity.User;
import com.example.realsoft.user_service.exception.APIException;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.UserLogin;
import com.example.realsoft.user_service.model.UserRequest;
import com.example.realsoft.user_service.model.UserResponse;
import com.example.realsoft.user_service.repository.RoleRepository;
import com.example.realsoft.user_service.repository.UserRepository;
import com.example.realsoft.user_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse getUserByEmail(UserLogin userLogin) throws ResourceNotFound {
        User user = userRepository.findByEmail(userLogin.getEmail());
        if(user == null) {
            log.error("This user doesn't exist in DB!");
            throw new ResourceNotFound("User", "Email", userLogin.getEmail());
        }
        if(!bCryptPasswordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            log.error("Password entered is incorrect!");
            throw new ResourceNotFound("User", "Password", userLogin.getPassword());
        }

        log.info("User {} found in database", userLogin);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws ResourceNotFound {
        Role role = roleRepository.findByName(userRequest.getRoleName());
        if(role == null) {
            log.error("User can't be created with this role name!");
            throw new ResourceNotFound("Role", "Name", userRequest.getRoleName());
        }
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            log.error("This username is already exists!");
            throw new APIException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }
        User user = User.builder().name(userRequest.getName())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                .roles(Collections.singleton(role)).build();

        log.info("User {} saved to DB successfully!", user);
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

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
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
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

    @Override
    public Role saveRole(Role role) {
        log.info("New role {} is being added to the DB", role.getName());
        return roleRepository.save(role);
    }

    private User findUser(Long userId) throws ResourceNotFound {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFound("User", "Id", userId.toString()));
    }
}
