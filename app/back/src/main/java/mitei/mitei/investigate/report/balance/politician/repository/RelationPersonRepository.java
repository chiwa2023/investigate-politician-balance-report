package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.RelationPersonEntity;

/**
 * relation_person接続用Repository
 */
public interface RelationPersonRepository  extends JpaRepository<RelationPersonEntity, Long>{

    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM relation_person WHERE saishin_kbn= 1 AND MATCH(relation_person_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<RelationPersonEntity> findFullText(String searchWords);
}
