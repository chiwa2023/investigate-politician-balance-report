package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0802WithdrawalItemsByTransferDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080200WithdrawalItemsByTransferDto;

/**
 * 政党交付金使途報告書(様式8その2)
 */
public class CreateTestDataPoliticalOrganization0802Logic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     *
     * @param allBookDto 政治資金収支報告書
     */
    public void practice(final AllBookDto allBookDto) {

        AllSheet0802WithdrawalItemsByTransferDto allSheet0802WithdrawalItemsByTransferDto = new AllSheet0802WithdrawalItemsByTransferDto();

        Sheet080200WithdrawalItemsByTransferDto sheet1 = new Sheet080200WithdrawalItemsByTransferDto();

        sheet1.setShishutsuKoumoku("支出項目");
        sheet1.setTekiyou("摘要");
        sheet1.setDantaiName0820("団体名称");

        allSheet0802WithdrawalItemsByTransferDto.getListSheet0802().add(sheet1);

        allBookDto.setAllSheet0802WithdrawalItemsByTransferDto(allSheet0802WithdrawalItemsByTransferDto);
    }
}
