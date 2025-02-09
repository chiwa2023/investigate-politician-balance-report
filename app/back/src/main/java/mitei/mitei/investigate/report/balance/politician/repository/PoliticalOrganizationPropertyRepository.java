package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;

/**
 * political_organization_property接続用Repository
 */
public interface PoliticalOrganizationPropertyRepository
        extends JpaRepository<PoliticalOrganizationPropertyEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM political_organization_property WHERE saishin_kbn= 1 AND MATCH(political_organization_property_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<PoliticalOrganizationPropertyEntity> findFullText(String searchWords);

    /**
     * 政治団体Idからデータを取得する
     *
     * @param poliOrgId  政治団体Id
     * @param saishinKbn 最新区分
     * @return 政治団体属性Entity
     */
    Optional<PoliticalOrganizationPropertyEntity> findByPoliticalOrganizationIdAndSaishinKbn(Long poliOrgId,
            Integer saishinKbn);

    /**
     * 関連者が一致する政治団体同一識別コードを重複なく抽出する
     *
     * @param listPersonCode 政治団体同一識別コード
     * @return 政治団体同一識別コードリスト
     */
    @Query(value = "SELECT DISTINCT political_organization_code FROM political_organization_property"
            + " WHERE (delegate_relation_person_code IN (?1) " + " OR account_manager_relation_person_code IN (?1) "
            + " OR shikin_daihyou_relation_person_code IN (?1) " + " OR giin1_relation_person_code IN (?1) "
            + " OR giin2_relation_person_code IN (?1) " + " OR giin3_relation_person_code IN (?1) )"
            + " AND saishin_kbn =1", nativeQuery = true)
    List<Integer> findSameRelationPerson(List<Integer> listPersonCode);

}
