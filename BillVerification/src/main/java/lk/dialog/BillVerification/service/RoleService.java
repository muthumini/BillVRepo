package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.model.Role;
import lk.dialog.BillVerification.repository.RoleRepository;
import lk.dialog.BillVerification.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sasini_08765
 */

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles() {
        try {
            logger.debug("Retrieve roles");
            return roleRepository.findAll();
        } catch (Exception e) {
            logger.error("Failed to retrieve roles");
            throw new ServiceException("Roles retrieving failed");
        }
    }
}
