package com.app.hospital.hospitalapp.security.repo;

import com.app.hospital.hospitalapp.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
        AppUser findAppUserByUsername(String username);
}
