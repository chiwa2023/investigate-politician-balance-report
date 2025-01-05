package mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Tuple;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheet0702And0713And0717Summary2023Entity;

/**
 * offering_balancesheet_0702_and_0713_and_0717_summary_2023接続用Repository
 */
public interface OfferingBalancesheet0702And0713And0717Summary2023Repository
        extends JpaRepository<OfferingBalancesheet0702And0713And0717Summary2023Entity, Integer> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM offering_balancesheet_0702_and_0713_and_0717_summary_2023 WHERE saishin_kbn= 1 AND MATCH(offering_balancesheet_0702_and_0713_and_0717_summary_2023_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<OfferingBalancesheet0702And0713And0717Summary2023Entity> findFullText(String searchWords);

    /**
     * テーブル同一識別コードがテーブルで最大行を取得する
     *
     * @return 最大値のOptional
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OfferingBalancesheet0702And0713And0717Summary2023Entity> findFirstByOrderByOfferingBalancesheet0702And0713And0717SummaryCodeDesc();

    /**
     * 同一識別コードが一致するデータをリストで取得する
     *
     * @param documentCode 使途報告書様式8その1と7同一識別コード
     * @return データリスト
     */
    List<OfferingBalancesheet0702And0713And0717Summary2023Entity> findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(
            Long documentCode);

    /**
     * 代表者から様式2データを取得する
     *
     * @param list       様式2
     * @param saishinKbn 最新区分
     * @return 検索結果
     */
    List<OfferingBalancesheet0702And0713And0717Summary2023Entity> findByRelationPersonCodeDelegateInAndSaishinKbn(
            List<Integer> list, Integer saishinKbn);

    /**
     * 政治団体から様式2データを取得す
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @param saishinKbn  最新区分
     * @return 検索結果
     */
    List<OfferingBalancesheet0702And0713And0717Summary2023Entity> findByPoliticalOrganizationCodeAndSaishinKbn(
            Integer poliOrgCode, Integer saishinKbn);

    /**
     * 関連者リストから会・員数の合計値を算出する
     *
     * @param list 関連者同一識別コードリスト
     * @return 合計結果のタプル
     */
    @Query(value = "SELECT SUM(koji_futan_goukei),SUM(koji_futan_suu)"
            + "FROM offering_balancesheet_0702_and_0713_and_0717_summary_2023 WHERE relation_person_code_delegate IN( ?1 ) AND saishin_kbn= 1", nativeQuery = true)
    Tuple findSumFeeAndInsu(List<Integer> list);

}
