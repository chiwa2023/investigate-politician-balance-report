package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage01Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage02Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage03Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage04Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage05Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage06Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage07Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage08Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage09Dto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;

/**
 * wk_tbl_ukai_kenkin接続用Repository
 */
public interface WkTblUkaiKenkinRepository extends JpaRepository<WkTblUkaiKenkinEntity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE saishin_kbn= 1 AND MATCH(wk_tbl_ukai_kenkin_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findFullText(String searchWords);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return allBookDto
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WkTblUkaiKenkinEntity> findFirstByOrderByWkTblUkaiKenkinCodeDesc();

    /**
     * ユーザ同一識別コード条件でデータを削除する
     *
     * @param userCode ユーザ同一識別コード
     */
    @Modifying
    @Query(value = "DELETE FROM wk_tbl_ukai_kenkin WHERE insert_user_code = ?1", nativeQuery = true)
    void deleteByInsertUserCode(Integer userCode);

    /**
     * 政治団体同一識別コードリストから該当取引を抽出する
     *
     * @param userCode       操作者同一識別コード
     * @param listPoliOrg    政治団体同一識別コードリスト
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND (youshiki_eda_kbn = 0 OR youshiki_eda_kbn = 3)  AND trading_partner_code IN (?2)", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findTradingByRelationPoliOrg(Integer userCode,
            List<Integer> listPoliOrg);

    /**
     * 個人データから取引を行った関連者個人同一識別コードを重複なしで取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 関連者個人同一識別コード
     */
    @Query(value = "SELECT DISTINCT trading_partner_id,trading_partner_code,trading_partner_name FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND youshiki_eda_kbn = 1", nativeQuery = true)
    List<Tuple3<Long, Integer, String>> findTradingPartnerCode(Integer userCode);

    /**
     * 企業データから取引を行った企業・団体の代表者関連者個人同一識別コードを重複なしで取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 企業・団体の代表者関連者個人同一識別コード
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_partner_delegate_id,trading_partner_delegate_code,trading_partner_delegate_name FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND youshiki_eda_kbn = 2", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingDelegateCode(Integer userCode);

    /**
     * 個人取引データの同一識別コードから同一の関連者を保持する企業・団体と政治団体を抜き出す
     * 取引者の同一識別コードが同じデータは検索者本人データなので除かなくてはならない
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param personCode 取引相手関連者同一識別コード
     * @return 関連者が含まれる寄付データ
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND ("
            + " trading_partner_delegate_code = ?2" + " OR trading_org_account_manager_code = ?2"
            + " OR trading_org_shikin_dantai_code = ?2" + " OR trading_org_kokkai_giin1_code = ?2"
            + " OR trading_org_kokkai_giin2_code = ?2" + " OR trading_org_kokkai_giin3_code = ?2"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findCorpAndPoriOrgByKojin(Integer userCode, Integer personCode);

    /**
     * 企業・団体の代表者の関連者が同じデータを抽出する
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param corpCode   除くべき企業・団体同一識別コード
     * @param personCode 関連者同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1" + " AND trading_partner_code != ?2 "
            + " AND (" + " trading_partner_code = ?3" + " OR trading_partner_delegate_code = ?3"
            + " OR trading_org_account_manager_code = ?3" + " OR trading_org_shikin_dantai_code = ?3"
            + " OR trading_org_kokkai_giin1_code = ?3" + " OR trading_org_kokkai_giin2_code = ?3"
            + " OR trading_org_kokkai_giin3_code = ?3" + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findDataByKigyouDaihyousha(Integer userCode, Integer corpCode, Integer personCode);

    
    
    @Query(value = "WITH poli_code_01 AS ("
            + "    SELECT DISTINCT political_org_code AS code01 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code = ?2"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage01Dto> findRouteStage01(Integer userCode,Integer poliOrgCode);

    @Query(value = "WITH poli_code_02 AS ("
            + "    SELECT DISTINCT political_org_code AS code09 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN = ?2"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage02Dto> findRouteStage02(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_03 AS ("
            + "    SELECT DISTINCT political_org_code AS code03 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code = ?2"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage03Dto> findRouteStage03(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_04 AS ("
            + "    SELECT DISTINCT political_org_code AS code04 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 4 AND trading_partner_code = ?2"
            + " ),"
            + " poli_code_03 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code03 FROM wk_tbl_ukai_kenkin,poli_code_04"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code IN (poli_code_04.code04)"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT code00,code01,code02,code03,code04 FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03,poli_code_04"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage04Dto> findRouteStage04(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_05 AS ("
            + "    SELECT DISTINCT political_org_code AS code05 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 5 AND trading_partner_code = ?2"
            + " ),"
            + " poli_code_04 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code04 FROM wk_tbl_ukai_kenkin,poli_code_05"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 4 AND trading_partner_code IN (poli_code_05.code05)"
            + " ),"
            + " poli_code_03 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code03 FROM wk_tbl_ukai_kenkin,poli_code_04"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code IN (poli_code_04.code04)"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03,poli_code_04"
            + "             ,poli_code_05"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage05Dto> findRouteStage05(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_06 AS ("
            + "    SELECT DISTINCT political_org_code AS code06 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 6 AND trading_partner_code = ?2"
            + " ),"
            + "poli_code_05 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code05 FROM wk_tbl_ukai_kenkin,poli_code_06"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 5 AND trading_partner_code IN (poli_code_06.code06)"
            + " ),"
            + " poli_code_04 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code04 FROM wk_tbl_ukai_kenkin,poli_code_05"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 4 AND trading_partner_code IN (poli_code_05.code05)"
            + " ),"
            + " poli_code_03 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code03 FROM wk_tbl_ukai_kenkin,poli_code_04"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code IN (poli_code_04.code04)"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03,poli_code_04"
            + "             ,poli_code_05,poli_code_06"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage06Dto> findRouteStage06(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_07 AS ("
            + "    SELECT DISTINCT political_org_code AS code07 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 7 AND trading_partner_code = ?2"
            + " ),"
            + "poli_code_06 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code06 FROM wk_tbl_ukai_kenkin,poli_code_07"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 6 AND trading_partner_code IN (poli_code_07.code07)"
            + " ),"
            + "poli_code_05 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code05 FROM wk_tbl_ukai_kenkin,poli_code_06"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 5 AND trading_partner_code IN (poli_code_06.code06)"
            + " ),"
            + " poli_code_04 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code04 FROM wk_tbl_ukai_kenkin,poli_code_05"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 4 AND trading_partner_code IN (poli_code_05.code05)"
            + " ),"
            + " poli_code_03 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code03 FROM wk_tbl_ukai_kenkin,poli_code_04"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code IN (poli_code_04.code04)"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03,poli_code_04"
            + "             ,poli_code_05,poli_code_06,poli_code_07"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage07Dto> findRouteStage07(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_08 AS ("
            + "    SELECT DISTINCT political_org_code AS code08 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 8 AND trading_partner_code = ?2"
            + " ),"
            + "poli_code_07 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code07 FROM wk_tbl_ukai_kenkin,poli_code_08"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 7 AND trading_partner_code IN (poli_code_08.code08)"
            + " ),"
            + "poli_code_06 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code06 FROM wk_tbl_ukai_kenkin,poli_code_07"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 6 AND trading_partner_code IN (poli_code_07.code07)"
            + " ),"
            + "poli_code_05 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code05 FROM wk_tbl_ukai_kenkin,poli_code_06"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 5 AND trading_partner_code IN (poli_code_06.code06)"
            + " ),"
            + " poli_code_04 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code04 FROM wk_tbl_ukai_kenkin,poli_code_05"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 4 AND trading_partner_code IN (poli_code_05.code05)"
            + " ),"
            + " poli_code_03 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code03 FROM wk_tbl_ukai_kenkin,poli_code_04"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code IN (poli_code_04.code04)"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03,poli_code_04"
            + "             ,poli_code_05,poli_code_06,poli_code_07,poli_code_08"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage08Dto> findRouteStage08(Integer userCode,Integer poliOrgCode);
    
    @Query(value = "WITH poli_code_09 AS ("
            + "    SELECT DISTINCT political_org_code AS code09 FROM wk_tbl_ukai_kenkin"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 9 AND trading_partner_code = ?2"
            + " ),"
            + "poli_code_08 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code08 FROM wk_tbl_ukai_kenkin,poli_code_09"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 8 AND trading_partner_code IN (poli_code_09.code09)"
            + " ),"
            + "poli_code_07 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code07 FROM wk_tbl_ukai_kenkin,poli_code_08"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 7 AND trading_partner_code IN (poli_code_08.code08)"
            + " ),"
            + "poli_code_06 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code06 FROM wk_tbl_ukai_kenkin,poli_code_07"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 6 AND trading_partner_code IN (poli_code_07.code07)"
            + " ),"
            + "poli_code_05 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code05 FROM wk_tbl_ukai_kenkin,poli_code_06"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 5 AND trading_partner_code IN (poli_code_06.code06)"
            + " ),"
            + " poli_code_04 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code04 FROM wk_tbl_ukai_kenkin,poli_code_05"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 4 AND trading_partner_code IN (poli_code_05.code05)"
            + " ),"
            + " poli_code_03 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code03 FROM wk_tbl_ukai_kenkin,poli_code_04"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 3 AND trading_partner_code IN (poli_code_04.code04)"
            + " ),"
            + " poli_code_02 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code02 FROM wk_tbl_ukai_kenkin,poli_code_03"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 2 AND trading_partner_code IN (poli_code_03.code03)"
            + " ),"
            + " poli_code_01 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code01 FROM wk_tbl_ukai_kenkin,poli_code_02"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 1 AND trading_partner_code IN (poli_code_02.code02)"
            + " ),"
            + " poli_code_00 AS ("
            + "    SELECT DISTINCT wk_tbl_ukai_kenkin.political_org_code AS code00 FROM wk_tbl_ukai_kenkin,poli_code_01"
            + "    WHERE insert_user_code = ?1 AND pickup_stage = 0 AND trading_partner_code IN (poli_code_01.code01)"
            + " )"
            + "SELECT * FROM poli_code_00,poli_code_01,poli_code_02,poli_code_03,poli_code_04"
            + "             ,poli_code_05,poli_code_06,poli_code_07,poli_code_08,poli_code_09"
            , nativeQuery = true)
    List<RouteUkaiKenkinStage09Dto> findRouteStage09(Integer userCode,Integer poliOrgCode);
    
    
}
