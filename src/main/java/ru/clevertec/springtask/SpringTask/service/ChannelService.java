package ru.clevertec.springtask.SpringTask.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.springtask.SpringTask.domain.AppUser;
import ru.clevertec.springtask.SpringTask.domain.Category;
import ru.clevertec.springtask.SpringTask.domain.Channel;
import ru.clevertec.springtask.SpringTask.domain.Language;
import ru.clevertec.springtask.SpringTask.domain.dto.ChannelInDto;
import ru.clevertec.springtask.SpringTask.exception.NotFoundException;
import ru.clevertec.springtask.SpringTask.repository.ChannelRepository;
import ru.clevertec.springtask.SpringTask.repository.UserRepository;
import ru.clevertec.springtask.SpringTask.util.ChannelMapper;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChannelService {
    private ChannelRepository channelRepo;
    private UserRepository userRepo;
    private ChannelMapper mapper;

    public Channel findById(long id) throws NotFoundException {
        return channelRepo.findById(id).orElseThrow(() -> new NotFoundException("Channel with such id has not been found", id));
    }

    public List<Channel> findAll(Map<String, String> params, Pageable pageable) {
        Channel channel = new Channel();
        String field;
        ExampleMatcher channelExampleMatcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
        if ((field = params.get("name")) != null) {
            channel.setName(field);
            channelExampleMatcher = channelExampleMatcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if ((field = params.get("language")) != null) {
            channel.setLanguage(Language.valueOf(field.toUpperCase()));
            channelExampleMatcher = channelExampleMatcher.withMatcher("language", ExampleMatcher.GenericPropertyMatchers.exact());
        }
        if ((field = params.get("category")) != null) {
            channel.setCategory(Category.valueOf(field.toUpperCase()));
            channelExampleMatcher = channelExampleMatcher.withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact());
        }
        channelExampleMatcher = channelExampleMatcher.withIgnorePaths("id");
        Example<Channel> newsExample = Example.of(channel, channelExampleMatcher);
        List<Channel> channelList = channelRepo.findAll(newsExample, pageable).toList();

        return channelList;
    }

    public Channel create(ChannelInDto dto) {
        Channel channel = mapper.toChannelFromIn(dto);
        AppUser author = userRepo.findById(dto.getAuthor())
                .orElseThrow(() -> new NotFoundException("User with such id has not been found", dto.getAuthor()));
        channel.setAuthor(author);
        return channelRepo.save(channel);
    }

    public Channel update(ChannelInDto dto) {
        Channel savedChannel = channelRepo.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Channel with such id has not been found", dto.getId()));
        AppUser author = userRepo.findById(dto.getAuthor())
                .orElseThrow(() -> new NotFoundException("User with such id has not found", dto.getAuthor()));
        savedChannel.setAuthor(author);
        savedChannel.setName(dto.getName());
        savedChannel.setDescription(dto.getDescription());
        savedChannel.setLanguage(dto.getLanguage());
        savedChannel.setCategory(dto.getCategory());
        return channelRepo.save(savedChannel);
    }

    public String subscribe(long channelId, long userId) {
        Channel channel = channelRepo.findById(channelId)
                .orElseThrow(() -> new NotFoundException("Channel with such id has not been found", channelId));
        AppUser user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with such id has not been found", userId));
        channel.addSubscriber(user);
        channelRepo.save(channel);
        return "subscribed";
    }

    public String unSubscribe(long channelId, long userId) {
        Channel channel = channelRepo.findById(channelId)
                .orElseThrow(() -> new NotFoundException("Channel with such id has not been found", channelId));
        AppUser user = channel.getSubscribers().stream().filter(us -> us.getId() == userId).findFirst()
                .orElseThrow(() -> new NotFoundException("User with such id has not been subscribed to channel)", userId));
        channel.getSubscribers().remove(user);
        channelRepo.save(channel);
        return "unsubscribed";
    }

}
