package org.acme;

import io.quarkus.scheduler.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Random;

public class WeeklyReportData {
    @Scheduled(cron = "0 0 0 ? * SUN")
    @Transactional
    public void createWeeklyReport(){
        Random random = new Random();
        Integer value = random.nextInt(5000,12000);
        Report report = new Report();
        report.komoditas = "Tomat";
        report.harga     = value;
        report.total     = 500;
        report.tanggalPanen = LocalDateTime.now();
        report.created_at   = LocalDateTime.now();
        report.updated_at   = LocalDateTime.now();
        report.persistAndFlush();
        System.out.println("weekly report data created");
    }
}
