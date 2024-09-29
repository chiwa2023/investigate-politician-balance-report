package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080601Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080602Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080603Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0806Dto;

/**
 * 政党交付金使途報告書様式8その6
 */
public class CreateTestDataPartyUsageShito0806Logic {
    // CHECKSTYLE:OFF

    /**
     * 政党交付金使途報告書様式8その6テストデータ生成
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) {

        Sheet0806Dto sheet = new Sheet0806Dto();
        sheet.setFundsName("極悪同盟専用");
        sheet.setTotalLastYear(999L);
        sheet.setFundsPurpose("強烈な裏金買収");
        sheet.setTotal(1111L);
        sheet.setTotalBikou("合計備考");
        sheet.setTotalThisYear(9119L);
        sheet.setTotalThisYearBikou("本年残備考");
        sheet.setTotalIncrease(334L);
        sheet.setTotalIncreaseBikou("増減備考");

        RowShito0806Dto row = new RowShito0806Dto();
        row.setRowNo(22);
        row.setAccrualDate("R4/9/19");
        row.setAmount(9955L);
        row.setBikou("備品");

        Kbn080601Dto kbn1 = new Kbn080601Dto();
        kbn1.setSubTotal(6654L);
        kbn1.setSubTotalBikou("積み立て特別備考");
        kbn1.getList().add(row);

        Kbn080602Dto kbn2 = new Kbn080602Dto();
        kbn2.setSubTotal(654L);
        kbn2.setSubTotalBikou("果実特別備考");
        kbn2.getList().add(row);

        Kbn080603Dto kbn3 = new Kbn080603Dto();
        kbn3.setSubTotal(854L);
        kbn3.setSubTotalBikou("取り崩し特別備考");
        kbn3.getList().add(row);

        sheet.setKbn080601Dto(kbn1);
        sheet.setKbn080602Dto(kbn2);
        sheet.setKbn080603Dto(kbn3);

        Shito0806Dto shito0806Dto = new Shito0806Dto();
        shito0806Dto.getList().add(sheet);

        allShitoBook.setShito0806Dto(shito0806Dto);
    }

}
