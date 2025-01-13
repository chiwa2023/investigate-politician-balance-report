package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 寄付上限額確認結果Dto
 */
public class KifuSouryouSeigenAllResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 個人が政党寄付(通称A枠)上限額Dtoリスト */
    private List<SouryouKiseiRelationCodeDto> listDtoKojinParty = new ArrayList<>();

    /** 個人がその他団体寄付(通称B枠)上限額Dtoリスト */
    private List<SouryouKiseiRelationCodeDto> listDtoKojinOtherOrg = new ArrayList<>();

    /** 企業・団体が政党寄付(通称A枠)上限額Dtoリスト */
    private List<SouryouKiseiRelationCodeDto> listDtoKigyouParty = new ArrayList<>();

    /** 企業・団体がその他団体寄付(通称B枠)上限額Dtoリスト */
    private List<SouryouKiseiRelationCodeDto> listDtoKigyouOtherOrg = new ArrayList<>();

    /**
     * 個人が政党寄付(通称A枠)上限額Dtoリストを取得する
     *
     * @return 個人が政党寄付(通称A枠)上限額Dtoリスト
     */
    public List<SouryouKiseiRelationCodeDto> getListDtoKojinParty() {
        return listDtoKojinParty;
    }

    /**
     * 個人が政党寄付(通称A枠)上限額Dtoリストを設定する
     *
     * @param listDtoKojinParty 個人が政党寄付(通称A枠)上限額Dtoリスト
     */
    public void setListDtoKojinParty(final List<SouryouKiseiRelationCodeDto> listDtoKojinParty) {
        this.listDtoKojinParty = listDtoKojinParty;
    }

    /**
     * 個人がその他団体寄付(通称B枠)上限額Dtoリストを取得する
     *
     * @return 個人がその他団体寄付(通称B枠)上限額Dtoリスト
     */
    public List<SouryouKiseiRelationCodeDto> getListDtoKojinOtherOrg() {
        return listDtoKojinOtherOrg;
    }

    /**
     * 個人がその他団体寄付(通称B枠)上限額Dtoリストを設定する
     *
     * @param listDtoKojinOtherOrg 個人がその他団体寄付(通称B枠)上限額Dtoリスト
     */
    public void setListDtoKojinOtherOrg(final List<SouryouKiseiRelationCodeDto> listDtoKojinOtherOrg) {
        this.listDtoKojinOtherOrg = listDtoKojinOtherOrg;
    }

    /**
     * 企業・団体が政党寄付(通称A枠)上限額Dtoリストを取得する
     *
     * @return 企業・団体が政党寄付(通称A枠)上限額Dtoリスト
     */
    public List<SouryouKiseiRelationCodeDto> getListDtoKigyouParty() {
        return listDtoKigyouParty;
    }

    /**
     * 企業・団体が政党寄付(通称A枠)上限額Dtoリストを設定する
     *
     * @param listDtoKigyouParty 企業・団体が政党寄付(通称A枠)上限額Dtoリスト
     */
    public void setListDtoKigyouParty(final List<SouryouKiseiRelationCodeDto> listDtoKigyouParty) {
        this.listDtoKigyouParty = listDtoKigyouParty;
    }

    /**
     * 企業・団体がその他団体寄付(通称B枠)上限額Dtoリストを取得する
     *
     * @return 企業・団体がその他団体寄付(通称B枠)上限額Dtoリスト
     */
    public List<SouryouKiseiRelationCodeDto> getListDtoKigyouOtherOrg() {
        return listDtoKigyouOtherOrg;
    }

    /**
     * 企業・団体がその他団体寄付(通称B枠)上限額Dtoリストを設定する
     *
     * @param listDtoKigyouOtherOrg 企業・団体がその他団体寄付(通称B枠)上限額Dtoリスト
     */
    public void setListDtoKigyouOtherOrg(final List<SouryouKiseiRelationCodeDto> listDtoKigyouOtherOrg) {
        this.listDtoKigyouOtherOrg = listDtoKigyouOtherOrg;
    }

    /** 政治団体区分指定のない上限額Dtoリスト */
    private List<SouryouKiseiRelationCodeDto> listDtoNoset = new ArrayList<>();

    /**
     * 政治団体区分指定のない上限額Dtoリストを取得する
     *
     * @return 政治団体区分指定のない上限額Dtoリスト
     */
    public List<SouryouKiseiRelationCodeDto> getListDtoNoset() {
        return listDtoNoset;
    }

    /**
     * 政治団体区分指定のない上限額Dtoリストを設定する
     *
     * @param listDtoNoset 政治団体区分指定のない上限額Dtoリスト
     */
    public void setListDtoNoset(final List<SouryouKiseiRelationCodeDto> listDtoNoset) {
        this.listDtoNoset = listDtoNoset;
    }

}
