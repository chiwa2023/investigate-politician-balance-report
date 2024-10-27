package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.investigate.report.balance.politician.entity.SaveFileHisotryEntity;

/**
 * save_file_hisotry接続用Repository
 */
public interface SaveFileHisotryRepository  extends JpaRepository<SaveFileHisotryEntity, Integer>{


    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM save_file_hisotry WHERE saishin_kbn= 1 AND MATCH(save_file_hisotry_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<SaveFileHisotryEntity> findFullText(String searchWords);
}
