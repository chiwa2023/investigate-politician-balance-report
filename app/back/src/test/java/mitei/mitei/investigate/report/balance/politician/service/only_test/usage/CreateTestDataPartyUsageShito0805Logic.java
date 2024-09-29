package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0805Dto;

/**
 * 政党交付金使途報告書様式8その5テストデータ生成
 */
public class CreateTestDataPartyUsageShito0805Logic {
    // CHECKSTYLE:OFF
    
    /**
     * テストデータ生成
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) {

        Sheet0805Dto sheet1 = new Sheet0805Dto();
        sheet1.setSumAmount(242424L); // NOPMD

        RowShito0805Dto row = new RowShito0805Dto();
        row.setRowNo(1);
        row.setSibuName("支部名称");
        row.setAmount(23456L); // NOPMD
        row.setAccrualDate("R3/11/13");
        row.setPurpose("事務所費");
        row.setBikou("備考");
        row.setRowKbn(100);

        sheet1.getList().add(row);

        Shito0805Dto shito1 = new Shito0805Dto();
        shito1.setSheet0805Dto(sheet1);

        allShitoBook.setShito0805Dto(shito1);
    }

}
