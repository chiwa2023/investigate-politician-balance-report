package mitei.mitei.investigate.report.balance.politician.dto.zengin_org_master;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * 全銀金融機関支店名csvワークテーブル登録Dto
 */
public class RegistZenginOrgWkTbleCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 全銀csv1ワークテーブル1用StorageDto */
    private SaveStorageResultDto storageWkTbl1Dto = new SaveStorageResultDto();

    /** 全銀csv1ワークテーブル2用StorageDto */
    private SaveStorageResultDto storageWkTbl2Dto = new SaveStorageResultDto();

    /** 全銀csv1ワークテーブル3用StorageDto */
    private SaveStorageResultDto storageWkTbl3Dto = new SaveStorageResultDto();

    /** 全銀csv1ワークテーブル4用StorageDto */
    private SaveStorageResultDto storageWkTbl4Dto = new SaveStorageResultDto();

    /**
     * 全銀csv1ワークテーブル1用StorageDtoを取得する
     *
     * @return 全銀csv1ワークテーブル1用StorageDto
     */
    public SaveStorageResultDto getStorageWkTbl1Dto() {
        return storageWkTbl1Dto;
    }

    /**
     * 全銀csv1ワークテーブル1用StorageDtoを設定する
     *
     * @param storageWkTbl1Dto 全銀csv1ワークテーブル1用StorageDto
     */
    public void setStorageWkTbl1Dto(final SaveStorageResultDto storageWkTbl1Dto) {
        this.storageWkTbl1Dto = storageWkTbl1Dto;
    }

    /**
     * 全銀csv1ワークテーブル2用StorageDtoを取得する
     *
     * @return 全銀csv1ワークテーブル2用StorageDto
     */
    public SaveStorageResultDto getStorageWkTbl2Dto() {
        return storageWkTbl2Dto;
    }

    /**
     * 全銀csv3ワークテーブル2用StorageDtoを設定する
     *
     * @param storageWkTbl2Dto 全銀csv3ワークテーブル2用StorageDto
     */
    public void setStorageWkTbl2Dto(final SaveStorageResultDto storageWkTbl2Dto) {
        this.storageWkTbl2Dto = storageWkTbl2Dto;
    }

    /**
     * 全銀csv3ワークテーブル3用StorageDtoを取得する
     *
     * @return 全銀csv3ワークテーブル3用StorageDto
     */
    public SaveStorageResultDto getStorageWkTbl3Dto() {
        return storageWkTbl3Dto;
    }

    /**
     * 全銀csv3ワークテーブル3用StorageDtoを設定する
     *
     * @param storageWkTbl3Dto 全銀csv3ワークテーブル3用StorageDto
     */
    public void setStorageWkTbl3Dto(final SaveStorageResultDto storageWkTbl3Dto) {
        this.storageWkTbl3Dto = storageWkTbl3Dto;
    }

    /**
     * 全銀csv1ワークテーブル4用StorageDtoを取得する
     *
     * @return 全銀csv1ワークテーブル4用StorageDto
     */
    public SaveStorageResultDto getStorageWkTbl4Dto() {
        return storageWkTbl4Dto;
    }

    /**
     * 全銀csv1ワークテーブル4用StorageDtoを設定する
     *
     * @param storageWkTbl4Dto 全銀csv4ワークテーブル1用StorageDto
     */
    public void setStorageWkTbl4Dto(final SaveStorageResultDto storageWkTbl4Dto) {
        this.storageWkTbl4Dto = storageWkTbl4Dto;
    }

}
