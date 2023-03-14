package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;

import javax.inject.Inject;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SendEmailReport {
    @Inject
    Mailer mailer;
    @Scheduled(cron = "0 10 0 1 * ?")
    public void sendEmailMontlyReport(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "MontlyReport-" + currentDate.format(formatter) + ".pdf";
        mailer.send(
                Mail.withText(
                        "f4j4rkokok@gmail.com",
                        "Laporan bulanan",
                        "Laporan panen tomat bulan"+LocalDate.now()+", detail laporan dapat dilihat di file lampiran"
                ).addAttachment(
                        "MontlyReport.PDF",
                        new File("externalResource/result/"+fileName),
                        "application/pdf"
                )
        );
        System.out.println("success send email");
    }
}
