package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;

/**
 * 迂回献金経路からSelectBox用選択肢を作成する
 */
@Component
public class CreateUkaiKenkinRouteSelectOptionLogic {

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /**
     * 処理を行う
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 選択肢リスト
     */
    public List<SelectOptionDto> practice(final Integer userCode) {

        List<WkTblUkaiKenkinPickupRouteEntity> listRoute = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeOrderByWkTblUkaiKenkinPickupRouteCodeAscPickupStageAsc(userCode);

        Integer code = -1;
        String goalName = "";
        final String init = "";
        List<SelectOptionDto> list = new ArrayList<>();
        SelectOptionDto dto = new SelectOptionDto();
        for (WkTblUkaiKenkinPickupRouteEntity entity : listRoute) {
            if (!code.equals(entity.getWkTblUkaiKenkinPickupRouteCode())) {
                // 初回以降は最後の取り引き相手を最終検出相手とする
                if (!init.equals(dto.getValue())) {
                    dto.setText(dto.getText() + goalName);
                }
                dto = this.createOption(entity);
                code = entity.getWkTblUkaiKenkinPickupRouteCode();
                list.add(dto);
            }
            goalName = entity.getTradingPartnerName();
        }

        // 最後の選択肢に追加
        dto.setText(dto.getText() + goalName);

        return list;
    }

    // 選択肢を作成する
    private SelectOptionDto createOption(final WkTblUkaiKenkinPickupRouteEntity entity) {

        SelectOptionDto dto = new SelectOptionDto();
        String value = String.valueOf(entity.getWkTblUkaiKenkinPickupRouteCode());
        dto.setValue(value);
        dto.setText("経路(" + value + ")" + entity.getPoliticalOrgName() + "-->");

        return dto;
    }
}
