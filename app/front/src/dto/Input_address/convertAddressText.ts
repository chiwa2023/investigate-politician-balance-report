import InputAddressDto from "./inputAddressDto";

export default function convertAddressText(dto: InputAddressDto): string {

    let text: string = "〒" + dto.postalcode1 + "-" + dto.postalcode1 + "\n" + dto.addressPostal + dto.addressBlock + "　" + dto.addressBuilding;

    return text;
}