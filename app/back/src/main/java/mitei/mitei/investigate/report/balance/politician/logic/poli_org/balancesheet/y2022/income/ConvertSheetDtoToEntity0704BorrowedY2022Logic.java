package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070400BorrowedMoneyDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Entity;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その4借入金をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0704BorrowedY2022Logic {

    /** 自然検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param sheet070400         シート記載内容
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2022Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Sheet070400BorrowedMoneyDto sheet070400, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2022Entity> list = new ArrayList<>();
        Long pageTotal0400 = sheet070400.getPageTotal();
        for (Row070400BorrowedMoneyDto rowDto : sheet070400.getList()) {
            list.add(
                    this.createEntity0400(documentCode, documentPropertyDto, pageTotal0400, rowDto, checkPrivilegeDto));
        }

        return list;
    }

    private OfferingBalancesheetIncome2022Entity createEntity0400(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Long pageTotal,
            final Row070400BorrowedMoneyDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2022Entity incomeEntity = new OfferingBalancesheetIncome2022Entity();

        // 文書属性
        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04);
        incomeEntity.setYoushikiEdaKbn(0); // 設定なし

        // シート内容
        incomeEntity.setPageTotal(pageTotal);

        // 記載内容
        BeanUtils.copyProperties(rowDto, incomeEntity);

        // 項目は借入金
        incomeEntity.setItemName(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04_TEXT);

        // 借り先は相手方名称に入力
        incomeEntity.setPartnerName(rowDto.getKarisaki());
        

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(formatNaturalSearchTextUtil.practice(builder.toString()));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
