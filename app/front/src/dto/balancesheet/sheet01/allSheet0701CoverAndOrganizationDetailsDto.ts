import  Sheet070100CoverAndOrganizationDetailsDto  from "./sheet070100CoverAndOrganizationDetailsDto";

export default interface AllSheet0701CoverAndOrganizationDetailsInterface {

    /** 様式7の1 */
    sheet070100CoverAndOrganizationDetailsDto: Sheet070100CoverAndOrganizationDetailsDto;

}


export default class AllSheet0701CoverAndOrganizationDetailsDto implements AllSheet0701CoverAndOrganizationDetailsInterface {

    /** 様式7の1 */
    sheet070100CoverAndOrganizationDetailsDto: Sheet070100CoverAndOrganizationDetailsDto;

    /**
     * Creates an instance of AllSheet0701CoverAndOrganizationDetailsDto.
     * @param {AllSheet0701CoverAndOrganizationDetailsInterface} [allSheet0701CoverAndOrganizationDetailsInterface]
     * @memberof AllSheet0701CoverAndOrganizationDetailsDto
     */
    constructor(allSheet0701CoverAndOrganizationDetailsInterface?: AllSheet0701CoverAndOrganizationDetailsInterface) {
        if (allSheet0701CoverAndOrganizationDetailsInterface?.sheet070100CoverAndOrganizationDetailsDto !== undefined) {
            this.sheet070100CoverAndOrganizationDetailsDto = allSheet0701CoverAndOrganizationDetailsInterface.sheet070100CoverAndOrganizationDetailsDto;
        }
        else {
            this.sheet070100CoverAndOrganizationDetailsDto = new Sheet070100CoverAndOrganizationDetailsDto();
        }
    }
}