package mitei.mitei.investigate.report.balance.politician.dto.common_check;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * コードだけを伝達するCapsuleDto
 */
public class TemplateWithInTaskPlanCodeCapsuleDto extends AbstractCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 取得コード */
    private Integer taskPlanCode = INIT_Integer;

    /** 登録年 */
    private Integer year = INIT_Integer;

    /**
     * 取得コードを取得する
     *
     * @return 取得コード
     */
    public Integer getTaskPlanCode() {
        return taskPlanCode;
    }

    /**
     * 取得コードを設定する
     *
     * @param taskPlanCode 取得コード
     */
    public void setTaskPlanCode(final Integer taskPlanCode) {
        this.taskPlanCode = taskPlanCode;
    }

    /**
     * 登録年を取得する
     *
     * @return 登録年
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 登録年を設定する
     *
     * @param year 登録年
     */
    public void setYear(final Integer year) {
        this.year = year;
    }

}
