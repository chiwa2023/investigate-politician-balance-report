package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * 取得階層1の経路データを挿入する
 */
@Component
public class InsertUkaiKenkinRouteTimes01Logic {

    /** 迂回献金明細ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 迂回献金明細経路変換Logic */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByInClassLogic convertUkaiKenkinDetailToRouteByInClassLogic;

    /** 迂回献金明細経路変換Logic */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;

    /**
     * 処理を行う
     *
     * @param userCode       操作ユーザ同一識別コード
     * @param entity         迂回献金経路Entity(検出した最終データ)
     * @param propertyEntity 政治団体属性Entity
     */
    public Integer practice(final Integer userCode, final WkTblUkaiKenkinPickupRouteEntity entity,
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

        // 経路を抽出
        List<Integer> listBase = new ArrayList<>();
        listBase.add(entity.getTradingPartnerCode());

        final int stage0 = 0;
        final int stage1 = 1;

        List<Integer> listStage1 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage1, listBase);
        List<Integer> listStage0 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage0, listStage1);

        // 経路は階層0→1,1→2,2→3,3-4,4-5(抽出された取り引相手政治団体識別コード)
        this.searchRoute(stage0, userCode, listStage0, listStage1, propertyEntity, code);

        // 迂回献金した場合の参照データを保存する
        List<WkTblUkaiKenkinEntity> listPair = wkTblUkaiKenkinRepository
                .findByInsertUserCodeAndTradingPartnerCodeAndPickupStage(userCode, entity.getTradingPartnerCode(),
                        stage0);
        this.recordEntity(listPair, propertyEntity, code);
        return code;
    }

    /**
     * 処理を行う
     *
     * @param userCode    操作ユーザ同一識別コード
     * @param entity      迂回献金経路Entity(検出した最終データ)
     * @param yakuwariDto 役割付き個人Dto
     */
    public Integer practice(final Integer userCode, final WkTblUkaiKenkinPickupRouteEntity entity,
            final RelationPersonWithYakuwariDto yakuwariDto) {

        // コードを取得して保存する(このコードを経路共通識別コードとする)
        Integer code = 1;
        Optional<WkTblUkaiKenkinPickupRouteEntity> optional = wkTblUkaiKenkinPickupRouteRepository
                .findFirstByOrderByWkTblUkaiKenkinPickupRouteCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblUkaiKenkinPickupRouteCode();
        }
        entity.setWkTblUkaiKenkinPickupRouteCode(code);
        wkTblUkaiKenkinPickupRouteRepository.save(entity);

        // 経路を抽出
        List<Integer> listBase = new ArrayList<>();
        listBase.add(entity.getTradingPartnerCode());

        final int stage0 = 0;
        final int stage1 = 1;

        List<Integer> listStage1 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage1, listBase);
        List<Integer> listStage0 = wkTblUkaiKenkinRepository.findRouteByOrgCodeAndStage(userCode, stage0, listStage1);

        // 経路は階層0→1,1→2,2→3,3-4,4-5(抽出された取り引相手政治団体識別コード)
        this.searchRoute(stage0, userCode, listStage0, listStage1, yakuwariDto, code);

        // 迂回献金した場合の参照データを保存する
        List<WkTblUkaiKenkinEntity> listPair = wkTblUkaiKenkinRepository
                .findByInsertUserCodeAndTradingPartnerCodeAndPickupStage(userCode, entity.getTradingPartnerCode(),
                        stage0);
        this.recordEntity(listPair, yakuwariDto, code);
        return code;
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

    // 該当する政治団体の明細リストから経路情報を記録する
    private void searchRoute(final Integer stage, final Integer userCode, final List<Integer> listPre,
            final List<Integer> listPro, final RelationPersonWithYakuwariDto yakuwariDto, final Integer tableCode) {
        for (Integer preCode : listPre) {
            for (Integer proCode : listPro) {
                List<WkTblUkaiKenkinEntity> listMeisai = wkTblUkaiKenkinRepository
                        .findByInsertUserCodeAndPoliticalOrgCodeAndTradingPartnerCodeAndPickupStage(userCode, preCode,
                                proCode, stage);
                this.recordEntity(listMeisai, yakuwariDto, tableCode);
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

    // 取得した明細データを経路データに変換して保存
    private void recordEntity(final List<WkTblUkaiKenkinEntity> listMeisai,
            final RelationPersonWithYakuwariDto yakuwariDto, final Integer tableCode) {

        if (!listMeisai.isEmpty()) {
            List<WkTblUkaiKenkinPickupRouteEntity> listRoute = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                    .practice(listMeisai, yakuwariDto);
            for (WkTblUkaiKenkinPickupRouteEntity entity : listRoute) {
                entity.setWkTblUkaiKenkinPickupRouteCode(tableCode);
            }
            wkTblUkaiKenkinPickupRouteRepository.saveAll(listRoute);
        }
    }

}
