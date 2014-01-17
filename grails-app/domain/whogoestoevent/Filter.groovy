package whogoestoevent

class Filter {
    String eventID = "farbar";
    Byte sex;
    String age;
    Boolean showWithoutAge;

    static constraints = {
    }

    public Byte getAgeFrom() {
        if (age != null && age.contains(",")) {
            String from = age.split(",")[0]
            if (from.isNumber()) {
                return from as Byte;
            }
        }
        return Byte.MIN_VALUE;
    }

    public Byte getAgeTo() {
        if (age != null && age.contains(",")) {
            String to = age.split(",")[1]
            if (to.isNumber()) {
                return to as Byte;
            }
        }
        return Byte.MAX_VALUE;
    }
}
