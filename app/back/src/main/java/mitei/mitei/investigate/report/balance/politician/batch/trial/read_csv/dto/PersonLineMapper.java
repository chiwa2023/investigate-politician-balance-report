package mitei.mitei.investigate.report.balance.politician.batch.trial.read_csv.dto;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * csvの行をDtoに変換
 */
@Component
public class PersonLineMapper implements LineMapper<PersonDto> {

    /**
     * CSVの行をPersonDtoに変換する
     */
    @Override
    public PersonDto mapLine(final String line, final int lineNumber) throws Exception {
        
        PersonDto dto = new PersonDto();

        String[] cell = line.split(",");

        dto.setFirstName(this.removeQuote(cell[0]));
        dto.setLastName(this.removeQuote(cell[1]));
        dto.setExamResult(Integer.parseInt(this.removeQuote(cell[2])));
        dto.setYearOfJoining(Integer.parseInt(this.removeQuote(this.removeQuote(cell[3])))); // CHECKSTYLE:OFF
        dto.setTrainingAmount(Integer.parseInt(this.removeQuote(this.removeQuote(cell[4])))); // CHECKSTYLE:OFF

        return dto;
    }

    private String removeQuote(final String data) {
        int posStart = 0;
        int posEnd = data.length();

        if (data.startsWith("\"")) {
            posStart++;
        }

        if (data.endsWith("\"")) {
            posEnd--;
        }

        return data.substring(posStart, posEnd);
    }

}
