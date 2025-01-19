export default function mockGetLimitAmount(dantaiKbn: string,amount: number): number {

    switch (dantaiKbn) {
        // 企業(仮で10億円で区切ってみる)
        case "1":
            if (amount < 1000000000) {
                return 7500000;
            } else {
                return 100000000;
            }

        // 組合(仮で3万人で区切ってみる)
        case "2":
            if (amount < 30000) {
                return 7500000;
            } else {
                return 100000000;
            }

        // その他団体(仮で500万円で区切ってみる)
        case "3":
            if (amount < 5000000) {
                return 7500000;
            } else {
                return 100000000;
            }

        default:
            return -1;
    }


}