package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0711ConsiderationPartyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071101Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071102Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071103Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071101ConsiderationPartyPerspnalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071102ConsiderationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071103ConsiderationPartyPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その3パーティ開催収入をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0711ConsiderationPartyY2025Logic {

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allSheet0711        パーティ開催収入
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2025Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0711ConsiderationPartyDto allSheet0711, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2025Entity> list = new ArrayList<>();

        /* 枝区分項目1 */
        AllSheetKbn071101Dto kbn071101Dto = allSheet0711.getAllSheetKbn071101Dto();
        Long pageTotal1101;
        String partyName1101;
        String sortNo1101;
        for (Sheet071101ConsiderationPartyPerspnalDto sheetDto : kbn071101Dto.getList()) {
            pageTotal1101 = sheetDto.getPageTotal();
            partyName1101 = sheetDto.getPartyName();
            sortNo1101 = sheetDto.getSortNo();
            for (Row070711DonateDto rowDto : sheetDto.getList()) {
                list.add(this.createEntity1100(documentCode, documentPropertyDto,
                        IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, pageTotal1101, partyName1101,
                        sortNo1101, rowDto, checkPrivilegeDto));
            }
        }

        /* 枝区分項目2 */
        AllSheetKbn071102Dto kbn071102Dto = allSheet0711.getAllSheetKbn071102Dto();
        Long pageTotal1102;
        String partyName1102;
        String sortNo1102;
        for (Sheet071102ConsiderationPartyGroupDto sheetDto : kbn071102Dto.getList()) {
            pageTotal1102 = sheetDto.getPageTotal();
            partyName1102 = sheetDto.getPartyName();
            sortNo1102 = sheetDto.getSortNo();
            for (Row070711DonateDto rowDto : sheetDto.getList()) {
                list.add(this.createEntity1100(documentCode, documentPropertyDto,
                        IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, pageTotal1102, partyName1102,
                        sortNo1102, rowDto, checkPrivilegeDto));
            }
        }

        /* 枝区分項目3 */
        AllSheetKbn071103Dto kbn071103Dto = allSheet0711.getAllSheetKbn071103Dto();
        Long pageTotal1103;
        String partyName1103;
        String sortNo1103;
        for (Sheet071103ConsiderationPartyPoliticOrgDto sheetDto : kbn071103Dto.getList()) {
            pageTotal1103 = sheetDto.getPageTotal();
            partyName1103 = sheetDto.getPartyName();
            sortNo1103 = sheetDto.getSortNo();
            for (Row070711DonateDto rowDto : sheetDto.getList()) {
                list.add(this.createEntity1100(documentCode, documentPropertyDto,
                        IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, pageTotal1103, partyName1103,
                        sortNo1103, rowDto, checkPrivilegeDto));
            }
        }

        return list;
    }

    private OfferingBalancesheetIncome2025Entity createEntity1100(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Integer youshikiEdaKbnKoumoku, final Long pageTotal, final String partyName, final String sortNo,
            final Row070711DonateDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2025Entity incomeEntity = new OfferingBalancesheetIncome2025Entity();

        // 文書属性
        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        incomeEntity.setYoushikiEdaKbn(youshikiEdaKbnKoumoku);

        // 様式区分;

        // 様式枝区分項目;
        // ページ計;
        incomeEntity.setPageTotal(pageTotal);
        // パーティ名称;
        incomeEntity.setPartyName(partyName);
        // ソート番号;
        incomeEntity.setSortNo(sortNo);

        BeanUtils.copyProperties(rowDto, incomeEntity);

        /// ** 連番 */
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;ichiren_no

        /// ** 寄付者の名前 */
        // @JacksonXmlProperty(localName = "KIFUSYA_NM")
        // private String kifusha;
        incomeEntity.setItemName(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11_TEXT+"("+partyName+")");
        incomeEntity.setPartnerName(rowDto.getKifusha());

        /// ** 金額 */
        // @JacksonXmlProperty(localName = "KINGAKU")
        // private Long kingaku;kingaku

        /// ** 発生日 */
        // @JacksonXmlProperty(localName = "DT")
        // private String accrualDate;accrualDate
        incomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(incomeEntity.getAccrualDate()));

        /// ** 住所 */
        // @JacksonXmlProperty(localName = "ADR")
        // private String jusho;jusho
        incomeEntity.setPartnerJuusho(rowDto.getJusho());

        /// ** 職業 */
        // @JacksonXmlProperty(localName = "SYOKUGYO")
        // private String shokugyou;

        /// ** 備考 */
        // @JacksonXmlProperty(localName = "BIKOU")
        // private String biko;biko

        /// ** 通し番号 */
        // @JacksonXmlProperty(localName = "SEQ_NO")
        // private Integer tohshibangou;

        /// ** 税額控除フラグ */
        // @JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
        // private Short flgZeigakuKohjo;flgZeigakuKohjo

        /// ** 行区分 */
        // @JacksonXmlProperty(localName = "ROWKBN")
        // private Short gyoukubun;gyoukubun

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(builder.toString().replaceAll(" ", ""));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
