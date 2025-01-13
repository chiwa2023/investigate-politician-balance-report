package mitei.mitei.investigate.report.balance.politician.service.kifu_jogen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.KifuSouryouSeigenAllResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CheckKifuSouryouKiseiByCorpCodeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CheckKifuSouryouKiseiByNameLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CheckKifuSouryouKiseiByPersonCodeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;

/**
 * 寄付上限情報を取得する
 */
@Service
public class CheckDonationSouryouUpperLimitService {

    /** 原文書名称・住所基準集計額取得リスト */
    @Autowired
    private CheckKifuSouryouKiseiByNameLogic checkKifuSouryouKiseiByNameLogic;

    /** 関連者個人同一識別コード・Id基準集計額取得リスト */
    @Autowired
    private CheckKifuSouryouKiseiByPersonCodeLogic checkKifuSouryouKiseiByPersonCodeLogic;

    /** 関連者企業団体同一識別コード・Id基準集計額取得リスト */
    @Autowired
    private CheckKifuSouryouKiseiByCorpCodeLogic checkKifuSouryouKiseiByCorpCodeLogic;

    /** 団体区分リスト取得Logic */
    @Autowired
    private GetDantaiKbnListLogic getDantaiKbnListLogic;

    /**
     * 処理を行う
     *
     * @param houkokuNen     報告年
     * @param poliOrgCode    政治団体同一識別コード
     * @param isSearchByName 検索方式を原文書名称で行う該否
     * @return すべての検索結果
     */
    public KifuSouryouSeigenAllResultDto practice(final Integer houkokuNen, final Integer poliOrgCode,
            final boolean isSearchByName) {

        // MEMO 政治資金規正法のあらまし2025 p17を参照のこと

        // 政治団体区分リスを取得する(通称A枠、B枠、政治団体区分未設定)
        List<String> listKbnSeitou = getDantaiKbnListLogic.practiceParty();
        List<String> listKbnOther = getDantaiKbnListLogic.practiceOtherOrg();
        List<String> listNotSet = getDantaiKbnListLogic.practiceNoSet();

        KifuSouryouSeigenAllResultDto resultDto = new KifuSouryouSeigenAllResultDto();

        if (isSearchByName) {

            // 総量規制通称A枠個人(政党・政治資金団体向け)(総量2000万円)
            resultDto.setListDtoKojinParty(this.createNoItem(checkKifuSouryouKiseiByNameLogic.practice(houkokuNen, poliOrgCode,
                    YoushikiEdaKbn.KOJIN, listKbnSeitou)));

            // 総量規制通称A枠企業団体(政党・政治資金団体向け)資本金に準ずる
            resultDto.setListDtoKigyouParty(this.createNoItem(
                    checkKifuSouryouKiseiByNameLogic.practice(houkokuNen, poliOrgCode, YoushikiEdaKbn.KIGYOU_DANTAI,
                            listKbnSeitou)));

            // 総量規制通称B枠個人(候補者・その他団体向け)総量1000万円
            resultDto.setListDtoKojinOtherOrg(this.createNoItem(
                    checkKifuSouryouKiseiByNameLogic.practice(houkokuNen, poliOrgCode, YoushikiEdaKbn.KOJIN,
                            listKbnOther)));

            // 総量規制通称B枠企業団体(候補者・その他団体向け)
            // 検索をトライして存在しないことを証明しなくてはいけない
            resultDto.setListDtoKigyouOtherOrg(
                    this.createNoItem(
                    checkKifuSouryouKiseiByNameLogic.practice(houkokuNen, poliOrgCode,
                    YoushikiEdaKbn.KIGYOU_DANTAI, listKbnOther)));

            // 政治団体の団体区分が未設定
            resultDto.setListDtoNoset(this.createNotSetList(
                    checkKifuSouryouKiseiByNameLogic.practice(houkokuNen, poliOrgCode, YoushikiEdaKbn.KOJIN,
                            listNotSet),
                    checkKifuSouryouKiseiByNameLogic.practice(houkokuNen, poliOrgCode, YoushikiEdaKbn.KIGYOU_DANTAI,
                            listNotSet)));

        } else {

            // 総量規制通称A枠個人(政党・政治資金団体向け)(総量2000万円)
            resultDto.setListDtoKojinParty(this.createNoItem(
                    checkKifuSouryouKiseiByPersonCodeLogic.practice(houkokuNen, poliOrgCode, listKbnSeitou)));

            // 総量規制通称A枠企業団体(政党・政治資金団体向け)資本金に準ずる
            resultDto.setListDtoKigyouParty(this.createNoItem(
                    checkKifuSouryouKiseiByCorpCodeLogic.practice(houkokuNen, poliOrgCode, listKbnSeitou)));

            // 総量規制通称B枠個人(候補者・その他団体向け)総量2000万円と質的制限150万円
            resultDto.setListDtoKojinOtherOrg(this.createNoItem(
                    checkKifuSouryouKiseiByPersonCodeLogic.practice(houkokuNen, poliOrgCode, listKbnOther)));

            // 総量規制通称B枠企業団体(候補者・その他団体向け)
            // 検索をトライして存在しないことを証明しなくてはいけない
            resultDto.setListDtoKigyouOtherOrg(this.createNoItem(
                    checkKifuSouryouKiseiByCorpCodeLogic.practice(houkokuNen, poliOrgCode, listKbnOther)));

            // 政治団体の団体区分が未設定
            resultDto.setListDtoNoset(this.createNotSetList(
                    checkKifuSouryouKiseiByPersonCodeLogic.practice(houkokuNen, poliOrgCode, listNotSet),
                    checkKifuSouryouKiseiByCorpCodeLogic.practice(houkokuNen, poliOrgCode, listNotSet)));
        }

        return resultDto;
    }

    // 未設定リストを生成する(該当データがないときも「ありません」表示をする)
    private List<SouryouKiseiRelationCodeDto> createNotSetList(final List<SouryouKiseiRelationCodeDto> listKojin,
            final List<SouryouKiseiRelationCodeDto> listKigyou) {
        List<SouryouKiseiRelationCodeDto> listResult = new ArrayList<>();
        if (!listKojin.isEmpty()) {
            listResult.addAll(listKojin);
        }
        if (!listKigyou.isEmpty()) {
            listResult.addAll(listKigyou);
        }

        if (listResult.isEmpty()) {
            SouryouKiseiRelationCodeDto dtoNoResult = new SouryouKiseiRelationCodeDto();
            dtoNoResult.setPartnerName("該当データがありませんでした");
            listResult.add(dtoNoResult);
        }

        return listResult;
    }
    
    // 未設定リストを生成する(該当データがないときも「ありません」表示をする)
    private List<SouryouKiseiRelationCodeDto> createNoItem(final List<SouryouKiseiRelationCodeDto> list){

        if(list.isEmpty()) {
            SouryouKiseiRelationCodeDto dtoNoResult = new SouryouKiseiRelationCodeDto();
            dtoNoResult.setPartnerName("該当データがありませんでした");
            list.add(dtoNoResult);
            return list;
        }else {
            return list;
        }
        
    }    
}
