package pl.javastart.equipy.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    List<String> map(List<Integer> items) {
        return items
                .stream()
                .map(i -> i.toString())
                .collect(Collectors.toList());
    }

    List<UserDto> findByLastName(String lastName) {
        return userRepository.findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    UserDto save(UserDto userDto) {
        Optional<User> userByPesel = userRepository.findByPesel(userDto.getPesel());
        /*
        if (userByPesel.isPresent()) {
            throw new DuplicatePeselException();
        }
         */
        userByPesel.ifPresent(u -> {
            throw  new DuplicatePeselException();
        });
        return mapAndSaveUser(userDto);
    }

    UserDto update(UserDto userDto) {
        Optional<User> userByPesel = userRepository.findByPesel(userDto.getPesel());
        userByPesel.ifPresent(u -> {
            if(u.getId().equals(userDto.getId()))
                throw new DuplicatePeselException();
        });
        return mapAndSaveUser(userDto);
    }

    UserDto mapAndSaveUser(UserDto userDto) {
        User userEntity = UserMapper.toEntity(userDto);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }



}
