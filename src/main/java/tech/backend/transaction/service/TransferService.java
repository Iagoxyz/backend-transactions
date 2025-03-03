package tech.backend.transaction.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import tech.backend.transaction.controller.dto.TransferDto;
import tech.backend.transaction.entity.Transfer;
import tech.backend.transaction.entity.Wallet;
import tech.backend.transaction.exception.*;
import tech.backend.transaction.repository.TransferRepository;
import tech.backend.transaction.repository.WalletRepository;

import java.math.BigDecimal;

@Service
public class TransferService {

    private TransferRepository transferRepository;
    private AuthorizationService authorizationService;
    private NotificationService notificationService;
    private WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, AuthorizationService authorizationService, NotificationService notificationService, WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer()).orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));
        var receiver = walletRepository.findById(transferDto.payee()).orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        valitedTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        return transferResult;
    }


    public void valitedTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletType();
        }

        if (!sender.isBalanceBiggerThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
