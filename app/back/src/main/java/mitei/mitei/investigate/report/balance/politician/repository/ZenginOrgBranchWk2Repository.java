package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;

/**
 * zengin_org_branch_master接続用Repository
 */
public interface ZenginOrgBranchWk2Repository extends JpaRepository<ZenginOrgBranchWk2Entity, Integer> {

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

    /**
     * 追加となった支店を検索する
     *
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM zengin_org_branch_wk2 " + "WHERE zengin_org_branch_wk2_name NOT IN "
            + "( SELECT zengin_org_tempo_master_name FROM zengin_org_branch_master WHERE org_number BETWEEN 200 AND 999)", nativeQuery = true)
    Page<ZenginOrgBranchWk2Entity> findAddBranch(Pageable pageable);

    /**
     * 異動となった支店を検索する
     *
     * @return 検索結果
     */
    @Query(value = "SELECT "
            + "zengin_org_tempo_master_id AS zengin_org_branch_wk2_id,"
            + "zengin_org_tempo_master_code AS zengin_org_branch_wk2_code,"
            + "zengin_org_branch_wk2_name,wk.saishin_kbn,wk.org_code,wk.branch_code,wk.org_name_kana,wk.org_name,wk.branch_name_kana,wk.branch_name,wk.postal_code,wk.branch_address,wk.phon_number,wk.bill_exchange_number,wk.order_code,wk.flg_naikoku_kawase,wk.insert_user_id,wk.insert_user_code,wk.insert_user_name,wk.insert_timestamp,wk.update_user_id,wk.update_user_code,wk.update_user_name,wk.update_timestamp FROM zengin_org_branch_wk2 AS wk "
            + "INNER JOIN zengin_org_branch_master AS master ON wk.zengin_org_branch_wk2_name = master.zengin_org_tempo_master_name "
            + "WHERE master.org_number BETWEEN 200 AND 999 AND "
            + "(wk.postal_code <> master.postal_code OR wk.branch_address <> master.branch_address OR wk.phon_number <> master.phon_number)", nativeQuery = true)
    Page<ZenginOrgBranchWk2Entity> findMoveBranch(Pageable pageable);

}
