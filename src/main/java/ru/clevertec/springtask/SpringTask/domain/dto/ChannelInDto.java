package ru.clevertec.springtask.SpringTask.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.springtask.SpringTask.domain.Category;
import ru.clevertec.springtask.SpringTask.domain.Language;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelInDto {
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Positive
    private long author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    @NotNull
    private Category category;
    @NotNull
    private Language language;
    private String avatar;
    @Null
    private List<Long> subscribers;
}
