package mitei.mitei.create.report.balance.politician.dto.publish;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 様式7の1収支報告書組織詳細データDto
 */
@JacksonXmlRootElement(localName = "SYUUSHI07_01")
public class AllSheet0701CoverAndOrganizationDetailsDto implements Serializable {

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 様式7の1 */
    @JacksonXmlProperty(localName = "SHEET")
    private Sheet070100CoverAndOrganizationDetailsDto sheet070100CoverAndOrganizationDetailsDto;

    /**
     * 様式7の1のシートを取得する
     *
     * @return 様式7の1のシート
     */
    public Sheet070100CoverAndOrganizationDetailsDto getSheet070100CoverAndOrganizationDetailsDto() {
        return sheet070100CoverAndOrganizationDetailsDto;
    }

    /**
     * 様式7の1のシートを設定する
     *
     * @param sheet070100CoverAndOrganizationDetailsDto 様式7の1のシート
     */
    public void setSheet070100CoverAndOrganizationDetailsDto(
            final Sheet070100CoverAndOrganizationDetailsDto sheet070100CoverAndOrganizationDetailsDto) {
        this.sheet070100CoverAndOrganizationDetailsDto = sheet070100CoverAndOrganizationDetailsDto;
    }

}
