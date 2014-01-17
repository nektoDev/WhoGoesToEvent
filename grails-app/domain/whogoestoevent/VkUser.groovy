package whogoestoevent

class VkUser {
    String id;
    String firstName;
    String lastName;
    String photo200;
    String cityId;
    Byte sex;
    Byte age;
    Date birthdate;
    String city;

    static constraints = {
    }

    boolean compareWithFilter(Filter filter) {
        Boolean result = true;
        if (filter.sex != null) {
            result = filter.sex?.equals(this.sex) && result;
        }
        if (filter.showWithoutAge != null) {
            if (this.age == null || this.age == 0) {
                result = filter.showWithoutAge && result;
            } else if (filter.age != null) {
                result = filter.getAgeFrom() <= this.age && filter.getAgeTo() >= this.age && result;
            }
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
                ", cityId='" + cityId + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthdate=" + birthdate +
                ", city='" + city + '\'' +
                '}';
    }
}
