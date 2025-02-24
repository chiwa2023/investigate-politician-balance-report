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
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes01Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes02Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes03Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes04Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes05Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes06Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes07Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes08Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes09Logic;
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
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
        poliOrgCode = Math.toIntExact(stepExecution.getJobParameters().getLong("poliOrgCode"));
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

        final int stage1 = 1;
        final int stage2 = 2;
        final int stage3 = 3;
        final int stage4 = 4;
        final int stage5 = 5;
        final int stage6 = 6;
        final int stage7 = 7;
        final int stage8 = 8;
        final int stage9 = 9;

        for (WkTblUkaiKenkinPickupRouteEntity entity : listPoliOrg) {

            switch (entity.getPickupStage()) {
                // 取得階層1
                case stage1:
                    insertUkaiKenkinRouteTimes01Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層2
                case stage2:
                    insertUkaiKenkinRouteTimes02Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層3
                case stage3:
                    insertUkaiKenkinRouteTimes03Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層4
                case stage4:
                    insertUkaiKenkinRouteTimes04Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層5
                case stage5:
                    insertUkaiKenkinRouteTimes05Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層6
                case stage6:
                    insertUkaiKenkinRouteTimes06Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層7
                case stage7:
                    insertUkaiKenkinRouteTimes07Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層8
                case stage8:
                    insertUkaiKenkinRouteTimes08Logic.practice(userCode, entity, propertyEntiy);
                    break;
                // 取得階層9
                case stage9:
                    insertUkaiKenkinRouteTimes09Logic.practice(userCode, entity, propertyEntiy);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + entity.getPickupStage());
            }
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
