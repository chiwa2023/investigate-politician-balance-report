package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071000SpecificPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071000SpecificPartyDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その10特定パーティ収入をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0710SpecificPartyY2024Logic {

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param sheet071000 その10特定パーティ
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2024Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Sheet071000SpecificPartyDto sheet071000, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2024Entity> list = new ArrayList<>();
        Long pageTotal1000 = sheet071000.getPageTotal();
        for (Row071000SpecificPartyDto rowDto : sheet071000.getList()) {

            list.add(
                    this.createEntity1000(documentCode, documentPropertyDto, pageTotal1000, rowDto, checkPrivilegeDto));
        }

        return list;
    }

    private OfferingBalancesheetIncome2024Entity createEntity1000(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Long pageTotal,
            final Row071000SpecificPartyDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2024Entity incomeEntity = new OfferingBalancesheetIncome2024Entity();

        // 文書属性
        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_10);
        incomeEntity.setYoushikiEdaKbn(0); // 設定なし

        incomeEntity.setPageTotal(pageTotal);
        BeanUtils.copyProperties(rowDto, incomeEntity);

        /// ** 連番 */
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;

        /// ** パーティ名称 */
        // @JacksonXmlProperty(localName = "PATYI_NM")
        // private String partyName;
        // →item_name
        incomeEntity.setItemName(rowDto.getPartyName() + "(" + rowDto.getKaisaiBasho() + ")");

        /// ** 金額 */
        // @JacksonXmlProperty(localName = "KINGAKU")
        // private Long kingaku;

        /// ** 支払い数 */
        // @JacksonXmlProperty(localName = "SHIHARAI_SU")
        // private Long shiharaisu;

        /// ** 開催日 */
        // @JacksonXmlProperty(localName = "KAISAI_DT")
        // private String accrualDate;
        incomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(rowDto.getAccrualDate()));

        /// ** 開催場所 */
        // @JacksonXmlProperty(localName = "KAISAI_BASYO")
        // private String kaisaiBasho;

        /// ** 備考 */
        // @JacksonXmlProperty(localName = "BIKOU")
        // private String biko;

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(builder.toString().replaceAll(" ", ""));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
