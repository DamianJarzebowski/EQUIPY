package pl.javastart.equipy.user;

public class UserMapper {

    static  UserDto toDto(User user) {
        var userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPesel(user.getPesel());
        return userDto;
    }

    static User toEntity(UserDto userDto) {
        var user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPesel(user.getPesel());
        return user;
    }
}
