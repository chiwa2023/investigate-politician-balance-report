package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0802Kbn02Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書収入テーブルワークテーブル複写用データ抽出Logic(2025)
 */
@Component
public class CopyUsageWkTblIncomeY2025Logic {

    /** 様式区分8の4交付金支出Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2025Repository offeringPartyUsage0802Kbn02Report2025Repository;


    /**
     * 該当文書の収入行を取得する
     *
     * @param documentCode 文書同一識別コード
     * @return 収入全件数
     */
    public Integer practiceSize(final Long documentCode){
        return offeringPartyUsage0802Kbn02Report2025Repository.findByDocumentRow(documentCode);
    }

    /**
     * 処理を行う
     *
     * @param documentCode 文書同一識別コード
     * @param pageNumber   ページ番号
     * @param privilegeDto 権限Dto
     * @return 検索結果
     */
    public List<WkTblUsageIncomeEntity> practice(final Long documentCode, final Integer pageNumber,
            final Integer chunkSize, final CheckPrivilegeDto privilegeDto) {

        Pageable pageable = Pageable.ofSize(chunkSize).withPage(pageNumber);

        List<OfferingPartyUsage0802Kbn02Report2025Entity> listSrc = offeringPartyUsage0802Kbn02Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0802Kbn02ReportId(documentCode, pageable);

        List<WkTblUsageIncomeEntity> list = new ArrayList<>();
        for (OfferingPartyUsage0802Kbn02Report2025Entity entitySrc : listSrc) {

            list.add(this.createEntity(entitySrc, privilegeDto));
        }

        return list;
    }

    /*
     * 使途報告書支出ワークテーブルEntityを作成する
     * 
     * @param entitySrc 使途報告書支出データ
     * 
     * @param privilegeDto 権限Dto
     * 
     * @return 使途報告書支出ワークテーブルEntity
     */
    private WkTblUsageIncomeEntity createEntity(final OfferingPartyUsage0802Kbn02Report2025Entity entitySrc,
            final CheckPrivilegeDto privilegeDto) {

        WkTblUsageIncomeEntity entity = new WkTblUsageIncomeEntity();

        BeanUtils.copyProperties(entitySrc, entity);
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        return entity;
    }

}
