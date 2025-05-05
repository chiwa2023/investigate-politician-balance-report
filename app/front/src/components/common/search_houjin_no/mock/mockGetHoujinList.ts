import  HoujinNoInterface  from '../../../../dto/partner_corp/houjinNoDto'; 
import  HoujinNoDto  from '../../../../dto/partner_corp/houjinNoDto'; 

export default function mockGetHoujinList(): HoujinNoInterface[] {
    const houjinList: HoujinNoInterface[] = [];

    for (let i = 0; i < 10; i++) {
        const houjin: HoujinNoInterface = new HoujinNoDto();
        houjin.houjinNo = "1234567890" + i;
        houjin.shoriKbn = "11";
        houjin.updateDate = new Date(2023, 10, 1);
        houjin.houjinSbts = "401";
        houjin.houjinNameKana = "ホウジンカブシキガイシャ" + i;
        houjin.houjinName = "法人株式会社" + i;
        houjin.postalcode = "123456" + i;
        houjin.addressPrefecture = "東京都" + i;
        houjin.addressCity = "千代田区" + i;
        houjin.addressBlock = "架空町４－５－" + i;
        houjinList.push(houjin);
    }

    return houjinList;
}
