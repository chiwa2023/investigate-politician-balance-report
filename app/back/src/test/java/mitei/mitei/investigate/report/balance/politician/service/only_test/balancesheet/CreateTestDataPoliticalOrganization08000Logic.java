package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0800DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row080000DifficultCollectReceiptDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet080000DifficultCollectReceiptDto;

/**
 * 政治資金収支報告書様式8領収書を徴しがたかったものテストデータ生成
 */
public class CreateTestDataPoliticalOrganization08000Logic {
    // CHECKSTYLE:OFF
    

    /**
     * テストデータ生成
     *
     * @param allBookDto 政治資金収支報告書Dto
     */
    public void practice(final AllBookDto allBookDto) {

        AllSheet0800DifficultCollectReceiptDto allSheet0800DifficultCollectReceiptDto = new AllSheet0800DifficultCollectReceiptDto();

        Sheet080000DifficultCollectReceiptDto sheet1 = new Sheet080000DifficultCollectReceiptDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row080000DifficultCollectReceiptDto row1 = new Row080000DifficultCollectReceiptDto();
        row1.setIchirenNo(1);
        row1.setKoumoku("支出項目");
        row1.setTekiyou("摘要");
        row1.setKingaku(30000L); // NOPMD
        row1.setAccrualDate("R4/12/1");
        row1.setJijyou("徴し難たかった事情");

        sheet1.getList().add(row1);

        allSheet0800DifficultCollectReceiptDto.setSheet080000DifficultCollectReceiptDto(sheet1);

        allBookDto.setAllSheet0800DifficultCollectReceiptDto(allSheet0800DifficultCollectReceiptDto);

    }
}
