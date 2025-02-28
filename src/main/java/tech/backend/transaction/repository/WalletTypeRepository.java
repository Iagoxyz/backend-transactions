package tech.backend.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.transaction.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
