package mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0801And0807Report2025Entity;

/**
 * offering_party_usage_0801_and_0807_report_2025接続用Repository
 */
public interface OfferingPartyUsage0801And0807Report2025Repository  extends JpaRepository<OfferingPartyUsage0801And0807Report2025Entity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_party_usage_0801_and_0807_report_2025 WHERE saishin_kbn= 1 AND MATCH(offering_party_usage_0801_and_0807_report_2025_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<OfferingPartyUsage0801And0807Report2025Entity> findFullText(String searchWords);
    
    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingPartyUsage0801And0807Report2025Entity> findFirstByOrderByPartyUsage0801And0807ReportCodeDesc();
    
    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param partyUsage0801And0807ReportCode 使途報告書様式8その1と7同一識別コード
     * @return データリスト
     */
    List<OfferingPartyUsage0801And0807Report2025Entity> findByPartyUsage0801And0807ReportCodeOrderByPartyUsage0801And0807ReportId(Long partyUsage0801And0807ReportCode);

    /**
     * 最新かつ政治団体Idと提出日が一致するデータを抽出する
     *
     * @param saishinKbn 最新区分
     * @param politicalOrgCode 政治団体同一識別コード
     * @param offeringDate 提出日
     * @return データリスト
     */
    List<OfferingPartyUsage0801And0807Report2025Entity> findBySaishinKbnAndPoliticalOrganizationCodeAndOfferingDate(Integer saishinKbn,Integer politicalOrgCode,LocalDate offeringDate);

}
