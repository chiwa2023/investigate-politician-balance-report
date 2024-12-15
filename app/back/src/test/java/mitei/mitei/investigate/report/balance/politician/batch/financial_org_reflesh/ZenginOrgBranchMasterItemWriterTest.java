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
 * ZenginOrgBranchMasterItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ZenginOrgBranchMasterItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ZenginOrgBranchMasterItemWriter zenginOrgBranchMasterItemWriter;

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
    void test() throws Exception { // NOPMD

        StepExecution execution = this.getStepExecution();
        zenginOrgBranchMasterItemWriter.beforeStep(execution);

        ZenginOrgBranchMasterEntity entity01 = new ZenginOrgBranchMasterEntity();
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

        ZenginOrgBranchMasterEntity entity02 = new ZenginOrgBranchMasterEntity();
        entity02.setOrgCode("211");
        entity02.setBranchCode("212");
        entity02.setOrgNameKana("213");
        entity02.setOrgName("214");
        entity02.setBranchNameKana("215");
        entity02.setBranchName("216");
        entity02.setPostalCode("217");
        entity02.setBranchAddress("218");
        entity02.setPhonNumber("219");
        entity02.setBillExchangeNumber("220");
        entity02.setOrderCode("221");
        entity02.setFlgNaikokuKawase("222");

        List<ZenginOrgBranchMasterEntity> list = new ArrayList<>();
        list.add(entity01);
        list.add(entity02);

        // Chunkを作成してセット
        Chunk<? extends ZenginOrgBranchMasterEntity> items = new Chunk<>(list);
        zenginOrgBranchMasterItemWriter.write(items);

        List<ZenginOrgBranchMasterEntity> listAnswer = zenginOrgBranchMasterRepository.findAll();
        assertEquals(2, listAnswer.size(), "2件登録して件取得");
        listAnswer.sort((e1, e2) -> e1.getZenginOrgTempoMasterCode() - e2.getZenginOrgTempoMasterCode());

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

        ZenginOrgBranchMasterEntity answerEntity01 = listAnswer.get(1);

        assertEquals("211", answerEntity01.getOrgCode(), "");
        assertEquals("212", answerEntity01.getBranchCode(), "");
        assertEquals("213", answerEntity01.getOrgNameKana(), "");
        assertEquals("214", answerEntity01.getOrgName(), "");
        assertEquals("215", answerEntity01.getBranchNameKana(), "");
        assertEquals("216", answerEntity01.getBranchName(), "");
        assertEquals("217", answerEntity01.getPostalCode(), "");
        assertEquals("218", answerEntity01.getBranchAddress(), "");
        assertEquals("219", answerEntity01.getPhonNumber(), "");
        assertEquals("220", answerEntity01.getBillExchangeNumber(), "");
        assertEquals("221", answerEntity01.getOrderCode(), "");
        assertEquals("222", answerEntity01.getFlgNaikokuKawase(), "");

        assertEquals(1, answerEntity00.getSaishinKbn(), "");
        assertEquals(339, answerEntity00.getInsertUserId(), "");
        assertEquals(330, answerEntity00.getInsertUserCode(), "");
        assertEquals("ユーザ", answerEntity00.getInsertUserName(), "");

    }

}
