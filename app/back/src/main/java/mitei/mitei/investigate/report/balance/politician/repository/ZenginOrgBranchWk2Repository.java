package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;

/**
 * zengin_org_branch_master接続用Repository
 */
public interface ZenginOrgBranchWk2Repository  extends JpaRepository<ZenginOrgBranchWk2Entity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_Wk2 WHERE saishin_kbn= 1 AND MATCH(zengin_org_branch_Wk2_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<ZenginOrgBranchMasterEntity> findFullText(String searchWords);


    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return optional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ZenginOrgBranchWk2Entity> findFirstByOrderByZenginOrgBranchWk2CodeDesc();

    /**
     * idを初期化する
     */
    @Modifying
    @Query(value = "ALTER TABLE zengin_org_branch_wk2 auto_increment = 1", nativeQuery = true)
    void initialId();

}
