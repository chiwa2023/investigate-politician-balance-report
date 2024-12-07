package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.UpdatePartyUsageWkTblCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_party.UpdatePartyUsageWkTbService;

/**
 * ビジネスロジックをまとめるWorksBandController(Transaction用)
 */
@Component
public class UpdatePartyUsageWkTbControllerWorksBand {

    /** 政党交付金使途報告書Service */
    @Autowired
    private UpdatePartyUsageWkTbService updatePartyUsageWkTbService;

    
    /**
     * 処理を行う
     *
     * @param capsuleDto 政党交付金使途報告書ワークテーブル更新Dto
     * @return 更新結果(Id)
     */
    @Transactional
    public TemplateFrameworkResultDto wakeBusiness(final UpdatePartyUsageWkTblCapsuleDto capsuleDto) {
        
        Long newId = updatePartyUsageWkTbService.practice(capsuleDto.getWkTblPoliPartyUsageReportEntity(),
                capsuleDto.getCheckPrivilegeDto());
        TemplateFrameworkResultDto resultDto = new TemplateFrameworkResultDto();
        final long noUpdate = 0L;
        if (noUpdate == newId) {
            resultDto.setIsOk(false);
            resultDto.setMessage("更新できませんでした");
        } else {
            resultDto.setIsOk(true);
            resultDto.setMessage("更新しました");
        }

        return resultDto;
    }

}
