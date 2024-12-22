package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
 * MoveZenginTenpoMasterItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MoveZenginTenpoMasterItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MoveZenginTenpoMasterItemWriter moveZenginTenpoMasterItemWriter;
    
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
    @Sql("zengin_org_branch_master.sql")
    void test()throws Exception {
        
        StepExecution execution = this.getStepExecution();
        moveZenginTenpoMasterItemWriter.beforeStep(execution);

        // id=39 みずほ銀行府中銀行が移転する想定になる
        ZenginOrgBranchMasterEntity entity01 = new ZenginOrgBranchMasterEntity();
        entity01.setZenginOrgTempoMasterId(39);
        entity01.setZenginOrgTempoMasterCode(33);
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
        moveZenginTenpoMasterItemWriter.write(items);

        List<ZenginOrgBranchMasterEntity> listAnswer = zenginOrgBranchMasterRepository.findAll();
        assertEquals(13, listAnswer.size(), "もとの12件から1件登録");

        // id逆順にソート
        listAnswer.sort((e1,e2) -> e2.getZenginOrgTempoMasterId()-e1.getZenginOrgTempoMasterId());
        ZenginOrgBranchMasterEntity entityAnswer = listAnswer.get(0);
        
        assertEquals("111", entityAnswer.getOrgCode(), "");
        assertEquals("112", entityAnswer.getBranchCode(), "");
        assertEquals("113", entityAnswer.getOrgNameKana(), "");
        assertEquals("114", entityAnswer.getOrgName(), "");
        assertEquals("115", entityAnswer.getBranchNameKana(), "");
        assertEquals("116", entityAnswer.getBranchName(), "");
        assertEquals("117", entityAnswer.getPostalCode(), "");
        assertEquals("118", entityAnswer.getBranchAddress(), "");
        assertEquals("119", entityAnswer.getPhonNumber(), "");
        assertEquals("120", entityAnswer.getBillExchangeNumber(), "");
        assertEquals("121", entityAnswer.getOrderCode(), "");
        assertEquals("122", entityAnswer.getFlgNaikokuKawase(), "");
        assertEquals(1, entityAnswer.getSaishinKbn(), "");
        assertEquals(339, entityAnswer.getInsertUserId(), "");
        assertEquals(330, entityAnswer.getInsertUserCode(), "");
        assertEquals("ユーザ", entityAnswer.getInsertUserName(), "");

        assertEquals(33, entityAnswer.getZenginOrgTempoMasterCode(), "最重要：編集前と同一識別コードが同じ");
        
        Optional<ZenginOrgBranchMasterEntity> optional = zenginOrgBranchMasterRepository.findById(39);
        assertEquals(0, optional.get().getSaishinKbn(), "最重要：元データは履歴に変更");
        
    }

}
