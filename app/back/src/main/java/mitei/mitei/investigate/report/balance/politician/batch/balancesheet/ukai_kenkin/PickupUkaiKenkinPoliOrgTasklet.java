package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

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

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.RecordPickupRouteStageLogic;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.PickupSamePoliOrgPartnerLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;

/**
 * 迂回献金政治団体経路抽出Taskelt
 */
@Component
public class PickupUkaiKenkinPoliOrgTasklet implements Tasklet, StepExecutionListener {

    /** 関係者が同じである政治団体明細から経路抽出Logic */
    @Autowired
    private PickupSamePoliOrgPartnerLogic pickupSamePoliOrgPartnerLogic;

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** (操作者指定)政治団体同一識別コード */
    private Integer poliOrgCode;

    /** 交付金気分 */
    private Boolean isSearchKoufukin;

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
        poliOrgCode = Math.toIntExact(stepExecution.getJobParameters().getLong("poliOrgCode"));
        isSearchKoufukin = Boolean.valueOf(stepExecution.getJobParameters().getString("isSearchKoufukin"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 政治団体データを抽出する
        Optional<PoliticalOrganizationPropertyEntity> optional = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrgCode, DataHistoryStatusConstants.INSERT.value());

        PoliticalOrganizationPropertyEntity propertyEntiy = optional.get();

        Integer koufukinKbn = 100; // SUPPRESS CHECKSTYLE MagicNumber
        if (isSearchKoufukin) {
            koufukinKbn = YoushikiKbn.KOUFUKIN;
        }

        // 献金が迂回された瞬間のデータを抽出する
        List<WkTblUkaiKenkinPickupRouteEntity> listPoliOrg = pickupSamePoliOrgPartnerLogic.practice(userCode,
                koufukinKbn, propertyEntiy);

        // 抽出した起点データから経路を保存
        for (WkTblUkaiKenkinPickupRouteEntity entity : listPoliOrg) {
            recordPickupRouteStageLogic.practice(userCode, entity, propertyEntiy);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
