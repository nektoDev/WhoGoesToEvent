import grails.converters.JSON
import groovyx.net.http.HTTPBuilder
import whogoestoevent.VkUser

/**
 * Created with IntelliJ IDEA.
 * User: Viacheslav
 * Date: 04.11.13
 * Time: 6:18
 * To change this template use File | Settings | File Templates.
 */


class VkApiService {
    def http = new HTTPBuilder("https://api.vk.com/method/")

    VkUser usersGet(String id) {

        http.get(path: 'users.get', query: [user_ids: id, v: 5.2, fields:"photo_200,city,sex"])
                { resp, json ->
                    def userJSON = json.response[0];
                    VkUser vkUser = createVkUser(userJSON)
                    return vkUser;
                }
    }

    VkUser usersGet(Integer id) {
        usersGet(id.toString());
    }

    def groupsGetMembers(String id) {
        http.get(path: 'groups.getMembers', query: [group_id: id, count: 10])
                { resp, json ->
                    return json.response;
                }
    }

    private VkUser createVkUser(userJSON) {
        VkUser vkUser = new VkUser();
        vkUser.id = userJSON.id;
        vkUser.lastName = userJSON.last_name;
        vkUser.firstName = userJSON.first_name;
        vkUser.sex = userJSON.sex;
        vkUser.photo200 = userJSON.photo_200;
        vkUser.city = userJSON.city;

        return vkUser;
    }


}
