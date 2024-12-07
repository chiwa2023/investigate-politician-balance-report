package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.BeanUtils;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * 政治資金収支報告書登録準備ワークテーブルから政治資金収支報告書Dtoに変換する
 */
public class ConvertWorkTableToReadXmlBalancesheetResultLogic {

    /**
     * 処理を行う
     *
     * @param balancesheetReportEntity 政治資金収支報告書登録準備Entity
     * @return 政治資金収支報告書Dto
     */
    public ReadXmlBalancesheetResultDto practice(final WkTblPoliOrgBalancesheetReportEntity balancesheetReportEntity) {

        ReadXmlBalancesheetResultDto resultDto = new ReadXmlBalancesheetResultDto();

        BalancesheetReportDocumentPoliticalPropertyDto documentlPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        BeanUtils.copyProperties(balancesheetReportEntity, documentlPropertyDto);
        resultDto.setDocumentPropertyDto(documentlPropertyDto);

        Sheet070100CoverAndOrganizationDetailsDto coverDto = new Sheet070100CoverAndOrganizationDetailsDto();
        BeanUtils.copyProperties(balancesheetReportEntity, coverDto);
        resultDto.setCoverDto(coverDto);

        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        BeanUtils.copyProperties(balancesheetReportEntity, saveStorageResultDto);
        resultDto.setSaveStorageResultDto(saveStorageResultDto);

        return resultDto;

    }

}
