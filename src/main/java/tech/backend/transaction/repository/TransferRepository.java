package tech.backend.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.transaction.entity.Transfer;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
