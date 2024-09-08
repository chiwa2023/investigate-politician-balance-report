package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070500IncomeRelatedToGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070500IncomeRelatedToGrantsDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その5本部支部交付金をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0705RelatedToGrantsY2024Logic {

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param sheet070500         シート記載内容
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2024Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Sheet070500IncomeRelatedToGrantsDto sheet070500, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2024Entity> list = new ArrayList<>();
        Long pageTotal0500 = sheet070500.getPageTotal();
        for (Row070500IncomeRelatedToGrantsDto rowDto : sheet070500.getList()) {

            list.add(
                    this.createEntity0500(documentCode, documentPropertyDto, pageTotal0500, rowDto, checkPrivilegeDto));
        }

        return list;
    }

    private OfferingBalancesheetIncome2024Entity createEntity0500(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Long pageTotal,
            final Row070500IncomeRelatedToGrantsDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2024Entity incomeEntity = new OfferingBalancesheetIncome2024Entity();

        // 文書属性
        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_05);
        incomeEntity.setYoushikiEdaKbn(0); // 設定なし

        // シート内容
        incomeEntity.setPageTotal(pageTotal);
        BeanUtils.copyProperties(rowDto, incomeEntity);

        // 相手先だけを記載しているので項目名は様式区分名称
        incomeEntity.setItemName(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_05_TEXT);

        // 発生日実値
        incomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(incomeEntity.getAccrualDate()));

        // 本部支部の名称
        incomeEntity.setPartnerName(rowDto.getHonbuShibuName());
        // 事務所の住所
        incomeEntity.setPartnerJuusho(rowDto.getJimushoJuusho());

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(builder.toString().replaceAll(" ", ""));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
