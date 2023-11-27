package ru.clevertec.springtask.SpringTask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.springtask.SpringTask.domain.AppUser;
import ru.clevertec.springtask.SpringTask.exception.NotFoundException;
import ru.clevertec.springtask.SpringTask.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepo;

    public AppUser findById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User with such id has not been found", id));
    }

    public AppUser create(AppUser user) {
        return userRepo.save(user);
    }

    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    public AppUser update(AppUser user) {
        Optional<AppUser> savedUser = userRepo.findById(user.getId());
        if (savedUser.isEmpty()) {
            throw new NotFoundException("User with such id has not been found", user.getId());
        }
        return userRepo.save(user);
    }

}
