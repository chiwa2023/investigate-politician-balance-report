package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.ConvertFukisaiIncomeY2022Logic;

/**
 * 収支データを不記載ワークテーブルに変換するFactoryLogic
 */
@Component
public class ConvertFukisaiIncomeLogic {

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private ConvertFukisaiIncomeY2022Logic convertFukisaiIncomeY2022Logic;

    /**
     * 処理を行う
     *
     * @param conditionDto 検索条件Dto
     * @param privilegeDto 権限確認Dto
     * @return 検索結果
     */
    public List<WkTblFukisaiBalancesheetEntity> practice(final FukisaiSearchConditionDto conditionDto,
            final CheckPrivilegeDto privilegeDto) {

        int key = conditionDto.getHoukokuNen();

        switch (key) {
            // 2022年
            case YEAR_2022:
                return convertFukisaiIncomeY2022Logic.practice(conditionDto, privilegeDto);

            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

}
