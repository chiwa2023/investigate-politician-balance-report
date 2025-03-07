package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;

/**
 * wk_tbl_ukai_kenkin接続用Repository
 */
public interface WkTblUkaiKenkinRepository extends JpaRepository<WkTblUkaiKenkinEntity, Integer> { // NOPMD

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
     * 0階層に存在する取引相手と同じ同一識別コードを持つ明細データを取得する
     *
     * @param userCode   操作者同一識別コード
     * @param kofukinKbn 交付金様式区分
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code = ?1 "
            + "  AND pickup_stage > 0 AND trading_partner_code IN ("
            + "    SELECT DISTINCT trading_partner_code FROM wk_tbl_ukai_kenkin"
            + "      WHERE insert_user_code = ?1 AND pickup_stage = 0"
            + "         AND ((youshiki_kbn = 7 AND youshiki_eda_kbn =3) OR youshiki_kbn = ?2)"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findTradingPartnerOverStageZeroByZeroStage(Integer userCode, Integer kofukinKbn);

    /**
     * 個人データから取引を行った関連者個人同一識別コードを0階層で重複なしで取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 関連者個人同一識別コード
     */
    @Query(value = "SELECT DISTINCT trading_partner_id,trading_partner_code,trading_partner_name FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code =?1 AND youshiki_eda_kbn = 1 "
            + "AND trading_partner_code <> 0 AND pickup_stage = 0", nativeQuery = true)
    List<Tuple3<Long, Integer, String>> findTradingPartnerCode(Integer userCode);

    /**
     * 個人データから取引を行った関連者個人同一識別コードを重複なしで取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 関連者個人同一識別コード
     */
    @Query(value = "SELECT DISTINCT trading_partner_id,trading_partner_code,trading_partner_name FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code =?1 AND youshiki_eda_kbn = 1 AND pickup_stage = 0", nativeQuery = true)
    List<Tuple3<Long, Integer, String>> findTradingPartnerCodeInStageZero(Integer userCode);

