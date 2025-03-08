package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByExternalPersonLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByInClassLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import reactor.util.function.Tuple3;

/**
 * 0階層における(迂回を持たない名義隠し)迂回献金例を抽出する
 */
@Component
public class PickupUkaiKenkinStageZeroTasklet implements Tasklet, StepExecutionListener {

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByInClassLogic convertUkaiKenkinDetailToRouteByInClassLogic;

    /** 階層0定数 */
    private static final int stage0 = 0;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        List<Integer> listPerson = wkTblUkaiKenkinRepository.findUkaiPickupStageZero(userCode, YoushikiEdaKbn.KOJIN);

        final int stage0 = 0;

        // 個人0階層迂回
        RelationPersonWithYakuwariDto personWithYakuwariDto;
        for (Integer partnerCode : listPerson) {
            List<WkTblUkaiKenkinEntity> listMeisai = wkTblUkaiKenkinRepository
                    .findByInsertUserCodeAndTradingPartnerCodeAndPickupStage(userCode, partnerCode, stage0);
            WkTblUkaiKenkinEntity entity0 = listMeisai.get(0);
            personWithYakuwariDto = this.createYakuwariDto(entity0.getTradingPartnerId(),
                    entity0.getTradingPartnerCode(), entity0.getTradingPartnerName());
            this.saveFlush(
                    convertUkaiKenkinDetailToRouteByExternalPersonLogic.practice(listMeisai, personWithYakuwariDto));
        }

        // 個人寄付データで同じ関連者を持つ企業・個人と政治団体データを抽出する(0階層限定)
        List<Tuple3<Long, Integer, String>> listKojin = wkTblUkaiKenkinRepository.findTradingPartnerCode(userCode);

        List<Integer> listKey = new ArrayList<>();
        // RelationPersonWithYakuwariDto personWithYakuwariDto;
        List<Integer> listKojinEda = new ArrayList<>();
        listKojinEda.add(YoushikiEdaKbn.KOJIN);

        for (Tuple3<Long, Integer, String> tuple3 : listKojin) {
            personWithYakuwariDto = this.createYakuwariDto(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI);

            if (!listKey.contains(tuple3.getT2())) {
                listKey.add(tuple3.getT2());

                List<WkTblUkaiKenkinEntity> listDetail = wkTblUkaiKenkinRepository.findCorpAndPoriOrgByKojinInZero(userCode,
                        personWithYakuwariDto.getCode());

                this.savePerson(listDetail, personWithYakuwariDto, listKojinEda);

            }
        }

        // 企業・団体0階層迂回
        List<Integer> listCorp = wkTblUkaiKenkinRepository.findUkaiPickupStageZero(userCode,
                YoushikiEdaKbn.KIGYOU_DANTAI);
        for (Integer partnerCode : listCorp) {
            List<WkTblUkaiKenkinEntity> listMeisai = wkTblUkaiKenkinRepository
                    .findByInsertUserCodeAndTradingPartnerCodeAndPickupStage(userCode, partnerCode, stage0);
            WkTblUkaiKenkinEntity entity0 = listMeisai.get(0);
            personWithYakuwariDto = this.createYakuwariDto(entity0.getTradingPartnerId(),
                    entity0.getTradingPartnerCode(), entity0.getTradingPartnerName());
            this.saveFlush(
                    convertUkaiKenkinDetailToRouteByExternalPersonLogic.practice(listMeisai, personWithYakuwariDto));
        }

        // 政治団体0階層迂回
        PoliticalOrganizationPropertyEntity propertyEntity;
        List<Integer> listPoliOrg = wkTblUkaiKenkinRepository.findUkaiPickupStageZeroPoliOrg(userCode);
        for (Integer partnerCode : listPoliOrg) {
            List<WkTblUkaiKenkinEntity> listMeisai = wkTblUkaiKenkinRepository
                    .findByInsertUserCodeAndTradingPartnerCodeAndPickupStage(userCode, partnerCode, stage0);
            // 迂回献金の取り引き相手から政治団体属性を生成する
            WkTblUkaiKenkinEntity entity0 = listMeisai.get(0);
            propertyEntity = this.createPoliOrgProperty(entity0);

            this.saveFlush(convertUkaiKenkinDetailToRouteByInClassLogic.practice(listMeisai, propertyEntity));
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    // 関連者役割付きDtoを作成する
    private RelationPersonWithYakuwariDto createYakuwariDto(final Long id // NOPMD
            , final Integer code, final String name) {
        RelationPersonWithYakuwariDto dto = new RelationPersonWithYakuwariDto();
        dto.setId(id);
        dto.setCode(code);
        dto.setName(name);

        return dto;
    }

    // 作成した迂回献金経路を保存する
    private void saveFlush(final List<WkTblUkaiKenkinPickupRouteEntity> list) {

        // コードを取得して保存する
        Integer code = 1;
        Optional<WkTblUkaiKenkinPickupRouteEntity> optional = wkTblUkaiKenkinPickupRouteRepository
                .findFirstByOrderByWkTblUkaiKenkinPickupRouteCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblUkaiKenkinPickupRouteCode();
        }

        for (WkTblUkaiKenkinPickupRouteEntity routeEntity : list) {
            routeEntity.setWkTblUkaiKenkinPickupRouteCode(code);
        }

        wkTblUkaiKenkinPickupRouteRepository.saveAllAndFlush(list);
    }

