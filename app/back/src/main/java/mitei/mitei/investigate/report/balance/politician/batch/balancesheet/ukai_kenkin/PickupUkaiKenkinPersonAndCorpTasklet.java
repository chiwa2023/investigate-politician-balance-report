package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonWithYakuwariDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RelationPersonYakuwariConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByExternalPersonLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;

/**
 * 迂回献金個人と企業団体経路抽出Taskelt
 */
@Component
public class PickupUkaiKenkinPersonAndCorpTasklet implements Tasklet, StepExecutionListener {

    /** 迂回献金明細Repository */
    @Autowired
    private WkTblUkaiKenkinRepository ukaiKenkinRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 迂回献金明細経路変換ロジック */
    @Autowired
    private ConvertUkaiKenkinDetailToRouteByExternalPersonLogic convertUkaiKenkinDetailToRouteByExternalPersonLogic;

    /** 操作者同一識別コード */
    private Integer userCode;

    /**
     * JobParameterを取得する
     *
     */
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

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

        // 企業・団体寄付データで同じ関連者を持つ企業・個人と政治団体データを抽出する
        for (Tuple4<Integer, Long, Integer, String> tuple4 : listCorp) {
            personWithYakuwariDto = this.createYakuwariDto(tuple4.getT2(), tuple4.getT3(), tuple4.getT4());
            personWithYakuwariDto.setYakuwari(RelationPersonYakuwariConstants.YAKUWARI_DAIHYOUSHA);

            int codeCorp = tuple4.getT1();
            List<WkTblUkaiKenkinEntity> listDetail = ukaiKenkinRepository.findDataByKigyouDaihyousha(userCode, codeCorp,
                    personWithYakuwariDto.getCode());
            this.recordMap(map,
                    convertUkaiKenkinDetailToRouteByExternalPersonLogic.practice(listDetail, personWithYakuwariDto));
        }

        List<WkTblUkaiKenkinPickupRouteEntity> listSave = map.values().stream().toList();

        // 経路コードを取得して設定(階層0なのですべてのデータが経路)
        Integer code = 1;
        Optional<WkTblUkaiKenkinPickupRouteEntity> optional = wkTblUkaiKenkinPickupRouteRepository
                .findFirstByOrderByWkTblUkaiKenkinPickupRouteCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblUkaiKenkinPickupRouteCode();
        }
        for (WkTblUkaiKenkinPickupRouteEntity entity : listSave) {
            entity.setWkTblUkaiKenkinPickupRouteCode(code);
            code++;
        }

        wkTblUkaiKenkinPickupRouteRepository.saveAll(listSave);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    // 重複なく抽出したリストを保存する
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
