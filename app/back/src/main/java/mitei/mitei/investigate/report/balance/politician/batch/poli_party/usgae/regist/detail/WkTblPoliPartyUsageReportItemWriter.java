package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import java.util.Optional;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * 政治資金収支報告書ワークテーブルに計画から作業内容を書き込むライタ
 */
@Component
public class WkTblPoliPartyUsageReportItemWriter extends JpaItemWriter<WkTblPoliPartyUsageReportEntity> {

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public WkTblPoliPartyUsageReportItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPoliPartyUsageReportEntity> items) {
        
        Integer code = 0;
        Optional<WkTblPoliPartyUsageReportEntity> optional = wkTblPoliPartyUsageReportRepository
                .findFirstByOrderByWkTblPoliPartyUsageReportCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getWkTblPoliPartyUsageReportCode();
        }

        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        privilegeDto.setLoginUserId(items.getItems().get(0).getInsertUserId());
        privilegeDto.setLoginUserCode(items.getItems().get(0).getInsertUserCode());
        privilegeDto.setLoginUserName(items.getItems().get(0).getInsertUserName());

        for (WkTblPoliPartyUsageReportEntity entity : items.getItems()) {
            code++;
            entity.setWkTblPoliPartyUsageReportCode(code);
        }
        // 登録処理
        wkTblPoliPartyUsageReportRepository.saveAll(items.getItems());
        wkTblPoliPartyUsageReportRepository.flush();
    }

}
