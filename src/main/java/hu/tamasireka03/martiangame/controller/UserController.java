package hu.tamasireka03.martiangame.controller;

import hu.tamasireka03.martiangame.dto.UserDTO;
import hu.tamasireka03.martiangame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO register(@RequestParam String username,
                            @RequestParam String martianName,
                            @RequestParam String password) {
        return userService.registerUser(username, martianName, password);
    }

    @PostMapping("/")
    public UserDTO login(@RequestParam String username,
                         @RequestParam String password) {
        return userService.loginUser(username, password);
    }

    @GetMapping("/{userId}/chocolate")
    public int getChocolate(@PathVariable Long userId) {
        return userService.getChocolate(userId);
    }

    @PostMapping("/{userId}/chocolate/add")
    public void addChocolate(@PathVariable Long userId,
                             @RequestParam int amount) {
        userService.addChocolate(userId, amount);
    }
}