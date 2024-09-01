package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0703JournalLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0704BorrowedLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0705RelatedToGrantsLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0706OtherIncomeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0707DonateLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0708MediationLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0710SpecificPartyLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0711ConsiderationPartyLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.income.ConvertSheetDtoToEntity0712PartyMediationLogic;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingBalancesheetIncome2025Repository;

/**
 * 政治資金収支報告書収入の部を登録する
 */
@Component
public class InsertPoliticalOrganizationIncomeAllLogic {

    /** 政治資金収支報告書収入提出分 */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** その3機関誌発行変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0703JournalLogic convertSheetDtoToEntity0703JournalLogic;

    /** その4借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0704BorrowedLogic convertSheetDtoToEntity0704BorrowedLogic;

    /** その5本部支部入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0705RelatedToGrantsLogic convertSheetDtoToEntity0705RelatedToGrantsLogic;

    /** その6借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0706OtherIncomeLogic convertSheetDtoToEntity0706OtherIncomeLogic;

    /** その7借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0707DonateLogic convertSheetDtoToEntity0707DonateLogic;

    /** その8借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0708MediationLogic convertSheetDtoToEntity0708MediationLogic;

    /** その9借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0709AnonymousInPoliticalPartyLogic convertSheetDtoToEntity0709AnonymousInPoliticalPartyLogic;

    /** その10借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0710SpecificPartyLogic convertSheetDtoToEntity0710SpecificPartyLogic;

    /** その11借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0711ConsiderationPartyLogic convertSheetDtoToEntity0711ConsiderationPartyLogic;

    /** その12借入金変換Logic */
    @Autowired
    private ConvertSheetDtoToEntity0712PartyMediationLogic convertSheetDtoToEntity0712PartyMediationLogic;

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

        List<OfferingBalancesheetIncome2025Entity> list = new ArrayList<>();

        /* その3 機関誌発行 */
        list.addAll(convertSheetDtoToEntity0703JournalLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0703JournalAndOtherDto().getSheet070300JournalAndOtherDto(), checkPrivilegeDto));

        /* その4 借入金 */
        list.addAll(convertSheetDtoToEntity0704BorrowedLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0704BorrowedMoneyDto().getSheet070400BorrowedMoneyDto(), checkPrivilegeDto));

        /* その5 本部支部交付金 */
        list.addAll(convertSheetDtoToEntity0705RelatedToGrantsLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0705IncomeRelatedToGrantsDto().getSheet070500IncomeRelatedToGrantsDto(),
                checkPrivilegeDto));

        /* その6 その他収入 */
        list.addAll(convertSheetDtoToEntity0706OtherIncomeLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0706OtherIncomeDto().getSheet070600OtherIncomeDto(), checkPrivilegeDto));

        /* その7 寄付 */
        list.addAll(convertSheetDtoToEntity0707DonateLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0707DonateDto(), checkPrivilegeDto));

        /* その8 寄付のうちあっせん */
        list.addAll(convertSheetDtoToEntity0708MediationLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0708MediationDto(), checkPrivilegeDto));

        /* その9 匿名寄附 */
        list.addAll(convertSheetDtoToEntity0709AnonymousInPoliticalPartyLogic.practice(documentCode,
                documentPropertyDto,
                allBookDto.getAllSheet0709AnonymousInPoliticalPartyDto().getSheet070900AnonymousInPoliticalPartyDto(),
                checkPrivilegeDto));

        /* その10 特定パーティ */
        list.addAll(convertSheetDtoToEntity0710SpecificPartyLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0710SpecificPartyDto().getSheet071000SpecificPartyDto(), checkPrivilegeDto));

        /* その11 政治資金パーティ */
        list.addAll(convertSheetDtoToEntity0711ConsiderationPartyLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0711ConsiderationPartyDto(), checkPrivilegeDto));

        /* その12 政治資金パーティあっせん */
        list.addAll(convertSheetDtoToEntity0712PartyMediationLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0712PartyMediationDto(), checkPrivilegeDto));

        
        // 登録作業
        Long code = 0L;
        Optional<OfferingBalancesheetIncome2025Entity> optional = offeringBalancesheetIncome2025Repository.findFirstByOrderByOfferingBalancesheetIncomeCodeDesc();
        if(!optional.isEmpty()) {
            code = optional.get().getOfferingBalancesheetIncomeCode();
        }

        for(OfferingBalancesheetIncome2025Entity entity : list) {
            code++;
            entity.setOfferingBalancesheetIncomeCode(code);
        }
        
        offeringBalancesheetIncome2025Repository.saveAll(list);

        return list.size();
    }

}
