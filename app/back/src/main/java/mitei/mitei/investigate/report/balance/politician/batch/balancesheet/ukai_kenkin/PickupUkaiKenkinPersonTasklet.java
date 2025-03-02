package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.RecordPickupRouteStageLogic;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByExternalPersonLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import reactor.util.function.Tuple3;

/**
 * 迂回献金政治団体経路抽出Taskelt
 */
@Component
public class PickupUkaiKenkinPersonTasklet implements Tasklet, StepExecutionListener {

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 迂回献金階層保存Logic */
    @Autowired
    private RecordPickupRouteStageLogic recordPickupRouteStageLogic;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;


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

        List<Tuple3<Long, Integer, String>> listKojin = wkTblUkaiKenkinRepository.findTradingPartnerCode(userCode);

        RelationPersonWithYakuwariDto personWithYakuwariDto;
        List<Integer> listKojinEda = new ArrayList<>();
        listKojinEda.add(YoushikiEdaKbn.KOJIN);
        for (Tuple3<Long, Integer, String> tuple3 : listKojin) {
            personWithYakuwariDto = this.createYakuwariDto(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI);

            // 
            List<WkTblUkaiKenkinEntity> listDetail = wkTblUkaiKenkinRepository.findTaishoKojinAndDantaiByKojin(userCode,
                    personWithYakuwariDto.getCode());

            // 呼び出し元データも一緒に保存
            List<WkTblUkaiKenkinEntity> listRoot = wkTblUkaiKenkinRepository
                    .findByInsertUserCodeAndTradingPartnerCodeAndPickupStageAndYoushikiEdaKbnIn(userCode,
                            personWithYakuwariDto.getCode(), stage0, listKojinEda);

            List<WkTblUkaiKenkinPickupRouteEntity> listTemp = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                    .practice(listDetail, personWithYakuwariDto);
            // 抽出した起点データから経路を保存
            Integer tableCode;
            for (WkTblUkaiKenkinPickupRouteEntity entity : listTemp) {
                tableCode = recordPickupRouteStageLogic.practice(userCode, entity, personWithYakuwariDto);
                // 抽出基準となった、かつ比較対象の0階層データを保存
                List<WkTblUkaiKenkinPickupRouteEntity> listFirst = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                        .practice(listRoot, personWithYakuwariDto);
                WkTblUkaiKenkinPickupRouteEntity entityFirst = listFirst.get(0);
                entityFirst.setWkTblUkaiKenkinPickupRouteCode(tableCode);
                wkTblUkaiKenkinPickupRouteRepository.saveAndFlush(entityFirst);

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

}
