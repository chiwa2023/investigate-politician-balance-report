import RenketsuKofukinDocumentCodeOptionResultInterface from "../../../../dto/renketsu_koufukin/renketsuKofukinDocumentCodeOptionResultDto";
import RenketsuKofukinDocumentCodeOptionResultDto from "../../../../dto/renketsu_koufukin/renketsuKofukinDocumentCodeOptionResultDto";
import SelectOptionInterface from "../../../../dto/selectOptionDto";
import SelectOptionDto from "../../../../dto/selectOptionDto";

export default function mockGetDocumentCode(houkokuNen: string): RenketsuKofukinDocumentCodeOptionResultInterface {

    const nen = parseInt(houkokuNen);
    const dto: RenketsuKofukinDocumentCodeOptionResultInterface = new RenketsuKofukinDocumentCodeOptionResultDto();

    // 収支報告書
    dto.listBalance.push(createOption("4111", (nen + 1) + "-03-28提出分"));
    dto.listBalance.push(createOption("4117", (nen + 1) + "-12-03提出分"));
    dto.listBalance.push(createOption("4119", (nen + 1) + "-12-07提出分"));
    dto.listBalance.push(createOption("4116", (nen + 1) + "-12-11提出分"));
    dto.listBalance.push(createOption("4122", (nen + 1) + "-12-14提出分"));
    dto.listBalance.push(createOption("4128", (nen + 1) + "-12-18提出分"));
    dto.listBalance.push(createOption("4134", (nen + 2) + "-01-09提出分"));
    dto.listBalance.push(createOption("4142", (nen + 2) + "-01-26提出分"));

    // 使途報告書
    dto.listUsage.push(createOption("2183", (nen + 1) + "-03-28提出分"));
    dto.listUsage.push(createOption("2192", (nen + 1) + "-12-09提出分"));
    dto.listUsage.push(createOption("2196", (nen + 1) + "-12-22提出分"));
    dto.listUsage.push(createOption("2204", (nen + 1) + "-12-24提出分"));
    dto.listUsage.push(createOption("2213", (nen + 2) + "-01-11提出分"));
    dto.listUsage.push(createOption("2236", (nen + 2) + "-02-04提出分"));

    return dto;
}

function createOption(value: string, text: string): SelectOptionInterface {
    const dto: SelectOptionInterface = new SelectOptionDto();
    dto.value = value;
    dto.text = text;
    return dto;
}