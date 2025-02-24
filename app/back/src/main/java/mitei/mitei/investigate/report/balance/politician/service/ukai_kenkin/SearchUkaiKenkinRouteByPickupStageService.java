package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinPickupRouteResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinSearchCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import reactor.util.function.Tuple2;

/**
 * 迂回献金経路をページごとに取得する
 */
@Service
public class SearchUkaiKenkinRouteByPickupStageService {

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 迂回献金検索条件Dto
     * @return 検索結果
     */
    public UkaiKenkinPickupRouteResultDto practice(final UkaiKenkinSearchCapsuleDto capsuleDto) {

        UkaiKenkinPickupRouteResultDto resultDto = new UkaiKenkinPickupRouteResultDto();

        Integer userCode = capsuleDto.getCheckPrivilegeDto().getLoginUserCode();

        final Integer zero = 0;
        if (zero.equals(capsuleDto.getCountAll())) {
            resultDto.setCountAll(wkTblUkaiKenkinPickupRouteRepository.findCount(userCode));
        }

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber());

        // 取得するデータのワークテーブルコードを取得する
        List<Integer> listCode = wkTblUkaiKenkinPickupRouteRepository.findCodeByPaging(userCode, pageable);

        // 保存するデータを取得する
        List<WkTblUkaiKenkinPickupRouteEntity> list = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeInOrderByPickupStageAsc(userCode, listCode);

        // 各コードにおける最大階層を取得する
        List<Tuple2<Integer, Integer>> listMax = wkTblUkaiKenkinPickupRouteRepository.findRouteCodeMaxStage(userCode,
                listCode);
        Map<Integer, Integer> map = this.convertMaxMap(listMax);
        List<WkTblUkaiKenkinPickupRouteEntity> listFirst = resultDto.getListFirstRouteEntity();
        List<WkTblUkaiKenkinPickupRouteEntity> listPick = resultDto.getListPickupRouteEntity();
        List<WkTblUkaiKenkinPickupRouteEntity> listLast = resultDto.getListLastRouteEntity();

        boolean isSave;
        // 階層0であれば最初のリストに経路の最終階層であれば最終リストに、いずれでもなければその他リストに入れる
        for (WkTblUkaiKenkinPickupRouteEntity entity : list) {
            isSave = false;
            if (zero.equals(entity.getPickupStage())) {
                listFirst.add(entity);
                isSave = true;
            }
            if (!isSave && map.get(entity.getWkTblUkaiKenkinPickupRouteCode()).equals(entity.getPickupStage())) {
                listLast.add(entity);
                isSave = true;
            }
            if (!isSave) {
                listPick.add(entity);
            }
        }

        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        return resultDto;
    }

    // 最大値のペアを比較しやすいようMap形式にする
    private Map<Integer, Integer> convertMaxMap(final List<Tuple2<Integer, Integer>> listMax) {

        Map<Integer, Integer> map = new TreeMap<>();

        for (Tuple2<Integer, Integer> tuple2 : listMax) {
            map.put(tuple2.getT1(), tuple2.getT2());
        }

        return map;
    }

}
