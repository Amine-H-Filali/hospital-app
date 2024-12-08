package com.app.hospital.hospitalapp.security.services;

import com.app.hospital.hospitalapp.security.entities.AppRole;
import com.app.hospital.hospitalapp.security.entities.AppUser;
import com.app.hospital.hospitalapp.security.repo.AppRoleRepository;
import com.app.hospital.hospitalapp.security.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    AppUserRepository userRepository;
    AppRoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
       AppUser user= userRepository.findAppUserByUsername(username);
       if(user!=null) throw  new RuntimeException("user already Exist");
       if(!password.equals(confirmPassword)) throw  new RuntimeException("Passwords not Match");

       user=userRepository.save(AppUser.builder().UserId(UUID.randomUUID().toString())
                       .email(email)
                       .password(passwordEncoder.encode(password))
                       .username(username)

               .build());
        return user;
    }

    @Override
    public AppRole addNewRole(String role) {
       AppRole appRole=roleRepository.findById(role).orElse(null);
       if(appRole!=null) throw  new RuntimeException("this Role already Exist");
       appRole=AppRole.builder().
        role(role).
               build();

       return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {

    AppUser appUser= userRepository.findAppUserByUsername(username);
    AppRole appRole=roleRepository.findById(role).get();
    appUser.getRoles().add(appRole);






    }

    @Override
    public void removeRoleFromUser(String username, String role) {

        AppUser appUser= userRepository.findAppUserByUsername(username);
        AppRole appRole=roleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUserName(String username) {
       return  userRepository.findAppUserByUsername(username);
    }

    @Override
    public AppRole loadRole(String role) {
        return roleRepository.findById(role).orElse(null);
    }
}
