package mitei.mitei.investigate.report.balance.politician.controller.renketsu_koufukin;

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

import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin.CreateKoufukinRenketsuBathConfiguration;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.CreateRenketsuKoufukinCapsuleDto;

/**
 * 交付金連結処理ワークテーブル非同期起動処理
 */
@Service
public class CreateRenketsuKoufukinWkTblAyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 不記載検出ワークテーブル作成BatchConfig */
    @Qualifier(CreateKoufukinRenketsuBathConfiguration.JOB_NAME)
    @Autowired
    private Job createKoufukinRenketsu;

    /**
     * 処理を行う
     *
     * @param capsuleDto 交付金連結Dto
     * @throws JobExecutionAlreadyRunningException Batch実行中例外
     * @throws JobRestartException                 Batch再起動例外
     * @throws JobInstanceAlreadyCompleteException Batch実施完了例外
     * @throws JobParametersInvalidException       起動条件不正例外
     */
    @Async
    public void practice(final CreateRenketsuKoufukinCapsuleDto capsuleDto, final String taskName,
            final Integer taskPlanCode, final Integer year) throws JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        CheckPrivilegeDto privilegeDto = capsuleDto.getCheckPrivilegeDto();

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(privilegeDto.getLoginUserCode()))
                .addString("userName", privilegeDto.getLoginUserName())
                .addLong("documentCodeUsage", capsuleDto.getDocumentCodeUsage())
                .addLong("documentCodeBalance", capsuleDto.getDocumentCodeBalance())
                .addLong("houkokuNen", Long.valueOf(capsuleDto.getHoukokuNen()))
                .addLong("taskPlanCode", Long.valueOf(taskPlanCode)).addString("taskName", taskName)
                .addLong("year", Long.valueOf(year)).toJobParameters();

        jobLauncher.run(createKoufukinRenketsu, jobParameters);
    }

}
