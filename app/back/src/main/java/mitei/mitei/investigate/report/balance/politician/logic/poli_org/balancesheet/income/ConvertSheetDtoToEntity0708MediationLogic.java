package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0708MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070801MediationPersonDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070802MediationGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070803MediationPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その8寄附あっせん収入収入をEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0708MediationLogic {

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allSheet0708MediationDto 寄付のうちあっせん
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2025Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0708MediationDto allSheet0708MediationDto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2025Entity> list = new ArrayList<>();

        // 枝区分1
        Sheet070801MediationPersonDto sheet070801 = allSheet0708MediationDto.getAllSheetKbn070801Dto()
                .getSheet070801MediationPersonDto();
        Long pageTotal1 = sheet070801.getPageTotal();
        String sonotaTotal1 = sheet070801.getSonotaTotal();
        for (Row070812MediationDto rowDto : sheet070801.getList()) {
            list.add(this.createEntity0800(documentCode, documentPropertyDto,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, pageTotal1, sonotaTotal1, rowDto,
                    checkPrivilegeDto));
        }

        // 枝区分2
        Sheet070802MediationGroupDto sheet070802 = allSheet0708MediationDto.getAllSheetKbn070802Dto()
                .getSheet070802MediationGroupDto();
        Long pageTotal2 = sheet070802.getPageTotal();
        String sonotaTotal2 = sheet070802.getSonotaTotal();
        for (Row070812MediationDto rowDto : sheet070802.getList()) {
            list.add(this.createEntity0800(documentCode, documentPropertyDto,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, pageTotal2, sonotaTotal2, rowDto,
                    checkPrivilegeDto));
        }

        // 枝区分3
        Sheet070803MediationPoliticOrgDto sheet070803 = allSheet0708MediationDto.getAllSheetKbn070803Dto()
                .getSheet070803MediationPoliticOrgDto();
        Long pageTotal3 = sheet070803.getPageTotal();
        String sonotaTotal3 = sheet070803.getSonotaTotal();
        for (Row070812MediationDto rowDto : sheet070803.getList()) {

            list.add(this.createEntity0800(documentCode, documentPropertyDto,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, pageTotal3, sonotaTotal3, rowDto,
                    checkPrivilegeDto));
        }

        return list;
    }

    private OfferingBalancesheetIncome2025Entity createEntity0800(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Integer youshikiEdaKbnKoumoku, final Long pageTotal, final String sonotaTotal,
            final Row070812MediationDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2025Entity incomeEntity = new OfferingBalancesheetIncome2025Entity();

        // 文書属性
        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        incomeEntity.setYoushikiEdaKbn(youshikiEdaKbnKoumoku);

        incomeEntity.setPageTotal(pageTotal);
        incomeEntity.setSonotaTotal(sonotaTotal);

        BeanUtils.copyProperties(rowDto, incomeEntity);

        /// ** 連番 */
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;

        /// ** 名前 */
        // @JacksonXmlProperty(localName = "NM")
        // private String name;
        incomeEntity.setPartnerName(rowDto.getName());
        incomeEntity.setItemName(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08_TEXT);

        /// ** 金額 */
        // @JacksonXmlProperty(localName = "KINGAKU")
        // private Long kingaku;

        /// ** 発生日 */
        // @JacksonXmlProperty(localName = "DT")
        // private String accrualDate;
        incomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(incomeEntity.getAccrualDate()));

        /// ** 斡旋の期間 */
        // @JacksonXmlProperty(localName = "KIKAN")
        // private String periodMediate;

        /// ** 住所 */
        // @JacksonXmlProperty(localName = "ADR")
        // private String juusho;
        incomeEntity.setPartnerJuusho(rowDto.getJuusho());

        /// ** 職業 */
        // @JacksonXmlProperty(localName = "SYOKUGYO")
        // private String shokugyou;

        /// ** 備考 */
        // @JacksonXmlProperty(localName = "BIKOU")
        // private String biko;

        /// ** 通し番号 */
        // @JacksonXmlProperty(localName = "SEQ_NO")
        // private Integer tohshibangou;

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
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(builder.toString().replaceAll(" ", ""));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
