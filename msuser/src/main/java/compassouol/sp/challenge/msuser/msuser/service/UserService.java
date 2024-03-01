package compassouol.sp.challenge.msuser.msuser.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import compassouol.sp.challenge.msuser.msuser.client.MsAddressClient;
import compassouol.sp.challenge.msuser.msuser.entity.DadosUserSistema;
import compassouol.sp.challenge.msuser.msuser.entity.Usuario;
import compassouol.sp.challenge.msuser.msuser.exception.DatasUniqueViolationException;
import compassouol.sp.challenge.msuser.msuser.exception.EntityNotFoundException;
import compassouol.sp.challenge.msuser.msuser.exception.UsernameUniqueViolationException;
import compassouol.sp.challenge.msuser.msuser.infra.mqueue.MovimentacaoUserPublisher;
import compassouol.sp.challenge.msuser.msuser.repository.UserRepository;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserCreateDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserResponseDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserUpdateDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final MovimentacaoUserPublisher movimentacaoUserPublisher;
    private final MsAddressClient msAddressClient;

   public Usuario salvar(Usuario user) throws DatasUniqueViolationException {
         try {
              return userRepository.save(user);

         } catch (DataIntegrityViolationException e) {
              throw new UsernameUniqueViolationException("Email or CPF already registered");
         }


   }


    public UserResponseDto createUser(UserCreateDto user) throws JsonProcessingException, UsernameUniqueViolationException {


        try{

            movimentacaoUserPublisher.movimentacaoUsuario(new DadosUserSistema(user.getEmail(), "CREATE", new Date()));

            Usuario userEntity = salvar(UserMapper.toEntity(user));


            buscarEnderecoPorCep(userEntity.getCep(), userEntity.getId());
            return UserMapper.toResponseDto(userEntity);
        }catch(DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException("Email or CPF already registered");
        }

        }


    public UserResponseDto getUserById(Long id) {
        Usuario user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );


        return UserMapper.toResponseDto(user);
    }

    public void updateUserPassword(Long id, String password) throws JsonProcessingException {

        Usuario user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        System.out.println(password);

        user.setPassword(password);
        userRepository.save(user);
        movimentacaoUserPublisher.movimentacaoUsuario(new DadosUserSistema(user.getEmail(), "UPDATE_PASSWORD", new Date()));
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto user) throws JsonProcessingException {
        Usuario userFinded = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UsernameUniqueViolationException("Email already registered");
        }
        if (userRepository.findByCpf(user.getCpf()).isPresent()){
            throw new UsernameUniqueViolationException("CPF already registered");
        }

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
        movimentacaoUserPublisher.movimentacaoUsuario(new DadosUserSistema(userFinded.getEmail(), "UPDATE", new Date()));
        return UserMapper.toResponseDto(userFinded);

    }

    @Transactional(readOnly = true)
    public Usuario findByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }
    @Transactional(readOnly = true)
    public Usuario.Role buscarRolerPorEmail(String username) {
        return userRepository.findRoleByEmail(username);

    }
    public Object movimentacaoSistema(DadosUserSistema dadosUserSistema) {
        try{
            movimentacaoUserPublisher.movimentacaoUsuario(dadosUserSistema);
            return "Message sent to queue";
        }catch (Exception e){
            throw new IllegalArgumentException("Error to send message to queue");
        }
    }
    public void buscarEnderecoPorCep(String cep, Long id) {
         msAddressClient.buscarEnderecoUsuario(cep, id);
    }

//    public void buscarUsuario(Usuario user) {
//         msAddressClient.buscarUsuario(user);
//    }
}
