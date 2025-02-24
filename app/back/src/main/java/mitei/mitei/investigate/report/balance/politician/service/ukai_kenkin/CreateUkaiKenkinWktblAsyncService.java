package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

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
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage01BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage02BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage03BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage04BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage05BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage06BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage07BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage08BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin.UkaiKenkinRouteByStage09BatchConfiguration;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinConditionCapsuleDto;

/**
 * 迂回献金抽出用ワークテーブル作成Service
 */
@Component
public class CreateUkaiKenkinWktblAsyncService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 迂回献金取得階層1ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage01BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage01;

    /** 迂回献金取得階層2ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage02BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage02;

    /** 迂回献金取得階層3ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage03BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage03;

    /** 迂回献金取得階層4ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage04BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage04;

    /** 迂回献金取得階層5ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage05BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage05;

    /** 迂回献金取得階層6ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage06BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage06;

    /** 迂回献金取得階層7ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage07BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage07;

    /** 迂回献金取得階層8ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage08BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage08;

    /** 迂回献金取得階層9ワークテーブル作成BatchConfig */
    @Qualifier(UkaiKenkinRouteByStage09BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage09;

    /** 取得階層1 */
    private static final int STAGE1 = 1;

    /** 取得階層1 */
    private static final int STAGE2 = 2;

    /** 取得階層1 */
    private static final int STAGE3 = 3;

    /** 取得階層1 */
    private static final int STAGE4 = 4;

    /** 取得階層1 */
    private static final int STAGE5 = 5;

    /** 取得階層1 */
    private static final int STAGE6 = 6;

    /** 取得階層1 */
    private static final int STAGE7 = 7;

    /** 取得階層1 */
    private static final int STAGE8 = 8;

    /** 取得階層1 */
    private static final int STAGE9 = 9;

    /**
     * 処理を行う
     *
     * @param capsuleDto   迂回献金データ抽出条件Dto
     * @param taskPlanCode タスク計画同一識別コード
     * @param taskName     タスク名
     * @param year         起動年
     * @throws JobExecutionAlreadyRunningException Batch実行中例外
     * @throws JobRestartException                 Batch再起動例外
     * @throws JobInstanceAlreadyCompleteException Batch実施完了例外
     * @throws JobParametersInvalidException       起動条件不正例外
     */
    @Async
    public void practice(final UkaiKenkinConditionCapsuleDto capsuleDto, final Integer taskPlanCode,
            final String taskName, final Integer year) throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        CheckPrivilegeDto privilegeDto = capsuleDto.getCheckPrivilegeDto();

        capsuleDto.setIsNameSearch(null);

        final String keyExecuteTime = "executeTime";
        final String keyUserId = "userId";
        final String keyUserCode = "userCode";
        final String keyUserName = "userName";
        final String keyHoukokuNen = "houkokuNen";
        final String keyIsSearchKoufukin = "isSearchKoufukin";
        final String keyPoliOrgCode = "poliOrgCode";
        final String keyIsNameSearch = "isNameSearch";
        final String keyTaskPlanCode = "taskPlanCode";
        final String keyTaskName = "taskName";
        final String keyYear = "year";

        switch (capsuleDto.getPickupTimes()) {
            // 取得階層1
            case STAGE1:
                JobParameters jobParameters1 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage01.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage01, jobParameters1);
                break;

            // 取得階層2
            case STAGE2:
                JobParameters jobParameters2 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage02.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage02, jobParameters2);
                break;

            // 取得階層3
            case STAGE3:
                JobParameters jobParameters3 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage03.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage03, jobParameters3);
                break;

            // 取得階層4
            case STAGE4:
                JobParameters jobParameters4 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage04.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage04, jobParameters4);
                break;

            // 取得階層5
            case STAGE5:
                JobParameters jobParameters5 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage05.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();
                jobLauncher.run(ukaiKenkinRouteByStage05, jobParameters5);
                break;

            // 取得階層6
            case STAGE6:
                JobParameters jobParameters6 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage06.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage06, jobParameters6);
                break;

            // 取得階層7
            case STAGE7:
                JobParameters jobParameters7 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage07.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage07, jobParameters7);
                break;

            // 取得階層8
            case STAGE8:
                JobParameters jobParameters8 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage01.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage08, jobParameters8);
                break;

            // 取得階層9
            case STAGE9:
                JobParameters jobParameters9 = new JobParametersBuilder(
                        ukaiKenkinRouteByStage09.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                        .addLocalDateTime(keyExecuteTime, LocalDateTime.now())
                        .addLong(keyUserId, privilegeDto.getLoginUserId())
                        .addLong(keyUserCode, Long.valueOf(privilegeDto.getLoginUserCode()))
                        .addString(keyUserName, privilegeDto.getLoginUserName())
                        .addLong(keyHoukokuNen, Long.valueOf(capsuleDto.getHoukokuNen()))
                        .addString(keyIsSearchKoufukin, String.valueOf(capsuleDto.getIsKoufukin()))
                        .addString(keyIsNameSearch, String.valueOf(capsuleDto.getIsNameSearch()))
                        .addLong(keyPoliOrgCode, Long.valueOf(capsuleDto.getPoliOrgCode()))
                        .addLong(keyTaskPlanCode, Long.valueOf(taskPlanCode)).addString(keyTaskName, taskName)
                        .addLong(keyYear, Long.valueOf(year)).toJobParameters();

                jobLauncher.run(ukaiKenkinRouteByStage09, jobParameters9);
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + capsuleDto.getPickupTimes());
        }

    }
}