    /**
     * 企業データから取引を行った企業・団体の代表者関連者個人同一識別コードが一致する代表者データを短縮してで取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 企業・団体の代表者関連者個人同一識別コード
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_partner_delegate_id,"
            + " trading_partner_delegate_code,trading_partner_delegate_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1" // NOPMD
            + " AND youshiki_eda_kbn = 2 AND trading_partner_delegate_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingCorpByDelegateCode(Integer userCode);

    /**
     * 団体代表者が一致する政治団体データを重複なしで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_partner_delegate_id"
            + ",trading_partner_delegate_code,trading_partner_delegate_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1  "
            + "AND pickup_stage=0 AND (youshiki_eda_kbn = 3 OR youshiki_eda_kbn = 0) "
            + "AND trading_partner_delegate_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingPoliOrgByDelegateCode(Integer userCode);

    /**
     * 会計責任者が一致する政治団体データを重複なしで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_org_account_manager_id"
            + ",trading_org_account_manager_code,trading_org_account_manager_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1"
            + " AND pickup_stage=0 AND (youshiki_eda_kbn = 3 OR youshiki_eda_kbn = 0)" // NOPMD
            + " AND trading_org_account_manager_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingPoliOrgByAccountManagerCode(Integer userCode);

    /**
     * 資金管理団体責任者が一致する政治団体データを重複なしで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_org_shikin_dantai_id"
            + ",trading_org_shikin_dantai_code,trading_org_shikin_dantai_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1"
            + " AND pickup_stage=0 AND (youshiki_eda_kbn = 3 OR youshiki_eda_kbn = 0)"
            + " AND trading_org_shikin_dantai_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingPoliOrgByShikinDantaiCode(Integer userCode);

    /**
     * 国会議員1が一致する政治団体データを重複なしで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_org_kokkai_giin1_id"
            + ",trading_org_kokkai_giin1_code,trading_org_kokkai_giin1_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1"
            + " AND pickup_stage=0 AND (youshiki_eda_kbn = 3 OR youshiki_eda_kbn = 0)"
            + " AND trading_org_kokkai_giin1_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingPoliOrgByKokkaiGin1Code(Integer userCode);

    /**
     * 国会議員2が一致する政治団体データを重複なしで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_org_kokkai_giin2_id"
            + ",trading_org_kokkai_giin2_code,trading_org_kokkai_giin2_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1"
            + " AND pickup_stage=0 AND (youshiki_eda_kbn = 3 OR youshiki_eda_kbn = 0)"
            + " AND trading_org_kokkai_giin2_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingPoliOrgByKokkaiGin2Code(Integer userCode);

    /**
     * 国会議員3が一致する政治団体データを重複なしで取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_org_kokkai_giin3_id"
            + ",trading_org_kokkai_giin3_code,trading_org_kokkai_giin3_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1"
            + " AND pickup_stage=0 AND (youshiki_eda_kbn = 3 OR youshiki_eda_kbn = 0)"
            + " AND trading_org_kokkai_giin3_id <> 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingPoliOrgByKokkaiGin3Code(Integer userCode);

    /**
     * 0階層に存在する企業の取引相手から1階層以上で存在するデータを取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin" + " WHERE insert_user_code =?1 AND youshiki_eda_kbn = 2"
            + " AND pickup_stage > 0 AND trading_partner_code IN ("
            + " SELECT DISTINCT trading_partner_code FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code =?1 AND youshiki_eda_kbn = 2"
            + " AND trading_partner_code <> 0 AND pickup_stage = 0" + " ) ", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findTradingCorpOverByZeroStage(Integer userCode);

    /**
     * 0階層の企業データを取得する
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT trading_partner_code,trading_partner_id"
            + ",trading_partner_code,trading_partner_name"
            + " FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND youshiki_eda_kbn = 2"
            + " AND trading_partner_code <> 0 AND pickup_stage = 0", nativeQuery = true)
    List<Tuple4<Integer, Long, Integer, String>> findTradingCorpInStageZero(Integer userCode);

    /**
     * 個人取引データの同一識別コードから同一の関連者を保持する企業・団体と政治団体を抜き出す
     * 取引者の同一識別コードが同じデータは検索者本人データなので除かなくてはならない
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param personCode 取引相手関連者同一識別コード
     * @return 関連者が含まれる寄付データ
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND pickup_stage > 0 AND ("
            + " trading_partner_delegate_code = ?2" + " OR trading_org_account_manager_code = ?2"
            + " OR trading_org_shikin_dantai_code = ?2" + " OR trading_org_kokkai_giin1_code = ?2"
            + " OR trading_org_kokkai_giin2_code = ?2" + " OR trading_org_kokkai_giin3_code = ?2"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findCorpAndPoriOrgByKojinOverStageZero(Integer userCode, Integer personCode);

    /**
     * 関連者に紐づく企業政治団体を階層0限定で取得する
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param personCode 関連者同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND pickup_stage = 0 AND ("
            + " trading_partner_delegate_code = ?2" + " OR trading_org_account_manager_code = ?2"
            + " OR trading_org_shikin_dantai_code = ?2" + " OR trading_org_kokkai_giin1_code = ?2"
            + " OR trading_org_kokkai_giin2_code = ?2" + " OR trading_org_kokkai_giin3_code = ?2"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findCorpAndPoriOrgByKojinInZero(Integer userCode, Integer personCode);

    /**
     * 関連者同一識別コードから0階層以外で、関連者が存在するすべての明細を取得する
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param personCode 関連者同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1"
            + " AND pickup_stage > 0 AND ( trading_partner_code = ?2 OR "
            + " trading_partner_delegate_code = ?2 OR trading_org_account_manager_code = ?2"
            + " OR trading_org_shikin_dantai_code = ?2" + " OR trading_org_kokkai_giin1_code = ?2"
            + " OR trading_org_kokkai_giin2_code = ?2" + " OR trading_org_kokkai_giin3_code = ?2"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findTaishoKojinAndDantaiByKojin(Integer userCode, Integer personCode);

    /**
     * 企業・団体の代表者の関連者が同じデータを抽出する
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param corpCode   除くべき企業・団体同一識別コード
     * @param personCode 関連者同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND trading_partner_code != ?2 "
            + " AND ( trading_partner_code = ?2 OR trading_partner_delegate_code = ?3"
            + " OR trading_org_account_manager_code = ?3 OR trading_org_shikin_dantai_code = ?3"
            + " OR trading_org_kokkai_giin1_code = ?3 OR trading_org_kokkai_giin2_code = ?3"
            + " OR trading_org_kokkai_giin3_code = ?3 )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findDataByKigyouDaihyousha(Integer userCode, Integer corpCode, Integer personCode);

    /**
     * 企業取引相手と関連者個人から企業・政治団体明細を取得する
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param corpCode   企業同一識別コード
     * @param personCode 関連者同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND trading_partner_code != ?2 "
            + " AND ( trading_partner_delegate_code = ?3" + " OR trading_org_account_manager_code = ?3"
            + " OR trading_org_shikin_dantai_code = ?3" + " OR trading_org_kokkai_giin1_code = ?3"
            + " OR trading_org_kokkai_giin2_code = ?3" + " OR trading_org_kokkai_giin3_code = ?3"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findMeisaiCorpPoliOrgByPartnerAndPerson(Integer userCode, Integer corpCode,
            Integer personCode);

    /**
     * 企業取引相手と関連者個人から企業・政治団体明細を0階層限定で取得する
     *
     * @param userCode   操作ユーザ同一識別コード
     * @param orgCode    政治団体同一識別コード
     * @param personCode 関連者同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1 AND trading_partner_code = ?2 "
            + " AND pickup_stage = 0 AND (youshiki_eda_kbn = 0 OR youshiki_eda_kbn = 3)"
            + " AND ( trading_partner_delegate_code = ?3" + " OR trading_org_account_manager_code = ?3"
            + " OR trading_org_shikin_dantai_code = ?3" + " OR trading_org_kokkai_giin1_code = ?3"
            + " OR trading_org_kokkai_giin2_code = ?3" + " OR trading_org_kokkai_giin3_code = ?3"
            + " )", nativeQuery = true)
    List<WkTblUkaiKenkinEntity> findMeisaiDataPoliOrgByPartnerAndPerson(Integer userCode, Integer orgCode,
            Integer personCode);

    /**
     * 登録済みの経路データから該当する記載政治団体と取り引き相手政治団体が一致するデータを抽出する
     *
     * @param userCode       操作ユーザ同一識別コード
     * @param poliOrgCode    収支報告書記載団体
     * @param tradingOrgCode 取り引き相手団体
     * @param stage          取得階層
     * @return 検索結果
     */
    List<WkTblUkaiKenkinEntity> findByInsertUserCodeAndPoliticalOrgCodeAndTradingPartnerCodeAndPickupStage(
            Integer userCode, Integer poliOrgCode, Integer tradingOrgCode, Integer stage);

