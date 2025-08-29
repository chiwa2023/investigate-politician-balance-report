package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022; // NOPMD

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import mitei.mitei.common.constants.blancesheet_report.OutcomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071402Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071403Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071501Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071502Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071503Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071504Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071505Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071506Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071507Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071508Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071509Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071415OrdinaryExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071401UtilityCostsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071501OrganizationalActivityExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071502ElectionRelatedExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071503MagazinePublicationExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071504AdvertisingExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071505PartyHostingFeeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071506OtherBusinessExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071507ResearchExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071508DonationsGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071509OtherExpensesDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.SearchPartnerHistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.SearchPartnerHistoryResultDto;
import reactor.core.publisher.Mono;

/**
 * 政治資金収支報告書の支出を登録する
 */
@Component
public class InsertPoliticalOrganizationOutcomeAllY2022Logic {

    /** 政治資金収支報告書支出提出分Repository */
    @Autowired
    private OfferingBalancesheetOutcome2022Repository offeringBalancesheetOutcome2022Repository;

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
     * @param allBookDto          全文書Dto(使用するのは様式7その14と15のみ)
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode, // SUPPRESS CHECKSTYLE NOPMD
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final AllBookDto allBookDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetOutcome2022Entity> list = new ArrayList<>();

        final String HIMOKU_BLANK = "";

