import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import type CheckRegistCorpResultInterface from "../../../../dto/partner_corp/checkRegistCorpResultDto";
import CheckRegistCorpResultDto from "../../../../dto/partner_corp/checkRegistCorpResultDto";
import type CorpNoInterface from "../../../../dto/partner_corp/corpNoDto";
import CorpNoInterDto from "../../../../dto/partner_corp/corpNoDto";

export default function mockCheckAlreadyRegist(corpNo: string, houjinNo: string): CheckRegistCorpResultInterface {


    const resultDto: CheckRegistCorpResultInterface = new CheckRegistCorpResultDto();

    // 企業団体番号が空でないときは常に既存データの編集処理
    if ("" === corpNo) {

        // 企業団体番号が空の時
        if ("" === houjinNo) {
            // 法人番号が空の時、法人番号指定警告
            resultDto.message = "法人番号を指定してください";
            return resultDto;
        } else {
            // 法人番号が空でない時、法人番号と入力済情報をback側で新規編集判定
            const date = new Date();
            if (date.getSeconds() % 2 == 0) {
                resultDto.message = "新規追加";
                resultDto.savedCorpNo = "新規";
            } else {
                resultDto.message = "既存法人番号データが存在";
                resultDto.listCorptDto = mockGetCorpList();

            }
            return resultDto;
        }
    } else {
        resultDto.message = "既存データの編集";
        return resultDto;
    }
}


function mockGetCorpList(): CorpNoInterface[] {
    const list: CorpNoInterface[] = [];

    list.push(createDto(11));
    list.push(createDto(33));

    return list;
}

function createDto(index: number): CorpNoInterface {
    const dto: CorpNoInterface = new CorpNoInterDto();

    dto.houjinNo = "3318-" + index;
    dto.corpNo = "6926-" + index + "-abcde";

    dto.corpName = "いいかげん政治団体" + index;
    dto.corpNameKana = "いいかげんせいじだんたい" + index;

    dto.isShiten = false;
    const addressDto: InputAddressDto = new InputAddressDto();
    addressDto.addressPostal = "和歌山県実在市山麓町" + index;
    addressDto.addressBlock = "6丁目" + index;
    addressDto.addressBuilding = "五芒形ビル" + index + "階";
    addressDto.postalcode1 = "98" + index;
    addressDto.postalcode2 = "765" + index;
    addressDto.addressAll = addressDto.addressPostal + addressDto.addressBlock + addressDto.addressBuilding;
    dto.addressDto = addressDto;
    dto.juusho = addressDto.addressAll;

    dto.orgDelegate = "代表者　太郎" + index;

    return dto;
}