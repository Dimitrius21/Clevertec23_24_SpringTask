package ru.clevertec.springtask.SpringTask.domain.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.clevertec.springtask.SpringTask.domain.Channel;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserDto {
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String nickName;
    @Email
    private String email;
    private List<Channel> channels;
}
