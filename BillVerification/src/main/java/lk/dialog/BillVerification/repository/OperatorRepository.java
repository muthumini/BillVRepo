package lk.dialog.BillVerification.repository;

import lk.dialog.BillVerification.model.Operator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Sasini_08765
 */

@RepositoryRestResource
public interface OperatorRepository extends JpaRepository<Operator, Long> {

	Operator findOperatorByName(String name);

	Operator findByCode(String code);

}
