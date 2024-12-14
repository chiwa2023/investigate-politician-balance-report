export default function getUnixTimeString() {

    const date = new Date();

    return convertDigit2(date.getFullYear())
        + convertDigit2(date.getMonth()+1)
        + convertDigit2(date.getDate())
        + convertDigit2(date.getHours())
        + convertDigit2(date.getMinutes())
        + convertDigit2(date.getSeconds());
}

function convertDigit2(data: number): string {

    if (data < 10) {
        return "0" + data;
    } else {
        return String(data);
    }

}