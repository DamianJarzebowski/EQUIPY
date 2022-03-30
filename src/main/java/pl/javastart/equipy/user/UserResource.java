package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    UserResource(UserService userService) {
        this.userService = userService;
    }

    // Request Param co to tu robi czym jest required (Sprawia, ze parametr jest domyślnie nieobecny?)
    @GetMapping("")
    public List<UserDto> findAll(@RequestParam(required=false) String lastName) {
        if (lastName != null)
            return userService.findByLastName(lastName);
        else
            return userService.findAll();
    }

    // Co to jest to uri i servleturi...
    @PostMapping("")
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        if (user.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
        UserDto savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

}
