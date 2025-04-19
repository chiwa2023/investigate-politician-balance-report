package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0804Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0804Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書支出データをワークテーブルに複写するデータを抽出する(2024)
 */
@Component
public class CopyUsageWkTbOutncomeY2024Logic {

    /** 様式区分8の4交付金支出Repository */
    @Autowired
    private OfferingPartyUsage0804Report2024Repository offeringPartyUsage0804Report2024Repository;


    /**
     * 該当文書の支出行を取得する
     *
     * @param documentCode 文書同一識別コード
     * @return 支出全件数
     */
    public Integer practiceSize(final Long documentCode){
        return offeringPartyUsage0804Report2024Repository.findByDocumentRow(documentCode);
    }

    /**
     * 処理を行う
     *
     * @param documentCode 文書同一識別コード
     * @param pageNumber   ページ番号
     * @param privilegeDto 権限Dto
     * @return 検索結果
     */
    public List<WkTblUsageOutcomeEntity> practice(final Long documentCode, final Integer pageNumber,
            final Integer chunkSize, final CheckPrivilegeDto privilegeDto) {

        Pageable pageable = Pageable.ofSize(chunkSize).withPage(pageNumber);

        List<OfferingPartyUsage0804Report2024Entity> listSrc = offeringPartyUsage0804Report2024Repository
                .findByDocumentCodeOrderByPartyUsage0804ReportId(documentCode, pageable);

        List<WkTblUsageOutcomeEntity> list = new ArrayList<>();
        for (OfferingPartyUsage0804Report2024Entity entitySrc : listSrc) {

            list.add(this.createEntity(entitySrc, privilegeDto));
        }

        return list;
    }

    /*
     * 使途報告書支出ワークテーブルEntityを作成する
     * 
     * @param entitySrc 使途報告書支出データ
     * @param privilegeDto 権限Dto
     * @return 使途報告書支出ワークテーブルEntity
     */
    private WkTblUsageOutcomeEntity createEntity(final OfferingPartyUsage0804Report2024Entity entitySrc,
            final CheckPrivilegeDto privilegeDto) {

        WkTblUsageOutcomeEntity entity = new WkTblUsageOutcomeEntity();

        BeanUtils.copyProperties(entitySrc, entity);
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        return entity;
    }

}
