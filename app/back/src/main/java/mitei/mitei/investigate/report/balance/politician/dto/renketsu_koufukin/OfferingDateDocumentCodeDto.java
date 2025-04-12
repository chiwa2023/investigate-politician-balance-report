package mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 文書コードと提出日格納Dto
 */
@Entity
public class OfferingDateDocumentCodeDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 提出日 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offering_date")
    private LocalDate offeringDate = INIT_LocalDate;

    /** 最大文書コード */
    @Column(name = "document_code")
    private Long documentCode = INIT_Long;

    /**
     * 提出日を取得する
     *
     * @return 提出日
     */
    public LocalDate getOfferingDate() {
        return offeringDate;
    }

    /**
     * 提出日を設定する
     *
     * @param offeringDate 提出日
     */
    public void setOfferingDate(final LocalDate offeringDate) {
        this.offeringDate = offeringDate;
    }

    /**
     * 最大文書コードを取得する
     *
     * @return 最大文書コード
     */
    public Long getDocumentCode() {
        return documentCode;
    }

    /**
     * 最大文書コードを設定する
     *
     * @param documentCode 最大文書コード
     */
    public void setDocumentCode(final Long documentCode) {
        this.documentCode = documentCode;
    }

}
