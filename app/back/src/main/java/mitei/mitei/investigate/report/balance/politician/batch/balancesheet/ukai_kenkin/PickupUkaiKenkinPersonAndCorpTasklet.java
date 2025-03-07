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
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByExternalPersonLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuple5;
import reactor.util.function.Tuples;

/**
 * 迂回献金個人と企業団体経路抽出Taskelt
 */
@Component
public class PickupUkaiKenkinPersonAndCorpTasklet implements Tasklet, StepExecutionListener {

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository ukaiKenkinRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;

    /** 操作者同一識別コード */
    private Integer userCode;

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

        // 個人寄付データで同じ関連者を持つ企業・個人と政治団体データを抽出する
        List<Tuple3<Long, Integer, String>> listKojin = ukaiKenkinRepository.findTradingPartnerCodeInStageZero(userCode);

        List<Integer> listKey = new ArrayList<>();
        RelationPersonWithYakuwariDto personWithYakuwariDto;
        List<Integer> listKojinEda = new ArrayList<>();
        listKojinEda.add(YoushikiEdaKbn.KOJIN);

        final int stage0 = 0;

        for (Tuple3<Long, Integer, String> tuple3 : listKojin) {
            personWithYakuwariDto = this.createYakuwariDto(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI);

            if (!listKey.contains(tuple3.getT2())) {
                listKey.add(tuple3.getT2());

                List<WkTblUkaiKenkinEntity> listDetail = ukaiKenkinRepository.findCorpAndPoriOrgByKojinInZero(userCode,
                        personWithYakuwariDto.getCode());

                if (!listDetail.isEmpty()) {

                    // 呼び出し元データも一緒に保存
                    List<WkTblUkaiKenkinEntity> listRoot = ukaiKenkinRepository
                            .findByInsertUserCodeAndTradingPartnerCodeAndPickupStageAndYoushikiEdaKbnIn(userCode,
                                    personWithYakuwariDto.getCode(), stage0, listKojinEda);
                    listDetail.addAll(0, listRoot);

                    // 0階層リストは同じ経路として保存
                    List<WkTblUkaiKenkinEntity> listStage0 = listDetail.stream()
                            .filter(e -> e.getPickupStage() == stage0).toList();
                    List<WkTblUkaiKenkinPickupRouteEntity> listRouteStage0 = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                            .practice(listStage0, personWithYakuwariDto);
                    this.recordData(listRouteStage0);

                    // TODO Stage0でない場合は経路データで登録
                }
            }
        }

        // 企業・団体寄付の代表者と同じ関連者を持つ企業・個人と政治団体データを抽出する
        List<Integer> listKigyouEda = new ArrayList<>();
        listKigyouEda.add(YoushikiEdaKbn.KIGYOU_DANTAI);
        List<Tuple4<Integer, Long, Integer, String>> listCorp = ukaiKenkinRepository.findTradingCorpByDelegateCode(userCode);
        for (Tuple4<Integer, Long, Integer, String> tuple4 : listCorp) {
            personWithYakuwariDto = this.createYakuwariDto(tuple4.getT2(), tuple4.getT3(), tuple4.getT4());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);
            int codeCorp = tuple4.getT1();

