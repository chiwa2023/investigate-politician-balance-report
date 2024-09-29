package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0902Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0902Dto;

/**
 * 政党交付金使途報告書様式9その2テストデータ生成
 */
public class CreateTestDataPartyUsageShito0902Logic {

    /**
     * テストデータをセットする
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) {
        
        Sheet0902Dto sheet1 = new Sheet0902Dto();
        sheet1.setItemName("事務所費1");
        sheet1.setDigest("適当な摘要1");

        Sheet0902Dto sheet2 = new Sheet0902Dto();
        sheet2.setItemName("事務所費2");
        sheet2.setDigest("適当な摘要2");

        Shito0902Dto shito0902Dto = new Shito0902Dto();
        shito0902Dto.getList().add(sheet1);
        shito0902Dto.getList().add(sheet2);

        allShitoBook.setShito0902Dto(shito0902Dto);
    }
}
