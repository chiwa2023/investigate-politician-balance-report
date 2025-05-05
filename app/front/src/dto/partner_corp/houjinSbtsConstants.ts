export default class HoujinSbtsConstants {

    /** 101 国の機関 */
    static readonly KUNI_KIKAN: string = "101";
    /** 101 国の機関 */
    static readonly KUNI_KIKAN_TEXT: string = "国の機関";

    /** 201 地方公共団体 */
    static readonly CHIHOU_KOUKYOU_DANTAI: string = "201";
    /** 201 地方公共団体 */
    static readonly CHIHOU_KOUKYOU_DANTAI_TEXT: string = "地方公共団体";

    /** 301 株式会社 */
    static readonly KABUSHIKI: string = "301";
    /** 301 株式会社 */
    static readonly KABUSHIKI_TEXT: string = "株式会社";

    /** 302 有限会社 */
    static readonly YUUGEN: string = "302";
    /** 302 有限会社 */
    static readonly YUUGEN_TEXT: string = "有限会社";

    /** 303 合名会社 */
    static readonly GOUMEI: string = "303";
    /** 303 合名会社 */
    static readonly GOUMEI_TEXT: string = "合名会社";

    /** 304 合資会社 */
    static readonly GOUSHI: string = "304";
    /** 304 合資会社 */
    static readonly GOUSHI_TEXT: string = "合資会社";

    /** 305 合同会社 */
    static readonly GOUDOU: string = "305";
    /** 305 合同会社 */
    static readonly GOUDOU_TEXT: string = "合同会社";

    /** 399 その他の設立登記法人 */
    static readonly SONOTA_TOUKI: string = "399";
    /** 399 その他の設立登記法人 */
    static readonly SONOTA_TOUKI_TEXT: string = "その他の設立登記法人";

    /** 401 外国会社等 */
    static readonly GAIKOKU: string = "401";
    /** 401 外国会社等 */
    static readonly GAIKOKU_TEXT: string = "外国会社等";

    /** 499 その他     */
    static readonly TOUKI_NASHI: string = "499";
    /** 499 その他     */
    static readonly TOUKI_NASHI_TEXT: string = "その他";

    static getLabel(value: string): string {

        switch (value) {

            // 101 国の機関 
            case this.KUNI_KIKAN:
                return this.KUNI_KIKAN_TEXT;

            // 201 地方公共団体
            case this.CHIHOU_KOUKYOU_DANTAI:
                return this.CHIHOU_KOUKYOU_DANTAI_TEXT;

            // 301 株式会社 
            case this.KABUSHIKI:
                return this.KABUSHIKI_TEXT;

            // 302 有限会社 
            case this.YUUGEN:
                return this.YUUGEN_TEXT;

            // 303 合名会社 
            case this.GOUMEI:
                return this.GOUMEI_TEXT;

            // 304 合資会社 
            case this.GOUSHI:
                return this.GOUSHI_TEXT;

            // 305 合同会社 
            case this.GOUDOU:
                return this.GOUDOU_TEXT;

            // 399 その他の設立登記法人 
            case this.SONOTA_TOUKI:
                return this.SONOTA_TOUKI_TEXT;

            // 401 外国会社等
            case this.GAIKOKU:
                return this.GAIKOKU_TEXT;

            // 499 その他    
            case this.TOUKI_NASHI:
                return this.TOUKI_NASHI_TEXT;

            default:
                return "";
        }
    }

}