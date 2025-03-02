package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * 階層ごとの経路を保存する
 */
@Component
public class RecordPickupRouteStageLogic {

    /** 階層1 */
    private static final int stage1 = 1;
    /** 階層2 */
    private static final int stage2 = 2;
    /** 階層3 */
    private static final int stage3 = 3;
    /** 階層4 */
    private static final int stage4 = 4;
    /** 階層5 */
    private static final int stage5 = 5;
    /** 階層6 */
    private static final int stage6 = 6;
    /** 階層7 */
    private static final int stage7 = 7;
    /** 階層8 */
    private static final int stage8 = 8;
    /** 階層9 */
    private static final int stage9 = 9;

    /** 取得階層1回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes01Logic insertUkaiKenkinRouteTimes01Logic;

    /** 取得階層2回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes02Logic insertUkaiKenkinRouteTimes02Logic;

    /** 取得階層3回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes03Logic insertUkaiKenkinRouteTimes03Logic;

    /** 取得階層4回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes04Logic insertUkaiKenkinRouteTimes04Logic;

    /** 取得階層5回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes05Logic insertUkaiKenkinRouteTimes05Logic;

    /** 取得階層6回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes06Logic insertUkaiKenkinRouteTimes06Logic;

    /** 取得階層7回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes07Logic insertUkaiKenkinRouteTimes07Logic;

    /** 取得階層8回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes08Logic insertUkaiKenkinRouteTimes08Logic;

    /** 取得階層9回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes09Logic insertUkaiKenkinRouteTimes09Logic;

    /**
     * 政治団体属性と比較して処理を行う
     *
     * @param userCode              操作ユーザ同一識別コード
     * @param entity                迂回献金経路Entity
     * @param personWithYakuwariDto 役割付き個人Dto
     */
    public Integer practice(final Integer userCode, final WkTblUkaiKenkinPickupRouteEntity entity,
            final RelationPersonWithYakuwariDto personWithYakuwariDto) {

        switch (entity.getPickupStage()) {
            // 取得階層1
            case stage1:
                return insertUkaiKenkinRouteTimes01Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層2
            case stage2:
                return insertUkaiKenkinRouteTimes02Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層3
            case stage3:
                return insertUkaiKenkinRouteTimes03Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層4
            case stage4:
                return insertUkaiKenkinRouteTimes04Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層5
            case stage5:
                return insertUkaiKenkinRouteTimes05Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層6
            case stage6:
                return insertUkaiKenkinRouteTimes06Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層7
            case stage7:
                return insertUkaiKenkinRouteTimes07Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層8
            case stage8:
                return insertUkaiKenkinRouteTimes08Logic.practice(userCode, entity, personWithYakuwariDto);
            // 取得階層9
            case stage9:
                return insertUkaiKenkinRouteTimes09Logic.practice(userCode, entity, personWithYakuwariDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + entity.getPickupStage());
        }
    }

    /**
     * 政治団体属性と比較して処理を行う
     *
     * @param userCode       操作ユーザ同一識別コード
     * @param entity         迂回献金経路Entity
     * @param propertyEntity 政治団体属性Entity
     */
    public Integer practice(final Integer userCode, final WkTblUkaiKenkinPickupRouteEntity entity,
            final PoliticalOrganizationPropertyEntity propertyEntity) {

        switch (entity.getPickupStage()) {
            // 取得階層1
            case stage1:
                return insertUkaiKenkinRouteTimes01Logic.practice(userCode, entity, propertyEntity);
            // 取得階層2
            case stage2:
                return insertUkaiKenkinRouteTimes02Logic.practice(userCode, entity, propertyEntity);
            // 取得階層3
            case stage3:
                return insertUkaiKenkinRouteTimes03Logic.practice(userCode, entity, propertyEntity);
            // 取得階層4
            case stage4:
                return insertUkaiKenkinRouteTimes04Logic.practice(userCode, entity, propertyEntity);
            // 取得階層5
            case stage5:
                return insertUkaiKenkinRouteTimes05Logic.practice(userCode, entity, propertyEntity);
            // 取得階層6
            case stage6:
                return insertUkaiKenkinRouteTimes06Logic.practice(userCode, entity, propertyEntity);
            // 取得階層7
            case stage7:
                return insertUkaiKenkinRouteTimes07Logic.practice(userCode, entity, propertyEntity);
            // 取得階層8
            case stage8:
                return insertUkaiKenkinRouteTimes08Logic.practice(userCode, entity, propertyEntity);
            // 取得階層9
            case stage9:
                return insertUkaiKenkinRouteTimes09Logic.practice(userCode, entity, propertyEntity);

            default:
                throw new IllegalArgumentException("Unexpected value: " + entity.getPickupStage());
        }
    }

}
