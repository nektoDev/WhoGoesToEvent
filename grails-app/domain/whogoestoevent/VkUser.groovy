package whogoestoevent

class VkUser {
    String id;
    String firstName;
    String lastName;
    String photo200;
    String city;
    Byte sex;

    static constraints = {
    }

    boolean compareWithFilter(Filter filter) {
        if (filter.sex == null) {
            return true;
        } else {
            return filter.sex?.equals(this.sex)
        }
    }
}
