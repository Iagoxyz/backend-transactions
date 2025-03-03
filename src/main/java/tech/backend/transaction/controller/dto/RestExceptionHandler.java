package tech.backend.transaction.controller.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.backend.transaction.exception.TransactionException;

import java.util.List;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ProblemDetail handleTransactionException(TransactionException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErrors = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setDetail("Your request parameters didn't validate");
        pb.setProperty("invalid-params", fieldErrors);

        return pb;
    }

    private record InvalidParam(String name, String reason) {}
}
