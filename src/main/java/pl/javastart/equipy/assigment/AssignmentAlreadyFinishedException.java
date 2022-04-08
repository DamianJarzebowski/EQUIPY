package pl.javastart.equipy.assigment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "To przypisanie już zostało zakończone")
class AssignmentAlreadyFinishedException extends RuntimeException { }