package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.UserWebAccessEntity;

/**
 * user_web_access接続用Repository
 */
public interface UserWebAccessRepository extends JpaRepository<UserWebAccessEntity, Long> {

    // TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM user_web_access WHERE saishin_kbn= 1 AND MATCH(user_web_access_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<UserWebAccessEntity> findFullText(String searchWords);

    
    /**
     * タスクの種類ごとに該当ユーザを抽出する
     *
     * @param saishinKbn 最新区分
     * @param listTaskLevel タスクレベルリスト
     * @return 該当ユーザのアクセス情報
     */
    List<UserWebAccessEntity> findBySaishinKbnAndTaskLevelIn(Integer saishinKbn,List<Integer> listTaskLevel);
    
    
}
