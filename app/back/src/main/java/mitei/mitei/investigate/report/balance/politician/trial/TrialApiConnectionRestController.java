package mitei.mitei.investigate.report.balance.politician.trial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;

/**
 * API接続確認Controller
 */
@RestController
public class TrialApiConnectionRestController {

    /** リスト生成 */
    @Autowired
    private MakeTrialTestDataLogic makeTrialTestDataLogic;

    //@Autowired
    //private InsertPartyUsageShito0801And0807Logic insertPartyUsageShito0801And0807Logic;

    //@Autowired
    //private DateConvertUtil dateConvertUtil;

    /**
     * ダミーのオプション項目リストを返却
     *
     * @return オプション項目リスト
     */
    @GetMapping("/trial-api-rest")
    @Transactional
    public List<SelectOptionDto> trialApiConnection() {

        //System.out.println("実行前");

        //AllShitoBook allShitoBook = new AllShitoBook();

        // ドキュメント情報
        //BookHeadDto head = new BookHeadDto();
        //head.setVersion("20191220");
        //head.setApliName("使途等報告書作成ソフト");
        //head.setFlgApli("0");
        //head.setFlgHonbu("0");
        //allShitoBook.setBookHeadDto(head);

        // データ有無テキスト
        //SitoUmuFlgDto sitoUmuFlgDto = new SitoUmuFlgDto();
        //sitoUmuFlgDto.setUmuStatusText("111100000000001100");
        //allShitoBook.setSitoUmuFlgDto(sitoUmuFlgDto);

        // 会計基準
        //KaikeiShishutuKijunDto kijunDto = new KaikeiShishutuKijunDto();
        //KaikeiKijunKingakuDto kijunKingakuDto = new KaikeiKijunKingakuDto();
        //kijunKingakuDto.setAmount(50000L);
        //kijunDto.setKaikeiKijunKingakuDto(kijunKingakuDto);
        //allShitoBook.setKaikeiShishutuKijunDto(kijunDto);

        // 様式8その1
        //Shito0801Dto shito0 = new Shito0801Dto();

        //shito0.getSheet0801Dto().setNendo(2022);
        //shito0.getSheet0801Dto().setPartyName("政党名称");
        //shito0.getSheet0801Dto().setPartyNameKana("政党名称かな");
        //shito0.getSheet0801Dto().setOfficeAddress("主たる事務所住所");
        //shito0.getSheet0801Dto().setDelegateName("代表者姓名");
        //shito0.getSheet0801Dto().setAccountManagerName("会計責任者姓名");
        //shito0.getSheet0801Dto().setWorker1Name("担当者1姓名");
        //shito0.getSheet0801Dto().setWorker1Tel("012-3456-xxxx");
        //shito0.getSheet0801Dto().setWorker2Name("担当者2姓名");
        //shito0.getSheet0801Dto().setWorker2Tel("098-7654-xxxx");
        //shito0.getSheet0801Dto().setShibuKbn(2);
        //shito0.getSheet0801Dto().setKaisanKbn(1);
        //shito0.getSheet0801Dto().setKaisanDate("R4/12/31");
        //shito0.getSheet0801Dto().setSeiriNo("445566");
        //shito0.getSheet0801Dto().setUketsukeNo("556677");
        //allShitoBook.setShito0801Dto(shito0);

        // 様式8その7
        //Shito0807Dto shito0807Dto = new Shito0807Dto();

        //Sheet0807Dto sheet = new Sheet0807Dto();
        //sheet.setCopyRecipt(0);
        //sheet.setAuditOption(1);
        //sheet.setAuditReport(0);
        //sheet.setShibuDocument(0);
        //sheet.setGoverningDocument(0);
        //sheet.setFlgConfirm(1);
        //sheet.setAccuralDate("R5/11/30");

        //shito0807Dto.setSheet0807Dto(sheet);
        //allShitoBook.setShito0807Dto(shito0807Dto);

        //PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();

        // 政治団体基礎情報
        //partyUsageDocumentPoliticalPropertyDto.setNendo(shito0.getSheet0801Dto().getNendo()); // 実際には表紙からコピー
        //partyUsageDocumentPoliticalPropertyDto
        //        .setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(sheet.getAccuralDate())); // 実際には宣誓書からコピー
        //partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationId(433L);
        //partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationCode(431);
        //partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        //partyUsageDocumentPoliticalPropertyDto.setDantaiName("ちゃらん団体");
        //partyUsageDocumentPoliticalPropertyDto.setDaihyouName("代表者 世間芸名");
        //partyUsageDocumentPoliticalPropertyDto.setRelationKbn(223);
        //partyUsageDocumentPoliticalPropertyDto.setRelationPersonIdDelegate(9898L);
        //partyUsageDocumentPoliticalPropertyDto.setRelationPersonCodeDelegate(9867);
        //partyUsageDocumentPoliticalPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        //CheckPrivilegeDto checkPrivilegeDto = new CheckPrivilegeDto();
        //final long ID = 123321L; // NOPMD
        //final int CODE = 987;
        //final String NAME = "ユーザ";

        //checkPrivilegeDto.setLoginUserId(ID);
        //checkPrivilegeDto.setLoginUserCode(CODE);
        //checkPrivilegeDto.setLoginUserName(NAME);

        //Long code = insertPartyUsageShito0801And0807Logic.practice(partyUsageDocumentPoliticalPropertyDto, allShitoBook,
        //        checkPrivilegeDto);

        //System.out.println("完了" + code);
        
        //System.out.println("完了");

        return makeTrialTestDataLogic.practice();
    }
}
