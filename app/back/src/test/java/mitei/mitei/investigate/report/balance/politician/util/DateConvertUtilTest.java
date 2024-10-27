package mitei.mitei.investigate.report.balance.politician.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * DateConvertUtil単体テスト
 */
class DateConvertUtilTest {
    // CHECKSTYLE:OFF MagicNumber
    
    @Test
    @Tag("TableTruncate")
    void testPracticeWarekiToLocalDate()throws Exception {
        
        DateConvertUtil dateConvertUtil = new DateConvertUtil();
        //正常処理
        assertThat(dateConvertUtil.practiceWarekiToLocalDate("R4/12/1")).isEqualTo(LocalDate.of(2022, 12, 1));

        //空文字=初期処理の場合は初期値を返す
        assertThat(dateConvertUtil.practiceWarekiToLocalDate("")).isEqualTo(LocalDate.of(1948, 7, 29));
        
        //文字列が和暦短縮形でない
        assertThrows(DateTimeParseException.class, () -> dateConvertUtil.practiceWarekiToLocalDate("2022.12.01"));
        
    }

    @Test
    void testPracticeLocalDateToWareki()throws Exception {
        
        DateConvertUtil dateConvertUtil = new DateConvertUtil();
        assertThat(dateConvertUtil.practiceLocalDateToWareki(LocalDate.of(2022, 12, 1))).isEqualTo("R4/12/1");
    }

}
