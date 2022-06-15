package pl.javastart.equipy.user;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.assigment.AssignmentDto;
import pl.javastart.equipy.assigment.AssignmentMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    List<String> map(List<Integer> items) {
        return items
                .stream()
                .map(i -> i.toString())
                .toList();
    }

    List<UserDto> findByLastName(String lastName) {
        return userRepository.findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::toDto)
                .toList();
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

    List<AssignmentDto> getUserAssignments(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAssignments)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(AssignmentMapper::toDto)
                .toList();
    }



}
