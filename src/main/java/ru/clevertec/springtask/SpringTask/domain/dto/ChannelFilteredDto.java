package ru.clevertec.springtask.SpringTask.domain.dto;

import lombok.Data;
import ru.clevertec.springtask.SpringTask.domain.Category;
import ru.clevertec.springtask.SpringTask.domain.Language;

@Data
public class ChannelFilteredDto {
    private String name;
    private Category category;
    private Language language;
    private String avatar;
    private long subscribersQtity;


}
