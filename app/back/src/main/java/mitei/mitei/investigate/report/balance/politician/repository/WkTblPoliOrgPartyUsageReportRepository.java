package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgPartyUsageReportEntity;

/**
 * wk_tbl_poli_org_party_usage_report接続用Repository
 */
public interface WkTblPoliOrgPartyUsageReportRepository
        extends JpaRepository<WkTblPoliOrgPartyUsageReportEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_poli_org_party_usage_report WHERE saishin_kbn= 1 AND MATCH(wk_tbl_poli_org_party_usage_report_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<WkTblPoliOrgPartyUsageReportEntity> findFullText(String searchWords);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return allBookDto
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WkTblPoliOrgPartyUsageReportEntity> findFirstByOrderByWkTblPoliOrgPartyUsageReportCodeDesc();

    /**
     * 該当する政治団体を指定して検索する(未指定0検出用)
     *
     * @param saishinKbn 最新区分
     * @param poliOrgId  政治団体Id
     * @return ワークテーブルリスト検索結果
     */
    List<WkTblPoliOrgPartyUsageReportEntity> findBySaishinKbnAndPoliticalOrganizationId(Integer saishinKbn,
            Long poliOrgId);

    /**
     * 該当する政治団体を指定して検索する(政治団体指定済検出用)
     *
     * @param saishinKbn 最新区分
     * @param poliOrgId  政治団体Id
     * @return ワークテーブルリスト検索結果
     */
    List<WkTblPoliOrgPartyUsageReportEntity> findBySaishinKbnAndPoliticalOrganizationIdNot(Integer saishinKbn,
            Long poliOrgId);

    /**
     * 該当する政治団体を指定して検索する(政治団体指定済検出用)
     *
     * @param saishinKbn 最新区分
     * @param poliOrgId  政治団体Id
     * @return ワークテーブルリスト検索結果
     */
    Page<WkTblPoliOrgPartyUsageReportEntity> findBySaishinKbnAndPoliticalOrganizationIdNot(Integer saishinKbn,
            Long poliOrgId, Pageable pageable);

    /**
     * 新規登録のみ抽出
     *
     * @param saishinKbn 最新区分
     * @return ワークテーブルリスト検索結果
     */
    Page<WkTblPoliOrgPartyUsageReportEntity> findBySaishinKbn(Integer saishinKbn, Pageable pageable);

}
