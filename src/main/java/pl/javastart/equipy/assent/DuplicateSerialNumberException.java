package pl.javastart.equipy.assent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Urządzenie z takim numerem seryjnym już istnieje")
public class DuplicateSerialNumberException extends RuntimeException {
}
