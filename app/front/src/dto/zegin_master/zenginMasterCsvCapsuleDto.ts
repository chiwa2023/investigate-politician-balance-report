import AbstractCapsuleDto from "../abstractCapsuleDto";
import SaveStorageResultDto from "../storage/saveStorageResultDto";

export default interface ZenginMasterCsvCapsuleInterface {

}

export default class ZenginMasterCsvCapsuleDto extends AbstractCapsuleDto implements ZenginMasterCsvCapsuleInterface {

    /** 書証保存Dto(csv1) */
    csvData1: SaveStorageResultDto;
    csvData2: SaveStorageResultDto;
    csvData3: SaveStorageResultDto;
    csvData4: SaveStorageResultDto;

    constructor() {
        super();

        this.csvData1 = new SaveStorageResultDto();
        this.csvData2 = new SaveStorageResultDto();
        this.csvData3 = new SaveStorageResultDto();
        this.csvData4 = new SaveStorageResultDto();

    }
}