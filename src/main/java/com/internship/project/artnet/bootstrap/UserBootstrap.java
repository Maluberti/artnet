package com.internship.project.artnet.bootstrap;

import com.internship.project.artnet.domain.Users;
import com.internship.project.artnet.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final UsersRepository usersRepository;

    public UserBootstrap(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        usersRepository.saveAll(getUsers());
        log.debug("Loading Bootstrap Data");
    }

    private List<Users> getUsers() {
        List<Users> users = new ArrayList<>(2);
        Users user1 = new Users((long) 1, "user1", "user1.com","user1password", true, false); //conferir como nao precisar inicializar o id
        users.add(user1);
        Users user2 = new Users((long) 2, "user2", "user2.com","user2password", true, false);
        users.add(user2);
        return users;
    }
}
