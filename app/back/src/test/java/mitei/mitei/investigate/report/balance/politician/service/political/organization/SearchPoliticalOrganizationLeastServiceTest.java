package mitei.mitei.investigate.report.balance.politician.service.political.organization;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PoliticalOrganizationLeastDto;
import mitei.mitei.investigate.report.balance.politician.dto.political.organization.SearchPoliticalOrganizationLeastCapsuleDto;

/**
 * SearchPoliticalOrganizationLeastService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPoliticalOrganizationLeastServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPoliticalOrganizationLeastService searchPoliticalOrganizationLeastService;

    @Test
    @Tag("NaturalTextSearch")
    //@Sql("political_organization_natural_search.sql")
    void testPractice() {

        SearchPoliticalOrganizationLeastCapsuleDto capsuleDto01 = new SearchPoliticalOrganizationLeastCapsuleDto();
        capsuleDto01.setSearchWords("団体2");
        capsuleDto01.setIsHisory(false);
        List<PoliticalOrganizationLeastDto> list01 = searchPoliticalOrganizationLeastService.practice(capsuleDto01);
        assertThat(list01.size()).isEqualTo(1); // 1件
        PoliticalOrganizationLeastDto dto01 = list01.get(0);
        assertThat(dto01.getPoliticalOrganizationName()).isEqualTo("ちゃらんぽらん政治団体2");
        assertThat(dto01.getPoliticalOrganizationCode()).isEqualTo(212);

        SearchPoliticalOrganizationLeastCapsuleDto capsuleDto02 = new SearchPoliticalOrganizationLeastCapsuleDto();
        capsuleDto02.setSearchWords("大阪");
        capsuleDto02.setIsHisory(true);
        List<PoliticalOrganizationLeastDto> list02 = searchPoliticalOrganizationLeastService.practice(capsuleDto02);
        assertThat(list02.size()).isEqualTo(3); // 3件

        list02.sort((e1,e2) -> e1.getPoliticalOrganizationCode()-e2.getPoliticalOrganizationCode());
        
        PoliticalOrganizationLeastDto dto11 = list02.get(0);
        PoliticalOrganizationLeastDto dto12 = list02.get(1);
        PoliticalOrganizationLeastDto dto13 = list02.get(2);

        assertThat(dto11.getPoliticalOrganizationName()).isEqualTo("ちゃらんぽらん政治団体2");
        assertThat(dto11.getPoliticalOrganizationCode()).isEqualTo(210);
        
        assertThat(dto12.getPoliticalOrganizationName()).isEqualTo("ちゃらんぽらん政治団体2");
        assertThat(dto12.getPoliticalOrganizationCode()).isEqualTo(211);

        assertThat(dto13.getPoliticalOrganizationName()).isEqualTo("ちゃらんぽらん政治団体3");
        assertThat(dto13.getPoliticalOrganizationCode()).isEqualTo(385);
    }

}
