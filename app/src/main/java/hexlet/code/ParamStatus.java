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

    public String getName() {
        return name;
    }

    public static ParamStatus getByName(String name) {
        return ParamStatus.valueOf(name.toUpperCase());
    }
}
