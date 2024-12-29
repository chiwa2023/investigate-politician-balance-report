package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalPartyEntity;

/**
 * political_party接続用Repository
 */
public interface PoliticalPartyRepository  extends JpaRepository<PoliticalPartyEntity, Long>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM political_party WHERE saishin_kbn= 1 AND MATCH(political_party_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<PoliticalPartyEntity> findFullText(String searchWords);
    
    
    /**
     * 最新データをすべて取得する
     *
     * @param saishinKbn 最新区分
     * @return 最新データリスト
     */
    List<PoliticalPartyEntity> findBySaishinKbn(Integer saishinKbn);
    
}
