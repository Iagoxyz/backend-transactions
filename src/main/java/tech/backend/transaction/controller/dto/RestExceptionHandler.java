package tech.backend.transaction.controller.dto;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.backend.transaction.exception.TransactionException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ProblemDetail handleTransactionException(TransactionException e) {
        return e.toProblemDetail();
    }
}
