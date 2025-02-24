package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;

/**
 * 迂回献金経路を経路別で取得する
 */
@Service
public class SearchUkaiKenkinRouteByRouteService {

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /**
     * 処理を行う
     *
     * @param userCode    操作ユーザ同一識別コード
     * @param optionValue 選択されたワークテーブルのコード
     * @return 検索結果
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> practice(final Integer userCode, final String optionValue) {

        Integer wkTblCode = Integer.parseInt(optionValue);
        final Integer zeroStageAll = 0;
        if (zeroStageAll.equals(wkTblCode)) {
            // 0の時は階層0データを取得する
            return wkTblUkaiKenkinPickupRouteRepository.findZeroStageAll(userCode);
        } else {
            // 0以外はコードで指定された経路のみを取得する
            return wkTblUkaiKenkinPickupRouteRepository
                    .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode, wkTblCode);
        }

    }

}
