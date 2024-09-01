package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0707DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070701DonatePersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070702DonateGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070703DonatePoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その7寄附収入をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0707DonateLogic {
    
    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode 文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allSheet0707DonateDto その1からその3までの寄附情報
     * @param checkPrivilegeDto 権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2025Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0707DonateDto allSheet0707DonateDto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2025Entity> list = new ArrayList<>();

        // 枝区分1
        Sheet070701DonatePersonDto sheet070701 = allSheet0707DonateDto.getAllSheetKbn070701Dto()
                .getSheet070701DonatePersonDto();
        Long pageTotal1 = sheet070701.getPageTotal();
        String sonotaTotal1 = sheet070701.getSonotaTotal();
        for (Row070711DonateDto rowDto : sheet070701.getList()) {
            list.add(this.createEntity0700(documentCode, documentPropertyDto, IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN,pageTotal1, sonotaTotal1, rowDto,
                    checkPrivilegeDto));
        }

        // 枝区分2
        Sheet070702DonateGroupDto sheet070702 = allSheet0707DonateDto.getAllSheetKbn070702Dto()
                .getSheet070702DonateGroupDto();
        Long pageTotal2 = sheet070702.getPageTotal();
        String sonotaTotal2 = sheet070702.getSonotaTotal();
        for (Row070711DonateDto rowDto : sheet070702.getList()) {
            list.add(this.createEntity0700(documentCode, documentPropertyDto,IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, pageTotal2, sonotaTotal2, rowDto,
                    checkPrivilegeDto));
        }

        // 枝区分3
        Sheet070703DonatePoliticOrgDto sheet070703 = allSheet0707DonateDto.getAllSheetKbn070703Dto()
                .getSheet070703DonatePoliticOrgDto();
        Long pageTotal3 = sheet070703.getPageTotal();
        String sonotaTotal3 = sheet070703.getSonotaTotal();
        for (Row070711DonateDto rowDto : sheet070703.getList()) {

            list.add(this.createEntity0700(documentCode, documentPropertyDto,IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, pageTotal3, sonotaTotal3, rowDto,
                    checkPrivilegeDto));
        }

        return list;
    }

    private OfferingBalancesheetIncome2025Entity createEntity0700(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,final Integer youshikiEdaKbnKoumoku, final Long pageTotal,
            final String sonotaTotal, final Row070711DonateDto rowDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2025Entity incomeEntity = new OfferingBalancesheetIncome2025Entity();

        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        incomeEntity.setYoushikiEdaKbn(youshikiEdaKbnKoumoku);

        incomeEntity.setPageTotal(pageTotal);
        incomeEntity.setSonotaTotal(sonotaTotal);
        

        BeanUtils.copyProperties(rowDto, incomeEntity);
        /// ** 連番 */
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;

        /// ** 寄付者の名前 */
        // @JacksonXmlProperty(localName = "KIFUSYA_NM")
        // private String kifusha;
        incomeEntity.setPartnerName(rowDto.getKifusha()); 
        incomeEntity.setItemName(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07_TEXT); 

        /// ** 金額 */
        // @JacksonXmlProperty(localName = "KINGAKU")
        // private Long kingaku;

        /// ** 発生日 */
        // @JacksonXmlProperty(localName = "DT")
        // private String accrualDate;
        incomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(incomeEntity.getAccrualDate())); 

        /// ** 住所 */
        // @JacksonXmlProperty(localName = "ADR")
        // private String jusho;
        incomeEntity.setPartnerJuusho(rowDto.getJusho()); 
        

        /// ** 職業 */
        // @JacksonXmlProperty(localName = "SYOKUGYO")
        // private String shokugyou;

        /// ** 備考 */
        // @JacksonXmlProperty(localName = "BIKOU")
        // private String biko;

        /// ** 通し番号 */
        // @JacksonXmlProperty(localName = "SEQ_NO")
        // private Integer tohshibangou;

        /// ** 税額控除フラグ */
        // @JacksonXmlProperty(localName = "ZEIGAKUKOUJYO")
        // private Short flgZeigakuKohjo;

        /// ** 行区分 */
        // @JacksonXmlProperty(localName = "ROWKBN")
        // private Short gyoukubun;

        // TODO 関連者区分
        final int EDA1 = IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN;
        final int EDA2 = IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN;
        final int EDA3 = IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI;

        switch (youshikiEdaKbnKoumoku) {
            case EDA1: 
                // 個人からのみ探す
                break;

            case EDA2: 
                // 法人からのみ探す

                break;

            case EDA3: 
                // 政治団体からのみ探す

                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + youshikiEdaKbnKoumoku);
        }
        

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName())
        .append(incomeEntity.getPartnerName()).append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(builder.toString().replaceAll(" ", ""));
        
        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
