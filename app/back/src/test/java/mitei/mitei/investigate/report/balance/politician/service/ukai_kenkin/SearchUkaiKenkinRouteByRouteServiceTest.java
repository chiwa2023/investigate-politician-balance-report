package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;


import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;

/**
 * SearchUkaiKenkinRouteByRouteService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchUkaiKenkinRouteByRouteServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchUkaiKenkinRouteByRouteService searchUkaiKenkinRouteByRouteService;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wktbl_route_full.sql")
    void test()throws Exception {
        
        Integer userCode = 987;

        String code1 = "13";
        List<WkTblUkaiKenkinPickupRouteEntity> list =         searchUkaiKenkinRouteByRouteService.practice(userCode, code1);
        assertEquals(4, list.size(),"経路13は4件");
        WkTblUkaiKenkinPickupRouteEntity entity00 = list.get(0);
        assertEquals(32L, entity00.getTablleId(),"経路13の1件目");
        WkTblUkaiKenkinPickupRouteEntity entity01 = list.get(1);
        assertEquals(122L, entity01.getTablleId(),"経路13の2件目");
        WkTblUkaiKenkinPickupRouteEntity entity02 = list.get(2);
        assertEquals(221L, entity02.getTablleId(),"経路13の3件目");
        WkTblUkaiKenkinPickupRouteEntity entity03 = list.get(3);
        assertEquals(320L, entity03.getTablleId(),"経路13の4件目");
        
        String code2 = "0";
        List<WkTblUkaiKenkinPickupRouteEntity> listZero =         searchUkaiKenkinRouteByRouteService.practice(userCode, code2);
        assertEquals(10, listZero.size(),"階層0は10件存在");
        
        WkTblUkaiKenkinPickupRouteEntity entity10 = listZero.get(0);
        assertEquals(1, entity10.getWkTblUkaiKenkinPickupRouteCode(),"0階層の1件目");
        WkTblUkaiKenkinPickupRouteEntity entity11 = listZero.get(1);
        assertEquals(2, entity11.getWkTblUkaiKenkinPickupRouteCode(),"0階層の2件目");
        WkTblUkaiKenkinPickupRouteEntity entity12 = listZero.get(2);
        assertEquals(3, entity12.getWkTblUkaiKenkinPickupRouteCode(),"0階層の3件目");
        WkTblUkaiKenkinPickupRouteEntity entity13 = listZero.get(3);
        assertEquals(4, entity13.getWkTblUkaiKenkinPickupRouteCode(),"0階層の4件目");
        WkTblUkaiKenkinPickupRouteEntity entity14 = listZero.get(4);
        assertEquals(5, entity14.getWkTblUkaiKenkinPickupRouteCode(),"0階層の5件目");
        WkTblUkaiKenkinPickupRouteEntity entity15 = listZero.get(5);
        assertEquals(6, entity15.getWkTblUkaiKenkinPickupRouteCode(),"0階層の6件目");
        WkTblUkaiKenkinPickupRouteEntity entity16 = listZero.get(6);
        assertEquals(7, entity16.getWkTblUkaiKenkinPickupRouteCode(),"0階層の7件目");
        WkTblUkaiKenkinPickupRouteEntity entity17 = listZero.get(7);
        assertEquals(8, entity17.getWkTblUkaiKenkinPickupRouteCode(),"0階層の8件目");
        WkTblUkaiKenkinPickupRouteEntity entity18 = listZero.get(8);
        assertEquals(9, entity18.getWkTblUkaiKenkinPickupRouteCode(),"0階層の9件目");
        WkTblUkaiKenkinPickupRouteEntity entity19 = listZero.get(9);
        assertEquals(10, entity19.getWkTblUkaiKenkinPickupRouteCode(),"0階層の10件目");
        
    }

}
