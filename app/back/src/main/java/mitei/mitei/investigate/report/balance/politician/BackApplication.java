package mitei.mitei.investigate.report.balance.politician;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 全体起動
 */
@SpringBootApplication
@EnableAsync
public class BackApplication { // NOPMD
    
    /**
     * 全体起動
     *
     * @param args 引数
     */
    public static void main(final String[] args)throws Exception {
        
        SpringApplication.run(BackApplication.class, args);
    }

}
