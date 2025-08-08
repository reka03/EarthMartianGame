package hu.tamasireka03.martiangame.service;

import hu.tamasireka03.martiangame.dto.UserDTO;
import hu.tamasireka03.martiangame.entities.User;
import hu.tamasireka03.martiangame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Új felhasználó regisztrációja
    public User registerUser(String username, String martianName, String rawPassword){
        if (userRepository.findByMartianName(martianName).isPresent()) {
            throw new RuntimeException("Ez a marslakó név már foglalt!");
        }

        User newUser = User.builder()
                .username(username)
                .martianName(martianName)
                .password(passwordEncoder.encode(rawPassword))
                .chocolate(0)
                .build();

        return userRepository.save(newUser);
    }

    //Felhasználó bejelentkezése
    public UserDTO loginUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Nincs ilyen felhasználó!"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Hibás jelszó!");
        }

        return new UserDTO(user.getId(), user.getUsername(), user.getMartianName(), user.getChocolate());
    }

    //csoki darabjának lekérdezése
    public int getChocolate(Long userId){
        return userRepository.findById(userId)
                .map(User::getChocolate)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));
    }

    //csoki hozzáadása
    public void addChocolate(Long userId, int amount){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));
        user.setChocolate(user.getChocolate() + amount);
        userRepository.save(user);
    }
}
