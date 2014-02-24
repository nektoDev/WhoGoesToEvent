package whogoestoevent
import groovyx.net.http.HTTPBuilder
/**
 * Created with IntelliJ IDEA.
 * User: Viacheslav
 * Date: 04.11.13
 * Time: 6:18
 * To change this template use File | Settings | File Templates.
 */


class VkApiService {
    def cityService;

    def http = new HTTPBuilder("https://api.vk.com/method/")

    public static final String ACCESS_TOKEN="d8ab8a0a1dd52e2b2047d2c114708bd7dff1805f636e6e8a52124ea0e386bc010977d4f20c8cf97c041f2"

    List<VkUser> getGroupsMembers(String id, Integer count = 1000, Integer offset = 0) {
        http.get(path: 'execute.groupsMembers', query: [group_id: id, count: count, offset: offset, access_token: ACCESS_TOKEN])
                { resp, json ->
                    def users = json.response;
                    List<VkUser> result = new ArrayList<>();
                    for (def userJSON : users) {
                        result.add(convertVkUser(userJSON));
                    }
                    return result;
                } as List<VkUser>
    }


    def groupsGetMembers(String id, Integer count = 1000, Integer offset = 0) {
        http.get(path: 'groups.getMembers', query: [group_id: id, count: count, offset: 0, sort: 'time_asc'])
                { resp, json ->
                    return json.response;
                }
    }

    def placesGetCities(String cids) {
        http.get(path: 'places.getCityById', query: [cids: cids])
                { resp, json ->
                    return json.response;
                }
    }

    private VkUser convertVkUser(userJSON) {
        VkUser vkUser = new VkUser();

        vkUser.id = userJSON.uid;
        vkUser.lastName = userJSON.last_name;
        vkUser.firstName = userJSON.first_name;
        vkUser.sex = userJSON.sex;
        vkUser.photo200 = userJSON.photo_200;
        vkUser.cityId = userJSON.city;
        vkUser.birthdate = convertDate(userJSON.bdate);
        vkUser.age = vkUser.birthdate?.age;
        vkUser.relation = userJSON.relation;
        return vkUser;
    }

    private Date convertDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        } else {
            if (date.matches($/\d{1,2}\.\d{1,2}\.\d{4}/$)) {
               return new Date().parse("d.M.yyyy", date);
            }
        }
    }
}
