package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheet0701And0720Surface2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheet0701And0720Surface2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書の表紙、宣誓書、文書属性を保存する
 */
@Component
public class InsertPoliticalOrganizationSheet0701And0720Y2023Logic {
    
    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository  */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2023Repository offeringBalancesheet0701And0720Surface2023Repository;
    
    /**
     * 登録作業を行う
     *
     * @param documentPropertyDto 政治団体収支報告書属性Dto
     * @param allBookDto 収支報告書全Dto 
     * @param checkPrivilegeDto 権限チェックDto
     * @return 同一識別コード
     */
    public Long practice(final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllBookDto allBookDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheet0701And0720Surface2023Entity balancesheetEntity = new OfferingBalancesheet0701And0720Surface2023Entity();
        // ヘッダ
        BeanUtils.copyProperties(allBookDto.getAllBookHeaderDto(), balancesheetEntity);

        // <VERSION>20191220</VERSION>
        // allBookDto.getAllBookHeaderDto().getVersion()version
        // <APP>収支報告書作成ソフト〔収支報告書作成ソフト〕</APP>
        // allBookDto.getAllBookHeaderDto().getAppName();app_name
        // <FILE_FORMAT_NO>1</FILE_FORMAT_NO>
        // allBookDto.getAllBookHeaderDto().getFileFormatNo();file_format_no
        // <KOKUJI_APP_FLG>0</KOKUJI_APP_FLG>
        // allBookDto.getAllBookHeaderDto().getFlgKokuji();flg_kokuji
        // <CHOUBO_APP_VER>20150701</CHOUBO_APP_VER>
        // allBookDto.getAllBookHeaderDto().getChouboAppVer();choubo_app_ver

        // 有無テキスト
        // allBookDto.getAllBookUmuInputDataDto().getInputBitText();input_bit_text
        balancesheetEntity.setInputBitText(allBookDto.getAllBookUmuInputDataDto().getInputBitText());
        
        // シート20宣誓書
        BeanUtils.copyProperties(allBookDto.getAllSheet0720OathDto().getSheet072000OathDto(), balancesheetEntity);

        // シート1表紙
        BeanUtils.copyProperties(allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto()
                .getSheet070100CoverAndOrganizationDetailsDto(), balancesheetEntity);

        // 
        // @JacksonXmlProperty(localName = "HOUKOKU_NEN")
        // private Integer houkokuNen = 0;houkoku_nen

        // 
        // @JacksonXmlProperty(localName = "KAISAI_DT")
        // private String dateKaisai;date_kaisai

        // 
        // @JacksonXmlProperty(localName = "DANTAI_NM")
        // private String dantaiName;dantai_name

        // 
        // @JacksonXmlProperty(localName = "DANTAI_KANA")
        // private String dantaiNameKana;dantai_name_kana

        // 
        // @JacksonXmlProperty(localName = "JIM_ADR")
        // private String jimushoJusho;jimusho_jusho

        // 
        // @JacksonXmlProperty(localName = "JIM_APA_ADR")
        // private String jimushoJushoTatemono;jimusho_jusho_tatemono

        // 
        // @JacksonXmlProperty(localName = "DAI_NM1")
        // private String daihyoushaNameLast;daihyousha_name_last

        // 
        // @JacksonXmlProperty(localName = "DAI_NM2")
        // private String daihyoushaNameFirst;daihyousha_name_first

        // 
        // @JacksonXmlProperty(localName = "KAI_NM1")
        // private String kaikeiSekinnshaNameLast;kaikei_sekinnsha_name_last

        // 
        // @JacksonXmlProperty(localName = "KAI_NM2")
        // private String kaikeiSekinnshaNameFirst;kaikei_sekinnsha_name_first

        // 
        // @JacksonXmlProperty(localName = "TANTOU1_NM1")
        // private String jimuTantousha1NameLast;jimu_tantousha1_name_last

        // 
        // @JacksonXmlProperty(localName = "TANTOU1_NM2")
        // private String jimuTantousha1NameFirst;jimu_tantousha1_name_first

        // 
        // @JacksonXmlProperty(localName = "TANTOU1_TEL")
        // private String jimuTantousha1Tel;jimu_tantousha1_tel

        // 
        // @JacksonXmlProperty(localName = "TANTOU2_NM1")
        // private String jimuTantousha2NameLast;jimu_tantousha2_name_last

        // 
        // @JacksonXmlProperty(localName = "TANTOU2_NM2")
        // private String jimuTantousha2NameFirst;jimu_tantousha2_name_first

        // 
        // @JacksonXmlProperty(localName = "TANTOU2_TEL")
        // private String jimuTantousha2Tel;jimu_tantousha2_tel

        // 
        // @JacksonXmlProperty(localName = "TANTOU3_NM1")
        // private String jimuTantousha3NameLast;jimu_tantousha3_name_last

        // 
        // @JacksonXmlProperty(localName = "TANTOU3_NM2")
        // private String jimuTantousha3NameFirst;jimu_tantousha3_name_first

        // 
        // @JacksonXmlProperty(localName = "TANTOU3_TEL")
        // private String jimuTantousha3Tel;jimu_tantousha3_tel

        // 
        // @JacksonXmlProperty(localName = "DANTAI_KBN")
        // private String dantaiKbn;dantai_kbn

        // 
        // @JacksonXmlProperty(localName = "KATU_KUKI")
        // private Integer katsudouKuikiKbn;katsudou_kuiki_kbn

        // 
        // @JacksonXmlProperty(localName = "SIKIN_UMU")
        // private Integer umuShikinKanrenDantai;umu_shikin_kanren_dantai

        // 
        // @JacksonXmlProperty(localName = "KOSYOKU_NM")
        // private String koushokuName;koushoku_name

        // 
        // @JacksonXmlProperty(localName = "KOSYOKU_KBN")
        // private String koushokuGenKouho;koushoku_gen_kouho

        // 
        // @JacksonXmlProperty(localName = "SIKIN_TODOKE_NM1")
        // private String shikinDaihyouName1;shikin_daihyou_name1

        // 
        // @JacksonXmlProperty(localName = "SIKIN_TODOKE_NM2")
        // private String shikinDaihyouName2;shikin_daihyou_name2

        // 
        // @JacksonXmlProperty(localName = "SIKIN_KIKAN1")
        // private String kanriDantaiPeriodStart;kanri_dantai_period_start

        // 
        // @JacksonXmlProperty(localName = "SIKIN_KIKAN2")
        // private String kanriDantaiPeriodEnd;kanri_dantai_period_end

        // 
        // @JacksonXmlProperty(localName = "SIKIN_KIKAN_FUKUSU")
        // private String kanriDantaiPeriodRest;kanri_dantai_period_rest

        // 
        // @JacksonXmlProperty(localName = "GIIN_DANTAI_KBN")
        // private Integer kokkaiGiinDantaiKbn;kokkai_giin_dantai_kbn

        // 
        // @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_NM_1")
        // private String kokkaiGiin1NameLast;kokkai_giin1_name_last

        // 
        // @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_NM_2")
        // private String kokkaiGiin1NameFirst;kokkai_giin1_name_first

        // 
        // @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_NM")
        // private String kokkaiGiin1ShuuSan;kokkai_giin1_shuu_san

        // 
        // @JacksonXmlProperty(localName = "GIIN1_KOSYOKU_KBN")
        // private String kokkaiGiin1GenKouho;kokkai_giin1_gen_kouho

        // 
        // @JacksonXmlProperty(localName = "GIIN_KIKAN1")
        // private String
        // 

        // 
        // @JacksonXmlProperty(localName = "GIIN_KIKAN2")
        // private String
        // 

        // 
        // @JacksonXmlProperty(localName = "GIIN_KIKAN_FUKUSU")
        // private String
        // 

        // 
        // @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_NM_1")
        // private String kokkaiGiin2NameLast;kokkai_giin2_name_last

        // 
        // @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_NM_2")
        // private String kokkaiGiin2NameFirst;kokkai_giin2_name_first

        // 
        // @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_NM")
        // private String kokkaiGiin2ShuuSan;kokkai_giin2\shuu_san

        // 
        // @JacksonXmlProperty(localName = "GIIN2_KOSYOKU_KBN")
        // private String kokkaiGiin2GenKouho;kokkai_giin2_gen_kouho

        // 
        // @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_NM_1")
        // private String kokkaiGiin3NameLast;kokkai_giin3_name_last

        // 
        // @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_NM_2")
        // private String kokkaiGiin3NameFirst;kokkai_giin3_name_first

        // 
        // @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_NM")
        // private String kokkaiGiin3ShuuSan;kokkai_giin3_shuu_san

        // 
        // @JacksonXmlProperty(localName = "GIIN3_KOSYOKU_KBN")
        // private String kokkaiGiin3GenKouho;kokkai_giin3_gen_kouho


        // 
        // @JacksonXmlProperty(localName = "RYOUSYU_UMU")
        // private Integer flgReciptCopy;flg_recipt_copy

        // 
        // @JacksonXmlProperty(localName = "KANSA_IKEN_UMU")
        // private Integer flgKansaIkensho;flg_kansa_ikensho

        // 
        // @JacksonXmlProperty(localName = "KANSA_HOUKOKU_UMU")
        // private Integer flgSeijishikinHohkokusho;flg_seijishikin_hohkokusho

        // 
        // @JacksonXmlProperty(localName = "SENSEI_DT")
        // private String dateOath;date_oath

        // 
        // @JacksonXmlProperty(localName = "DANTAI_NM")
        // private String dantaiName;dantai_name

        // 
        // @JacksonXmlProperty(localName = "KAI_NM1")
        // private String kaikeiSekininshaNameLast;kaikei_sekininsha_nameLast

        // 
        // @JacksonXmlProperty(localName = "KAI_NM2")
        // private String kaikeiSekininshaNameFirst;kaikei_sekininsha_name_first

        // 
        // @JacksonXmlProperty(localName = "DAI_NM1")
        // private String daihyoushaNameLast;daihyousha_name_last

        // 
        // @JacksonXmlProperty(localName = "DAI_NM2")
        // private String daihyoushaNameFirst = "";daihyousha_name_first

        
        SetTableDataHistoryUtil.practice(checkPrivilegeDto, balancesheetEntity, DataHistoryStatusConstants.INSERT);

        //同一識別コードの設定
        Long code = 1L;
        Optional<OfferingBalancesheet0701And0720Surface2023Entity> optional = offeringBalancesheet0701And0720Surface2023Repository.findFirstByOrderByOfferingBalancesheet0701And0720SurfaceCodeDesc();
        if(!optional.isEmpty()) {
            code = code + optional.get().getOfferingBalancesheet0701And0720SurfaceCode();
        }
        balancesheetEntity.setOfferingBalancesheet0701And0720SurfaceCode(code);
        
        // ドキュメント属性
        BeanUtils.copyProperties(documentPropertyDto, balancesheetEntity);
        
        offeringBalancesheet0701And0720Surface2023Repository.save(balancesheetEntity);
        
        return code;
    }

}
