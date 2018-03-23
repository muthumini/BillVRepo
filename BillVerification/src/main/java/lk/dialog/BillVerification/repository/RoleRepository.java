package lk.dialog.BillVerification.repository;

import lk.dialog.BillVerification.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Sasini_08765
 */

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

}
