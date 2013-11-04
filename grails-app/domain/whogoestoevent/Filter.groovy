package whogoestoevent

class Filter {
    String eventURL = "http://vk.com/farbar";

    static constraints = {
    }

    public String getEventId() {
        return eventURL?.substring(eventURL?.lastIndexOf("/") + 1)
    }
}
