package tech.backend.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.transaction.entity.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCpnj, String email);
}
