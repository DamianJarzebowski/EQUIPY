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

    // Co tutaj sie dokładnie zadziało (co sie dzieje na wywołanej liscie)
    List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    // Dlaczego po 2 próbie wyszukiwania wyskakuje wyjątek?
    List<UserDto> findByLastName(String lastName) {
        return userRepository.findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    //  Nie jestem pewien czy dobrze rozumiem te metode jest strasznie skrótowa jak ona przelatuje baze danych i sprawdza czy ten pesel juz istnieje i jak to jest z obśługiwaniem tego wyjątku przez responsestatus
    UserDto save(UserDto userDto) {
        Optional<User> userByPesel = userRepository.findByPesel(userDto.getPesel());
        userByPesel.ifPresent(u -> {
            throw  new DuplicatePeselException();
        });
        User userEntity = UserMapper.toEntity(userDto);
        User savedUser = userRepository.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

}
