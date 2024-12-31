package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk4Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk4Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 全銀マスタテーブルに書き込みするItemWriter
 */
@Component
public class ZenginOrgBranchWkTbl4ItemWriter extends JpaItemWriter<ZenginOrgBranchWk4Entity> {

    /** 全銀協記入機関・店舗ワークテーブル1Repository */
    @Autowired
    private ZenginOrgBranchWk4Repository zenginOrgBranchWk4Repository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public ZenginOrgBranchWkTbl4ItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /** 操作者同一識別コード */
    private Long userId;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 操作者同一識別コード */
    private String userName;

    /**
     * ログインユーザ情報を取得する
     *
     * @param stepExecution Step実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userId = stepExecution.getJobParameters().getLong("userId");
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
        userName = stepExecution.getJobParameters().getString("userName");
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends ZenginOrgBranchWk4Entity> items) {

        Integer code = 0;
        Optional<ZenginOrgBranchWk4Entity> optional = zenginOrgBranchWk4Repository
                .findFirstByOrderByZenginOrgBranchWk4CodeDesc();

        if (!optional.isEmpty()) {
            code += optional.get().getZenginOrgBranchWk4Code();
        }

        for (ZenginOrgBranchWk4Entity entity : items) {
            code++;
            SetTableDataHistoryUtil.practice(CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName), entity,
                    DataHistoryStatusConstants.INSERT);
            entity.setZenginOrgBranchWk4Code(code);
        }

        zenginOrgBranchWk4Repository.saveAll(items).size();
    }

}
