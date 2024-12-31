package mitei.mitei.investigate.report.balance.politician.controller.poli_org.fukisai;

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

import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai.CreateWkTblFukisaiBatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchBalancesheetFukisaiCapsuleDto;

/**
 * 不記載検出ワークテーブル作成非同期Service
 */
@Service
public class CreateFukisaiWkTblAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 不記載検出ワークテーブル作成BatchConfig */
    @Qualifier(CreateWkTblFukisaiBatchConfiguration.JOB_NAME)
    @Autowired
    private Job createWkTblFukisai;

    /**
     * 処理を行う
     *
     * @param capsuleDto   不記載検出条件Dto
     * @param taskName     タスク名
     * @param taskPlanCode 登録済タスクコード
     * @param year         実施年
     * @throws JobExecutionAlreadyRunningException Batch実行中例外
     * @throws JobRestartException                 Batch再起動例外
     * @throws JobInstanceAlreadyCompleteException Batch実施完了例外
     * @throws JobParametersInvalidException       起動条件不正例外
     */
    @Async
    public void practice(final SearchBalancesheetFukisaiCapsuleDto capsuleDto, final String taskName,
            final Integer taskPlanCode, final Integer year) throws JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        CheckPrivilegeDto privilegeDto = capsuleDto.getCheckPrivilegeDto();
        FukisaiSearchConditionDto conditionDto = capsuleDto.getFukisaiSearchConditionDto();

        JobParameters jobParameters = new JobParametersBuilder(
                createWkTblFukisai.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(privilegeDto.getLoginUserCode()))
                .addString("userName", privilegeDto.getLoginUserName())
                .addLong("taskPlanCode", Long.valueOf(taskPlanCode)).addString("taskName", taskName)
                .addLong("year", Long.valueOf(year)).addLong("poliOrgCode", Long.valueOf(conditionDto.getPoliOrgCode()))
                .addLong("houkokunen", Long.valueOf(conditionDto.getHoukokuNen()))
                .addString("isSearchCode", String.valueOf(conditionDto.getIsSearchCode())).toJobParameters();

        jobLauncher.run(createWkTblFukisai, jobParameters);
    }
}
