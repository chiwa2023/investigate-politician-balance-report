package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025.income;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0712PartyMediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071201Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071202Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071203Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071201ConsiderationMediationPartyPersonalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071202ConsiderationMediationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071203ConsiderationMediationPartyPoliticOrgDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式7その12パーティ収入あっせんをEntity変換
 */
@Component
public class ConvertSheetDtoToEntity0712PartyMediationY2025Logic {

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 自然検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allSheet0712 開催パーティ収入のうちあっせん
     * @param checkPrivilegeDto   権限確認Dto
     * @return 収支報告書収入Entityリスト
     */
    public List<OfferingBalancesheetIncome2025Entity> practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0712PartyMediationDto allSheet0712, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2025Entity> list = new ArrayList<>();

        // 枝区分項目1
        AllSheetKbn071201Dto kbn071201Dto = allSheet0712.getAllSheetKbn071201Dto();
        Long pageTotal1201;
        String partyName1201;
        String sortNo1201;
        for (Sheet071201ConsiderationMediationPartyPersonalDto sheetDto : kbn071201Dto.getList()) {
            pageTotal1201 = sheetDto.getPageTotal();
            partyName1201 = sheetDto.getPartyName();
            sortNo1201 = sheetDto.getSortNo();
            for (Row070812MediationDto rowDto : sheetDto.getList()) {

                list.add(this.createEntity1200(documentCode, documentPropertyDto,
                        IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, pageTotal1201, partyName1201,
                        sortNo1201, rowDto, checkPrivilegeDto));
            }
        }

        // 枝区分項目2
        AllSheetKbn071202Dto kbn071202Dto = allSheet0712.getAllSheetKbn071202Dto();
        Long pageTotal1202;
        String partyName1202;
        String sortNo1202;
        for (Sheet071202ConsiderationMediationPartyGroupDto sheetDto : kbn071202Dto.getList()) {
            pageTotal1202 = sheetDto.getPageTotal();
            partyName1202 = sheetDto.getPartyName();
            sortNo1202 = sheetDto.getSortNo();
            for (Row070812MediationDto rowDto : sheetDto.getList()) {

                list.add(this.createEntity1200(documentCode, documentPropertyDto,
                        IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, pageTotal1202, partyName1202,
                        sortNo1202, rowDto, checkPrivilegeDto));
            }
        }

        // 枝区分項目3
        AllSheetKbn071203Dto kbn071203Dto = allSheet0712.getAllSheetKbn071203Dto();
        Long pageTotal1203;
        String partyName1203;
        String sortNo1203;
        for (Sheet071203ConsiderationMediationPartyPoliticOrgDto sheetDto : kbn071203Dto.getList()) {
            pageTotal1203 = sheetDto.getPageTotal();
            partyName1203 = sheetDto.getPartyName();
            sortNo1203 = sheetDto.getSortNo();
            for (Row070812MediationDto rowDto : sheetDto.getList()) {

                list.add(this.createEntity1200(documentCode, documentPropertyDto,
                        IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, pageTotal1203, partyName1203,
                        sortNo1203, rowDto, checkPrivilegeDto));
            }
        }

        return list;
    }

    private OfferingBalancesheetIncome2025Entity createEntity1200(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Integer youshikiEdaKbnKoumoku, final Long pageTotal, final String partyName, final String sortNo,
            final Row070812MediationDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetIncome2025Entity incomeEntity = new OfferingBalancesheetIncome2025Entity();

        incomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, incomeEntity);

        // 様式区分
        incomeEntity.setYoushikiKbn(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
        incomeEntity.setYoushikiEdaKbn(youshikiEdaKbnKoumoku);

        incomeEntity.setPageTotal(pageTotal);
        incomeEntity.setPartyName(partyName);
        incomeEntity.setSortNo(sortNo);

        BeanUtils.copyProperties(rowDto, incomeEntity);

        /// ** 連番 */
        // @JacksonXmlProperty(localName = "ICHIREN_NO")
        // private Integer ichirenNo;

        /// ** 名前 */
        // @JacksonXmlProperty(localName = "NM")
        // private String name;
        incomeEntity.setPartnerName(rowDto.getName());
        incomeEntity.setItemName(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12_TEXT + "(" + partyName + ")");

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

        // 自由検索 項目名称+相手方名称+相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(incomeEntity.getItemName()).append(incomeEntity.getPartnerName())
                .append(incomeEntity.getPartnerJuusho());
        incomeEntity.setSearchWords(formatNaturalSearchTextUtil.practice(builder.toString()));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, incomeEntity, DataHistoryStatusConstants.INSERT);

        return incomeEntity;
    }

}
