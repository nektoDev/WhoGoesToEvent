package whogoestoevent

class VkUser {
    String id;
    String firstName;
    String lastName;
    String photo200;
    String city;
    Byte sex;
    Byte age;
    Date birthdate;

    static constraints = {
    }

    boolean compareWithFilter(Filter filter) {
        Boolean result = true;
        if (filter.sex != null) {
            result = filter.sex?.equals(this.sex) && result;
        }

        if (filter.age != null) {
            result = filter.age?.equals(this.age) && result;
        }

        return result;
    }
}
