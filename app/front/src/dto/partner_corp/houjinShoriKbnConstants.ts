export default class HoujinShoriKbnConstants {

    /** 01 新規 */
    static readonly SHINKI: string = "01";
    /** 01 新規(表示) */
    static readonly SHINKI_TEXT: string = "新規";

    /** 11 商号又は名称の変更 */
    static readonly MEISHOU: string = "11";
    /** 11 商号又は名称の変更 (表示)*/
    static readonly MEISHOU_TEXT: string = "商号又は名称の変更";

    /** 12 国内所在地の変更 */
    static readonly SHOZAICHI_KOKUNAI: string = "12";
    /** 12 国内所在地の変更(表示) */
    static readonly SHOZAICHI_KOKUNAI_TEXT: string = "国内所在地の変更";

    /** 13 国外所在地の変更 */
    static readonly SHOZAICHI_KOKUGAI: string = "13";
    /** 13 国外所在地の変更(表示) */
    static readonly SHOZAICHI_KOKUGAI_TEXT: string = "国外所在地の変更";

    /** 21 登記記録の閉鎖等 */
    static readonly TOUKI_HEISA: string = "21";
    /** 21 登記記録の閉鎖等(表示) */
    static readonly TOUKI_HEISA_TEXT: string = "登記記録の閉鎖等";

    /** 22 登記記録の復活等 */
    static readonly TOUKI_FUKKATSU: string = "22";
    /** 22 登記記録の復活等(表示) */
    static readonly TOUKI_FUKKATSU_TEXT: string = "登記記録の復活等";

    /** 71 吸収合併 */
    static readonly GAPPEI: string = "71";
    /** 71 吸収合併(表示) */
    static readonly GAPPEI_TEXT: string = "吸収合併";

    /** 72 吸収合併無効 */
    static readonly GAPPEI_MUKOU: string = "72";
    /** 72 吸収合併無効(表示) */
    static readonly GAPPEI_MUKOU_TEXT: string = "吸収合併無効";

    /** 81 商号の登記の抹消 */
    static readonly SHOUGOU_MASSHOu: string = "81";
    /** 81 商号の登記の抹消(表示) */
    static readonly SHOUGOU_MASSHOu_TEXT: string = "商号の登記の抹消";

    /** 99 削除 */
    static readonly SAKUJO: string = "99";
    /** 99 削除(表示) */
    static readonly SAKUJO_TEXT: string = "削除";


    static getLabel(value: string): string {

        switch (value) {

            // 新規 */
            case this.SHINKI:
                return this.SHINKI_TEXT;

            // 11 商号又は名称の変更 */
            case this.MEISHOU:
                return this.MEISHOU_TEXT;

            // 12 国内所在地の変更 */
            case this.SHOZAICHI_KOKUNAI:
                return this.SHOZAICHI_KOKUNAI_TEXT;

            // 13 国外所在地の変更 
            case this.SHOZAICHI_KOKUGAI:
                return this.SHOZAICHI_KOKUGAI_TEXT;

            // 21 登記記録の閉鎖等
            case this.TOUKI_HEISA:
                return this.TOUKI_HEISA_TEXT;

            // 22 登記記録の復活等
            case this.TOUKI_FUKKATSU:
                return this.TOUKI_FUKKATSU_TEXT;

            // 71 吸収合併 
            case this.GAPPEI:
                return this.GAPPEI_TEXT;

            // 72 吸収合併無効
            case this.GAPPEI_MUKOU:
                return this.GAPPEI_MUKOU_TEXT;

            // 81 商号の登記の抹消
            case this.SHOUGOU_MASSHOu:
                return this.SHOUGOU_MASSHOu_TEXT;

            // 99 削除
            case this.SAKUJO:
                return this.SAKUJO_TEXT;

            default:
                return "";
        }
    }

}