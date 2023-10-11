package com.library.management.initializer;

import com.library.management.model.ERole;
import com.library.management.model.Role;
import com.library.management.repository.IRoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private IRoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void loadData() {
        if (roleRepository.findByName(ERole.ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(ERole.ADMIN);
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName(ERole.USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setName(ERole.USER);
            roleRepository.save(userRole);
        }
    }
}
