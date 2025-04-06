package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblBalancesheetOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 収支報告書支出データワークテーブル複写用抽出Logic
 */
@Component
public class CopyBalancesheetWkTblOutcomeY2022Logic {

    /** 様式区分8の4交付金支出Repository */
    @Autowired
    private OfferingBalancesheetOutcome2022Repository offeringBalancesheetOutcome2022Repository;


    /**
     * 該当文書の支出行を取得する
     *
     * @param documentCode 文書同一識別コード
     * @return 支出全件数
     */
    public Integer practiceSize(final Long documentCode){
        return offeringBalancesheetOutcome2022Repository.findByDocumentRow(documentCode);
    }

    /**
     * 処理を行う
     *
     * @param documentCode 文書同一識別コード
     * @param pageNumber   ページ番号
     * @param privilegeDto 権限Dto
     * @return 検索結果
     */
    public List<WkTblBalancesheetOutcomeEntity> practice(final Long documentCode, final Integer pageNumber,
            final Integer chunkSize, final CheckPrivilegeDto privilegeDto) {

        Pageable pageable = Pageable.ofSize(chunkSize).withPage(pageNumber);

        List<OfferingBalancesheetOutcome2022Entity> listSrc = offeringBalancesheetOutcome2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetOutcomeId(documentCode, pageable);

        List<WkTblBalancesheetOutcomeEntity> list = new ArrayList<>();
        for (OfferingBalancesheetOutcome2022Entity entitySrc : listSrc) {

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
    private WkTblBalancesheetOutcomeEntity createEntity(final OfferingBalancesheetOutcome2022Entity entitySrc,
            final CheckPrivilegeDto privilegeDto) {

        WkTblBalancesheetOutcomeEntity entity = new WkTblBalancesheetOutcomeEntity();

        BeanUtils.copyProperties(entitySrc, entity);
        entity.setPartnerJusho(entitySrc.getPartnerJuusho());
        
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        entity.setWorktableId(0L); // auto_increment明示

        return entity;
    }

}
