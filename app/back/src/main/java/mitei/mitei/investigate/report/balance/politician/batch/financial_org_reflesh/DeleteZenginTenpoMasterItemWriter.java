package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchMasterRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * マスタテーブル削除ItemWriter
 */
@Component
public class DeleteZenginTenpoMasterItemWriter extends JpaItemWriter<ZenginOrgBranchMasterEntity> {

    /** 全銀協記入機関・店舗マスタRepository */
    @Autowired
    private ZenginOrgBranchMasterRepository zenginOrgBranchMasterRepository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public DeleteZenginTenpoMasterItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends ZenginOrgBranchMasterEntity> items) {

        List<ZenginOrgBranchMasterEntity> listMoved = new ArrayList<>();
        CheckPrivilegeDto privilegeDto = CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName);
        for (ZenginOrgBranchMasterEntity entity : items) {
            listMoved.add(this.getSrcEntity(entity.getZenginOrgTempoMasterName(), privilegeDto));
        }

        zenginOrgBranchMasterRepository.saveAllAndFlush(listMoved);
    }

    private ZenginOrgBranchMasterEntity getSrcEntity(final String tableName, final CheckPrivilegeDto privilegeDto) {

        ZenginOrgBranchMasterEntity entity = zenginOrgBranchMasterRepository
                .findByZenginOrgTempoMasterNameAndSaishinKbn(tableName, DataHistoryStatusConstants.INSERT.value())
                .get(0);
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.UPDATE);

        return entity;
    }

}
