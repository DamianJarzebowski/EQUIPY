package pl.javastart.equipy.user;

public class UserMapper {

    static  UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
    }
}
