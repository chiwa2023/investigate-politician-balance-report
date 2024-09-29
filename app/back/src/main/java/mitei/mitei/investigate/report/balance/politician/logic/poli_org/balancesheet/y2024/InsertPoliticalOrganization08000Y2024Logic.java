package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0800DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row080000DifficultCollectReceiptDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetDifficalt0800Recipt2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetDifficalt0800Recipt2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式8 領収書を徴しがたかった支出項目一覧表登録Logic
 */
@Component
public class InsertPoliticalOrganization08000Y2024Logic {

    /** 様式8 領収書を徴しがたかった支出項目一覧表Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2024Repository offeringBalancesheetDifficalt0800Recipt2024Repository;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 収支報告書文書属性Dto
     * @param difficultReciptDto  様式8 領収書を徴しがたかった支出項目
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0800DifficultCollectReceiptDto difficultReciptDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetDifficalt0800Recipt2024Entity> list = new ArrayList<>();

        // コードの取得
        Long code = 0L;
        Optional<OfferingBalancesheetDifficalt0800Recipt2024Entity> optional = offeringBalancesheetDifficalt0800Recipt2024Repository
                .findFirstByOrderByOfferingBalancesheetDifficalt0800ReciptCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getOfferingBalancesheetDifficalt0800ReciptCode();
        }
        for (Row080000DifficultCollectReceiptDto rowDto : difficultReciptDto.getSheet080000DifficultCollectReceiptDto()
                .getList()) {
            code++;
            list.add(this.createEntity(code, documentPropertyDto, documentCode, rowDto, checkPrivilegeDto));
        }

        return offeringBalancesheetDifficalt0800Recipt2024Repository.saveAll(list).size();
    }

    private OfferingBalancesheetDifficalt0800Recipt2024Entity createEntity(final Long code,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Long documentCode,
            final Row080000DifficultCollectReceiptDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetDifficalt0800Recipt2024Entity balancesheetEntity = new OfferingBalancesheetDifficalt0800Recipt2024Entity();
        balancesheetEntity.setDocumentCode(documentCode);
        balancesheetEntity.setOfferingBalancesheetDifficalt0800ReciptCode(code);

        BeanUtils.copyProperties(documentPropertyDto, balancesheetEntity);
        BeanUtils.copyProperties(rowDto, balancesheetEntity);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, balancesheetEntity, DataHistoryStatusConstants.INSERT);

        return balancesheetEntity;
    }
}
