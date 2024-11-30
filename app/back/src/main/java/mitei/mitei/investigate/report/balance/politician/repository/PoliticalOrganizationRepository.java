package mitei.mitei.investigate.report.balance.politician.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;

/**
 * political_organization接続用Repository
 */
public interface PoliticalOrganizationRepository extends JpaRepository<PoliticalOrganizationEntity, Long> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM political_organization WHERE saishin_kbn= 1 AND MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    List<PoliticalOrganizationEntity> findFullText(String searchWords);

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM political_organization WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    List<PoliticalOrganizationEntity> findFullTextPlushistory(String searchWords);

    /**
     * 団体名、代表者名、団体区分から該当リストを取得する
     *
     * @param dantaiName   政治団体名称
     * @param dantaiKbn    団体区分
     * @param delegateName 代表者名
     * @return 該当政治団体Entityリスト
     */
    List<PoliticalOrganizationEntity> findByPoliticalOrganizationNameAndDantaiKbnAndDaihyoshaName(String dantaiName,
            String dantaiKbn, String delegateName);

    /**
     * 最大同一識別コードのEntityを取得する
     *
     * @return allBookDto
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PoliticalOrganizationEntity> findFirstByOrderByPoliticalOrganizationCodeDesc();

}
