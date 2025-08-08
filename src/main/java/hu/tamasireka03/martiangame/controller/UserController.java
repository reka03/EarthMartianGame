package hu.tamasireka03.martiangame.controller;

import hu.tamasireka03.martiangame.dto.UserDTO;
import hu.tamasireka03.martiangame.entities.User;
import hu.tamasireka03.martiangame.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController( final UserService userService ) {
        this.userService = userService;
    }

    //Regisztráció
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String username,
                                             @RequestParam String martianName,
                                             @RequestParam String password){
        return ResponseEntity.ok(userService.registerUser(username, martianName, password));
    }

    //bejelentkezés
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String martianName,
                                       @RequestParam String password){
        try{
            UserDTO user = userService.loginUser(martianName, password);

            UserDTO dto = new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getMartianName(),
                    user.getChocolate()
            );

            return ResponseEntity.ok(dto);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Csoki lekérdezése
    @GetMapping("/{id}/chocolate")
    public ResponseEntity<Integer> getChocolate( @PathVariable Long id ){
        return ResponseEntity.ok(userService.getChocolate(id));
    }

    //Csoki hozzáadása
    @PostMapping("{id}/chocolate")
    public ResponseEntity<String> addChocolate(@PathVariable Long id, @RequestParam int amount){
        userService.addChocolate(id, amount);
        return ResponseEntity.ok("Csoki hozzáadva!");
    }
}
