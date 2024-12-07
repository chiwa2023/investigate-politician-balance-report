package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh;


import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * RefleshYearTrialLogic単体テスト
 */
class RefleshYearTrialLogicTest {

    @Test
    @Tag("SourceReflesh")
   void testPractice()throws Exception {
        
        RefleshYearTrialLogic refleshYearTrialLogic = new RefleshYearTrialLogic();
        
        final int baseYear = 2025;
        final int copyYear = 2024;
               
        refleshYearTrialLogic.practice(String.valueOf(baseYear), String.valueOf(copyYear));
        
        fail("Not yet implemented");
    }

}
