package hu.tamasireka03.martiangame.service;

import hu.tamasireka03.martiangame.dto.UserDTO;
import hu.tamasireka03.martiangame.entities.User;
import hu.tamasireka03.martiangame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Új felhasználó regisztrációja
    public UserDTO registerUser(String username, String martianName, String rawPassword){
        if (userRepository.findByMartianName(martianName).isPresent()) {
            throw new IllegalArgumentException("Ez a marslakó név már foglalt!");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Ez a felhasználónév már foglalt!");
        }

        User newUser = User.builder()
                .username(username)
                .martianName(martianName)
                .password(passwordEncoder.encode(rawPassword))
                .chocolateCount(0)
                .build();

        User savedUser = userRepository.save(newUser);

        return new UserDTO(savedUser.getUserid(), savedUser.getUsername(), savedUser.getMartianName(), savedUser.getChocolateCount());
    }

    // Felhasználó bejelentkezése
    public UserDTO loginUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Nincs ilyen felhasználó!"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Hibás jelszó!");
        }

        return new UserDTO(user.getUserid(), user.getUsername(), user.getMartianName(), user.getChocolateCount());
    }

    // Csoki darabjának lekérdezése
    public int getChocolate(Long userId){
        return userRepository.findById(userId)
                .map(User::getChocolateCount)
                .orElseThrow(() -> new IllegalArgumentException("Felhasználó nem található"));
    }

    // Csoki hozzáadása
    public void addChocolate(Long userId, int amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("A csoki mennyiségének pozitívnak kell lennie!");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Felhasználó nem található"));
        user.setChocolateCount(user.getChocolateCount() + amount);
        userRepository.save(user);
    }
}