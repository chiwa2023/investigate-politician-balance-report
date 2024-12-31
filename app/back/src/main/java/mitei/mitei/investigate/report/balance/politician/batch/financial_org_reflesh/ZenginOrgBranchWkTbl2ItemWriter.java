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
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk2Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 全銀マスタテーブルに書き込みするItemWriter
 */
@Component
public class ZenginOrgBranchWkTbl2ItemWriter extends JpaItemWriter<ZenginOrgBranchWk2Entity> {

    /** 全銀協記入機関・店舗ワークテーブル1Repository */
    @Autowired
    private ZenginOrgBranchWk2Repository zenginOrgBranchWk2Repository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public ZenginOrgBranchWkTbl2ItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends ZenginOrgBranchWk2Entity> items) {

        Integer code = 0;
        Optional<ZenginOrgBranchWk2Entity> optional = zenginOrgBranchWk2Repository
                .findFirstByOrderByZenginOrgBranchWk2CodeDesc();

        if (!optional.isEmpty()) {
            code += optional.get().getZenginOrgBranchWk2Code();
        }

        for (ZenginOrgBranchWk2Entity entity : items) {
            code++;
            SetTableDataHistoryUtil.practice(CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName), entity,
                    DataHistoryStatusConstants.INSERT);
            entity.setZenginOrgBranchWk2Code(code);
        }

        zenginOrgBranchWk2Repository.saveAll(items).size();
    }

}
