package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.UpdatePartyUsageTaskPlanCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_party.UpdatePartyUsagePlanService;

/**
 * ビジネスロジックをまとめるWorksBandController(Transaction用)
 */
@Component
public class UpdatePartyUsagePlanControllerWorksBand {

    /** 政党交付金使途報告書Service */
    @Autowired
    private UpdatePartyUsagePlanService updatePartyUsagePlanService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 政党交付金使途報告書計画予定更新Dto
     * @return 更新結果Dto
     * @throws EmptyResultDataAccessException Idで呼び出しても呼び出せなかった
     * @throws IOException                    ファイル読み取り例外
     */
    @Transactional
    public TemplateFrameworkResultDto wakeBusiness(final UpdatePartyUsageTaskPlanCapsuleDto capsuleDto)
            throws  IOException {

        Long newId = updatePartyUsagePlanService.practice(capsuleDto.getTaskPlanPartyUsageDetailEntity(),
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
