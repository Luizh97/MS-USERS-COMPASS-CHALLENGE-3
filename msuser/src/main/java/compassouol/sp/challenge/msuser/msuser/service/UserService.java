package compassouol.sp.challenge.msuser.msuser.service;

import compassouol.sp.challenge.msuser.msuser.entity.User;
import compassouol.sp.challenge.msuser.msuser.repository.UserRepository;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserCreateDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserResponseDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserUpdateDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserCreateDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.toResponseDto(userRepository.save(UserMapper.toEntity(user)));
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        return UserMapper.toResponseDto(user);
    }

    public void updateUserPassword(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        user.setPassword(passwordEncoder.encode(password));
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto user) {
        User userFinded = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        if(user.getFirstName() != null && !user.getFirstName().isEmpty()){
            userFinded.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null && !user.getLastName().isEmpty()){
            userFinded.setLastName(user.getLastName());
        }
        if(user.getEmail() != null && !user.getEmail().isEmpty()){
            userFinded.setEmail(user.getEmail());
        }
        if(user.getCpf() != null && !user.getCpf().isEmpty()){
            userFinded.setCpf(user.getCpf());
        }
        if(user.getCep() != null && !user.getCep().isEmpty()){
            userFinded.setCep(user.getCep());
        }
        userRepository.save(userFinded);
        return UserMapper.toResponseDto(userFinded);

    }

    public void login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("User not found. Please check your email")
        );

    }
}
