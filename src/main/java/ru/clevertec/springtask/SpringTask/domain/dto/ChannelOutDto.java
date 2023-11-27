package ru.clevertec.springtask.SpringTask.domain.dto;

import lombok.Data;
import ru.clevertec.springtask.SpringTask.domain.Category;
import ru.clevertec.springtask.SpringTask.domain.Language;

import java.time.LocalDate;
import java.util.List;

@Data
public class ChannelOutDto {
    private long id;
    private String name;
    private String description;
    private String author;
    private LocalDate createDate;
    private Category category;
    private Language language;
    private String avatar;
    private List<String> subscribers;
}
