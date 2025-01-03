package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchMasterRepository;

/**
 * DeleteZenginTenpoMasterItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteZenginTenpoMasterItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private DeleteZenginTenpoMasterItemWriter deleteZenginTenpoMasterItemWriter;

    /** 全銀協記入機関・店舗マスタRepository */
    @Autowired
    private ZenginOrgBranchMasterRepository zenginOrgBranchMasterRepository;

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", 339L).addLong("userCode", 330L).addString("userName", "ユーザ").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("zengin_org_branch_master2.sql")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        deleteZenginTenpoMasterItemWriter.beforeStep(execution);

        // みずほ銀行府中支店が廃止する想定になる
        Integer idPre = 39;
        ZenginOrgBranchMasterEntity entity01 = new ZenginOrgBranchMasterEntity();
        entity01.setZenginOrgTempoMasterId(idPre);
        entity01.setZenginOrgTempoMasterCode(33);
        entity01.setZenginOrgTempoMasterName("みずほ銀行府中支店");
        entity01.setOrgCode("111");
        entity01.setBranchCode("112");
        entity01.setOrgNameKana("113");
        entity01.setOrgName("114");
        entity01.setBranchNameKana("115");
        entity01.setBranchName("116");
        entity01.setPostalCode("117");
        entity01.setBranchAddress("118");
        entity01.setPhonNumber("119");
        entity01.setBillExchangeNumber("120");
        entity01.setOrderCode("121");
        entity01.setFlgNaikokuKawase("122");

        List<ZenginOrgBranchMasterEntity> list = new ArrayList<>();
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends ZenginOrgBranchMasterEntity> items = new Chunk<>(list);
        deleteZenginTenpoMasterItemWriter.write(items);

        List<ZenginOrgBranchMasterEntity> listAnswer = zenginOrgBranchMasterRepository.findAll();
        assertEquals(12, listAnswer.size(), "もとの12件から1件登録");

        ZenginOrgBranchMasterEntity entitySrc = zenginOrgBranchMasterRepository.findById(idPre).get();
        assertEquals(0, entitySrc.getSaishinKbn(), "元データが履歴");

    }

}
