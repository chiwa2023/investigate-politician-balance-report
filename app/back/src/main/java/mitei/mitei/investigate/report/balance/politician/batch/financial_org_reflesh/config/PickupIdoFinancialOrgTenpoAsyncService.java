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

/**
 * 全銀金融機関支店から再テーブルに複写処理を非同期で行う
 */
@Service
public class PickupIdoFinancialOrgTenpoAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob1 */
    @Qualifier(PickupIdo1FinancialOrgTenpoConfiguration.JOB_NAME)
    @Autowired
    private Job pickupIdoWkTbl1Job;

    /** 起動をするJob2 */
    @Qualifier(PickupIdo2FinancialOrgTenpoConfiguration.JOB_NAME)
    @Autowired
    private Job pickupIdoWkTbl2Job;

    /** 起動をするJob1 */
    @Qualifier(PickupIdo3FinancialOrgTenpoConfiguration.JOB_NAME)
    @Autowired
    private Job pickupIdoWkTbl3Job;

    /** 起動をするJob1 */
    @Qualifier(PickupIdo4FinancialOrgTenpoConfiguration.JOB_NAME)
    @Autowired
    private Job pickupIdoWkTbl4Job;

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
    public void practice(final CheckPrivilegeDto privilegeDto) // 改行よけ
            throws JobExecutionAlreadyRunningException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        // テーブル履歴情報読みだし
        Long loginUserId = privilegeDto.getLoginUserId();
        Integer loginUserCode = privilegeDto.getLoginUserCode();
        String loginUserName = privilegeDto.getLoginUserName();

        final String paramKeyTime = "executeTime";
        final String paramKeyUserId = "loginUserId";
        final String paramKeyUserCode = "loginUserCode";
        final String paramKeyUserName = "loginUserName";

        // ワークテーブル1
        JobParameters jobParametersWkTbl1 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now()).addLong(paramKeyUserId, loginUserId)
                .addString(paramKeyUserCode, String.valueOf(loginUserCode)).addString(paramKeyUserName, loginUserName)
                .toJobParameters();

        jobLauncher.run(pickupIdoWkTbl1Job, jobParametersWkTbl1);

        // ワークテーブル1
        JobParameters jobParametersWkTbl2 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now()).addLong(paramKeyUserId, loginUserId)
                .addString(paramKeyUserCode, String.valueOf(loginUserCode)).addString(paramKeyUserName, loginUserName)
                .toJobParameters();

        jobLauncher.run(pickupIdoWkTbl2Job, jobParametersWkTbl2);

        // ワークテーブル3
        JobParameters jobParametersWkTbl3 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now()).addLong(paramKeyUserId, loginUserId)
                .addString(paramKeyUserCode, String.valueOf(loginUserCode)).addString(paramKeyUserName, loginUserName)
                .toJobParameters();

        jobLauncher.run(pickupIdoWkTbl3Job, jobParametersWkTbl3);

        // ワークテーブル4
        JobParameters jobParametersWkTbl4 = new JobParametersBuilder()
                .addLocalDateTime(paramKeyTime, LocalDateTime.now()).addLong(paramKeyUserId, loginUserId)
                .addString(paramKeyUserCode, String.valueOf(loginUserCode)).addString(paramKeyUserName, loginUserName)
                .toJobParameters();

        jobLauncher.run(pickupIdoWkTbl4Job, jobParametersWkTbl4);

    }

}
