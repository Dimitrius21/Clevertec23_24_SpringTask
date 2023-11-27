package ru.clevertec.springtask.SpringTask.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.springtask.SpringTask.domain.Channel;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelFilteredDto;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelInDto;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelOutDto;
import ru.clevertec.springtask.SpringTask.domain.dto.SubscribeDto;
import ru.clevertec.springtask.SpringTask.service.ChannelService;
import ru.clevertec.springtask.SpringTask.util.ChannelMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/channel")
@AllArgsConstructor
public class ChannelController {
    private ChannelService channelService;
    private ChannelMapper mapper;

    @GetMapping
    public ResponseEntity<ChannelOutDto> getById(@RequestParam @Positive long id) {
        Channel channel = channelService.findById(id);
        ChannelOutDto dto = mapper.toChannelOutDto(channel);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChannelFilteredDto>> getAllChannels(@RequestParam Map<String, String> parameters,
                                                                   Pageable pageable) {
        List<Channel> channels = channelService.findAll(parameters, pageable);
        List<ChannelFilteredDto> dtoList = channels.stream().map(it -> mapper.toChannelFilteredDto(it)).toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChannelOutDto> createChannel(@Valid @RequestBody ChannelInDto dto) {

        Channel channel = channelService.create(dto);
        ChannelOutDto outDto = mapper.toChannelOutDto(channel);
        return new ResponseEntity<>(outDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ChannelOutDto> updateChannel(@Valid @RequestBody ChannelInDto dto) {

        Channel channel = channelService.update(dto);
        ChannelOutDto outDto = mapper.toChannelOutDto(channel);
        return new ResponseEntity<>(outDto, HttpStatus.OK);
    }


    @PostMapping("/subscribe")
    public String subscribeUser(@Valid @RequestBody SubscribeDto dto) {
        return channelService.subscribe(dto.getChannelId(), dto.getUserId());
    }

    @PostMapping("/unsubscribe")
    public String unSubscribeUser(@Valid @RequestBody SubscribeDto dto) {
        return channelService.unSubscribe(dto.getChannelId(), dto.getUserId());
    }
}
