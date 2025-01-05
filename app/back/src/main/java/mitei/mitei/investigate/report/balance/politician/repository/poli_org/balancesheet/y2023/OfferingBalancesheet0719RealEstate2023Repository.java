package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheet0719RealEstate2023Entity;

/**
 * offering_balancesheet_0719_real_estate_2023接続用Repository
 */
public interface OfferingBalancesheet0719RealEstate2023Repository  extends JpaRepository<OfferingBalancesheet0719RealEstate2023Entity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_0719_real_estate_2023 WHERE saishin_kbn= 1 AND MATCH(offering_balancesheet_0719_real_estate_2023_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<OfferingBalancesheet0719RealEstate2023Entity> findFullText(String searchWords);

    
    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheet0719RealEstate2023Entity> findFirstByOrderByOfferingBalancesheet0719RealEstateCodeDesc();
    
    /**
     * 文書同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 文書同一識別コード
     * @return データリスト
     */
    List<OfferingBalancesheet0719RealEstate2023Entity> findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(Long documentCode);

}
