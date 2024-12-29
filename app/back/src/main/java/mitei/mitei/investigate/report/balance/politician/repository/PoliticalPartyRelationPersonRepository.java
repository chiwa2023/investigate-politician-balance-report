package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalPartyRelationPersonEntity;

/**
 * political_party_relation_person接続用Repository
 */
@Component
public interface PoliticalPartyRelationPersonRepository
        extends JpaRepository<PoliticalPartyRelationPersonEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM political_party_relation_person WHERE saishin_kbn= 1 AND MATCH(political_party_relation_person_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<PoliticalPartyRelationPersonEntity> findFullText(String searchWords);

    /**
     * 政党に紐づく関連者の総数を取得する
     *
     * @param poliPtyCode 政党同一識別コード
     * @return 総数
     */
    @Query(value = "SELECT count(*) FROM political_party_relation_person WHERE political_party_code = ?1 AND saishin_kbn= 1", nativeQuery = true)
    Long countSaishin(int poliPtyCode);

    /**
     * 政党に紐づく関連者を取得する
     *
     * @param poliPtyCode 政党同一識別コード
     * @return 検索結果
     */
    @Query(value = "SELECT (relation_person_code) FROM political_party_relation_person WHERE political_party_code = ?1 AND saishin_kbn= 1", nativeQuery = true)
    List<Integer> findByPoliticalPartyCode(int poliPtyCode, Pageable pageable);
}
