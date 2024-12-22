package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
 * AddZenginTenpoMasterItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AddZenginTenpoMasterItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private AddZenginTenpoMasterItemWriter addZenginTenpoMasterItemWriter;

    /** 全銀協記入機関・店舗マスタRepository */
    @Autowired
    private ZenginOrgBranchMasterRepository zenginOrgBranchMasterRepository;

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("loginUserId", 339L).addString("loginUserCode", "330").addString("loginUserName", "ユーザ")
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_zengin_org_branch_master.sql")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        addZenginTenpoMasterItemWriter.beforeStep(execution);

        ZenginOrgBranchMasterEntity entity01 = new ZenginOrgBranchMasterEntity();
        entity01.setZenginOrgTempoMasterId(1);
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
        addZenginTenpoMasterItemWriter.write(items);

        List<ZenginOrgBranchMasterEntity> listAnswer = zenginOrgBranchMasterRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        ZenginOrgBranchMasterEntity answerEntity00 = listAnswer.get(0);

        assertEquals("111", answerEntity00.getOrgCode(), "");
        assertEquals("112", answerEntity00.getBranchCode(), "");
        assertEquals("113", answerEntity00.getOrgNameKana(), "");
        assertEquals("114", answerEntity00.getOrgName(), "");
        assertEquals("115", answerEntity00.getBranchNameKana(), "");
        assertEquals("116", answerEntity00.getBranchName(), "");
        assertEquals("117", answerEntity00.getPostalCode(), "");
        assertEquals("118", answerEntity00.getBranchAddress(), "");
        assertEquals("119", answerEntity00.getPhonNumber(), "");
        assertEquals("120", answerEntity00.getBillExchangeNumber(), "");
        assertEquals("121", answerEntity00.getOrderCode(), "");
        assertEquals("122", answerEntity00.getFlgNaikokuKawase(), "");

        assertEquals(1, answerEntity00.getSaishinKbn(), "");
        assertEquals(339, answerEntity00.getInsertUserId(), "");
        assertEquals(330, answerEntity00.getInsertUserCode(), "");
        assertEquals("ユーザ", answerEntity00.getInsertUserName(), "");

        // わざわざItemWriterを分ける理由につき、最重要
        assertNotEquals(entity01.getZenginOrgTempoMasterId(), answerEntity00.getZenginOrgTempoMasterId(),
                "Id=1で登録しようというは入っているが、違うIdで新規登録されている");
    }

}