    /**
     * 取得回数、取り引き相手から記載政治団体コードリストを抽出する
     *
     * @param userCode    操作ユーザ同一識別コード
     * @param stage       取得階層
     * @param listOrgCode 取り引き相手政治団体同一識別コードリスト
     * @return 検索結果
     */
    @Query(value = "SELECT DISTINCT political_org_code AS code00 FROM wk_tbl_ukai_kenkin WHERE insert_user_code = ?1 AND pickup_stage = ?2 AND trading_partner_code IN (?3)", nativeQuery = true)
    List<Integer> findRouteByOrgCodeAndStage(Integer userCode, Integer stage, List<Integer> listOrgCode);

    /**
     * 操作ユーザ同一識別コードに合致する迂回献金明細を取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @param pageable ページング条件
     * @return 検索結果
     */
    List<WkTblUkaiKenkinEntity> findByInsertUserCodeOrderByPoliticalOrgCodeAscPickupStageAsc(Integer userCode,
            Pageable pageable);

    /**
     * 操作ユーザ同一識別コードに合致する迂回献金明細総件数を取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT COUNT(*) FROM wk_tbl_ukai_kenkin WHERE insert_user_code =?1", nativeQuery = true)
    Integer findCount(Integer userCode);

    /**
     * 階層と取引相手が一致する明細を取得する
     *
     * @param userCode    操作ユーザ同一識別コード
     * @param partnerCode 取引相手同一識別コード
     * @param stage       階層
     * @return 検索結果
     */
    List<WkTblUkaiKenkinEntity> findByInsertUserCodeAndTradingPartnerCodeAndPickupStage(Integer userCode,
            Integer partnerCode, Integer stage);

    /**
     * 階層と取引相手、様式枝区分項目が一致する明細を取得する
     *
     * @param userCode    操作ユーザ同一識別コード
     * @param partnerCode 取引相手同一識別コード
     * @param stage       階層
     * @param listEdaKbn  該当様式枝区分コードリスト
     * @return 検索結果
     */
    List<WkTblUkaiKenkinEntity> findByInsertUserCodeAndTradingPartnerCodeAndPickupStageAndYoushikiEdaKbnIn(
            Integer userCode, Integer partnerCode, Integer stage, List<Integer> listEdaKbn);

    /**
     * 階層と取引相手、代表者同一識別コード、様式枝区分項目が一致する明細を取得する
     *
     * @param userCode     操作ユーザ同一識別コード
     * @param partnerCode  取引相手同一識別コード
     * @param delegateCode 代表者同一識別コード
     * @param stage        階層
     * @param listEdaKbn   該当様式枝区分コードリスト
     * @return 検索結果
     */
    List<WkTblUkaiKenkinEntity> findByInsertUserCodeAndTradingPartnerCodeAndTradingPartnerDelegateCodeAndPickupStageAndYoushikiEdaKbnIn(
            Integer userCode, Integer partnerCode, Integer delegateCode, Integer stage, List<Integer> listEdaKbn);

    /**
     * 0階層に存在する取引相手のうち、0階層でない階層でで１件でも明細が存在するで取引相手を様式枝区分項目で限定して取得する
     *
     * @param userCode       操作ユーザ同一識別コード
     * @param youshikiEdaKbn 様式枝区分項目リスト
     * @return 検索結果
     */
    @Query(value = "SELECT trading_partner_code FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code = ?1 AND pickup_stage = 0 AND youshiki_eda_kbn = ?2"
            + " GROUP BY trading_partner_code" + " HAVING COUNT(political_org_code) > 1", nativeQuery = true)
    List<Integer> findUkaiPickupStageZero(Integer userCode, Integer youshikiEdaKbn);

    /**
     * 0階層に存在する取引相手のうち、0階層でない階層で１件でも明細が存在する政治団体を限定で取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT trading_partner_code FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code = ?1 AND pickup_stage = 0 AND (youshiki_eda_kbn = 0 OR youshiki_eda_kbn = 3)"
            + " GROUP BY trading_partner_code" + " HAVING COUNT(political_org_code) > 1", nativeQuery = true)
    List<Integer> findUkaiPickupStageZeroPoliOrg(Integer userCode);

}
