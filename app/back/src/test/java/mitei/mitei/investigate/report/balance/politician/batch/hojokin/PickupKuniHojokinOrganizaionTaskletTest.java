package mitei.mitei.investigate.report.balance.politician.batch.hojokin;

import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * PickupKuniHojokinOrganizaionTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupKuniHojokinOrganizaionTaskletTest {

    /** テスト対象 */
    @Autowired
    private PickupKuniHojokinOrganizaionTasklet pickupKuniHojokinOrganizaionTasklet;
    
    @Test
    void test()throws Exception {

        pickupKuniHojokinOrganizaionTasklet.beforeStep(getStepExecution());
        pickupKuniHojokinOrganizaionTasklet.execute(null, null);
        
        fail("Not yet implemented");
    }

    private StepExecution getStepExecution() {

        String year = "2019";
        Path pathRead = Paths.get("c:/temp/Hojokinjoho_SJIS_20250705.csv");
        Path pathWrite = Paths.get("c:/temp/hojokin_chushutsu_"+year+".csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", pathRead.toString())
                .addString("writeFilePath", pathWrite.toString())
                .addString("year", year)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