            if (!listKey.contains(tuple4.getT3())) {
                listKey.add(tuple4.getT3());

                List<WkTblUkaiKenkinEntity> listDetail = ukaiKenkinRepository.findMeisaiCorpPoliOrgByPartnerAndPerson(userCode,
                        codeCorp, personWithYakuwariDto.getCode());

                if (!listDetail.isEmpty()) {

                    // 呼び出し元データも一緒に保存
                    List<WkTblUkaiKenkinEntity> listRoot = ukaiKenkinRepository
                            .findByInsertUserCodeAndTradingPartnerCodeAndTradingPartnerDelegateCodeAndPickupStageAndYoushikiEdaKbnIn(
                                    userCode, codeCorp, personWithYakuwariDto.getCode(), stage0, listKigyouEda);
                    listDetail.addAll(0, listRoot);

                    List<WkTblUkaiKenkinEntity> listStage0 = listDetail.stream().filter(e -> e.getPickupStage() == 0)
                            .toList();

                    // 階層0はすべて同じ経路として保存
                    List<WkTblUkaiKenkinPickupRouteEntity> listRouteStage0 = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                            .practice(listStage0, personWithYakuwariDto);
                    this.recordData(listRouteStage0);

                    // TODO Stage0でない場合は経路データで登録
                }
            }
        }

        // 寄付者の政治団体と同じ関連者を持つ企業と政治団体データを抽出する
        List<Tuple5<Integer, Long, Integer, String, String>> listPoliOrg = new ArrayList<>();
        listPoliOrg.addAll(this.createKanrenshaList(listKey, ukaiKenkinRepository.findTradingPoliOrgByDelegateCode(userCode),
                RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA));
        listPoliOrg
                .addAll(this.createKanrenshaList(listKey, ukaiKenkinRepository.findTradingPoliOrgByAccountManagerCode(userCode),
                        RelationPersonYakuwariConstants.YAKUWARI_KAIKEISEKINISHA));
        listPoliOrg
                .addAll(this.createKanrenshaList(listKey, ukaiKenkinRepository.findTradingPoliOrgByShikinDantaiCode(userCode),
                        RelationPersonYakuwariConstants.YAKUWARI_SHIKIN_SEKININSHA));
        listPoliOrg.addAll(this.createKanrenshaList(listKey, ukaiKenkinRepository.findTradingPoliOrgByKokkaiGin1Code(userCode),
                RelationPersonYakuwariConstants.YAKUWARI_GIIN1));
        listPoliOrg.addAll(this.createKanrenshaList(listKey, ukaiKenkinRepository.findTradingPoliOrgByKokkaiGin2Code(userCode),
                RelationPersonYakuwariConstants.YAKUWARI_GIIN2));
        listPoliOrg.addAll(this.createKanrenshaList(listKey, ukaiKenkinRepository.findTradingPoliOrgByKokkaiGin3Code(userCode),
                RelationPersonYakuwariConstants.YAKUWARI_GIIN3));

        for (Tuple5<Integer, Long, Integer, String, String> tuple5 : listPoliOrg) {
            personWithYakuwariDto = this.createYakuwariDto(tuple5.getT2(), tuple5.getT3(), tuple5.getT4());
            personWithYakuwariDto.setYakuwari(tuple5.getT5());
            int codeCorp = tuple5.getT1();

            List<WkTblUkaiKenkinEntity> listDetail = ukaiKenkinRepository.findMeisaiCorpPoliOrgByPartnerAndPerson(userCode,
                    codeCorp, personWithYakuwariDto.getCode());

            if (!listDetail.isEmpty()) {

                // 呼び出し元データも一緒に保存
                List<WkTblUkaiKenkinEntity> listRoot = ukaiKenkinRepository.findMeisaiDataPoliOrgByPartnerAndPerson(userCode,
                        codeCorp, personWithYakuwariDto.getCode());
                listDetail.addAll(0, listRoot);

                List<WkTblUkaiKenkinEntity> listStage0 = listDetail.stream().filter(e -> e.getPickupStage() == 0)
                        .toList();
                // 階層0はすべて同じ経路として保存
                List<WkTblUkaiKenkinPickupRouteEntity> listRouteStage0 = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                        .practice(listStage0, personWithYakuwariDto);
                this.recordData(listRouteStage0);

                // TODO Stage0でない場合は経路データで登録
            }

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

    private List<Tuple5<Integer, Long, Integer, String, String>> createKanrenshaList(final List<Integer> listTrading,
            final List<Tuple4<Integer, Long, Integer, String>> listGetData, final String yakuwari) {

        List<Tuple5<Integer, Long, Integer, String, String>> list = new ArrayList<>();
        for (Tuple4<Integer, Long, Integer, String> tuple4 : listGetData) {
            if (!listTrading.contains(tuple4.getT3())) {
                Tuple5<Integer, Long, Integer, String, String> tuple5 = Tuples.of(tuple4.getT1(), tuple4.getT2(),
                        tuple4.getT3(), tuple4.getT4(), yakuwari);
                list.add(tuple5);
                listTrading.add(tuple4.getT3());
            }
        }

        return list;
    }

}
