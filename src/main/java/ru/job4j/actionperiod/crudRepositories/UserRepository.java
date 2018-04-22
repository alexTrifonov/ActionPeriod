package ru.job4j.actionperiod.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.actionperiod.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository for User.
 * @author atrifonov.
 * @version 1.
 * @since 12.04.2018.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findByLogin(String login);
}
