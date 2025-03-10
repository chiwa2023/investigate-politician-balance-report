package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import java.util.Optional;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * 政治資金収支報告書ワークテーブルに計画から作業内容を書き込むライタ
 */
@Component
public class WkTblPoliOrgBalancesheetReportItemWriter extends JpaItemWriter<WkTblPoliOrgBalancesheetReportEntity> {

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public WkTblPoliOrgBalancesheetReportItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items) {
        
        Integer code = 0;
        Optional<WkTblPoliOrgBalancesheetReportEntity> optional = wkTblPoliOrgBalancesheetReportRepository
                .findFirstByOrderByWkTblPoliOrgBalancesheetReportCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getWkTblPoliOrgBalancesheetReportCode();
        }

        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        privilegeDto.setLoginUserId(items.getItems().get(0).getInsertUserId());
        privilegeDto.setLoginUserCode(items.getItems().get(0).getInsertUserCode());
        privilegeDto.setLoginUserName(items.getItems().get(0).getInsertUserName());

        for (WkTblPoliOrgBalancesheetReportEntity entity : items.getItems()) {
            code++;
            entity.setWkTblPoliOrgBalancesheetReportCode(code);
        }
        // 登録処理
        wkTblPoliOrgBalancesheetReportRepository.saveAll(items.getItems());
        wkTblPoliOrgBalancesheetReportRepository.flush();
    }

}
