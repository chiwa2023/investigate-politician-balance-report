package mitei.mitei.investigate.report.balance.politician.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 * 日付変換Utility
 */
@Component
public class DateConvertUtil {

    /** 和暦短縮形フォーマット文字列 */
    private static final String TIME_FORMAT_PATTERN_WAREKI = "GGGGGy/M/d";

    /**
     * 和暦文字列からLocalDateに変換する
     *
     * @param data 日付文字列
     * @return 変換後の日付
     * @throws ParseException 和暦短縮形でない文字列が引数の場合
     */
    public LocalDate practiceWarekiToLocalDate(final String data) {

        if ("".equals(data)) { // NOPMD
            return LocalDate.of(1948, 7, 29); // SUPPRESS CHECKSTYLE MagicNumber
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN_WAREKI, Locale.JAPAN)
                .withChronology(JapaneseChronology.INSTANCE).withResolverStyle(ResolverStyle.LENIENT);

        JapaneseDate wareki = JapaneseDate.from(formatter.parse(data));

        return LocalDate.from(wareki);
    }

    /**
     * 日付から和暦短縮形文字列に変換する
     *
     * @param data 日付
     * @return 和暦短縮形文字列
     */
    public String practiceLocalDateToWareki(final LocalDate data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN_WAREKI);

        JapaneseDate japaneseDate = JapaneseDate.from(data);

        return japaneseDate.format(formatter);
    }

}
