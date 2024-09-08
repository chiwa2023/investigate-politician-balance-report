package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070900AnonymousInPoliticalPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070900AnonymousInPoliticalPartyDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その9匿名寄附収入をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyY2024Logic {

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param sheet070900         その9匿名寄附
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2024Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Sheet070900AnonymousInPoliticalPartyDto sheet070900, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2024Entity> list = new ArrayList<>();
        Long pageTotal0900 = sheet070900.getPageTotal();

        for (Row070900AnonymousInPoliticalPartyDto rowDto : sheet070900.getList()) {
            list.add(
                    this.createEntity0900(documentCode, documentPropertyDto, pageTotal0900, rowDto, checkPrivilegeDto));
        }

        return list;
    }

    private OfferingBalancesheetIncome2024Entity createEntity0900(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Long pageTotal,
            final Row070900AnonymousInPoliticalPartyDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2024Entity incomeEntity = new OfferingBalancesheetIncome2024Entity();

        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09);
        incomeEntity.setYoushikiEdaKbn(0); // 設定なし

        incomeEntity.setPageTotal(pageTotal);
        BeanUtils.copyProperties(rowDto, incomeEntity);

        /// ** 連番 */
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;

        /// ** 場所 */
        // @JacksonXmlProperty(localName = "BASYO")
        // private String basho;
        // item_name;
        incomeEntity.setItemName(rowDto.getBasho()+"での"+IncomeYoushikiKbnConstants.YOUSHIKI_KBN_09_TEXT);
        incomeEntity.setKaisaiBasho(rowDto.getBasho());

        /// ** 金額 */
        // @JacksonXmlProperty(localName = "KINGAKU")
        // private Long kingaku;

        /// ** 発生日 */
        // @JacksonXmlProperty(localName = "DT")
        // private String accrualDate;
        incomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(incomeEntity.getAccrualDate()));

        /// ** 備考 */
        // @JacksonXmlProperty(localName = "BIKOU")
        // private String biko;

        // TODO 関連者区分

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(builder.toString().replaceAll(" ", ""));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
