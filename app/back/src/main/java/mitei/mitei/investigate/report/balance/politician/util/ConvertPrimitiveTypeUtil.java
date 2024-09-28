package mitei.mitei.investigate.report.balance.politician.util;

import org.springframework.stereotype.Component;

/**
 * 型変換を行う
 */
@Component
public class ConvertPrimitiveTypeUtil {

    /** フラグがONであることを示す値(数値) */
    private static final int FLG_ON_INT = 1;

    /** フラグがOFFであることを示す値(数値) */
    private static final int FLG_OFF_INT = 0;

    /**
     * boolean値を数値(0/1)に変換する
     *
     * @param isData 変換したいboolean値
     * @return 数値(1/0)
     */
    public int practiceBooleanToInteger(final boolean isData) {

        if (isData) {
            return FLG_ON_INT;
        } else {
            return FLG_OFF_INT;
        }

    }

    /**
     * フラグ数値(0/1)をbooleanに変換する
     *
     * @param data フラグ数値(0/1)
     * @return 変換後のboolean値
     * @throws IllegalArgumentException 変換しようとする値が(0/1)フラグ値で場合
     */
    public boolean practiceIntegerToBoolean(final int data) throws IllegalArgumentException { // NOPMD

        if (data == FLG_OFF_INT) {
            return false;
        }

        if (data == FLG_ON_INT) {
            return true;
        }

        throw new IllegalArgumentException("0/1以外の値が入力されました");
    }

}
