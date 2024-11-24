package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDocumentEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDocumentRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.ConvertCheckPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書ワークテーブル終了報告Tasklet
 */
@Component
public class UpdateFinishedWkTblBalancesheetTasklet implements Tasklet {

    /** 登録済Idと文書同一識別コード一時保存Repository */
    @Autowired
    private TaskPlanBalancesheetDocumentRepository taskPlanBalancesheetDocumentRepository;

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /** 権限Dto変換Utility */
    @Autowired
    private ConvertCheckPrivilegeDtoUtil convertCheckPrivilegeDtoUtil;

    /** 1回で取得するデータ数(Chunk数と意味は同じ) */
    private static final int LIMIT_COUNT = 1;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        int count = taskPlanBalancesheetDocumentRepository.findBySaishinKbnCount();
        final int INSERT = DataHistoryStatusConstants.INSERT.value();
        // すべての該当データが存在しなくなるまで処理する
        while (count > 0) {

            List<TaskPlanBalancesheetDocumentEntity> list = taskPlanBalancesheetDocumentRepository
                    .findBySaishinKbnLimit(LIMIT_COUNT);
            List<WkTblPoliOrgBalancesheetReportEntity> listWk = new ArrayList<>(); // NOPMD
            // 値を更新して変更
            for (TaskPlanBalancesheetDocumentEntity entity : list) {

                // 文書同一識別コードとワークテーブルIdの値の設定
                SetTableDataHistoryUtil.practice(convertCheckPrivilegeDtoUtil.practice(entity), entity,
                        DataHistoryStatusConstants.UPDATE);

                Optional<WkTblPoliOrgBalancesheetReportEntity> optional =wkTblPoliOrgBalancesheetReportRepository
                        .findById(entity.getTaskPlanBalancesheetDetailId());
                
                if(!optional.isEmpty()) { // NOPMD
                    WkTblPoliOrgBalancesheetReportEntity reportEntity = optional.get();
                    
                    // ワークテーブルの設定
                    if (INSERT == reportEntity.getSaishinKbn()) {
                        SetTableDataHistoryUtil.practice(convertCheckPrivilegeDtoUtil.practice(reportEntity), reportEntity,
                                DataHistoryStatusConstants.UPDATE);
                        listWk.add(reportEntity);
                    }
                }else {
                    // TODO 対処方法が決定次第修正する
                    //System.out.println("---------------");
                    //System.out.println("Id呼び出し不可" + entity.getTaskPlanBalancesheetDetailId());
                }
                

            }

            // 全体更新
            wkTblPoliOrgBalancesheetReportRepository.saveAllAndFlush(listWk);
            taskPlanBalancesheetDocumentRepository.saveAllAndFlush(list);

            // 更新後の取得
            count = taskPlanBalancesheetDocumentRepository.findBySaishinKbnCount();
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
