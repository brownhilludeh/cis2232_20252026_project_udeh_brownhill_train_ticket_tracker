package ca.hccis.t3.entity;

public class TrainAPI {
    private String aimedDepartureTime;
    private String originName;
    private String destinationName;
    private String platform;
    private String trainUid;

    public String getAimedDepartureTime() {
        return aimedDepartureTime;
    }

    public void setAimedDepartureTime(String aimedDepartureTime) {
        this.aimedDepartureTime = aimedDepartureTime;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTrainUid() {
        return trainUid;
    }

    public void setTrainUid(String trainUid) {
        this.trainUid = trainUid;
    }
}
