package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0802WithdrawalItemsByTransferDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080200WithdrawalItemsByTransferDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetWithdrawal0802Transfer2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetWithdrawal0802Transfer2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式08その2 支出項目別金額の内訳
 */
@Component
public class InsertPoliticalOrganization0802Y2025Logic {

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetWithdrawal0802Transfer2025Repository offeringBalancesheetWithdrawal0802Transfer2025Repository;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param itemTransfer0802Dto 様式8その2Dto
     * @param checkPrivilegeDto   権限Dto
     * @return 登録行数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0802WithdrawalItemsByTransferDto itemTransfer0802Dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetWithdrawal0802Transfer2025Entity> list = new ArrayList<>();

        // 同一識別コードの取得
        Long code = 0L;
        Optional<OfferingBalancesheetWithdrawal0802Transfer2025Entity> optional = offeringBalancesheetWithdrawal0802Transfer2025Repository
                .findFirstByOrderByOfferingBalancesheetWithdrawal0802TransferCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getOfferingBalancesheetWithdrawal0802TransferCode();
        }

        for (Sheet080200WithdrawalItemsByTransferDto sheetDto : itemTransfer0802Dto.getListSheet0802()) {
            code++;
            list.add(this.createEntity(code, documentCode, sheetDto, documentPropertyDto, checkPrivilegeDto));
        }

        // 登録
        return offeringBalancesheetWithdrawal0802Transfer2025Repository.saveAll(list).size();
    }

    private OfferingBalancesheetWithdrawal0802Transfer2025Entity createEntity(final Long code, final Long documentCode,
            final Sheet080200WithdrawalItemsByTransferDto sheetDto,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final CheckPrivilegeDto checkPrivilegeDto) {
        OfferingBalancesheetWithdrawal0802Transfer2025Entity balancesheetEntity = new OfferingBalancesheetWithdrawal0802Transfer2025Entity();

        balancesheetEntity.setOfferingBalancesheetWithdrawal0802TransferCode(code);
        // 登録履歴
        BeanUtils.copyProperties(documentPropertyDto, balancesheetEntity);
        balancesheetEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(sheetDto, balancesheetEntity);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, balancesheetEntity, DataHistoryStatusConstants.INSERT);

        return balancesheetEntity;
    }

}
