package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.SouryouKiseiByCodeEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.SouryouKiseiGenBunshoEntity;

/**
 * offering_balancesheet_income_2024接続用Repository
 */
public interface OfferingBalancesheetIncome2024Repository
        extends JpaRepository<OfferingBalancesheetIncome2024Entity, Integer> { // NOPMD

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 全文検索をする
     *
     * @param searchWords 検索語
     * @param offset      検索表示位置
     * @param startDate   開始日付
     * @param endDate     終了日付
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_income_2024 WHERE saishin_kbn= 1 AND accrual_date_value BETWEEN ?3 AND ?4 AND MATCH(search_words) AGAINST (?1 IN BOOLEAN MODE) LIMIT 100 OFFSET ?2", nativeQuery = true)
    List<OfferingBalancesheetIncome2024Entity> findFullText(String searchWords, int offset, LocalDate startDate,
            LocalDate endDate);

    /**
     * 検索対象行数を取得する
     *
     * @param searchWords 検索語
     * @param startDate   開始日付
     * @param endDate     終了日付
     * @return 件数
     */
    @Query(value = "SELECT count(*) AS count FROM offering_balancesheet_income_2024 WHERE saishin_kbn= 1 AND accrual_date_value BETWEEN ?2 AND ?3 AND MATCH(search_words) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    Integer findFullTextCount(String searchWords, LocalDate startDate, LocalDate endDate);

    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheetIncome2024Entity> findFirstByOrderByOfferingBalancesheetIncomeCodeDesc();

    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 文書同一識別コード
     * @return データリスト
     */
    List<OfferingBalancesheetIncome2024Entity> findByDocumentCodeOrderByOfferingBalancesheetIncomeId(Long documentCode);

    /**
     * 政治団体とと取引相手の同一識別コードを条件に収入を取得する
     *
     * @param relationCode 取引相手同一識別コード
     * @param saishinKbn   最新区分
     * @param poliOrgCode  政治団体同一識別コード
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2024Entity> findByRelationPoliticalOrgCodeIncomeNotAndSaishinKbnAndPoliticalOrganizationCode(
            Integer relationCode, Integer saishinKbn, Integer poliOrgCode);

    /**
     * 原文書団体名と取引相手名を条件に収入を取得する
     *
     * @param partnerName 取引相手名
     * @param saishinKbn  最新区分
     * @param dantaiName  団体名
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2024Entity> findByPartnerNameIsNotAndSaishinKbnAndDantaiName(String partnerName,
            Integer saishinKbn, String dantaiName);

    /**
     * 団体名称取得用に該当の最初のデータを取得する
     *
     * @param poliOrgCode 政治団体
     * @param saishinKbn  最新区分
     * @return 収入最初の1件
     */
    Optional<OfferingBalancesheetIncome2024Entity> findFirstByPoliticalOrganizationCodeAndSaishinKbn(
            Integer poliOrgCode, Integer saishinKbn);

    /**
     * 政治団体同一識別コードから取引相手名称リストを取得する
     *
     * @param poliOrgCode    政治団体同一識別コード
     * @param youshikiEdaKbn 様式枝区分
     * @return 取引相手名称リスト
     */
    @Query(value = "SELECT DISTINCT partner_name FROM offering_balancesheet_income_2024 WHERE political_organization_code=?1 AND youshiki_kbn=7 AND youshiki_eda_kbn=?2 ", nativeQuery = true)
    List<String> findPartnerNameByPoliOrg(Integer poliOrgCode, Integer youshikiEdaKbn);

    /**
     * 政治団体同一識別コードから取引相手関連者個人同一識別コードを取得する
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 取引相手関連者同一識別コードリスト
     */
    @Query(value = "SELECT DISTINCT relation_person_code_income FROM offering_balancesheet_income_2024 WHERE political_organization_code=?1 AND youshiki_kbn=7 AND youshiki_eda_kbn=1 ", nativeQuery = true)
    List<Integer> findRelationPersonByPoliOrg(Integer poliOrgCode);

    /**
     * 政治団体同一識別コードから取引相手関連者企業同一識別コードを取得する
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 取引相手関連者同一識別コードリスト
     */
    @Query(value = "SELECT DISTINCT relation_corp_code_income FROM offering_balancesheet_income_2024 WHERE political_organization_code=?1 AND youshiki_kbn=7 AND youshiki_eda_kbn=2 ", nativeQuery = true)
    List<Integer> findRelationCorpByPoliOrg(Integer poliOrgCode);

    /**
     * 政治団体同一識別コードから取引相手関連者政治団体同一識別コードを取得する
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 取引相手関連者同一識別コードリスト
     */
    @Query(value = "SELECT DISTINCT relation_political_org_code_income FROM offering_balancesheet_income_2024 WHERE political_organization_code=?1 AND youshiki_kbn=7 AND youshiki_eda_kbn=3 ", nativeQuery = true)
    List<Integer> findRelationPoliOrgByPoliOrg(Integer poliOrgCode);

    /**
     * 総量規制チェックのための取引相手ごとの取引金額合計を取得する
     *
     * @param listDantaiName 団体名リスト
     * @param youshikiEdaKbn 様式枝区分
     * @param listDantaiKbn  団体区分
     * @return 合計額リスト
     */
    @Query(value = "SELECT sum(kingaku) AS sum , partner_name AS partner_name , partner_juusho AS partner_juusho FROM offering_balancesheet_income_2024 WHERE partner_name IN (?1) AND dantai_kbn IN (?3) AND youshiki_kbn=7 AND youshiki_eda_kbn=?2 AND saishin_kbn=1 GROUP BY partner_name,partner_juusho", nativeQuery = true)
    List<SouryouKiseiGenBunshoEntity> calcSumByPartnerName(List<String> listDantaiName, Integer youshikiEdaKbn,
            List<String> listDantaiKbn);

    /**
     * 総量規制チェックのための関連者区分ごとの取引金額合計を取得する
     *
     * @param listrelationPersonCode 関連者個人同一識別コードリスト
     * @param listDantaiKbn          団体区分リスト
     * @return 合計額リスト
     */
    @Query(value = "SELECT sum(kingaku) AS sum ,relation_person_id_income AS relation_id , relation_person_code_income AS relation_code FROM offering_balancesheet_income_2024 WHERE relation_person_code_income IN (?1) AND dantai_kbn IN (?2) AND youshiki_kbn=7 AND youshiki_eda_kbn=1 AND saishin_kbn=1 GROUP BY relation_person_code_income,relation_person_id_income", nativeQuery = true)
    List<SouryouKiseiByCodeEntity> calcSumByRelationPerson(List<Integer> listrelationPersonCode,
            List<String> listDantaiKbn);

    /**
     * 総量規制チェックのための関連者区分ごとの取引金額合計を取得する
     *
     * @param listRelationCorpCode 企業団体同一識別コードリスト
     * @param listDantaiKbn        団体区分リスト
     * @return 合計額リスト
     */
    @Query(value = "SELECT sum(kingaku) AS sum ,relation_corp_id_income AS relation_id , relation_corp_code_income AS relation_code FROM offering_balancesheet_income_2024 WHERE relation_corp_code_income IN (?1) AND dantai_kbn IN (?2) AND youshiki_kbn=7 AND youshiki_eda_kbn=2 AND saishin_kbn=1 GROUP BY relation_corp_code_income,relation_corp_id_income", nativeQuery = true)
    List<SouryouKiseiByCodeEntity> calcSumByRelationCorp(List<Integer> listRelationCorpCode,
            List<String> listDantaiKbn);

    /**
     * 原文書取引相手名称から取引明細を取得する
     *
     * @param listDantaiName 取引相手名称リスト
     * @param listDantaiKbn  団体区分リスト
     * @param youshikiEdaKbn 様式枝区分
     * @param saishinKbn     最新区分
     * @param pageable       ページング情報
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2024Entity> findByPartnerNameInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByPartnerNameAscPartnerJuushoAsc(
            List<String> listDantaiName, List<String> listDantaiKbn, Integer youshikiEdaKbn, Integer saishinKbn,
            Pageable pageable);

    /**
     * 同一識別コードリストから寄付上限明細リストを取得する
     *
     * @param listRelationCode 関連者個人同一識別コードリスト
     * @param listDantaiKbn    団体区分リスト
     * @param youshikiEdaKbn   様式枝区分
     * @param saishinKbn       最新区分
     * @param pageable         ページング情報
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2024Entity> findByRelationPersonCodeIncomeInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByRelationPersonIdIncomeAsc(
            List<Integer> listRelationCode, List<String> listDantaiKbn, Integer youshikiEdaKbn, Integer saishinKbn,
            Pageable pageable);

    /**
     * 同一識別コードリストから寄付上限明細リストを取得する
     *
     * @param listRelationCode 関連者企業・団体同一識別コードリスト
     * @param listDantaiKbn    団体区分リスト
     * @param youshikiEdaKbn   様式枝区分
     * @param saishinKbn       最新区分
     * @param pageable         ページング情報
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2024Entity> findByRelationCorpCodeIncomeInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByRelationCorpCodeIncomeAsc(
            List<Integer> listRelationCode, List<String> listDantaiKbn, Integer youshikiEdaKbn, Integer saishinKbn,
            Pageable pageable);

    /**
     * 同一識別コードリストから寄付上限明細リストを取得する
     *
     * @param listRelationCode 関連者政治団体同一識別コードリスト
     * @param listDantaiKbn    団体区分リスト
     * @param youshikiEdaKbn   様式枝区分
     * @param saishinKbn       最新区分
     * @param pageable         ページング情報
     * @return 検索結果
     */
    List<OfferingBalancesheetIncome2024Entity> findByRelationPoliticalOrgCodeIncomeInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByRelationPoliticalOrgCodeIncomeAsc(
            List<Integer> listRelationCode, List<String> listDantaiKbn, Integer youshikiEdaKbn, Integer saishinKbn,
            Pageable pageable);

    
    /**
     * 政治団体から取引データをすべて取得する
     *
     * @param listPoliOrgCode 政治団体同一識別コードリスト
     * @param saishinKbn 最新区分
     * @return 収入データリスト
     */
    List<OfferingBalancesheetIncome2024Entity> findByPoliticalOrganizationCodeInAndYoushikiKbnInAndSaishinKbn(List<Integer> listPoliOrgCode,List<Integer> listYoushikiKbn,Integer saishinKbn);

    
    /**
     * 迂回献金明細テーブルの直前の取得階層に存在する政治団体の収入データを取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @param stage 取得階層
     * @param koufukinKbn 交付金検索有無
     * @param pageable ページング条件
     * @return 収支報告書収入データ
     */
    @Query(value = "SELECT *,(?2) AS pickup_stage FROM offering_balancesheet_income_2024 WHERE political_organization_code IN ("
            + " SELECT trading_partner_code FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code = ?1 AND pickup_stage = (?2-1)"
            + " AND ((youshiki_eda_kbn = 3 AND youshiki_kbn =7) OR youshiki_kbn = ?3)"
            + ")"
            + " AND ( youshiki_kbn =7 OR youshiki_kbn =?3)"
            , nativeQuery = true)
    Page<OfferingBalancesheetIncomeEntity> findUkaiKenkiMeisai(Integer userCode,Integer stage,Integer koufukinKbn,Pageable pageable);

    /**
     * 迂回献金明細テーブルの直前の取得階層に存在する政治団体の収入データの総件数を取得する
     *
     * @param userCode 操作ユーザ同一識別コード
     * @param stage 取得階層
     * @param koufukinKbn 交付金検索有無
     * @return 総件数
     */
    @Query(value = "SELECT COUNT(*) FROM offering_balancesheet_income_2024 WHERE political_organization_code IN ("
            + " SELECT trading_partner_code FROM wk_tbl_ukai_kenkin"
            + " WHERE insert_user_code = ?1 AND  pickup_stage = (?2-1) "
            + " AND ((youshiki_eda_kbn = 3 AND youshiki_kbn = 7) OR youshiki_kbn = ?3)"
            + ")"
            + " AND (youshiki_kbn = 7 OR youshiki_kbn = ?3)", nativeQuery = true)
    Integer findUkaiKenkiMeisaiCount(Integer userCode,Integer stage,Integer koufukinKbn);
}