    // 明細から政治団体属性を作成する
    private PoliticalOrganizationPropertyEntity createPoliOrgProperty(final WkTblUkaiKenkinEntity entityMeisai) {
        PoliticalOrganizationPropertyEntity propertyEntity = new PoliticalOrganizationPropertyEntity();

        propertyEntity.setDelegateRelationPersonId(entityMeisai.getTradingPartnerDelegateId());
        propertyEntity.setDelegateRelationPersonCode(entityMeisai.getTradingPartnerDelegateCode());
        propertyEntity.setDelegateKoushokuName(entityMeisai.getTradingPartnerDelegateName());

        propertyEntity.setAccountManagerRelationPersonId(entityMeisai.getTradingOrgAccountManagerId());
        propertyEntity.setAccountManagerRelationPersonCode(entityMeisai.getTradingOrgAccountManagerCode());
        propertyEntity.setAccountManagerKoushokuName(entityMeisai.getTradingOrgAccountManagerName());

        propertyEntity.setShikinDaihyouRelationPersonId(entityMeisai.getTradingOrgShikinDantaiId());
        propertyEntity.setShikinDaihyouRelationPersonCode(entityMeisai.getTradingOrgShikinDantaiCode());
        propertyEntity.setShikinDaihyouKoushokuName(entityMeisai.getTradingOrgShikinDantaiName());

        propertyEntity.setGiin1RelationPersonId(entityMeisai.getTradingOrgKokkaiGiin3Id());
        propertyEntity.setGiin1RelationPersonCode(entityMeisai.getTradingOrgKokkaiGiin1Code());
        propertyEntity.setGiin1KoushokuName(entityMeisai.getTradingOrgKokkaiGiin1Name());

        propertyEntity.setGiin2RelationPersonId(entityMeisai.getTradingOrgKokkaiGiin2Id());
        propertyEntity.setGiin2RelationPersonCode(entityMeisai.getTradingOrgKokkaiGiin2Code());
        propertyEntity.setGiin3KoushokuName(entityMeisai.getTradingOrgKokkaiGiin2Name());

        propertyEntity.setGiin3RelationPersonId(entityMeisai.getTradingOrgKokkaiGiin3Id());
        propertyEntity.setGiin3RelationPersonCode(entityMeisai.getTradingOrgKokkaiGiin3Code());
        propertyEntity.setGiin3KoushokuName(entityMeisai.getTradingOrgKokkaiGiin3Name());

        return propertyEntity;
    }

    // 関連者役割付きDtoを作成する
    private void savePerson(final List<WkTblUkaiKenkinEntity> listDetail,
            final RelationPersonWithYakuwariDto personWithYakuwariDto, final List<Integer> listKojinEda) {
        if (!listDetail.isEmpty()) {

            // 呼び出し元データも一緒に保存
            List<WkTblUkaiKenkinEntity> listRoot = wkTblUkaiKenkinRepository
                    .findByInsertUserCodeAndTradingPartnerCodeAndPickupStageAndYoushikiEdaKbnIn(userCode,
                            personWithYakuwariDto.getCode(), stage0, listKojinEda);
            listDetail.addAll(0, listRoot);

            // 0階層リストは同じ経路として保存
            List<WkTblUkaiKenkinEntity> listStage0 = listDetail.stream().filter(e -> e.getPickupStage() == stage0)
                    .toList();
            List<WkTblUkaiKenkinPickupRouteEntity> listRouteStage0 = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                    .practice(listStage0, personWithYakuwariDto);
            this.recordData(listRouteStage0);
        }
    }

    // 経路を保存する
    private void recordData(final List<WkTblUkaiKenkinPickupRouteEntity> listSave) {
        // 経路コードを取得して設定(階層0なのですべてのデータが経路)
        Integer code = 1;
        Optional<WkTblUkaiKenkinPickupRouteEntity> optional = wkTblUkaiKenkinPickupRouteRepository
                .findFirstByOrderByWkTblUkaiKenkinPickupRouteCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblUkaiKenkinPickupRouteCode();
        }
        for (WkTblUkaiKenkinPickupRouteEntity entity : listSave) {
            entity.setWkTblUkaiKenkinPickupRouteCode(code);
        }

        wkTblUkaiKenkinPickupRouteRepository.saveAll(listSave);
    }

}
