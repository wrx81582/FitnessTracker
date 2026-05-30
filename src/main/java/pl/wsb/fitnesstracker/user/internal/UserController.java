package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * Kontroler REST dla operacji CRUD na użytkownikach.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /** GET /v1/users — pełna lista użytkowników */
    @GetMapping
    public List<UserDto.UserFullDto> getAllUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toFullDto)
                .toList();
    }

    /** GET /v1/users/simple — lista z samym imieniem i nazwiskiem */
    @GetMapping("/simple")
    public List<UserDto.UserSimpleDto> getSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /** GET /v1/users/{id} — szczegóły po ID */
    @GetMapping("/{id}")
    public UserDto.UserFullDto getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toFullDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    /** GET /v1/users/email?email=... — wyszukiwanie po emailu */
    @GetMapping("/email")
    public List<UserDto.UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userService.findUsersByEmailFragment(email).stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /** GET /v1/users/older/{time} — użytkownicy starsi niż podana data */
    @GetMapping("/older/{time}")
    public List<UserDto.UserFullDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findUsersOlderThan(time).stream()
                .map(userMapper::toFullDto)
                .toList();
    }

    /** POST /v1/users — tworzenie użytkownika */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto.UserFullDto createUser(@RequestBody UserDto.UserRequestDto request) {
        return userMapper.toFullDto(
                userService.createUser(userMapper.toEntity(request))
        );
    }

    /** PUT /v1/users/{userId} — aktualizacja użytkownika */
    @PutMapping("/{userId}")
    public UserDto.UserFullDto updateUser(@PathVariable Long userId,
                                          @RequestBody UserDto.UserRequestDto request) {
        return userMapper.toFullDto(
                userService.updateUser(userId, userMapper.toEntity(request))
        );
    }

    /** DELETE /v1/users/{userId} — usunięcie użytkownika */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}