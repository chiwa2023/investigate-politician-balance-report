package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;

/**
 * zengin_org_branch_master接続用Repository
 */
public interface ZenginOrgBranchMasterRepository extends JpaRepository<ZenginOrgBranchMasterEntity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_master WHERE saishin_kbn= 1 AND MATCH(zengin_org_branch_master_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<ZenginOrgBranchMasterEntity> findFullText(String searchWords);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return optional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ZenginOrgBranchMasterEntity> findFirstByOrderByZenginOrgTempoMasterCodeDesc();

    /**
     * 全銀金融機関支店ワークテーブル1と比較して廃止対象を抽出する
     *
     * @param pageable ページングDto
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_master" // NOPMD
            + "    WHERE  org_number BETWEEN 0 AND 199 AND" + "                 zengin_org_tempo_master_name NOT IN (" // NOPMD
            + "        SELECT  zengin_org_branch_wk1_name FROM zengin_org_branch_wk1)", nativeQuery = true)
    Page<ZenginOrgBranchMasterEntity> findDelteBranchWkTbl1(Pageable pageable);

    /**
     * 全銀金融機関支店ワークテーブル2と比較して廃止対象を抽出する
     *
     * @param pageable ページングDto
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_master" + "    WHERE  org_number BETWEEN 200 AND 999 AND"
            + "                 zengin_org_tempo_master_name NOT IN ("
            + "        SELECT  zengin_org_branch_wk2_name FROM zengin_org_branch_wk2)", nativeQuery = true)
    Page<ZenginOrgBranchMasterEntity> findDelteBranchWkTbl2(Pageable pageable);

    /**
     * 全銀金融機関支店ワークテーブル3と比較して廃止対象を抽出する
     *
     * @param pageable ページングDto
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_master" + "    WHERE  org_number BETWEEN 1000 AND 2999 AND"
            + "                 zengin_org_tempo_master_name NOT IN ("
            + "        SELECT  zengin_org_branch_wk3_name FROM zengin_org_branch_wk3)", nativeQuery = true)
    Page<ZenginOrgBranchMasterEntity> findDelteBranchWkTbl3(Pageable pageable);

    /**
     * 全銀金融機関支店ワークテーブル4と比較して廃止対象を抽出する
     *
     * @param pageable ページングDto
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_master" + "    WHERE  org_number BETWEEN 3000 AND 9999 AND"
            + "                 zengin_org_tempo_master_name NOT IN ("
            + "        SELECT  zengin_org_branch_wk4_name FROM zengin_org_branch_wk4)", nativeQuery = true)
    Page<ZenginOrgBranchMasterEntity> findDelteBranchWkTbl4(Pageable pageable);

    
    /**
     * 最新かつ金融機関名+店舗名が一致したマスタデータを検索する
     *
     * @param name 名称
     * @param saishinKbn 最新区分
     * @return 検索結果
     */
    List<ZenginOrgBranchMasterEntity> findByZenginOrgTempoMasterNameAndSaishinKbn(String name,Integer saishinKbn);
}
