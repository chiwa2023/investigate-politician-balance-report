package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;

/**
 * 個人寄付の取引相手、企業・団体寄付の団体代表者から迂回献金を抽出する
 */
@Component
public class PickupSameRelationPersonDataLogic {

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository ukaiKenkinRepository;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;

    /**
     * 処理を行う
     *
     * @param userCode 操作ユーザ同一識別コード
     */
    public List<WkTblUkaiKenkinPickupRouteEntity> practice(final Integer userCode) {

        // 個人寄付データで同じ関連者を持つ企業・個人と政治団体データを抽出する
        List<Tuple3<Long, Integer, String>> listKojin = ukaiKenkinRepository.findTradingPartnerCode(userCode);

        Map<Long, WkTblUkaiKenkinPickupRouteEntity> map = new TreeMap<>();
        RelationPersonWithYakuwariDto personWithYakuwariDto;
        for (Tuple3<Long, Integer, String> tuple3 : listKojin) {
            personWithYakuwariDto = this.createYakuwariDto(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_TORIHIKI);

            List<WkTblUkaiKenkinEntity> listDetail = ukaiKenkinRepository.findCorpAndPoriOrgByKojin(userCode,
                    personWithYakuwariDto.getCode());
            this.recordMap(map,
                    convertUkaiKenkinDetailToRouteByExternalPersonLogic.practice(listDetail, personWithYakuwariDto));
        }

        List<Tuple4<Integer, Long, Integer, String>> listCorp = ukaiKenkinRepository.findTradingDelegateCode(userCode);

        for (Tuple4<Integer, Long, Integer, String> tuple4 : listCorp) {
            personWithYakuwariDto = this.createYakuwariDto(tuple4.getT2(), tuple4.getT3(), tuple4.getT4());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

            int codeCorp = tuple4.getT1();
            List<WkTblUkaiKenkinEntity> listDetail = ukaiKenkinRepository.findDataByKigyouDaihyousha(userCode, codeCorp,
                    personWithYakuwariDto.getCode());
            this.recordMap(map,
                    convertUkaiKenkinDetailToRouteByExternalPersonLogic.practice(listDetail, personWithYakuwariDto));
        }

        return map.values().stream().toList();
    }

    // 重複なｌく抽出したリストを保存する
    private void recordMap(final Map<Long, WkTblUkaiKenkinPickupRouteEntity> map,
            final List<WkTblUkaiKenkinPickupRouteEntity> list) {
        for (WkTblUkaiKenkinPickupRouteEntity entity : list) {
            if (!map.containsKey(entity.getTablleId())) {
                map.put(entity.getTablleId(), entity);
            }
        }
    }

    // 関連者役割付きDtoを作成する
    private RelationPersonWithYakuwariDto createYakuwariDto(final Long id // NOPMD
            , final Integer code, final String name) {
        RelationPersonWithYakuwariDto dto = new RelationPersonWithYakuwariDto();
        dto.setId(id);
        dto.setCode(code);
        dto.setName(name);

        return dto;
    }
}
