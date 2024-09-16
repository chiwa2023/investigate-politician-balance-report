package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Repository;

/**
 * すでに登録済の場合は表紙、誓約書の文書同一識別コードのリストを返す
 */
@Component
public class CheckAllreadyRegistDataY2022Logic {

    /** 登録年2022年Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2022Repository offeringBalancesheet0701And0720Surface2022Repository;

    /**
     * 提出年と政治団体同一識別コードが同じかつ最新区分が最新であるコードを返却する
     *
     * @param documentPropertyDto 文書同一識別コード
     * @return 検索条件に一致する文書同一識別コード
     */
    public List<Long> practice(final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto) {

        List<OfferingBalancesheet0701And0720Surface2022Entity> listData = offeringBalancesheet0701And0720Surface2022Repository
                .findBySaishinKbnAndPoliticalOrganizationCodeAndOfferingDate(DataHistoryStatusConstants.INSERT.value(),
                        documentPropertyDto.getPoliticalOrganizationCode(), documentPropertyDto.getOfferingDate());

        List<Long> list = new ArrayList<>();
        for (OfferingBalancesheet0701And0720Surface2022Entity entity : listData) {
            list.add(entity.getOfferingBalancesheet0701And0720SurfaceCode());
        }

        return list;
    }

}
