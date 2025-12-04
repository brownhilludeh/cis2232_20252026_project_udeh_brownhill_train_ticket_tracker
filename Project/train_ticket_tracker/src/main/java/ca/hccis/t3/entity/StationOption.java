package ca.hccis.t3.entity;

public class StationOption {

    /**
     * The code of the station
     * 
     * @since 2025-12-04
     * @author Brownhill Udeh
     */
    private String code;
    private String name;

    public StationOption() {
    }

    public StationOption(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
