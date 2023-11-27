package ru.clevertec.springtask.SpringTask.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.springtask.SpringTask.domain.AppUser;
import ru.clevertec.springtask.SpringTask.domain.dto.UserDto;
import ru.clevertec.springtask.SpringTask.service.UserService;
import ru.clevertec.springtask.SpringTask.util.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserMapper mapper;

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam @Positive long id) {
        AppUser user = userService.findById(id);
        UserDto dto = mapper.toUserDto(user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/subscription")
    public ResponseEntity<List<String>> getUserSubscription(@RequestParam @Positive long id) {
        AppUser user = userService.findById(id);
        List<String> subscription = user.getChannels().stream().map(it -> it.getName()).toList();
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto) {
        AppUser user = mapper.toUser(dto);
        AppUser createdUser = userService.create(user);
        dto = mapper.toUserDto(createdUser);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserById(@RequestAttribute @Positive long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto) {
        AppUser user = mapper.toUser(dto);
        AppUser updatedUser = userService.update(user);
        dto = mapper.toUserDto(updatedUser);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
