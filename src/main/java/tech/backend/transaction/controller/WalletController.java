package tech.backend.transaction.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.backend.transaction.controller.dto.CreateWalletDto;
import tech.backend.transaction.entity.Wallet;
import tech.backend.transaction.service.WalletService;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {
            var wallet = walletService.createWallet(dto);
            return ResponseEntity.ok(wallet);
    }

}
