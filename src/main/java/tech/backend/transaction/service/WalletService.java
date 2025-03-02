package tech.backend.transaction.service;

import org.springframework.stereotype.Service;
import tech.backend.transaction.controller.dto.CreateWalletDto;
import tech.backend.transaction.entity.Wallet;
import tech.backend.transaction.exception.WalletDataAlreadyExistsException;
import tech.backend.transaction.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var walletdb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletdb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

       return walletRepository.save(dto.toWallet());
    }

}
