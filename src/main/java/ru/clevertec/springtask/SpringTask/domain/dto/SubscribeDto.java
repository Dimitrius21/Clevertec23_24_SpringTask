package ru.clevertec.springtask.SpringTask.domain.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscribeDto {
    @Positive
    private long channelId;
    @Positive
    private long userId;
}
