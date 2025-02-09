package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * CreateUkaiKenkinDataStageZeroTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateUkaiKenkinDataStageZeroTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CreateUkaiKenkinDataStageZeroTasklet createUkaiKenkinDataStageZeroTasklet;

    /** 迂回献金(明細)Dao */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({"truncate_wk_tbl_ukai_kenkin.sql","tasklet_stage0_income_2022.sql","tasklet_stage0_poli_org_property.sql"})
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        createUkaiKenkinDataStageZeroTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, createUkaiKenkinDataStageZeroTasklet.execute(null, null), "実行");

        List<WkTblUkaiKenkinEntity> list =        wkTblUkaiKenkinRepository.findAll();
        
        assertEquals(15, list.size(),"取得リストサイズ");
        
        
        //101
        WkTblUkaiKenkinEntity entity00 = list.get(0);
        assertEquals(101L, entity00.getTablleId(),"テーブルId00");
        
        //102
        WkTblUkaiKenkinEntity entity01 = list.get(1);
        assertEquals(102L, entity01.getTablleId(),"テーブルId01");
        
        //103
        WkTblUkaiKenkinEntity entity02 = list.get(2);
        assertEquals(103L, entity02.getTablleId(),"テーブルId02");
        
        //104
        WkTblUkaiKenkinEntity entity03 = list.get(3);
        assertEquals(104L, entity03.getTablleId(),"テーブルId03");
        
        //105
        WkTblUkaiKenkinEntity entity04 = list.get(4);
        assertEquals(105L, entity04.getTablleId(),"テーブルId04");
        
        //106
        WkTblUkaiKenkinEntity entity05 = list.get(5);
        assertEquals(106L, entity05.getTablleId(),"テーブルId05");
        
        //107
        WkTblUkaiKenkinEntity entity06 = list.get(6);
        assertEquals(107L, entity06.getTablleId(),"テーブルId06");
        
        //108
        WkTblUkaiKenkinEntity entity07 = list.get(7);
        assertEquals(108L, entity07.getTablleId(),"テーブルId07");
        
        //201
        WkTblUkaiKenkinEntity entity08 = list.get(8);
        assertEquals(201L, entity08.getTablleId(),"テーブルId08");
        
        //202
        WkTblUkaiKenkinEntity entity09 = list.get(9);
        assertEquals(202L, entity09.getTablleId(),"テーブルId09");
        
        //203
        WkTblUkaiKenkinEntity entity10 = list.get(10);
        assertEquals(203L, entity10.getTablleId(),"テーブルId10");
        
        //204
        WkTblUkaiKenkinEntity entity11 = list.get(11);
        assertEquals(204L, entity11.getTablleId(),"テーブルId11");
        
        //205
        WkTblUkaiKenkinEntity entity12 = list.get(12);
        assertEquals(205L, entity12.getTablleId(),"テーブルId12");
        
        //206
        WkTblUkaiKenkinEntity entity13 = list.get(13);
        assertEquals(206L, entity13.getTablleId(),"テーブルId13");
        
        //301
        WkTblUkaiKenkinEntity entity14 = list.get(14);
        assertEquals(301L, entity14.getTablleId(),"テーブルId14");
        
    }

    private StepExecution getStepExecution() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(privilegeDto.getLoginUserCode()))
                .addString("userName", privilegeDto.getLoginUserName())
                .addLong("poliOrgCode", Long.valueOf(100))
                .addString("isSearchKoufukin", Boolean.FALSE.toString())
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
