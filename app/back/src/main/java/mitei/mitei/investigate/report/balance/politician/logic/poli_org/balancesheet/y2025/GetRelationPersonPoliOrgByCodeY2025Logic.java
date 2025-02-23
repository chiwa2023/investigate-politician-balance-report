package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0701And0720Surface2025Repository;

/**
 * 対象の政治団体に属する関連者すべてを抽出する(2025年)
 */
@Component
public class GetRelationPersonPoliOrgByCodeY2025Logic {

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 政治資金収支報告書表紙0701Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2025Repository offeringBalancesheet0701And0720Surface2025Repository;

    /**
     * 抽出処理をする
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 政治団体属性Entity
     */
    public PoliticalOrganizationPropertyEntity practice(final Integer poliOrgCode) {

        final int insertValue = DataHistoryStatusConstants.INSERT.value();

        // 指定された政治団体同一識別コードから政治団体を特定
        Optional<OfferingBalancesheet0701And0720Surface2025Entity> optionalSurface = offeringBalancesheet0701And0720Surface2025Repository
                .findFirstByPoliticalOrganizationCodeAndSaishinKbnOrderByOfferingDateDesc(poliOrgCode, insertValue);
        if (optionalSurface.isEmpty()) {
            throw new EmptyResultDataAccessException(0);
        }

        // 検索対象として指定された政治団体を抽出
        Optional<PoliticalOrganizationPropertyEntity> optionalProperty = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationIdAndSaishinKbn(optionalSurface.get().getPoliticalOrganizationId(),
                        insertValue);

        if (optionalProperty.isEmpty()) {
            throw new EmptyResultDataAccessException(0);
        }

        return optionalProperty.get();
    }
}
