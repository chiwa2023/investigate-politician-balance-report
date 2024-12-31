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
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 全銀マスタテーブルに書き込みするItemWriter
 */
@Component
public class ZenginOrgChangeBranchItemWriter extends JpaItemWriter<ZenginOrgChangeBranchEntity> {

    /** 全銀協記入機関・店舗マスタRepository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public ZenginOrgChangeBranchItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends ZenginOrgChangeBranchEntity> items) {

        Integer code = 0;
        Optional<ZenginOrgChangeBranchEntity> optional = zenginOrgChangeBranchRepository
                .findFirstByOrderByZenginOrgChangeBranchCodeDesc();

        if (!optional.isEmpty()) {
            code += optional.get().getZenginOrgChangeBranchCode();
        }

        for (ZenginOrgChangeBranchEntity entity : items) {
            code++;
            SetTableDataHistoryUtil.practice(CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName), entity,
                    DataHistoryStatusConstants.INSERT);
            entity.setZenginOrgChangeBranchCode(code);
        }

        zenginOrgChangeBranchRepository.saveAllAndFlush(items);
    }

}
