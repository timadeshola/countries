package com.countries.core.installer;

import com.countries.jpa.entity.Country;
import com.countries.jpa.entity.Role;
import com.countries.jpa.entity.User;
import com.countries.jpa.repository.CountryRepository;
import com.countries.jpa.repository.RoleRepository;
import com.countries.jpa.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DefaultInstaller implements ApplicationListener<ContextRefreshedEvent> {

    private Boolean alreadySetup = Boolean.FALSE;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    public DefaultInstaller(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CountryRepository countryRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
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

        createCountryIfNotExist("Afghanistan", "Asia", "AF");
        createCountryIfNotExist("Albania", "Europe", "AL");
        createCountryIfNotExist("Algeria", "Africa", "AG");
        createCountryIfNotExist("Andorra", "Europe", "AD");
        createCountryIfNotExist("Angola", "Africa", "AO");
        createCountryIfNotExist("Anguilla", "Americas", "AI");
        createCountryIfNotExist("Argentina", "Americas", "AR");
        createCountryIfNotExist("Australia", "Oceania", "AU");
        createCountryIfNotExist("Austria", "Europe", "AT");
        createCountryIfNotExist("Benin", "Africa", "BJ");
        createCountryIfNotExist("Nigeria", "Africa", "NGR");
        createCountryIfNotExist("Burkina Faso", "Africa", "BF");
        createCountryIfNotExist("United Kingdom", "Europe", "UK");
        createCountryIfNotExist("United States of America", "Americas", "USA");
        createCountryIfNotExist("Ghana", "Africa", "GH");
        createCountryIfNotExist("Cameroon", "Africa", "CM");
        createCountryIfNotExist("Uruguay", "Americas", "UY");
        createCountryIfNotExist("Africa", "Africa", "UG");
        createCountryIfNotExist("Ukraine", "Europe", "UA");
        createCountryIfNotExist("Turkey", "Asia", "TR");

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
    protected void createCountryIfNotExist(String name, String continent, String code) {
        Optional<Country> optionalBook = countryRepository.findByName(name);
        if(optionalBook.isPresent()) {
            return;
        }
        Country country = new Country();
        country.setName(name);
        country.setContinent(continent);
        country.setCode(code);
        countryRepository.save(country);
    }
}
