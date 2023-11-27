package ru.clevertec.springtask.SpringTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.springtask.SpringTask.domain.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
