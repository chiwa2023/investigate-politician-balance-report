package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.constants.blancesheet_report.RealEstateKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0718AssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0719RealEstateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071800ClassificationOfAssetsByItemDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071900CurrentStatusOfRealEstateUseDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071801LandAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071802BuildingsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071803SurfaceRightsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071804MovablesAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071805SavingsAmmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071806TrustAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071807SecuritiesAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071808InvestmentAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071809LoanAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071810DepositAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071811FacilityUsageRightsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071812BorrowingsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071900RealEstateDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0718Estate2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0719RealEstate2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingBalancesheet0718Estate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingBalancesheet0719RealEstate2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書の資産明細を登録する
 * 悲観テーブルロックの時間をできうる限り短くするため、単純ロジック数多く繰り返しているだけで複雑度上がっていることを加味して、
 * すべての資産データ登録をこのLogicに集めた
 */
@Component
public class InsertPoliticalOrganizationEstateAllLogic {

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2025Repository offeringBalancesheet0718Estate2025Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2025Repository offeringBalancesheet0719RealEstate2025Repository;

    /**
     * 登録作業を行う
     *
     * @param documentCode              文書同一識別コード
     * @param documentPropertyDto       文書属性Dto
     * @param allSheet0718AssetsDto     様式7その18資産Dto
     * @param allSheet0719RealEstateDto 様式7その19不動産Dto
     * @param checkPrivilegeDto         権限Dto
     * @return このロジックで登録された行数
     */
    public int practice(final Long documentCode, // SUPPRESS CHECKSTYLE NOPMD
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0718AssetsDto allSheet0718AssetsDto,
            final AllSheet0719RealEstateDto allSheet0719RealEstateDto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingBalancesheet0718Estate2025Entity> listAsset = new ArrayList<>();

        // シート18-1を登録する
        Sheet071801LandAmongAssetsDto sheet071801Dto = allSheet0718AssetsDto.getAllSheetKbn071801Dto()
                .getSheet071801LandAmongAssetsDto();

        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071801Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-2を登録する
        Sheet071802BuildingsAmongAssetsDto sheet071802Dto = allSheet0718AssetsDto.getAllSheetKbn071802Dto()
                .getSheet071802BuildingsAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071802Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-3を登録する
        Sheet071803SurfaceRightsAmongAssetsDto sheet071803Dto = allSheet0718AssetsDto.getAllSheetKbn071803Dto()
                .getSheet071803SurfaceRightsAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071803Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-4を登録する
        Sheet071804MovablesAmongAssetsDto sheet071804Dto = allSheet0718AssetsDto.getAllSheetKbn071804Dto()
                .getSheet071804MovablesAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071804Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-5を登録する
        Sheet071805SavingsAmmongAssetsDto sheet071805Dto = allSheet0718AssetsDto.getAllSheetKbn071805Dto()
                .getSheet071805SavingsAmmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071805Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-6を登録する
        Sheet071806TrustAmongAssetsDto sheet071806Dto = allSheet0718AssetsDto.getAllSheetKbn071806Dto()
                .getSheet071806TrustAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071806Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-7を登録する
        Sheet071807SecuritiesAmongAssetsDto sheet071807Dto = allSheet0718AssetsDto.getAllSheetKbn071807Dto()
                .getSheet071807SecuritiesAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071807Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-8を登録する
        Sheet071808InvestmentAmongAssetsDto sheet071808Dto = allSheet0718AssetsDto.getAllSheetKbn071808Dto()
                .getSheet071808InvestmentAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071808Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-9を登録する
        Sheet071809LoanAmongAssetsDto sheet071809Dto = allSheet0718AssetsDto.getAllSheetKbn071809Dto()
                .getSheet071809LoanAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071809Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-10を登録する
        Sheet071810DepositAmongAssetsDto sheet071810Dto = allSheet0718AssetsDto.getAllSheetKbn071810Dto()
                .getSheet071810DepositAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071810Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-11を登録する
        Sheet071811FacilityUsageRightsAmongAssetsDto sheet071811Dto = allSheet0718AssetsDto.getAllSheetKbn071811Dto()
                .getSheet071811FacilityUsageRightsAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071811Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // シート18-12を登録する
        Sheet071812BorrowingsAmongAssetsDto sheet071812Dto = allSheet0718AssetsDto.getAllSheetKbn071812Dto()
                .getSheet071812BorrowingsAmongAssetsDto();
        for (Row071800ClassificationOfAssetsByItemDto rowDto : sheet071812Dto.getList()) {
            listAsset.add(this.createAssetEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // 同一識別コードの設定
        Long codeAsset = 0L;
        Optional<OfferingBalancesheet0718Estate2025Entity> optionalAsset = offeringBalancesheet0718Estate2025Repository
                .findFirstByOrderByOfferingBalancesheet0718EstateCodeDesc();
        if (!optionalAsset.isEmpty()) {
            codeAsset = optionalAsset.get().getOfferingBalancesheet0718EstateCode();
        }
        for (OfferingBalancesheet0718Estate2025Entity entity : listAsset) {
            codeAsset++;
            entity.setOfferingBalancesheet0718EstateCode(codeAsset);
        }

        final int sizeAsset = offeringBalancesheet0718Estate2025Repository.saveAll(listAsset).size();

        // シート19-1,2,3を登録する
        List<OfferingBalancesheet0719RealEstate2025Entity> listRealEstate = new ArrayList<>();
        Integer kbnEstate;
        for (Sheet071900RealEstateDto sheetDto : allSheet0719RealEstateDto.getListSheet0719()) {

            kbnEstate = sheetDto.getKbnRealEstitate();

            for (Row071900CurrentStatusOfRealEstateUseDto rowDto : sheetDto.getList()) {

                listRealEstate.add(this.createRealEstateEntity(documentCode, documentPropertyDto, rowDto, kbnEstate,
                        checkPrivilegeDto));
            }
        }

        // 同一識別コードの設定
        Long codeRealEstate = 0L;
        Optional<OfferingBalancesheet0719RealEstate2025Entity> optionalRealEstate = offeringBalancesheet0719RealEstate2025Repository
                .findFirstByOrderByOfferingBalancesheet0719RealEstateCodeDesc();
        if (!optionalRealEstate.isEmpty()) {
            codeRealEstate = optionalRealEstate.get().getOfferingBalancesheet0719RealEstateCode();
        }
        for (OfferingBalancesheet0719RealEstate2025Entity entity : listRealEstate) {
            codeRealEstate++;
            entity.setOfferingBalancesheet0719RealEstateCode(codeRealEstate);
        }

        int sizeRealEstate = offeringBalancesheet0719RealEstate2025Repository.saveAll(listRealEstate).size();

        return sizeAsset + sizeRealEstate;
    }

    private OfferingBalancesheet0718Estate2025Entity createAssetEntity(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Row071800ClassificationOfAssetsByItemDto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheet0718Estate2025Entity balancesheetEntity = new OfferingBalancesheet0718Estate2025Entity();

        balancesheetEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, balancesheetEntity);
        BeanUtils.copyProperties(rowDto, balancesheetEntity);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, balancesheetEntity, DataHistoryStatusConstants.INSERT);

        return balancesheetEntity;
    }

    private OfferingBalancesheet0719RealEstate2025Entity createRealEstateEntity(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final Row071900CurrentStatusOfRealEstateUseDto rowDto, final Integer kbn,
            final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheet0719RealEstate2025Entity balancesheetEntity = new OfferingBalancesheet0719RealEstate2025Entity();

        balancesheetEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, balancesheetEntity);
        BeanUtils.copyProperties(rowDto, balancesheetEntity);

        balancesheetEntity.setRealEstateKbn(kbn);
        balancesheetEntity.setRealEstateKbnName(RealEstateKbnConstants.getText(kbn));

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, balancesheetEntity, DataHistoryStatusConstants.INSERT);

        return balancesheetEntity;
    }
}
