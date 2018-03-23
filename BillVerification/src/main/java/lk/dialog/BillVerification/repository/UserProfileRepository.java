package lk.dialog.BillVerification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lk.dialog.BillVerification.model.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Sasini_08765
 */

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

    @Query("select u from UserProfile u where u.user.role.role = :role")
    List<UserProfile> findUserByRole(@Param("role") String role);

}
