package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto;

import java.io.Serializable;

/**
 * 勤続年数、試験回数など個人の経験を示すDto
 */
public class PersonDto implements Serializable { // NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 姓 */
    private String firstName;
    /** 名 */
    private String lastName;
    /** 試験結果 */
    private int examResult;
    /** 勤続年数 */
    private int yearOfJoining;
    /** 試験回数 */
    private int trainingAmount;

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
     * 試験結果を取得する
     *
     * @return 試験結果
     */
    public int getExamResult() {
        return examResult;
    }

    /**
     * 試験結果を設定する
     *
     * @param examResult 試験結果
     */
    public void setExamResult(final int examResult) {
        this.examResult = examResult;
    }

    /**
     * 勤続年数を取得する
     *
     * @return 勤続年数
     */
    public int getYearOfJoining() {
        return yearOfJoining;
    }

    /**
     * 勤続年数を設定する
     *
     * @param yearOfJoining 勤続年数
     */
    public void setYearOfJoining(final int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    /**
     * 試験回数を取得する
     *
     * @return 試験回数
     */
    public int getTrainingAmount() {
        return trainingAmount;
    }

    /**
     * 試験回数を設定する
     *
     * @param trainingAmount 試験回数
     */
    public void setTrainingAmount(final int trainingAmount) {
        this.trainingAmount = trainingAmount;
    }

}
