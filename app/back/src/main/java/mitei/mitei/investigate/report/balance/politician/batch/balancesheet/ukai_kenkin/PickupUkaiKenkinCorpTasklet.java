package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.Arrays;
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
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByExternalPersonLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * 迂回献金政治団体経路抽出Taskelt
 */
@Component
public class PickupUkaiKenkinCorpTasklet implements Tasklet, StepExecutionListener {

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;

    /** 迂回献金階層保存Logic */
    @Autowired
    private RecordPickupRouteStageLogic recordPickupRouteStageLogic;

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

        // 階層1以上で階層0で出現した企業・団体をすべて抽出
        List<WkTblUkaiKenkinEntity> listCorp = wkTblUkaiKenkinRepository.findTradingCorpOverByZeroStage(userCode);

        // 抽出された団体を経路で保存
        RelationPersonWithYakuwariDto personWithYakuwariDto;
        for (WkTblUkaiKenkinEntity entityMeisai : listCorp) {

            personWithYakuwariDto = this.createYakuwariDto(entityMeisai.getTradingPartnerId(),
                    entityMeisai.getTradingPartnerCode(), entityMeisai.getTradingPartnerName());
            List<WkTblUkaiKenkinPickupRouteEntity> listTemp = convertUkaiKenkinDetailToRouteByExternalPersonLogic
                    .practice(Arrays.asList(entityMeisai), personWithYakuwariDto);

            // 抽出した起点データから経路を保存
            for (WkTblUkaiKenkinPickupRouteEntity entity : listTemp) {
                recordPickupRouteStageLogic.practice(userCode, entity, personWithYakuwariDto);
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
