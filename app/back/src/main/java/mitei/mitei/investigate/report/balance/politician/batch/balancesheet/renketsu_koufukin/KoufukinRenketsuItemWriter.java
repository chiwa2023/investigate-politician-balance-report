package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;

/**
 * 交付金連結ワークテーブルItemWriter
 */
@Component
public class KoufukinRenketsuItemWriter extends JpaItemWriter<WkTblRenketsuKoufukinEntity> {

    /** 交付金連結Repository */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public KoufukinRenketsuItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblRenketsuKoufukinEntity> items) {
        wkTblRenketsuKoufukinRepository.saveAllAndFlush(items);
    }
}
