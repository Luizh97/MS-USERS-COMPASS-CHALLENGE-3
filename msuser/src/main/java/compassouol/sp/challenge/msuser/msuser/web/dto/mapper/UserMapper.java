package compassouol.sp.challenge.msuser.msuser.web.dto.mapper;

import compassouol.sp.challenge.msuser.msuser.entity.Usuario;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserCreateDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserResponseDto;

public class UserMapper {
    public static Usuario toEntity(UserCreateDto dto) {
        Usuario user = new Usuario();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCpf(dto.getCpf());
        user.setCep(dto.getCep());
        user.setBirthDate(dto.getBirthDate());
        user.setStatus(dto.isStatus());
        return user;
    }

    public static UserResponseDto toResponseDto(Usuario user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setCpf(user.getCpf());
        userResponseDto.setCep(user.getCep());
        userResponseDto.setBirthDate(user.getBirthDate());
        userResponseDto.setStatus(user.isStatus());
        return userResponseDto;
    }
}
