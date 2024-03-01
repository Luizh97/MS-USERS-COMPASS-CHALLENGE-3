package compassouol.sp.challenge.msuser.msuser.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import compassouol.sp.challenge.msuser.msuser.service.UserService;
import compassouol.sp.challenge.msuser.msuser.web.dto.*;
import compassouol.sp.challenge.msuser.msuser.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um usuário")

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(summary = "Create a new user", description = "Resource to create a new user",
            responses = {
                @ApiResponse(responseCode = "201", description = "Reourse created successfully", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserResponseDto.class))),
                @ApiResponse(responseCode = "409", description = "User e-mail already registered in the system", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422", description = "Resource not processed due to invalid input data", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorMessage.class)))
            })

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserCreateDto user) throws JsonProcessingException {
        UserResponseDto dto=  userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Find user by id", description = "Resource to find user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reourse find successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)))
            })

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        UserResponseDto dto=  userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Update password", description = "Resource to update a password of a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User password updated", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Resource not processed due to invalid input data", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PutMapping("/{id}/password")
    public ResponseEntity updateUserPassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordDto passwordDto) throws JsonProcessingException {
        userService.updateUserPassword(id, passwordDto.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
    }

    @Operation(summary = "Update user", description = "Resource to update a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "409", description = "User e-mail already registered in the system", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)))
            })

    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto user) throws JsonProcessingException {
        UserResponseDto dto =  userService.updateUser(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}