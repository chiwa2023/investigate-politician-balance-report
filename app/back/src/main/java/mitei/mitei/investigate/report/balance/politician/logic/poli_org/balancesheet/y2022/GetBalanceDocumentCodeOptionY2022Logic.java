package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.OfferingDateDocumentCodeDto;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Repository;

/**
 * 収支報告書の文書一覧をセレクトボックスの選択肢形式で取得する
 */
@Component
public class GetBalanceDocumentCodeOptionY2022Logic {

    /** 収支報告書表紙Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2022Repository offeringBalancesheet0701And0720Surface2022Repository;

    /**
     * 処理を行う
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return オプション項目リスト
     */
    public List<SelectOptionDto> practice(final Integer poliOrgCode) {

        // 提出日と最大文書同一識別コードのペアを取得
        List<OfferingDateDocumentCodeDto> listDocumet = offeringBalancesheet0701And0720Surface2022Repository
                .findLatestDocumentGroupOfferringDate(poliOrgCode);

        List<SelectOptionDto> list = new ArrayList<>();
        for (OfferingDateDocumentCodeDto dto : listDocumet) {
            list.add(this.convertOption(dto));
        }

        return list;
    }

    /*
     * オプション項目に変換する
     * 
     * @param dto 取得した最大文書同一識別コードと提出日のペア
     * @return オプション項目
     */
    private SelectOptionDto convertOption(final OfferingDateDocumentCodeDto dto) {
        SelectOptionDto optionItem = new SelectOptionDto();

        optionItem.setValue(dto.getDocumentCode().toString());
        optionItem.setText(dto.getOfferingDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "提出分");

        return optionItem;
    }

}
