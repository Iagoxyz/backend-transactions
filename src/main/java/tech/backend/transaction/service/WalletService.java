package tech.backend.transaction.service;

import org.springframework.stereotype.Service;
import tech.backend.transaction.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public
}
