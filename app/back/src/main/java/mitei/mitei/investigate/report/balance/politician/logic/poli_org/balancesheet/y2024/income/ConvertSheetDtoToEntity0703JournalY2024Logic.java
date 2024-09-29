package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070300JournalAndOtherDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その3機関誌発行その他事業収入をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0703JournalY2024Logic {

    /** 自然検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換作業を行う
     *
     * @param documentCode 文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param sheet070300 記載シート
     * @param checkPrivilegeDto 権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public  List<OfferingBalancesheetIncome2024Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Sheet070300JournalAndOtherDto sheet070300,
            final CheckPrivilegeDto checkPrivilegeDto){
        
        List<OfferingBalancesheetIncome2024Entity> list = new ArrayList<>();
        Long pageTotal0300 = sheet070300.getPageTotal();
        for(Row070300JournalAndOtherDto rowDto : sheet070300.getList()) {
            list.add(
                    this.createEntity0300(documentCode, documentPropertyDto, pageTotal0300 ,rowDto, checkPrivilegeDto));
        }
        
        return list;
    }

    private OfferingBalancesheetIncome2024Entity createEntity0300(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Long pageTotal,
            final Row070300JournalAndOtherDto rowDto,
            final CheckPrivilegeDto checkPrivilegeDto) {
        
        OfferingBalancesheetIncome2024Entity incomeEntity = new OfferingBalancesheetIncome2024Entity();
        
        // 文書属性
        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        //様式区分;
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_03);
        incomeEntity.setYoushikiEdaKbn(0); // 初期値

        //ページ計;
        incomeEntity.setPageTotal(pageTotal);

        // 記載内容
        BeanUtils.copyProperties(rowDto, incomeEntity);

        incomeEntity.setItemName(rowDto.getJigyoNoShurui());
        
        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName())
        .append(incomeEntity.getPartnerName()).append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(formatNaturalSearchTextUtil.practice(builder.toString()));
        
        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
