package poc.springbootexample.models.User;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by norner on 11/03/2017.
 */
public interface UserManyManyDao extends CrudRepository<UserManyMany, Long>{
}
