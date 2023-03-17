package org.acme;

import io.vertx.core.json.JsonObject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

@Path("weekly-report")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeeklyReportDataResource {
//    @Scheduled(cron = "0 0 0 ? * SUN")
//    @Transactional
//    public void createWeeklyReport(){
//        Random random = new Random();
//        Integer value = random.nextInt(5000,12000);
//        Report report = new Report();
//        report.komoditas = "Tomat";
//        report.harga     = value;
//        report.total     = 500;
//        report.tanggalPanen = LocalDateTime.now();
//        report.created_at   = LocalDateTime.now();
//        report.updated_at   = LocalDateTime.now();
//        report.persistAndFlush();
//        System.out.println("weekly report data created");
//    }

    @GET
    public List<Report> getListReport(){
        return Report.listAll();
    }

    @POST
    @Transactional
    public Report addDataWeekly(JsonObject body){
        Report report = new Report();
        report.komoditas = body.getString("komoditas");
        report.total     = body.getInteger("total");
        report.harga     = body.getInteger("harga");
//        report.tanggalPanen = LocalDateTime.now();
//        report.created_at   = LocalDateTime.now();
        report.updated_at   = null;
        report.persistAndFlush();
        return report;
    }

    public Report getReportById(String id){
        return Report.find("id=?1",id).firstResult();
    }

    @PUT
    @Transactional
    @Path("{id}/update")
    public JsonObject updateDataWeekly(@PathParam("id") String id, JsonObject body){
        Report reportOld = getReportById(id);
        String komoditas = body.getString("komoditas");
        Integer total = body.getInteger("total");
        Integer harga     = body.getInteger("harga");
        LocalDateTime tanggalPanen = reportOld.tanggalPanen;
        LocalDateTime created_at   = reportOld.created_at;
        LocalDateTime updated_at   = LocalDateTime.now();
        System.out.println(reportOld.tanggalPanen);
        Report.update("id=?1,created_at=?2,harga=?3,komoditas=?4,tanggal_panen=?5,total=?6,updated_at=?7 WHERE id=?8",id,created_at,harga,komoditas,tanggalPanen,total,updated_at,id);
        return body;
    }

    @DELETE
    @Path("{id}/delete")
    @Transactional
    public String deleteById(@PathParam("id") String id){
        Report.deleteById(id);
        return "success";
    }
}
