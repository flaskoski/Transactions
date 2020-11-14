package laskoski.f.felipe.SmartInvest.Transactions.repository;

import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Page<Asset> findByCode(String code, Pageable page);
}
