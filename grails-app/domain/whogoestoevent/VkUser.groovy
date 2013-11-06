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

    VkUser(String id) {
        this.id = id;
    }

    VkUser() {
    }

    @Override
    public java.lang.String toString() {
        return "VkUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo200='" + photo200 + '\'' +
                ", city='" + city + '\'' +
                ", sex=" + sex +
                '}';
    }
}
