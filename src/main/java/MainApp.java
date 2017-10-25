
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
import org.codehaus.jackson.map.ObjectMapper;


public class MainApp {

    private static List<Regions> arrayOfThem = new ArrayList<Regions>();
    private static WritableWorkbook myFirstWbook = null;
    private static long nanoToSeconds = 1000000000;

    public static void main(String [] args){

        String fileName = "test";
//        ReadJson("test.json");
        for(int n = 0; n < 10000; n++){
            if(new File(fileName+n+".json").exists())
                ReadJson(fileName+n+".json");
        }

        double slowest = 0, fastest = 1000000000 , totalrequests = 0;
        double AveTimeForReq = 0, AverReqPerSec = 0;
        //Do some data manipulation
        for(Regions each : arrayOfThem){
                each.setRegion("Overall");
                each.setSlowest((each.getSlowest()/nanoToSeconds));
                each.setFastest((each.getFastest()/nanoToSeconds));
//                each.setAveReqPerSec((each.getAveReqPerSec()));
        }

        printToExcel();
    }

    private static void printToExcel(){

        String fileName = System.getProperty("user.dir")+"/"+"ResultsGoad1.xls";
        System.out.println(fileName);




        //Create work book
        try {
            myFirstWbook = Workbook.createWorkbook(new File(fileName));
            WritableSheet excelSheet = myFirstWbook.createSheet("Results", 0);
            addHeaders();
            NumberFormat decimalNo = new NumberFormat("#.0000");
            WritableCellFormat numberFormat = new WritableCellFormat(decimalNo);

            for(int x = 1; x < arrayOfThem.size();x++) {

                Label labelTotReqs = new Label(0,x, ""+arrayOfThem.get(x).getTotalReqs());
                Label totTimeOut = new Label(1,x,""+arrayOfThem.get(x).getTotalTimedOut());
                Label totConnError = new Label(2,x,""+arrayOfThem.get(x).getTotalCnnectionError());
                Label aveTimeToFirst = new Label(3,x,""+arrayOfThem.get(x).getAveTimeToFirst());
                Label totBytesRead = new Label(4,x,""+arrayOfThem.get(x).getTotBytesRead());
                Label statuses = new Label(5,x,""+arrayOfThem.get(x).getStatuses());
                Label avgTimeForReq = new Label(6,x,""+(arrayOfThem.get(x).getAveTimeForReq()));
                Number avgReqPerSec = new Number(7,x,arrayOfThem.get(x).getAveReqPerSec(),numberFormat);
//                Label avgReqPerSec = new Label (7,x,""+arrayOfThem.get(x).getAveReqPerSec());
                Label timeDelta = new Label(8,x,""+arrayOfThem.get(x).getTimeDelta());
                Label avgKBytesPerSec = new Label(9,x,""+arrayOfThem.get(x).getAveKBytesPerSec());
                Number slowest = new Number(10,x,arrayOfThem.get(x).getSlowest(),numberFormat);
//                Label slowest = new Label(10,x,""+(arrayOfThem.get(x).getSlowest()));
                Number fastest = new Number(11,x,arrayOfThem.get(x).getFastest(),numberFormat);
//                Label fastest = new Label(11,x,""+(arrayOfThem.get(x).getFastest()));
                Label region = new Label(12,x,arrayOfThem.get(x).getRegion());
                Label fatalError = new Label(13,x,""+arrayOfThem.get(x).getFatalError());
                Label finished = new Label(14,x,""+arrayOfThem.get(x).isFinished());


                excelSheet.addCell(labelTotReqs);
                excelSheet.addCell(totTimeOut);
                excelSheet.addCell(totConnError);
                excelSheet.addCell(aveTimeToFirst);
                excelSheet.addCell(totBytesRead);
                excelSheet.addCell(statuses);
                excelSheet.addCell(avgTimeForReq);
                excelSheet.addCell(avgReqPerSec);
                excelSheet.addCell(timeDelta);
                excelSheet.addCell(avgKBytesPerSec);
                excelSheet.addCell(slowest);
                excelSheet.addCell(fastest);
                excelSheet.addCell(region);
                excelSheet.addCell(fatalError);
                excelSheet.addCell(finished);


            }

            myFirstWbook.write();


        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }//catch
            }//if not null


        }//finally


    }

    private static void addHeaders(){
        String [] arrayHeaders = {
                "Total Requests","Total Timed Out","Total Connection Errors", "Average Time To First","Total Bytes Read",
                "Statuses","Average Time For Requests", "Average Request per second", "Time delta","Average Kilobytes Per Sec",
                "Slowest Request","Fastest Request","Region","Fatal Error","Finished"
        };

        //Write Header
        WritableCellFormat header = new WritableCellFormat();
        WritableSheet excelSheet = myFirstWbook.getSheet(0);

        try{

            header.setBackground(Colour.DARK_YELLOW);

            for(int x = 0; x < arrayHeaders.length; x ++) {

                Label label = new Label(x,0,arrayHeaders[x]);
                excelSheet.addCell(label);
                WritableCell c = excelSheet.getWritableCell(x,0);
                c.setCellFormat(header);
            }

        }catch(Exception e){
            System.out.println("Bad error in Header.... "  + e.getMessage());

        }


    }


    private static void ReadJson(String s){

        ObjectMapper mapper = new ObjectMapper();

        try {
            Map map = mapper.readValue(new File(s), Map.class);
            System.out.println(map.keySet());
            //We are only looking at the overall result
            map.remove("eu-west-1");
            map.remove("ap-northeast-1");
            map.remove("us-east-1");
            for(int x =0; x < map.size(); x ++){
                arrayOfThem.add(mapper.convertValue(map.values().toArray()[x],Regions.class));
            }

            for(Regions regions : arrayOfThem)
                System.out.println(regions);




        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }



    /*
    "TotalReqs": 166,
    "TotalTimedOut": 0,
    "TotalConnectionError": 0,
    "AveTimeToFirst": 546951901,
    "TotBytesRead": 10292,
    "Statuses": {
      "200": 166
    },
    "AveTimeForReq": 546985985,
    "AveReqPerSec": 9.383508938525111,
    "TimeDelta": 17690610313,
    "AveKBytesPerSec": 581.7775541885568,
    "Slowest": 1685238874,
    "Fastest": 120380008,
    "Region": "eu-west-1",
    "FatalError": "",
    "Finished": true
     */




}
