package compassouol.sp.challenge.msuser.msuser.web.controller;

import compassouol.sp.challenge.msuser.msuser.service.UserService;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserCreateDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserLoginDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserResponseDto;
import compassouol.sp.challenge.msuser.msuser.web.dto.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserCreateDto user){
        UserResponseDto dto=  userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        UserResponseDto dto=  userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @PutMapping("/{id}/password")
    public ResponseEntity updateUserPassword(@PathVariable Long id, @RequestBody String password){
        userService.updateUserPassword(id, password);

        return ResponseEntity.status(HttpStatus.OK).body("Password updated");
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto user){
        UserResponseDto dto =  userService.updateUser(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }



}
