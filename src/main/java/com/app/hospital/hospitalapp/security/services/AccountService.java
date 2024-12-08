package com.app.hospital.hospitalapp.security.services;

import com.app.hospital.hospitalapp.security.entities.AppRole;
import com.app.hospital.hospitalapp.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String password,String email,String confirmPassword);

    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);

    AppUser loadUserByUserName(String username);
    AppRole loadRole(String role);

}
