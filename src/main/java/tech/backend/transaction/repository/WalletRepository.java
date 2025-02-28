package tech.backend.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.transaction.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
