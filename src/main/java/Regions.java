import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonSetter;

import java.util.Map;

public class Regions {

    private int TotalReqs;
    private int TotalTimedOut;
    private int TotalCnnectionError;
    private long AveTimeToFirst;
    private int TotBytesRead;
    private Map<String,Integer> Statuses;
    private long AveTimeForReq;
    private float AveReqPerSec;
    private long TimeDelta;
    private float AveKBytesPerSec;
    private double Slowest;
    private double Fastest;
    private String Region;
    private String FatalError;
    private boolean Finished;


    @Override
    public String toString() {
        return "Regions{" +
                "TotalReqs=" + TotalReqs +
                ", TotalTimedOut=" + TotalTimedOut +
                ", TotalCnnectionError=" + TotalCnnectionError +
                ", AveTimeToFirst=" + AveTimeToFirst +
                ", TotBytesRead=" + TotBytesRead +
                ", Statuses=" + Statuses +
                ", AveTimeForReq=" + AveTimeForReq +
                ", AveReqPerSec=" + AveReqPerSec +
                ", TimeDelta=" + TimeDelta +
                ", AveKBytesPerSec=" + AveKBytesPerSec +
                ", Slowest=" + Slowest +
                ", Fastest=" + Fastest +
                ", Region='" + Region + '\'' +
                ", FatalError='" + FatalError + '\'' +
                ", Finished=" + Finished +
                '}';
    }



    public int getTotalReqs() {
        return TotalReqs;
    }

    @JsonSetter("TotalReqs")
    public void setTotalReqs(int totalReqs) {
        TotalReqs = totalReqs;
    }

    public int getTotalTimedOut() {
        return TotalTimedOut;
    }

    @JsonSetter("TotalTimedOut")
    public void setTotalTimedOut(int totalTimedOut) {
        TotalTimedOut = totalTimedOut;
    }

    public int getTotalCnnectionError() {
        return TotalCnnectionError;
    }

    @JsonSetter("TotalConnectionError")
    public void setTotalCnnectionError(int totalCnnectionError) {
        TotalCnnectionError = totalCnnectionError;
    }

    public long getAveTimeToFirst() {
        return AveTimeToFirst;
    }
    @JsonSetter("AveTimeToFirst")
    public void setAveTimeToFirst(long aveTimeToFirst) {
        AveTimeToFirst = aveTimeToFirst;
    }

    public int getTotBytesRead() {
        return TotBytesRead;
    }

    @JsonSetter("TotBytesRead")
    public void setTotBytesRead(int totBytesRead) {
        TotBytesRead = totBytesRead;
    }


    public Map<String, Integer> getStatuses() {
        return Statuses;
    }

    @JsonSetter("Statuses")
    public void setStatuses(Map<String, Integer> statuses) {
        Statuses = statuses;
    }

    public long getAveTimeForReq() {
        return AveTimeForReq;
    }

    @JsonSetter("AveTimeForReq")
    public void setAveTimeForReq(long aveTimeForReq) {
        AveTimeForReq = aveTimeForReq;
    }

    public float getAveReqPerSec() {
        return AveReqPerSec;
    }

    @JsonSetter("AveReqPerSec")
    public void setAveReqPerSec(float aveReqPerSec) {
        AveReqPerSec = aveReqPerSec;
    }

    public long getTimeDelta() {
        return TimeDelta;
    }

    @JsonSetter("TimeDelta")
    public void setTimeDelta(long timeDelta) {
        TimeDelta = timeDelta;
    }

    public float getAveKBytesPerSec() {
        return AveKBytesPerSec;
    }

    @JsonSetter("AveKBytesPerSec")
    public void setAveKBytesPerSec(float aveKBytesPerSec) {
        AveKBytesPerSec = aveKBytesPerSec;
    }

    public double getSlowest() {
        return Slowest;
    }

    @JsonSetter("Slowest")
    public void setSlowest(double slowest) {
        Slowest = slowest;
    }

    public double getFastest() {
        return Fastest;
    }

    @JsonSetter("Fastest")
    public void setFastest(double fastest) {
        Fastest = fastest;
    }

    public String getRegion() {
        return Region;
    }

    @JsonSetter("Region")
    public void setRegion(String region) {
        Region = region;
    }

    public String getFatalError() {
        return FatalError;
    }

    @JsonSetter("FatalError")
    public void setFatalError(String fatalError) {
        FatalError = fatalError;
    }

    public boolean isFinished() {
        return Finished;
    }

    @JsonSetter("Finished")
    public void setFinished(boolean finished) {
        Finished = finished;
    }
}
