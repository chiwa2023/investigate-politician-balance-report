package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import java.util.List;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0901Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0901Dto;

/**
 * 政党交付金使途報告書様式9(その1)
 */
public class CreateTestDataPartyUsageShito0901Logic {
    // CHECKSTYLE:OFF

    /**
     * 政党交付金使途報告書様式9のテストデータ生成
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) {
        
        Shito0901Dto shito0901Dto = new Shito0901Dto();

        List<RowShito0901Dto> listRow = shito0901Dto.getSheet0901Dto().getList();
        
        RowShito0901Dto row1 = new RowShito0901Dto();

        row1.setRowNo(7);
        row1.setItemName("備品・消耗品費");
        row1.setDigest("aaaaa");
        row1.setAmount(20000L); // NOPMD
        row1.setAccrualDate("R4/12/1");
        row1.setExplainText("相手が〇社だった");

        RowShito0901Dto row2 = new RowShito0901Dto();

        row2.setRowNo(9);
        row2.setItemName("備品・消耗品費");
        row2.setDigest("bbbbb");
        row2.setAmount(30000L); // NOPMD
        row2.setAccrualDate("R5/12/1");
        row2.setExplainText("相手が反〇だった");
        
        listRow.add(row1);
        listRow.add(row2);

        allShitoBook.setShito0901Dto(shito0901Dto);
    }

}
