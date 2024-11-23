package mitei.mitei.investigate.report.balance.politician.logic.task_plan.party_usage.detail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertTaskPlanPartyUsageDetailLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertTaskPlanPartyUsageDetailLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanPartyUsageDetailLogic insertTaskPlanPartyUsageDetailLogic;

    /** 政党交付金使途報告書一括解析予定Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_task_plan_party_usage_detail.sql")
    void testPractice() {

        SaveStorageResultDto resultDto00 = new SaveStorageResultDto();
        resultDto00.setCharset("utf8");
        resultDto00.setChildDir("aaa/bbb/ccc");
        resultDto00.setFileName("abcde.xml");
        resultDto00.setFullPath("aaa/bbb/ccc/abcde.xml");
        resultDto00.setRegistTimeText("20221201");
        resultDto00.setShoshouId(103L);
        resultDto00.setShoshouCode(100L);
        resultDto00.setShoshouKbn(1);

        SaveStorageResultDto resultDto01 = new SaveStorageResultDto();

        resultDto01.setCharset(null); // ←意識的にnullを入れているので超重要
        resultDto01.setChildDir("ddd/eee");
        resultDto01.setFileName("defg.xml");
        resultDto01.setFullPath("ddd/eee/defg.xml");
        resultDto01.setRegistTimeText("20221201");
        resultDto01.setShoshouId(212L);
        resultDto01.setShoshouCode(200L);
        resultDto01.setShoshouKbn(3);

        List<SaveStorageResultDto> listStorage = new ArrayList<>();
        listStorage.add(resultDto00);
        listStorage.add(resultDto01);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = insertTaskPlanPartyUsageDetailLogic.practice(listStorage, privilegeDto);

        // 2件取得想定
        assertThat(size).isEqualTo(2);

        List<TaskPlanPartyUsageDetailEntity> list = taskPlanPartyUsageDetailRepository.findAll();
        list.stream().sorted((e1, e2) -> Math
                .toIntExact(e1.getTaskPlanPartyUsageDetailCode() - e2.getTaskPlanPartyUsageDetailCode()));

        // 全取得の場合登録件数と同じ件数取得
        assertThat(list.size()).isEqualTo(size);

        TaskPlanPartyUsageDetailEntity entity00 = list.get(0);

        assertThat(entity00.getCharset()).isEqualTo("utf8");
        assertThat(entity00.getChildDir()).isEqualTo("aaa/bbb/ccc");
        assertThat(entity00.getFileName()).isEqualTo("abcde.xml");
        assertThat(entity00.getFullPath()).isEqualTo("aaa/bbb/ccc/abcde.xml");
        assertThat(entity00.getIsFinished()).isEqualTo(false);
        assertThat(entity00.getShoshouId()).isEqualTo(103L);
        assertThat(entity00.getShoshouCode()).isEqualTo(100);
        assertThat(entity00.getShoshouKbn()).isEqualTo(1);

        assertThat(entity00.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity00.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(entity00.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(entity00.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

        TaskPlanPartyUsageDetailEntity entity01 = list.get(1);

        assertThat(entity01.getCharset()).isEqualTo(null);
        assertThat(entity01.getChildDir()).isEqualTo("ddd/eee");
        assertThat(entity01.getFileName()).isEqualTo("defg.xml");
        assertThat(entity01.getFullPath()).isEqualTo("ddd/eee/defg.xml");
        assertThat(entity01.getIsFinished()).isEqualTo(false);
        assertThat(entity01.getShoshouId()).isEqualTo(212L);
        assertThat(entity01.getShoshouCode()).isEqualTo(200L);
        assertThat(entity01.getShoshouKbn()).isEqualTo(3);

        fail("Not yet implemented");
    }

}
