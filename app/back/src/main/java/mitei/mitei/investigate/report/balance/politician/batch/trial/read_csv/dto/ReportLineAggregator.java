package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.stereotype.Component;

/**
 * DtoをCsvの行に変換
 */
@Component
public class ReportLineAggregator implements LineAggregator<ReportDto> {

    /**
     * ReportDtoをcsv行に変換
     */
    @Override
    public String aggregate(final ReportDto item) {

        final String quate = "\"";
        final String comma = ",";

        StringBuffer buffer = new StringBuffer();
        buffer.append(quate).append(item.getFirstName()).append(quate).append(comma); // NOPMD
        buffer.append(quate + item.getLastName() + quate + comma); // NOPMD
        buffer.append(quate + item.getGradeName() + quate); // NOPMD

        return buffer.toString();
    }

}
