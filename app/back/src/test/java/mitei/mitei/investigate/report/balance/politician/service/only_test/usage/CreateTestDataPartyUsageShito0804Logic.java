package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShitoCoreDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0804Dto;

/**
 * 政党交付金使途報告書様式8その4テストデータ生成
 */
public class CreateTestDataPartyUsageShito0804Logic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成する
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) {

        // 1行データあり
        RowShitoCoreDto row1 = new RowShitoCoreDto();
        row1.setRowNo(1);
        row1.setUsageItem("取引項目");
        row1.setAmountAll(10000L); // NOPMD
        row1.setAmountKoufukin(4000L);
        row1.setAmountMyFunds(6000L);
        row1.setAccrualDate("R4/12/21");
        row1.setPayeeName("取引先名称");
        row1.setAddress("取引先住所");
        row1.setBikou("備考");
        row1.setFlgCollectRecipt(1);

        Sheet0804Dto sheet1 = new Sheet0804Dto();

        sheet1.setHimoku("費目");
        sheet1.setSonotaAmount(500L);
        sheet1.setSonotaKoufukin("300");
        sheet1.setSonotaMyFunds("200");
        sheet1.setAmountAll(5000L);
        sheet1.setAmountAllKoufukin(3000L);
        sheet1.setAmountAllMyFunds(2000L);

        sheet1.getList().add(row1);

        Shito0804Dto shito1 = new Shito0804Dto();
        shito1.getKbn080401Dto().getList().add(sheet1);
        shito1.getKbn080401Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080402Dto().getList().add(sheet1);
        shito1.getKbn080402Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080403Dto().getList().add(sheet1);
        shito1.getKbn080403Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080404Dto().getList().add(sheet1);
        shito1.getKbn080404Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080405Dto().getList().add(sheet1);
        shito1.getKbn080405Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080406Dto().getList().add(sheet1);
        shito1.getKbn080406Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080407Dto().getList().add(sheet1);
        shito1.getKbn080407Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080408Dto().getList().add(sheet1);
        shito1.getKbn080408Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080409Dto().getList().add(sheet1);
        shito1.getKbn080409Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080410Dto().getList().add(sheet1);
        shito1.getKbn080410Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080411Dto().getList().add(sheet1);
        shito1.getKbn080411Dto().getList().add(new Sheet0804Dto());
        shito1.getKbn080412Dto().getList().add(sheet1);
        shito1.getKbn080412Dto().getList().add(new Sheet0804Dto());

        allShitoBook.setShito0804Dto(shito1);
        
        
    }

}
