package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024; // NOPMD

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0703JournalY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0704BorrowedY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0705RelatedToGrantsY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0706OtherIncomeY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0707DonateY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0708MediationY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0710SpecificPartyY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0711ConsiderationPartyY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024.income.ConvertSheetDtoToEntity0712PartyMediationY2024Logic;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Repository;

/**
 * 政治資金収支報告書収入の部を登録する
 */
@Component
public class InsertPoliticalOrganizationIncomeAllY2024Logic {

    /** 政治資金収支報告書収入提出分 */
    @Autowired
    private OfferingBalancesheetIncome2024Repository offeringBalancesheetIncome2024Repository;

    /** その3機関誌発行変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0703JournalY2024Logic convertSheetDtoToEntity0703JournalY2024Logic;

    /** その4借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0704BorrowedY2024Logic convertSheetDtoToEntity0704BorrowedY2024Logic;

    /** その5本部支部入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0705RelatedToGrantsY2024Logic convertSheetDtoToEntity0705RelatedToGrantsY2024Logic;

    /** その6借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0706OtherIncomeY2024Logic convertSheetDtoToEntity0706OtherIncomeY2024Logic;

    /** その7借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0707DonateY2024Logic convertSheetDtoToEntity0707DonateY2024Logic;

    /** その8借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0708MediationY2024Logic convertSheetDtoToEntity0708MediationY2024Logic;

    /** その9借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyY2024Logic convertSheetDtoToEntity0709AnonymousInPoliticalPartyY2024Logic;

    /** その10借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0710SpecificPartyY2024Logic convertSheetDtoToEntity0710SpecificPartyY2024Logic;

    /** その11借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0711ConsiderationPartyY2024Logic convertSheetDtoToEntity0711ConsiderationPartyY2024Logic;

    /** その12借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0712PartyMediationY2024Logic convertSheetDtoToEntity0712PartyMediaY2024Logic;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allBookDto          全文書Dto(使用するのは様式7その14と15のみ)
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final AllBookDto allBookDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheetIncome2024Entity> list = new ArrayList<>();

        /* その3 機関誌発行 */
        list.addAll(convertSheetDtoToEntity0703JournalY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0703JournalAndOtherDto().getSheet070300JournalAndOtherDto(), checkPrivilegeDto));

        /* その4 借入金 */
        list.addAll(convertSheetDtoToEntity0704BorrowedY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0704BorrowedMoneyDto().getSheet070400BorrowedMoneyDto(), checkPrivilegeDto));

        /* その5 本部支部交付金 */
        list.addAll(convertSheetDtoToEntity0705RelatedToGrantsY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().getSheet070500IncomeRelatedToGrantsDto(),
                checkPrivilegeDto));

        /* その6 その他収入 */
        list.addAll(convertSheetDtoToEntity0706OtherIncomeY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0706OtherIncomeDto().getSheet070600OtherIncomeDto(), checkPrivilegeDto));

        /* その7 寄付 */
        list.addAll(convertSheetDtoToEntity0707DonateY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0707DonateDto(), checkPrivilegeDto));

        /* その8 寄付のうちあっせん */
        list.addAll(convertSheetDtoToEntity0708MediationY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0708MediationDto(), checkPrivilegeDto));

        /* その9 匿名寄附 */
        list.addAll(convertSheetDtoToEntity0709AnonymousInPoliticalPartyY2024Logic.practice(documentCode,
                documentPropertyDto,
                allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto().getSheet070900AnonymousInPoliticalPartyDto(),
                checkPrivilegeDto));

        /* その10 特定パーティ */
        list.addAll(convertSheetDtoToEntity0710SpecificPartyY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0710SpecificPartyDto().getSheet071000SpecificPartyDto(), checkPrivilegeDto));

        /* その11 政治資金パーティ */
        list.addAll(convertSheetDtoToEntity0711ConsiderationPartyY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0711ConsiderationPartyDto(), checkPrivilegeDto));

        /* その12 政治資金パーティあっせん */
        list.addAll(convertSheetDtoToEntity0712PartyMediaY2024Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0712PartyMediationDto(), checkPrivilegeDto));

        
        // 登録作業
        Long code = 0L;
        Optional<OfferingBalancesheetIncome2024Entity> optional = offeringBalancesheetIncome2024Repository.findFirstByOrderByOfferingBalancesheetIncomeCodeDesc();
        if(!optional.isEmpty()) {
            code = optional.get().getOfferingBalancesheetIncomeCode();
        }

        for(OfferingBalancesheetIncome2024Entity entity : list) {
            code++;
            entity.setOfferingBalancesheetIncomeCode(code);
        }
        
        offeringBalancesheetIncome2024Repository.saveAll(list);

        return list.size();
    }

}
