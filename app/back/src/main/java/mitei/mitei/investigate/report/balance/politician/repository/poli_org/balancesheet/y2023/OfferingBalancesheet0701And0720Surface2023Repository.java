package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheet0701And0720Surface2023Entity;

/**
 * offering_balancesheet_0701_and_0720_surface_2023接続用Repository
 */
public interface OfferingBalancesheet0701And0720Surface2023Repository  extends JpaRepository<OfferingBalancesheet0701And0720Surface2023Entity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_0701_and_0720_surface_2023 WHERE saishin_kbn= 1 AND MATCH(offering_balancesheet_0701_and_0720_surface_2023_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<OfferingBalancesheet0701And0720Surface2023Entity> findFullText(String searchWords);

    
    
    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheet0701And0720Surface2023Entity> findFirstByOrderByOfferingBalancesheet0701And0720SurfaceCodeDesc();
    
    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param partyUsage0801And0807ReportCode 使途報告書様式8その1と7同一識別コード
     * @return データリスト
     */
    List<OfferingBalancesheet0701And0720Surface2023Entity> findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(Long partyUsage0801And0807ReportCode);

    /**
     * 最新かつ政治団体Idと提出日が一致するデータを抽出する
     *
     * @param saishinKbn 最新区分
     * @param politicalOrgCode 政治団体同一識別コード
     * @param offeringDate 提出日
     * @return データリスト
     */
    List<OfferingBalancesheet0701And0720Surface2023Entity> findBySaishinKbnAndPoliticalOrganizationCodeAndOfferingDate(Integer saishinKbn,Integer politicalOrgCode,LocalDate offeringDate);

    /**
     * 政治団体同一識別コードが一致する最新データを取得する
     *
     * @param politicalOrgCode 政治団体同一識別コード
     * @param saishinKbn 最新区分
     * @return 表紙データ
     */
    Optional<OfferingBalancesheet0701And0720Surface2023Entity> findFirstByPoliticalOrganizationCodeAndSaishinKbnOrderByOfferingDateDesc(Integer politicalOrgCode,Integer saishinKbn);

}
