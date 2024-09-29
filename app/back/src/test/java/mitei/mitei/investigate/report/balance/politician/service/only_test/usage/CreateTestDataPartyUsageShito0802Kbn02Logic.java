package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080202Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0802Dto;

/**
 * 政党交付金使途報告書様式8その2区分2テストデータ生成
 */
public class CreateTestDataPartyUsageShito0802Kbn02Logic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) {

        Kbn080202Dto kbn1 = new Kbn080202Dto();
        kbn1.setTotalAmount(200000L); // NOPMD

        RowShito0802Dto row0 = new RowShito0802Dto();
        row0.setRowNo(3);
        row0.setItemName("項目名称");
        row0.setAccrualDate("R4/12/1");
        row0.setAmount(65432L); // NOPMD
        kbn1.getList().add(row0);
        
        allShitoBook.getShito0802Dto().getSheet0802Dto().setKbn080202Dto(kbn1);
    }

}
