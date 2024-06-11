package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.PersonDto;
import mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto.ReportDto;

/**
 * PersonDtoとReportDtoを変換
 */
@Component
public class ReadCsvItemProcessor implements ItemProcessor<PersonDto, ReportDto> {

    /**
     * PersonDtoからReportDtoへの変換作業を行う
     */
    @Override
    public ReportDto process(final PersonDto item) throws Exception {

        ReportDto reportDto = new ReportDto();

        reportDto.setFirstName(item.getFirstName());
        reportDto.setLastName(item.getLastName());

        // 簡単なグレード分け
        if (item.getExamResult() > 50) { // CHECKSTYLE:OFF NOPMD
            reportDto.setGradeName("グレードA");

        } else {
            if (item.getYearOfJoining() < 3) { // CHECKSTYLE:OFF NOPMD
                reportDto.setGradeName("グレードB");
            } else {
                reportDto.setGradeName("グレードC");
            }
        }

        return reportDto;
    }

}
