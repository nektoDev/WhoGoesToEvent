package whogoestoevent

class Relation {

    private int code;
    private String description;

    static constraints = {
    }

    Relation(int code, String description) {
        this.code = code
        this.description = description
    }

    int getCode() {
        return code
    }

    void setCode(int code) {
        this.code = code
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}
