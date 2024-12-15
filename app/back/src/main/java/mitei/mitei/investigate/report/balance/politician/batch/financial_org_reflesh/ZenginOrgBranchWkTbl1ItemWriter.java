package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


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
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchWk1Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 全銀マスタテーブルに書き込みするItemWriter
 */
@Component
public class ZenginOrgBranchWkTbl1ItemWriter extends JpaItemWriter<ZenginOrgBranchWk1Entity>  {

    /** 全銀協記入機関・店舗ワークテーブル1Repository */
    @Autowired
    private ZenginOrgBranchWk1Repository zenginOrgBranchwk1Repository;
    
    
    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public ZenginOrgBranchWkTbl1ItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }


    /** 権限確認Dto */
    private final CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
    
    /**
     * ログインユーザ情報を取得する
     *
     * @param stepExecution Step実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        
        privilegeDto.setLoginUserId(stepExecution.getJobParameters().getLong("loginUserId"));
        privilegeDto.setLoginUserCode(Integer.parseInt(stepExecution.getJobParameters().getString("loginUserCode")));
        privilegeDto.setLoginUserName(stepExecution.getJobParameters().getString("loginUserName"));
    }
    
    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends ZenginOrgBranchWk1Entity> items) {
        
        Integer code = 0;
        Optional<ZenginOrgBranchWk1Entity> optional = zenginOrgBranchwk1Repository.findFirstByOrderByZenginOrgBranchWk1CodeDesc();
        
        if(!optional.isEmpty()) {
            code += optional.get().getZenginOrgBranchWk1Code();
        }
        
        for(ZenginOrgBranchWk1Entity entity : items) {
            code++;
            SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
            entity.setZenginOrgBranchWk1Code(code);
        }
        
        zenginOrgBranchwk1Repository.saveAll(items).size();
    }


}
