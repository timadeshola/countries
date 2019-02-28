package com.countries.core.installer;

import com.countries.jpa.entity.Country;
import com.countries.jpa.entity.Role;
import com.countries.jpa.entity.User;
import com.countries.jpa.repository.CountryRepository;
import com.countries.jpa.repository.RoleRepository;
import com.countries.jpa.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Profile("staging")
public class DefaultInstaller implements ApplicationListener<ContextRefreshedEvent> {

    private Boolean alreadySetup = Boolean.FALSE;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CountryRepository countryRepository;

    public DefaultInstaller(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryRepository = countryRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        final Role admin = createRoleIfNotFound("ADMIN");
        final Role user = createRoleIfNotFound("USER");
        final Role guest = createRoleIfNotFound("GUEST");

        final Set<Role> adminRole = new HashSet<>(Arrays.asList(admin, user, guest));
        final Set<Role> userRole = new HashSet<>(Collections.singletonList(user));
        final Set<Role> guestRole = new HashSet<>(Collections.singletonList(guest));

        createUserIfNotFound("timadeshola", "timadeshola@gmail.com", "John", "Adeshola", "Password@123",  adminRole);
        createUserIfNotFound("user", "user@example.com", "Paul", "Essien", "Password@123", userRole);

//        createCountryIfNotExist("Afghanistan", "Asia");
//        createCountryIfNotExist("Albania", "Europe");
//        createCountryIfNotExist("Algeria", "Africa");
//        createCountryIfNotExist("Andorra", "Europe");
//        createCountryIfNotExist("Angola", "Africa");
//        createCountryIfNotExist("Anguilla", "Americas");
//        createCountryIfNotExist("Argentina", "Americas");
//        createCountryIfNotExist("Australia", "Oceania");
//        createCountryIfNotExist("Austria", "Europe");
//        createCountryIfNotExist("Benin", "Africa");
//        createCountryIfNotExist("Nigeria", "Africa");
//        createCountryIfNotExist("Burkina Faso", "Africa");
//        createCountryIfNotExist("United Kingdom", "Europe");
//        createCountryIfNotExist("United States of America", "Americas");
//        createCountryIfNotExist("Ghana", "Africa");
//        createCountryIfNotExist("Cameroon", "Africa");
//        createCountryIfNotExist("Uruguay", "Americas");
//        createCountryIfNotExist("Africa", "Africa");
//        createCountryIfNotExist("Ukraine", "Europe");
//        createCountryIfNotExist("Turkey", "Asia");

        alreadySetup = Boolean.TRUE;
    }

    @Transactional
    protected Role createRoleIfNotFound(final String name) {
        Optional<Role> roleCheck = roleRepository.findRoleByName(name);
        if (roleCheck.isPresent()) {
            return roleCheck.get();
        }
        Role role = new Role();
        role.setName(name);
        role.setStatus(true);
        roleRepository.save(role);
        return role;
    }

    @Transactional
    protected void createUserIfNotFound(final String username, final String email, final String firstName, final String lastName, final String password, final Set<Role> roles) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setStatus(true);
        user.setRoles(roles);
        user.setStatus(true);
        userRepository.save(user);
    }

    @Transactional
    protected void createCountryIfNotExist(String name, String continent) {
        Optional<Country> optionalCountry = countryRepository.findByName(name);
        if(optionalCountry.isPresent()) {
            return;
        }
        Country country = new Country();
        country.setName(name);
        country.setContinent(continent);
        countryRepository.save(country);
    }
}
