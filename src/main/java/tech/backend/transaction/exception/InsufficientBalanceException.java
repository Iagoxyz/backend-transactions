package tech.backend.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends TransactionException {
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insuffcient balance");
        pb.setDetail("You cannot transfer a value bigger than your current balance");

        return pb;
    }
}
