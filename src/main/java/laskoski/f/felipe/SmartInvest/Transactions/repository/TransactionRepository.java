package laskoski.f.felipe.SmartInvest.Transactions.repository;

import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAssetCode(String code, Pageable page);
    List<Transaction> findByAssetId(Long symbol);
}
