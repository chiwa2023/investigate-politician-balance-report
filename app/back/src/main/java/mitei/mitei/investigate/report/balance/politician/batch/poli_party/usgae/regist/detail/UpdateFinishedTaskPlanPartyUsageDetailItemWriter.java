package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.ConvertCheckPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ワークテーブルへのデータコピーが終わった処理計画データを処理済に更新する
 */
@Component
public class UpdateFinishedTaskPlanPartyUsageDetailItemWriter
        extends JpaItemWriter<WkTblPoliPartyUsageReportEntity> {

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public UpdateFinishedTaskPlanPartyUsageDetailItemWriter(
            final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /** 政治資金収支報告書準備計画Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    /** 履歴テーブルから権限確認Dto生成Utility */
    @Autowired
    private ConvertCheckPrivilegeDtoUtil convertCheckPrivilegeDtoUtil;

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPoliPartyUsageReportEntity> items) {

        TaskPlanPartyUsageDetailEntity detailEntity;
        Integer saishin = DataHistoryStatusConstants.INSERT.value();
        for (WkTblPoliPartyUsageReportEntity entity : items) {
            detailEntity = taskPlanPartyUsageDetailRepository.findById(entity.getTaskPlanPartyUsageDetailId()).get();
            if (!detailEntity.getIsFinished() && saishin.equals(detailEntity.getSaishinKbn())) {
                SetTableDataHistoryUtil.practice(convertCheckPrivilegeDtoUtil.practice(entity), detailEntity,
                        DataHistoryStatusConstants.UPDATE);
                detailEntity.setIsFinished(true);
                taskPlanPartyUsageDetailRepository.save(detailEntity);
            }
        }
    }
}
