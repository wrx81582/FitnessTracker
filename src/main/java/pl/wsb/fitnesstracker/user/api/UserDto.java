package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public class UserDto {

    public record UserFullDto(
            @Nullable Long id,
            String firstName,
            String lastName,
            @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
            String email
    ) {}

    public record UserSimpleDto(
            @Nullable Long id,
            String firstName,
            String lastName
    ) {}

    public record UserEmailDto(
            Long id,
            String email
    ) {}

    public record UserRequestDto(
            String firstName,
            String lastName,
            @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
            String email
    ) {}
}