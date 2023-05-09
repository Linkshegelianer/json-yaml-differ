package hexlet.code;

public enum ParamStatus {

    ADDED("added"),
    DELETED("deleted"),
    UPDATED("updated"),
    UNMODIFIED("unmodified");

    private final String name;

    ParamStatus(String statusName) {
        this.name = statusName;
    }

    public String getStatus() {
        return name;
    }

    public static ParamStatus getByStatus(String name) {
        return ParamStatus.valueOf(name.toUpperCase());
    }
}
