package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto;

import java.io.Serializable;

/**
 * 判定グレードを表すDto
 */
public class ReportDto implements Serializable{ // NOPMD

    /** SerialId */
    private static final long serialVersionUID = 1L;
    
    /** 姓 */
    private String firstName;
    /** 名 */
    private String lastName;
    /** 技能階級 */
    private String gradeName;

    /**
     * 姓を取得する
     *
     * @return 姓
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 姓を設定する
     *
     * @param firstName 姓
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * 名を取得する
     *
     * @return 名
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 名を設定する
     *
     * @param lastName 名
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * 技能階級を取得する
     *
     * @return 技能階級
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * 技能階級を設定する
     *
     * @param gradeName 技能階級
     */
    public void setGradeName(final String gradeName) {
        this.gradeName = gradeName;
    }

    
}
