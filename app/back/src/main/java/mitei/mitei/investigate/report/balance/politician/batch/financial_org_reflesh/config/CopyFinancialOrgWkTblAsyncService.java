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
import mitei.mitei.investigate.report.balance.politician.dto.zengin_org_master.RegistZenginOrgWkTbleCapsuleDto;

/**
 * 全銀csvを一括読み取りしワークテーブルに保存するService
 */
@Service
public class CopyFinancialOrgWkTblAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob1 */
    @Qualifier(CopyFinancialOrgWkTbl1BatchConfiguration.JOB_NAME)
    @Autowired
    private Job copyWkTbl1Job;

    /** 起動をするJob2 */
    @Qualifier(CopyFinancialOrgWkTbl2BatchConfiguration.JOB_NAME)
    @Autowired
    private Job copyWkTbl2Job;

    /** 起動をするJob3 */
    @Qualifier(CopyFinancialOrgWkTbl3BatchConfiguration.JOB_NAME)
    @Autowired
    private Job copyWkTbl3Job;

    /** 起動をするJob1 */
    @Qualifier(CopyFinancialOrgWkTbl4BatchConfiguration.JOB_NAME)
    @Autowired
    private Job copyWkTbl4Job;

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
    public void practice(final RegistZenginOrgWkTbleCapsuleDto capsuleDto) // 改行よけ
            throws IOException, // SUPPRESS CHECKSTYLE ThrowsExceptionCount
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {

        // テーブル履歴情報読みだし
        CheckPrivilegeDto privilegeDto = capsuleDto.getCheckPrivilegeDto();
        Long loginUserId = privilegeDto.getLoginUserId();
        Integer loginUserCode = privilegeDto.getLoginUserCode();
        String loginUserName = privilegeDto.getLoginUserName();

        final String paramKeyTime = "executeTime";
        final String paramKeyUserId = "userId";
        final String paramKeyUserCode = "userCode";
        final String paramKeyUserName = "userName";
        final String paramKeyFile = "readFileName";

        JobParameters jobParametersWkTbl1 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now())
                .addString(paramKeyFile, capsuleDto.getStorageWkTbl1Dto().getFullPath())
                .addLong(paramKeyUserId, loginUserId).addString(paramKeyUserCode, String.valueOf(loginUserCode))
                .addString(paramKeyUserName, loginUserName).toJobParameters();

        jobLauncher.run(copyWkTbl1Job, jobParametersWkTbl1);

        // ワークテーブル2
        JobParameters jobParametersWkTbl2 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now())
                .addString(paramKeyFile, capsuleDto.getStorageWkTbl2Dto().getFullPath())
                .addLong(paramKeyUserId, loginUserId).addString(paramKeyUserCode, String.valueOf(loginUserCode))
                .addString(paramKeyUserName, loginUserName).toJobParameters();

        jobLauncher.run(copyWkTbl2Job, jobParametersWkTbl2);

        // ワークテーブル3
        JobParameters jobParametersWkTbl3 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now())
                .addString(paramKeyFile, capsuleDto.getStorageWkTbl3Dto().getFullPath())
                .addLong(paramKeyUserId, loginUserId).addString(paramKeyUserCode, String.valueOf(loginUserCode))
                .addString(paramKeyUserName, loginUserName).toJobParameters();

        jobLauncher.run(copyWkTbl3Job, jobParametersWkTbl3);

        // ワークテーブル4
        JobParameters jobParametersWkTbl4 = new JobParametersBuilder() // NOPMD
                .addLocalDateTime(paramKeyTime, LocalDateTime.now())
                .addString(paramKeyFile, capsuleDto.getStorageWkTbl4Dto().getFullPath())
                .addLong(paramKeyUserId, loginUserId).addString(paramKeyUserCode, String.valueOf(loginUserCode))
                .addString(paramKeyUserName, loginUserName).toJobParameters();

        jobLauncher.run(copyWkTbl4Job, jobParametersWkTbl4);

    }
}
