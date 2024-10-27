package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_org.regist;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.RegistPoliticalOrgBalancesheetReportCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.RegistPoliticalOrgBalancesheetReportResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ReadAllBookByXmlFileLogic;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_org.InsertPoliticalOrgnaizationBalancesheetReportService;

/**
 * 政治資金収支報告書の公式XMLを登録する
 */
@Controller
public class InsertPoliticalOrgnaizationBalancesheetReportController {

    /** 政治資金収支報告書の公式XMLを登録Service */
    @Autowired
    private InsertPoliticalOrgnaizationBalancesheetReportService insertPoliticalOrgnaizationBalancesheetReportService;

    /** 政治資金収支報告書読みだしLogic */
    @Autowired
    private ReadAllBookByXmlFileLogic readAllBookByXmlFileLogic;

    /**
     * 政治資金収支報告書の公式XMLを登録する
     *
     * @param capsuleDto 登録一括Dto
     * @return 登録結果Dto
     */
    @Transactional
    @PostMapping("/insert-political-orgnaization-balancesheet-report")
    public ResponseEntity<RegistPoliticalOrgBalancesheetReportResultDto> practice(
            @RequestBody final RegistPoliticalOrgBalancesheetReportCapsuleDto capsuleDto) throws IOException {

        // 保存したXMLからの全データ
        AllBookDto allBookDto = readAllBookByXmlFileLogic.practice(capsuleDto.getSaveStorageResultDto().getFullPath(),
                capsuleDto.getSaveStorageResultDto().getCharset());

        // 登録作業
        RegistPoliticalOrgBalancesheetReportResultDto resultDto = insertPoliticalOrgnaizationBalancesheetReportService
                .practice(capsuleDto.getDocumentPropertyDto(), allBookDto, capsuleDto.getCheckPrivilegeDto());

        return ResponseEntity.ok(resultDto);
    }

}