        // シート14-1を登録する
        Sheet071401UtilityCostsDto sheet071401UtilityCostsDto = allBookDto.getAllSheet0714ConstsDto()
                .getAllSheetKbn071401Dto().getSheet071401UtilityCostsDto();
        list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14,
                OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_KOUNETSUHI, sheet071401UtilityCostsDto.getList(),
                sheet071401UtilityCostsDto.getPageTotal(), sheet071401UtilityCostsDto.getSonotaTotal(), HIMOKU_BLANK,
                checkPrivilegeDto));

        // シート14-2を登録する
        AllSheetKbn071402Dto allSheetKbn071402Dto = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071402Dto();
        list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14,
                OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_SHOUMOUHINHI,
                allSheetKbn071402Dto.getSheet071402EquipmentCostsDto().getList(),
                allSheetKbn071402Dto.getSheet071402EquipmentCostsDto().getPageTotal(),
                allSheetKbn071402Dto.getSheet071402EquipmentCostsDto().getSonotaTotal(), HIMOKU_BLANK,
                checkPrivilegeDto));

        // シート14-3を登録する
        AllSheetKbn071403Dto allSheetKbn071403Dto = allBookDto.getAllSheet0714ConstsDto().getAllSheetKbn071403Dto();
        list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_14,
                OutcomeYoushikiKbnConstants.YOUSHIKI_EDA14_KBN_JIMUSHOHI,
                allSheetKbn071403Dto.getSheet071403OfficeExpensesDto().getList(),
                allSheetKbn071403Dto.getSheet071403OfficeExpensesDto().getPageTotal(),
                allSheetKbn071403Dto.getSheet071403OfficeExpensesDto().getSonotaTotal(), HIMOKU_BLANK,
                checkPrivilegeDto));

        // シート15-1を登録する
        AllSheetKbn071501Dto allSheetKbn071501Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071501Dto();
        for (Sheet071501OrganizationalActivityExpensesDto sheetDto : allSheetKbn071501Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SOSHIKIKATSUDOUHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-2を登録する
        AllSheetKbn071502Dto allSheetKbn071502Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071502Dto();
        for (Sheet071502ElectionRelatedExpensesDto sheetDto : allSheetKbn071502Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENKYOKATSUDOUHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-3を登録する
        AllSheetKbn071503Dto allSheetKbn071503Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071503Dto();
        for (Sheet071503MagazinePublicationExpensesDto sheetDto : allSheetKbn071503Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIKANSHIHAKKOUHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-4を登録する
        AllSheetKbn071504Dto allSheetKbn071504Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto();
        for (Sheet071504AdvertisingExpensesDto sheetDto : allSheetKbn071504Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENDENKOUKOKUHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-5を登録する
        AllSheetKbn071505Dto allSheetKbn071505Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto();
        for (Sheet071505PartyHostingFeeDto sheetDto : allSheetKbn071505Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_PARTYKAISAIHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-6を登録する
        AllSheetKbn071506Dto allSheetKbn071506Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto();
        for (Sheet071506OtherBusinessExpensesDto sheetDto : allSheetKbn071506Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAJIGYOU, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-7を登録する
        AllSheetKbn071507Dto allSheetKbn071507Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto();
        for (Sheet071507ResearchExpensesDto sheetDto : allSheetKbn071507Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_CHOUSAKENKYUHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-8を登録する
        AllSheetKbn071508Dto allSheetKbn071508Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto();
        for (Sheet071508DonationsGrantsDto sheetDto : allSheetKbn071508Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIFUKOUFUKIN, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // シート15-9を登録する
        AllSheetKbn071509Dto allSheetKbn071509Dto = allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto();
        for (Sheet071509OtherExpensesDto sheetDto : allSheetKbn071509Dto.getList()) {
            list.addAll(this.createList(documentCode, documentPropertyDto, OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAKEIHI, sheetDto.getList(),
                    sheetDto.getPageTotal(), sheetDto.getSonotaTotal(), sheetDto.getHimoku(), checkPrivilegeDto));
        }

        // 同一識別コードの設定
        Long code = 0L;
        Optional<OfferingBalancesheetOutcome2022Entity> optional = offeringBalancesheetOutcome2022Repository
                .findFirstByOrderByOfferingBalancesheetOutcomeCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getOfferingBalancesheetOutcomeCode();
        }
        for (OfferingBalancesheetOutcome2022Entity entity : list) {
            code++;
            entity.setOfferingBalancesheetOutcomeCode(code);
        }

        offeringBalancesheetOutcome2022Repository.saveAll(list);

        return list.size();
    }

    private List<OfferingBalancesheetOutcome2022Entity> createList(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Integer youshikiKbn,
            final Integer youshikiEdaKbn, final List<Row071415OrdinaryExpensesDto> listRow, final Long pageTotal,
            final String sonotaTotal, final String himoku, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetOutcome2022Entity> list = new ArrayList<>();

        for (Row071415OrdinaryExpensesDto rowDto : listRow) {
            list.add(this.createEntity(documentCode, documentPropertyDto, youshikiKbn, youshikiEdaKbn, rowDto,
                    pageTotal, sonotaTotal, himoku, checkPrivilegeDto));
        }

        return list;

    }

    private OfferingBalancesheetOutcome2022Entity createEntity(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final Integer youshikiKbn,
            final Integer youshikiEdaKbn, final Row071415OrdinaryExpensesDto rowDto, final Long pageTotal,
            final String sonotaTotal, final String himoku, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheetOutcome2022Entity outcomeEntity = new OfferingBalancesheetOutcome2022Entity();

        outcomeEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, outcomeEntity);

        // 様式区分
        outcomeEntity.setYoushikiKbn(youshikiKbn);
        outcomeEntity.setYoushikiEdaKbn(youshikiEdaKbn);

        // シート情報
        outcomeEntity.setPageTotal(pageTotal);
        outcomeEntity.setSonotaTotal(sonotaTotal);
        outcomeEntity.setHimoku(himoku);

        BeanUtils.copyProperties(rowDto, outcomeEntity);

        outcomeEntity.setPartnerName(rowDto.getName());
        outcomeEntity.setPartnerJusho(rowDto.getJusho());

        outcomeEntity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(outcomeEntity.getAccrualDate()));

        SearchPartnerHistoryCapsuleDto searchCapsuleDto = new SearchPartnerHistoryCapsuleDto();
        searchCapsuleDto.setPartnerName(outcomeEntity.getPartnerName());
        searchCapsuleDto.setAllAddress(outcomeEntity.getPartnerJuusho());
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3NTY5OTA2NzksInN1YiI6IjU1NTU1QHNlaWppc2hpa2luLm5ldCIsImlhdCI6MTc1NjM4NTg3OX0.cOfvbWr3S1xn6dve-ly1BCMZPZnrxsIQtaOL1TCuIAjJQpnW-1gfsYhBseZiqoqkYBvRam6IbZ05jdt3uqor4qJMzXkVNLyhJmFIu7t0uJ6uwfmdpeCx30yUmAAwYTb6K4rDB7JWUvjZcG6EHVcZ-ADKx1OlfsymIlDvIs8eotKNmcyt9RUa3jViymwQ-lBvOkL8cDlD55zdnWSRayTYlQUxUMjWGoEbOPTFuJBLSSexUGpTYIUhCgoTUplmxliHi6Gsq5-cZ-TApP_H7RdtAzdHdZLg7_HrQkc5rOHhehFZmrLGjFRAx-84KL4RcbyVL5XiG3Qye0RYobjdKvkV-w";

        SearchPartnerHistoryResultDto personResultDto = WebClient.create("http://localhost:6080/kanrenssha-list/search")
                .post().header("X-AUTH-TOKEN", "Bearer " + token)
                .body(Mono.just(searchCapsuleDto), SearchPartnerHistoryResultDto.class)
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(SearchPartnerHistoryResultDto.class)
                .blockFirst();
        final int resultOne = 1;
        List<PartnerCommonInfoDto> listRelation = personResultDto.getListHistoryInfo();
        if (listRelation.size() == resultOne) {
            PartnerCommonInfoDto partnerDto = listRelation.get(0);
            outcomeEntity.setRelationKbn(Integer.parseInt(String.valueOf(partnerDto.getKanrenshaKbn())));
            switch (partnerDto.getKanrenshaKbn()) {
                case 1:
                    outcomeEntity.setRelationPersonNameOutcome(partnerDto.getPartnerName());
                    outcomeEntity.setRelationPersonNameDelegate(partnerDto.getKanrenshaCode());
                    break;
                case 2:
                    outcomeEntity.setRelationCorpNameOutcome(partnerDto.getPartnerName());
                    outcomeEntity.setRelationPersonNameDelegate(partnerDto.getKanrenshaCode());
                    break;
                case 3:
                    outcomeEntity.setRelationPoliticalOrgNameOutcome(partnerDto.getPartnerName());
                    outcomeEntity.setRelationPersonNameDelegate(partnerDto.getKanrenshaCode());
                    break;
                default:
                    // 何もしない
                    break;
            }
        }

        // 自由検索 費目＋相手方氏名＋相手方住所
        StringBuilder builder = new StringBuilder();
        builder.append(himoku).append(outcomeEntity.getMokuteki()).append(outcomeEntity.getPartnerName())
                .append(outcomeEntity.getPartnerJuusho());
        outcomeEntity.setSearchWords(formatNaturalSearchTextUtil.practice(builder.toString()));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, outcomeEntity, DataHistoryStatusConstants.INSERT);

        return outcomeEntity;
    }
}
