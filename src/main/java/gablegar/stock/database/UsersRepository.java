package gablegar.stock.database;

import gablegar.stock.database.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by glegarda on 14/03/18.
 * This interface is used for the interaction with db using CRUD operations for User entity
 */
public interface UsersRepository extends CrudRepository<User, Long>
{
    User findByUsername(final String username);
}
