package org.acme;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class MontlyReport {

    @Inject
    JasperReportGeneratorService jasperReportGeneratorService;
    @Scheduled(cron = "0 0 0 1 * ?")
    public void get() throws Exception {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = "MontlyReport-" + currentDate.format(formatter) + ".pdf";
        String outputFileName = "externalResource/result/" + fileName;
        String jasperReportPath = "externalResource/templateJasper/tomat.jrxml";
        jasperReportGeneratorService.generatePdfReport(jasperReportPath,outputFileName);
        System.out.println("MontlyReport.pdf created");
    }
}
