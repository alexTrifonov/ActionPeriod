package ru.job4j.actionperiod.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.actionperiod.models.Role;

import java.util.List;


/**
 * Repository for Role.
 * @author atrifonov.
 * @version 1.
 * @since 12.04.2018.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    List<Role> findByName(String name);
}
