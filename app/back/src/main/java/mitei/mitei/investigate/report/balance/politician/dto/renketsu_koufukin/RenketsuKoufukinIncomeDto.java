package mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin;

import java.io.Serializable;
import java.util.List;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageIncomeEntity;

/**
 * 交付金連結収入格納Dto
 */
public class RenketsuKoufukinIncomeDto implements Serializable { //NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 紐づけ成功リスト */
    private List<WkTblRenketsuKoufukinEntity> listSuccess;

    /** 紐づけ重複リスト */
    private List<WkTblRenketsuKoufukinEntity> listDuplicate;

    /** 紐づけ重複リスト */
    private List<WkTblUsageIncomeEntity> listFailure;

    /**
     * 紐づけ成功リストを取得する
     *
     * @return 紐づけ成功リスト
     */
    public List<WkTblRenketsuKoufukinEntity> getListSuccess() {
        return listSuccess;
    }

    /**
     * 紐づけ成功リストを設定する
     *
     * @param listSuccess 紐づけ成功リスト
     */
    public void setListSuccess(final List<WkTblRenketsuKoufukinEntity> listSuccess) {
        this.listSuccess = listSuccess;
    }

    /**
     * 紐づけ重複リストを取得する
     *
     * @return 紐づけ重複リスト
     */
    public List<WkTblRenketsuKoufukinEntity> getListDuplicate() {
        return listDuplicate;
    }

    /**
     * 紐づけ重複リストを設定する
     *
     * @param listDuplicate 紐づけ重複リスト
     */
    public void setListDuplicate(final List<WkTblRenketsuKoufukinEntity> listDuplicate) {
        this.listDuplicate = listDuplicate;
    }

    /**
     * 紐づけ重複リストを取得する
     *
     * @return 紐づけ重複リスト
     */
    public List<WkTblUsageIncomeEntity> getListFailure() {
        return listFailure;
    }

    /**
     * 紐づけ重複リストを設定する
     *
     * @param listFailure 紐づけ重複リスト
     */
    public void setListFailure(final List<WkTblUsageIncomeEntity> listFailure) {
        this.listFailure = listFailure;
    }

}
