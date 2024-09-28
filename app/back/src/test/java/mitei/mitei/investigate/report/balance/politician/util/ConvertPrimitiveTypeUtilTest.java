package mitei.mitei.investigate.report.balance.politician.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * ConvertPrimitiveTypeUtil単体テスト
 */
class ConvertPrimitiveTypeUtilTest extends ConvertPrimitiveTypeUtil {
    //CHECKSTYLE:OFF MagicNumber
    

    @Test
    void testPracticeBooleanToInteger() {
        
        ConvertPrimitiveTypeUtil convertPrimitiveTypeUtil = new ConvertPrimitiveTypeUtil();
        
        assertThat(convertPrimitiveTypeUtil.practiceBooleanToInteger(false)).isEqualTo(0);
        assertThat(convertPrimitiveTypeUtil.practiceBooleanToInteger(true)).isEqualTo(1);
        
    }

    @Test
    void testPracticeIntegerToBoolean() {

        ConvertPrimitiveTypeUtil convertPrimitiveTypeUtil = new ConvertPrimitiveTypeUtil();

        assertThat(convertPrimitiveTypeUtil.practiceIntegerToBoolean(0)).isEqualTo(false);
        assertThat(convertPrimitiveTypeUtil.practiceIntegerToBoolean(1)).isEqualTo(true);
        
        assertThrows(IllegalArgumentException.class, () -> convertPrimitiveTypeUtil.practiceIntegerToBoolean(1212));
    }

}
