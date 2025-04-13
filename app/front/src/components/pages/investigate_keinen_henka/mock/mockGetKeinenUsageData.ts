import keinenUsageSurface02103SummaryByYearInterface from "../../../../dto/keinen_henka/keinenUsageSurface02103SummaryByYearDto";
import keinenUsageSurface02103SummaryByYearDto from "../../../../dto/keinen_henka/keinenUsageSurface02103SummaryByYearDto";

export default function mockGetKeinenUsageData(startYear: number, endYear: number): keinenUsageSurface02103SummaryByYearInterface[] {

    const list: keinenUsageSurface02103SummaryByYearInterface[] = []

    for (let year = startYear; year <= endYear; year++) {
        list.push(creatDto(String(year)));
    }

    return list;

}

function creatDto(houkokuNen: string): keinenUsageSurface02103SummaryByYearInterface {

    const dto: keinenUsageSurface02103SummaryByYearInterface = new keinenUsageSurface02103SummaryByYearDto();

    const year = parseInt(houkokuNen);
    dto.houkokuNen = year;

    dto.surfaceEntity.dantaiName = "ちゃらんぽらん政治団体";
    dto.surfaceEntity.daihyouName = "代表者　太郎";
    dto.surfaceEntity.accountManagerName = "会計責任者　花子";
    dto.surfaceEntity.offeringDate = new Date(year, 2, 16);

    // 収入
    dto.summaryEntity.partyAllKoufukin = 100000 + year;
    dto.summaryEntity.lastYearRest = 110000 + year;
    dto.summaryEntity.outcomeKoufukinAll = 120000 + year;
    dto.summaryEntity.outcomeKoufukin = 130000 + year;
    dto.summaryEntity.outcomeShibuKikin = 140000 + year;
    dto.summaryEntity.withdrawalFunds = 150000 + year;
    dto.summaryEntity.accumulateFunds = 160000 + year;
    dto.summaryEntity.fundsSumGain = 170000 + year;
    dto.summaryEntity.thisYearRest = 180000 + year;
    dto.summaryEntity.bikouDiffer = 190000 + year;


    // 支出


    /** 全政治活動全合計 */
    dto.summaryEntity.totalAllActionAll = 200000 + year;

    /** 全政治活動交付金充当 */
    dto.summaryEntity.totalAllActionJutoKoufukin = 210000 + year;

    /** 全政治活動自党基金充当 */
    dto.summaryEntity.totalAllActionJutoMyFunds = 220000 + year;

    /** 全政治活動備考 */
    dto.summaryEntity.totalAllActionBikou = "備考1";

    /** 全総計全合計 */
    dto.summaryEntity.totalAllAmountAll = 230000 + year;

    /** 全総計交付金充当 */
    dto.summaryEntity.totalAllAmountJutoKoufukin = 240000 + year;

    /** 総計自党基金充当 */
    dto.summaryEntity.totalAllAmountJutoMyFunds = 250000 + year;

    /** 全総計備考 */
    dto.summaryEntity.totalAllAmountBikou = "備考2";

    /** 全事業総計全合計 */
    dto.summaryEntity.totalAllJigyouAll = 260000 + year;

    /** 全事業総計交付金充当 */
    dto.summaryEntity.totalAllJigyouJutoKoufukin = 270000 + year;

    /** 全事業自党基金充当 */
    dto.summaryEntity.totalAllJigyouJutoMyFunds = 280000 + year;

    /** 全事業備考 */
    dto.summaryEntity.totalAllJigyouBikou = "備考3";

    /** 備品消耗品費全合計 */
    dto.summaryEntity.totalBihinAll = 290000 + year;

    /** 備品消耗品費交付金充当 */
    dto.summaryEntity.totalBihinJutoKoufukin = 300000 + year;

    /** 備品消耗品費自党基金充当 */
    dto.summaryEntity.totalBihinJutoMyFunds = 310000 + year;

    /** 備品消耗品費備考 */
    dto.summaryEntity.totalBihinBikou = "備考4";

    /** 調査研究費全合計 */
    dto.summaryEntity.totalChousaAll = 320000 + year;

    /** 調査研究費交付金充当 */
    dto.summaryEntity.totalChousaJutoKoufukin = 330000 + year;

    /** 調査研究費自党基金充当 */
    dto.summaryEntity.totalChousaJutoMyFunds = 340000 + year;

    /** 調査研究費備考 */
    dto.summaryEntity.totalChousaBikou = "備考5";

    /** 事務所費全合計 */
    dto.summaryEntity.totalJimushoAll = 350000 + year;

    /** 事務所費交付金充当 */
    dto.summaryEntity.totalJimushoJutoKoufukin = 360000 + year;

    /** 事務所費自党基金充当 */
    dto.summaryEntity.totalJimushoJutoMyFunds = 370000 + year;

    /** 事務所費備考 */
    dto.summaryEntity.totalJimushoBikou = "備考6";

    /** 人件費全合計 */
    dto.summaryEntity.totalJinkenhiAll = 380000 + year;

    /** 人件費交付金充当 */
    dto.summaryEntity.totalJinkenhiJutoKoufukin = 390000 + year;

    /** 人件費自党基金充当 */
    dto.summaryEntity.totalJinkenhiJutoMyFunds = 400000 + year;

    /** 人件費備考 */
    dto.summaryEntity.totalJinkenhiBikou = "備考7";

    /** 経費全合計 */
    dto.summaryEntity.totalKeihiAll = 410000 + year;

    /** 経費交付金充当 */
    dto.summaryEntity.totalKeihiJutoKoufukin = 420000 + year;

    /** 経費自党基金充当 */
    dto.summaryEntity.totalKeihiJutoMyFunds = 430000 + year;

    /** 経費備考 */
    dto.summaryEntity.totalKeihiBikou = "備考8";

    /** 寄附全合計 */
    dto.summaryEntity.totalKifuAll = 440000 + year;

    /** 寄附交付金充当 */
    dto.summaryEntity.totalKifuJutoKoufukin = 450000 + year;

    /** 寄附自党基金充当 */
    dto.summaryEntity.totalKifuJutoMyFunds = 460000 + year;

    /** 寄附備考 */
    dto.summaryEntity.totalKifuBikou = "備考9";

    /** 機関誌発行全合計 */
    dto.summaryEntity.totalKikanshiAll = 470000 + year;

    /** 機関誌発行交付金充当 */
    dto.summaryEntity.totalKikanshiJutoKoufukin = 480000 + year;

    /** 機関誌発行自党基金充当 */
    dto.summaryEntity.totalKikanshiJutoMyFunds = 490000 + year;

    /** 機関誌発行備考 */
    dto.summaryEntity.totalKikanshiBikou = "備考10";

    /** 光熱費全合計 */
    dto.summaryEntity.totalKounetsuhiAll = 500000 + year;

    /** 光熱費交付金充当 */
    dto.summaryEntity.totalKounetsuhiJutoKoufukin = 510000 + year;

    /** 光熱費自党基金充当 */
    dto.summaryEntity.totalKounetsuhiJutoMyFunds = 520000 + year;

    /** 光熱費備考 */
    dto.summaryEntity.totalKounetsuhiBikou = "備考11";

    /** パーティ費全合計 */
    dto.summaryEntity.totalPartyAll = 530000 + year;

    /** パーティ費交付金充当 */
    dto.summaryEntity.totalPartyJutoKoufukin = 540000 + year;

    /** パーティ費自党基金充当 */
    dto.summaryEntity.totalPartyJutoMyFunds = 550000 + year;

    /** パーティ費備考 */
    dto.summaryEntity.totalPartyBikou = "備考12";

    /** 宣伝費全合計 */
    dto.summaryEntity.totalSendenAll = 560000 + year;

    /** 宣伝費交付金充当 */
    dto.summaryEntity.totalSendenJutoKoufukin = 570000 + year;

    /** 宣伝費自党基金充当 */
    dto.summaryEntity.totalSendenJutoMyFunds = 580000 + year;

    /** 宣伝費備考 */
    dto.summaryEntity.totalSendenBikou = "備考13";

    /** 選挙費全合計 */
    dto.summaryEntity.totalSenkyoAll = 590000 + year;

    /** 選挙費交付金充当 */
    dto.summaryEntity.totalSenkyoJutoKoufukin = 600000 + year;

    /** 選挙費自党基金充当 */
    dto.summaryEntity.totalSenkyoJutoMyFunds = 610000 + year;

    /** 選挙費備考 */
    dto.summaryEntity.totalSenkyoBikou = "備考14";

    /** 支部交付金全合計 */
    dto.summaryEntity.totalShibuKoufuAll = 620000 + year;

    /** 支部交付金交付金充当 */
    dto.summaryEntity.totalShibuKoufuJutoKoufukin = 630000 + year;

    /** 支部交付金自党基金充当 */
    dto.summaryEntity.totalShibuKoufuJutoMyFunds = 640000 + year;

    /** 支部交付金備考 */
    dto.summaryEntity.totalShibuKoufuBikou = "備考15";

    /** その他事業費全合計 */
    dto.summaryEntity.totalSonotaJigyouAll = 650000 + year;

    /** その他事業費交付金充当 */
    dto.summaryEntity.totalSonotaJigyouJutoKoufukin = 660000 + year;

    /** その他事業費自党基金充当 */
    dto.summaryEntity.totalSonotaJigyouJutoMyFunds = 670000 + year;

    /** その他事業費備考 */
    dto.summaryEntity.totalSonotaJigyouBikou = "備考16";

    /** その他経費全合計 */
    dto.summaryEntity.totalSonotaKeihiAll = 680000 + year;

    /** その他経費交付金充当 */
    dto.summaryEntity.totalSonotaKeihiJutoKoufukin = 690000 + year;

    /** その他経費自党基金充当 */
    dto.summaryEntity.totalSonotaKeihiJutoMyFunds = 700000 + year;

    /** その他経費備考 */
    dto.summaryEntity.totalSonotaKeihiBikou = "備考17";

    /** 組織費全合計 */
    dto.summaryEntity.totalSoshikiAll = 710000 + year;

    /** 組織費交付金充当 */
    dto.summaryEntity.totalSoshikiJutoKoufukin = 720000 + year;

    /** 組織費自党基金充当 */
    dto.summaryEntity.totalSoshikiJutoMyFunds = 730000 + year;

    /** 組織費備考 */
    dto.summaryEntity.totalSoshikiBikou = "備考18";


    return dto;
}
