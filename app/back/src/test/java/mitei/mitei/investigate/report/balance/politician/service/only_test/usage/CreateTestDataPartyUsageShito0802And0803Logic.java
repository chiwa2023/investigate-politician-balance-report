package mitei.mitei.investigate.report.balance.politician.service.only_test.usage;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080201Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0803Dto;
import mitei.mitei.common.publish.party.usage.report.logic.CreateShito0802Kbn01WrapperLogic;

/**
 * 政党交付金使途報告書集計表(その2と3)テストデータ生成
 */
public class CreateTestDataPartyUsageShito0802And0803Logic {
    // CHECKSTYLE:OFF
    
    /**
     * テストデータ生成
     *
     * @param allShitoBook 政党交付金使途報告書Dto
     */
    public void practice(final AllShitoBook allShitoBook) { // NOPMD

        
        /* 様式8その3 */
        Shito0803Dto shito3 = new Shito0803Dto();

        shito3.getSheet0803Dto().setTotalShibuKoufuAll(1001L);
        shito3.getSheet0803Dto().setTotalShibuKoufuJutoKoufukin(1002L);
        shito3.getSheet0803Dto().setTotalShibuKoufuJutoMyFunds(1003L);
        shito3.getSheet0803Dto().setTotalShibuKoufuBikou("aaaa");
        shito3.getSheet0803Dto().setTotalJinkenhiAll(2001L);
        shito3.getSheet0803Dto().setTotalJinkenhiJutoKoufukin(2002L);
        shito3.getSheet0803Dto().setTotalJinkenhiJutoMyFunds(2003L);
        shito3.getSheet0803Dto().setTotalJinkenhiBikou("bbbb");
        shito3.getSheet0803Dto().setTotalKounetsuhiAll(3001L);
        shito3.getSheet0803Dto().setTotalKounetsuhiJutoKoufukin(3002L);
        shito3.getSheet0803Dto().setTotalKounetsuhiJutoMyFunds(3003L);
        shito3.getSheet0803Dto().setTotalKounetsuhiBikou("cccc");
        shito3.getSheet0803Dto().setTotalBihinAll(4001L);
        shito3.getSheet0803Dto().setTotalBihinJutoKoufukin(4002L);
        shito3.getSheet0803Dto().setTotalBihinJutoMyFunds(4003L);
        shito3.getSheet0803Dto().setTotalBihinBikou("dddd");
        shito3.getSheet0803Dto().setTotalJimushoAll(5001L);
        shito3.getSheet0803Dto().setTotalJimushoJutoKoufukin(5002L);
        shito3.getSheet0803Dto().setTotalJimushoJutoMyFunds(5003L);
        shito3.getSheet0803Dto().setTotalJimushoBikou("rrrr");
        shito3.getSheet0803Dto().setTotalKeihiAll(6001L);
        shito3.getSheet0803Dto().setTotalKeihiJutoKoufukin(6002L);
        shito3.getSheet0803Dto().setTotalKeihiJutoMyFunds(6003L);
        shito3.getSheet0803Dto().setTotalKeihiBikou("eeee");
        shito3.getSheet0803Dto().setTotalSoshikiAll(7001L);
        shito3.getSheet0803Dto().setTotalSoshikiJutoKoufukin(7002L);
        shito3.getSheet0803Dto().setTotalSoshikiJutoMyFunds(7003L);
        shito3.getSheet0803Dto().setTotalSoshikiBikou("ffff");
        shito3.getSheet0803Dto().setTotalSenkyoAll(8001L);
        shito3.getSheet0803Dto().setTotalSenkyoJutoKoufukin(8002L);
        shito3.getSheet0803Dto().setTotalSenkyoJutoMyFunds(8003L);
        shito3.getSheet0803Dto().setTotalSenkyoBikou("gggg");
        shito3.getSheet0803Dto().setTotalAllJigyouAll(9001L);
        shito3.getSheet0803Dto().setTotalAllJigyouJutoKoufukin(9002L);
        shito3.getSheet0803Dto().setTotalAllJigyouJutoMyFunds(9003L);
        shito3.getSheet0803Dto().setTotalAllJigyouBikou("qqqq");
        shito3.getSheet0803Dto().setTotalKikanshiAll(1001L);
        shito3.getSheet0803Dto().setTotalKikanshiJutoKoufukin(1002L);
        shito3.getSheet0803Dto().setTotalKikanshiJutoMyFunds(1003L);
        shito3.getSheet0803Dto().setTotalKikanshiBikou("hhhh");
        shito3.getSheet0803Dto().setTotalSendenAll(1101L);
        shito3.getSheet0803Dto().setTotalSendenJutoKoufukin(1102L);
        shito3.getSheet0803Dto().setTotalSendenJutoMyFunds(1103L);
        shito3.getSheet0803Dto().setTotalSendenBikou("pppp");
        shito3.getSheet0803Dto().setTotalPartyAll(1201L);
        shito3.getSheet0803Dto().setTotalPartyJutoKoufukin(1202L);
        shito3.getSheet0803Dto().setTotalPartyJutoMyFunds(1203L);
        shito3.getSheet0803Dto().setTotalPartyBikou("iiii");
        shito3.getSheet0803Dto().setTotalSonotaJigyouAll(1301L);
        shito3.getSheet0803Dto().setTotalSonotaJigyouJutoKoufukin(1302L);
        shito3.getSheet0803Dto().setTotalSonotaJigyouJutoMyFunds(1303L);
        shito3.getSheet0803Dto().setTotalSonotaJigyouBikou("jjjj");
        shito3.getSheet0803Dto().setTotalChousaAll(1401L);
        shito3.getSheet0803Dto().setTotalChousaJutoKoufukin(1402L);
        shito3.getSheet0803Dto().setTotalChousaJutoMyFunds(1403L);
        shito3.getSheet0803Dto().setTotalChousaBikou("kkkk");
        shito3.getSheet0803Dto().setTotalKifuAll(1501L);
        shito3.getSheet0803Dto().setTotalKifuJutoKoufukin(1502L);
        shito3.getSheet0803Dto().setTotalKifuJutoMyFunds(1503L);
        shito3.getSheet0803Dto().setTotalKifuBikou("llll");
        shito3.getSheet0803Dto().setTotalSonotaKeihiAll(1601L);
        shito3.getSheet0803Dto().setTotalSonotaKeihiJutoKoufukin(1602L);
        shito3.getSheet0803Dto().setTotalSonotaKeihiJutoMyFunds(1603L);
        shito3.getSheet0803Dto().setTotalSonotaKeihiBikou("mmmm");
        shito3.getSheet0803Dto().setTotalAllActionAll(1701L);
        shito3.getSheet0803Dto().setTotalAllActionJutoKoufukin(1702L);
        shito3.getSheet0803Dto().setTotalAllActionJutoMyFunds(1703L);
        shito3.getSheet0803Dto().setTotalAllActionBikou("nnnn");
        shito3.getSheet0803Dto().setTotalAllAmountAll(1801L);
        shito3.getSheet0803Dto().setTotalAllAmountJutoKoufukin(1802L);
        shito3.getSheet0803Dto().setTotalAllAmountJutoMyFunds(1803L);
        shito3.getSheet0803Dto().setTotalAllAmountBikou("oooo");

        /* 様式8その2区分1 */
        CreateShito0802Kbn01WrapperLogic createShito0802Kbn01WrapperLogic = new CreateShito0802Kbn01WrapperLogic();

        Kbn080201Dto Kbn080201Dto = createShito0802Kbn01WrapperLogic.getDto();

        // 他のDtoをテストに介在させないためにここでは特別にしているが、業務的にはきわめて望ましくない触り方
        Kbn080201Dto.getList().get(0).setAmount(12345L); // NOPMD
        Kbn080201Dto.getList().get(1).setAmount(23456L); // NOPMD
        Kbn080201Dto.getList().get(2).setAmount(34567L); // NOPMD
        Kbn080201Dto.getList().get(3).setAmount(45678L); // NOPMD
        Kbn080201Dto.getList().get(4).setAmount(56789L); // NOPMD
        Kbn080201Dto.getList().get(5).setAmount(67890L); // NOPMD
        Kbn080201Dto.getList().get(6).setAmount(78901L); // NOPMD
        Kbn080201Dto.getList().get(7).setAmount(89012L); // NOPMD
        Kbn080201Dto.getList().get(8).setAmount(90123L); // NOPMD
        Kbn080201Dto.getList().get(9).setAmount(65432L); // NOPMD

        Sheet0802Dto sheet = new Sheet0802Dto();
        sheet.setKbn080201Dto(Kbn080201Dto);

        Shito0802Dto shito2 = new Shito0802Dto();
        shito2.setSheet0802Dto(sheet);

        
        allShitoBook.setShito0802Dto(shito2);
        allShitoBook.setShito0803Dto(shito3);
        
    }

}
