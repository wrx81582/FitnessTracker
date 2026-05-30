package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper konwertujący encję User na obiekty DTO.
 */
@Component
class UserMapper {

    UserDto.UserFullDto toFullDto(User user) {
        return new UserDto.UserFullDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    UserDto.UserSimpleDto toSimpleDto(User user) {
        return new UserDto.UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    UserDto.UserEmailDto toEmailDto(User user) {
        return new UserDto.UserEmailDto(
                user.getId(),
                user.getEmail()
        );
    }

    User toEntity(UserDto.UserRequestDto dto) {
        return new User(
                dto.firstName(),
                dto.lastName(),
                dto.birthdate(),
                dto.email()
        );
    }
}