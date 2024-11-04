package mitei.mitei.investigate.report.balance.politician.dto.task_plan;

import java.io.Serializable;

import jakarta.persistence.Column;
import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;

/**
 * 未処理タスク計画Dto
 */
public class TaskPlanSelectOptionDto extends SelectOptionDto implements Serializable{ // NOPMD DataClass


    /** SerialId */
    private static final long serialVersionUID = 1L;


    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;
    
    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;
    
    /** 計画予定Id */
    private Long taskPlanId = INIT_Long;

    /**
     * タスク予定Idを取得する
     *
     * @return タスク予定Id
     */
    public Long getTaskPlanId() {
        return taskPlanId;
    }

    /**
     * タスク予定Idを設定する
     *
     * @param taskPlanId タスク予定Id
     */
    public void setTaskPlanId(final Long taskPlanId) {
        this.taskPlanId = taskPlanId;
    }

    /** タスク予定同一識別コード */
    @Column(name = "task_plan_code")
    private Integer taskPlanCode = INIT_Integer;

    /**
     * タスク予定同一識別コードを取得する
     *
     * @return タスク予定同一識別コード
     */
    public Integer getTaskPlanCode() {
        return taskPlanCode;
    }

    /**
     * タスク予定同一識別コードを設定する
     *
     * @param taskPlanCode タスク予定同一識別コード
     */
    public void setTaskPlanCode(final Integer taskPlanCode) {
        this.taskPlanCode = taskPlanCode;
    }

    /** タスク予定名称 */
    @Column(name = "task_plan_name")
    private String taskPlanName = INIT_String;

    /**
     * タスク予定名称を取得する
     *
     * @return タスク予定名称
     */
    public String getTaskPlanName() {
        return taskPlanName;
    }

    /**
     * タスク予定名称を設定する
     *
     * @param taskPlanName タスク予定名称
     */
    public void setTaskPlanName(final String taskPlanName) {
        this.taskPlanName = taskPlanName;
    }


    /** 政治団体Id */
    @Column(name = "political_organization_id")
    private Long politicalOrganizationId = INIT_Long;

    /**
     * 政治団体Idを取得する
     *
     * @return 政治団体Id
     */
    public Long getPoliticalOrganizationId() {
        return politicalOrganizationId;
    }

    /**
     * 政治団体Idを設定する
     *
     * @param politicalOrganizationId 政治団体Id
     */
    public void setPoliticalOrganizationId(final Long politicalOrganizationId) {
        this.politicalOrganizationId = politicalOrganizationId;
    }

    /** 政治団体同一識別コード */
    @Column(name = "political_organization_code")
    private Integer politicalOrganizationCode = INIT_Integer;

    /**
     * 政治団体同一識別コードを取得する
     *
     * @return 政治団体同一識別コード
     */
    public Integer getPoliticalOrganizationCode() {
        return politicalOrganizationCode;
    }

    /**
     * 政治団体同一識別コードを設定する
     *
     * @param politicalOrganizationCode 政治団体同一識別コード
     */
    public void setPoliticalOrganizationCode(final Integer politicalOrganizationCode) {
        this.politicalOrganizationCode = politicalOrganizationCode;
    }

    /** 政治団体名称 */
    @Column(name = "political_organization_name")
    private String politicalOrganizationName = INIT_String;

    /**
     * 政治団体名称を取得する
     *
     * @return 政治団体名称
     */
    public String getPoliticalOrganizationName() {
        return politicalOrganizationName;
    }

    /**
     * 政治団体名称を設定する
     *
     * @param politicalOrganizationName 政治団体名称
     */
    public void setPoliticalOrganizationName(final String politicalOrganizationName) {
        this.politicalOrganizationName = politicalOrganizationName;
    }

    /** 遷移パス */
    @Column(name = "transfer_pass")
    private String transferPass = INIT_String;

    /**
     * 遷移パスを取得する
     *
     * @return 遷移パス
     */
    public String getTransferPass() {
        return transferPass;
    }

    /**
     * 遷移パスを設定する
     *
     * @param transferPass 遷移パス
     */
    public void setTransferPass(final String transferPass) {
        this.transferPass = transferPass;
    }


    
}
