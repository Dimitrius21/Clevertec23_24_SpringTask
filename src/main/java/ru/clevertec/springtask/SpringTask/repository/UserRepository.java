package ru.clevertec.springtask.SpringTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.springtask.SpringTask.domain.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
}
