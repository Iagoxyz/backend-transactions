package tech.backend.transaction.service;

import org.springframework.stereotype.Service;
import tech.backend.transaction.client.AuthorizationClient;
import tech.backend.transaction.entity.Transfer;
import tech.backend.transaction.exception.TransactionException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transfer transfer) {

        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new TransactionException();
        }

        return resp.getBody().authorized();
    }
}
