package lk.dialog.BillVerification.repository;

import lk.dialog.BillVerification.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Sasini_08765
 */

//Exposing repository interface as a RESTFUL resource
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);
}
