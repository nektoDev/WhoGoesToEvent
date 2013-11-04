package whogoestoevent

import grails.converters.JSON
import net.sf.json.JSONObject

class SearchController {
    def vkApiService;
    def searchService;

    def index() {
        [user: vkApiService.usersGet("3388135")]

    }

    def search() {
        List<VkUser> vkUserList = new ArrayList<VkUser>();

        def response = vkApiService.groupsGetMembers("farbar");
        def count = response.count;
        def usersJSON = response.users;
        usersJSON.each() {
            Thread.sleep(500);
            vkUserList.add(vkApiService.usersGet(it));
        };
        render(template:"layouts/vkUser", collection: vkUserList, var: 'user') ;
    }

    def search2() {
        Filter filter = new Filter(params?.filter);
        List<VkUser> vkUserList = searchService.searchUsers(filter);

        render(template:"layouts/vkUser", collection: vkUserList, var: 'user') ;
    }
}
