package ru.clevertec.springtask.SpringTask.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.springtask.SpringTask.domain.Channel;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelFilteredDto;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelInDto;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelOutDto;


@Mapper(componentModel = "spring")
public interface ChannelMapper {

    @Mapping(target = "avatar", expression = "java(channel.getAvatar()!=null ? java.util.Base64.getDecoder().decode(dto.getAvatar()) : null)")
    @Mapping(target = "author", expression = "java(null)")
    @Mapping(target = "subscribers", expression = "java(null)")
    @Mapping(target = "createDate", defaultExpression = "java(java.time.LocalDate.now())")
    public Channel toChannelFromIn(ChannelInDto dto);

    @Mapping(target = "subscribers", expression = "java(channel.getSubscribers()!=null ? channel.getSubscribers().stream().map(it -> it.getName()).toList() : new java.util.ArrayList<>())")
    @Mapping(target = "author", expression = "java(channel.getAuthor().getName())")
    @Mapping(target = "avatar", expression = "java( channel.getAvatar()!=null ? java.util.Base64.getEncoder().encodeToString(channel.getAvatar()):null  ) ")
    public ChannelOutDto toChannelOutDto(Channel channel);

    @Mapping(target = "subscribersQtity", expression = "java(channel.getSubscribers().size())")
    @Mapping(target = "avatar", expression = "java( channel.getAvatar()!=null ? java.util.Base64.getEncoder().encodeToString(channel.getAvatar()):null  ) ")
    public ChannelFilteredDto toChannelFilteredDto(Channel channel);
}
