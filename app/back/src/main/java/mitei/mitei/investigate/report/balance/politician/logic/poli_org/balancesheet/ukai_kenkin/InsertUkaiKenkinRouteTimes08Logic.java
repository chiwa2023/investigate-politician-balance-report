package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * 取得階層8の経路データを挿入する
 */
@Component
public class InsertUkaiKenkinRouteTimes08Logic {

    /** 迂回献金明細ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 迂回献金明細経路変換Logic */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByInClassLogic convertUkaiKenkinDetailToRouteByInClassLogic;

    /**
     * 処理を行う
     *
     * @param userCode       操作ユーザ同一識別コード
     * @param entity         迂回献金経路Entity(検出した最終データ)
     * @param propertyEntity 政治団体属性Entity
     */
    public void practice(final Integer userCode, final WkTblUkaiKenkinPickupRouteEntity entity,
            final PoliticalOrganizationPropertyEntity propertyEntity) {

        // コードを取得して保存する(このコードを経路共通識別コードとする)
        Integer code = 1;
        Optional<WkTblUkaiKenkinPickupRouteEntity> optional = wkTblUkaiKenkinPickupRouteRepository
                .findFirstByOrderByWkTblUkaiKenkinPickupRouteCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblUkaiKenkinPickupRouteCode();
        }
        entity.setWkTblUkaiKenkinPickupRouteCode(code);
        wkTblUkaiKenkinPickupRouteRepository.save(entity);

        List<Integer> listBase = new ArrayList<>();
        listBase.add(entity.getTradingPartnerCode());

        final int stage0 = 0;
        final int stage1 = 1;
        final int stage2 = 2;
        final int stage3 = 3;
        final int stage4 = 4;
        final int stage5 = 5;
        final int stage6 = 6;
        final int stage7 = 7;
        final int stage8 = 8;

        List<Integer> listStage8 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage8, listBase);
        List<Integer> listStage7 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage7, listStage8);
        List<Integer> listStage6 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage6, listStage7);
        List<Integer> listStage5 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage5, listStage6);
        List<Integer> listStage4 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage4, listStage5);
        List<Integer> listStage3 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage3, listStage4);
        List<Integer> listStage2 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage2, listStage3);
        List<Integer> listStage1 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage1, listStage2);
        List<Integer> listStage0 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage0, listStage1);

        // 経路は階層0→1,1→2,2→3,3-4,4-5(抽出された取り引相手政治団体識別コード)
        this.searchRoute(stage0, userCode, listStage0, listStage1, propertyEntity, code);
        this.searchRoute(stage1, userCode, listStage1, listStage2, propertyEntity, code);
        this.searchRoute(stage2, userCode, listStage2, listStage3, propertyEntity, code);
        this.searchRoute(stage3, userCode, listStage3, listStage4, propertyEntity, code);
        this.searchRoute(stage4, userCode, listStage4, listStage5, propertyEntity, code);
        this.searchRoute(stage5, userCode, listStage5, listStage6, propertyEntity, code);
        this.searchRoute(stage6, userCode, listStage6, listStage7, propertyEntity, code);
        this.searchRoute(stage7, userCode, listStage7, listStage8, propertyEntity, code);

    }
 
    // 該当する政治団体の明細リストから経路情報を記録する
    private void searchRoute(final Integer stage, final Integer userCode, final List<Integer> listPre,
            final List<Integer> listPro, final PoliticalOrganizationPropertyEntity propertyEntity,
            final Integer tableCode) {
        for (Integer preCode : listPre) {
            for (Integer proCode : listPro) {
                List<WkTblUkaiKenkinEntity> listMeisai = wkTblUkaiKenkinRepository
                        .findByInsertUserCodeAndPoliticalOrgCodeAndTradingPartnerCodeAndPickupStage(userCode, preCode,
                                proCode, stage);
                this.recordEntity(listMeisai, propertyEntity, tableCode);
            }
        }
    }

    // 取得した明細データを経路データに変換して保存
    private void recordEntity(final List<WkTblUkaiKenkinEntity> listMeisai,
            final PoliticalOrganizationPropertyEntity propertyEntity, final Integer tableCode) {

        if (!listMeisai.isEmpty()) {
            List<WkTblUkaiKenkinPickupRouteEntity> listRoute = convertUkaiKenkinDetailToRouteByInClassLogic
                    .practice(listMeisai, propertyEntity);
            for (WkTblUkaiKenkinPickupRouteEntity entity : listRoute) {
                entity.setWkTblUkaiKenkinPickupRouteCode(tableCode);
            }
            wkTblUkaiKenkinPickupRouteRepository.saveAll(listRoute);
        }
    }

}
