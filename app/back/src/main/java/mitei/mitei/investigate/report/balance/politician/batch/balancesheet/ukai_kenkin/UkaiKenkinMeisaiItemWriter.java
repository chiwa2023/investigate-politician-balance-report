package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 迂回献金明細に登録するItemWriter
 */
@Component
public class UkaiKenkinMeisaiItemWriter extends JpaItemWriter<WkTblUkaiKenkinEntity> {

    /** 迂回献金明細ワークテーブル1Repository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public UkaiKenkinMeisaiItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /** 権限確認Dto */
    private CheckPrivilegeDto privilegeDto;

    /**
     * ログインユーザ情報を取得する
     *
     * @param stepExecution Step実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        Long userId = stepExecution.getJobParameters().getLong("userId");
        Integer userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
        String userName = stepExecution.getJobParameters().getString("userName");

        privilegeDto = CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblUkaiKenkinEntity> items) {

        // テーブル同一識別コード処理
        Integer code = 0;
        Optional<WkTblUkaiKenkinEntity> optional = wkTblUkaiKenkinRepository
                .findFirstByOrderByWkTblUkaiKenkinCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getWkTblUkaiKenkinCode();
        }

        for (WkTblUkaiKenkinEntity entity : items.getItems()) {
            code++;
            entity.setWkTblUkaiKenkinCode(code);
            SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        }

        wkTblUkaiKenkinRepository.saveAll(items);
    }
}
