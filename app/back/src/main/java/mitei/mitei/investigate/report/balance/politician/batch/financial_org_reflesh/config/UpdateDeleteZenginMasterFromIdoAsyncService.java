package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh.config;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkCapsuleDto;

/**
 * 異動テーブルからマスタテーブルに廃止かつ移動先決定データを複写するBatch非同期起動Service
 */
@Service
public class UpdateDeleteZenginMasterFromIdoAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob1 */
    @Qualifier(UpdateDeleteZenginMasterFromIdoBatchConfiguration.JOB_NAME)
    @Autowired
    private Job updateDeleteZenginMasterFromIdo;

    
    /**
     * 非同期処理を行う
     *
     * @throws IOException                         ファイル関係例外
     * @throws JobExecutionAlreadyRunningException バッチ実行時例外
     * @throws JobRestartException                 バッチ実行時例外
     * @throws JobInstanceAlreadyCompleteException バッチ実行時例外
     * @throws JobParametersInvalidException       バッチ実行時例外
     */
    @Async
    public void practice(final TemplateFrameworkCapsuleDto capsuleDto) // 改行よけ
            throws JobExecutionAlreadyRunningException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        // テーブル履歴情報読みだし
        CheckPrivilegeDto privilegeDto = capsuleDto.getCheckPrivilegeDto();
        Long loginUserId = privilegeDto.getLoginUserId();
        Integer loginUserCode = privilegeDto.getLoginUserCode();
        String loginUserName = privilegeDto.getLoginUserName();

        final String paramKeyTime = "executeTime";
        final String paramKeyUserId = "userId";
        final String paramKeyUserCode = "userCode";
        final String paramKeyUserName = "userName";

        // ワークテーブル1
        JobParameters jobParametersWkTbl1 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now()).addLong(paramKeyUserId, loginUserId)
                .addLong(paramKeyUserCode, Long.valueOf(loginUserCode)).addString(paramKeyUserName, loginUserName)
                .toJobParameters();

        jobLauncher.run(updateDeleteZenginMasterFromIdo, jobParametersWkTbl1);

    }    
    
    
    
}
