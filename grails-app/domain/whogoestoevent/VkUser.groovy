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
            result = filter.getAgeFrom() <= this.age && filter.getAgeTo() >= this.age && result;
        }

        return result;
    }


    @Override
    public String toString() {
        return "VkUser{" +
                "version=" + version +
                ", id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo200='" + photo200 + '\'' +
                ", city='" + city + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthdate=" + birthdate +
                '}';
    }
}
