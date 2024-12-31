package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.ConvertFukisaiOutcomeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;

/**
 * 政治資金収支報告書支出テーブルから不記載ワークテーブルデータを算出する
 */
@Component
public class ConvertFukisaiOutcomeLogic {

    /** 不記載ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired
    private ConvertFukisaiOutcomeY2022Logic convertFukisaiOutcomeY2022Logic;

    /**
     * 処理を行う
     *
     * @param conditionDto 検索条件Dto
     * @param privilegeDto 権限確認Dto
     * @return 変換された不記載検出ワークテーブル
     */
    public List<WkTblFukisaiBalancesheetEntity> practice(final FukisaiSearchConditionDto conditionDto,
            final CheckPrivilegeDto privilegeDto) {

        if (conditionDto.getIsSearchCode()) {

            // コード検索の場合
            return this.practiceCode(conditionDto, wkTblFukisaiBalancesheetRepository.findUserAndRelationCode(
                    privilegeDto.getLoginUserCode(), conditionDto.getPoliOrgCode()), privilegeDto);
        } else {

            // 名称検索の場合
            return this.practiceName(conditionDto, wkTblFukisaiBalancesheetRepository.findUserAndPartnerName(
                    privilegeDto.getLoginUserCode(), conditionDto.getDantaiName()), privilegeDto);
        }
    }

    // コード検索
    private List<WkTblFukisaiBalancesheetEntity> practiceCode(final FukisaiSearchConditionDto conditionDto,
            final List<Integer> listRelationCode, final CheckPrivilegeDto privilegeDto) {

        int key = conditionDto.getHoukokuNen();

        switch (key) {
            // 2022年
            case YEAR_2022:
                return convertFukisaiOutcomeY2022Logic.practiceCode(conditionDto.getPoliOrgCode(), listRelationCode,
                        privilegeDto);
            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

    // 名称検索 TODO コード標準化がされたら削除する
    private List<WkTblFukisaiBalancesheetEntity> practiceName(final FukisaiSearchConditionDto conditionDto,
            final List<String> listDantaiName, final CheckPrivilegeDto privilegeDto) {

        int key = conditionDto.getHoukokuNen();

        switch (key) {
            // 2022年
            case YEAR_2022:

                return convertFukisaiOutcomeY2022Logic.practiceName(conditionDto.getDantaiName(), listDantaiName,
                        privilegeDto);
            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

}
