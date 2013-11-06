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
        Filter filter = new Filter(params?.filter);
        List<VkUser> vkUserList = searchService.searchUsers(filter);

        render(template:"layouts/vkUser", collection: vkUserList, var: 'user') ;
    }

    def search2() {
        Filter filter = new Filter(params?.filter);
        List<VkUser> vkUserList = searchService.searchUsers(filter);

        render(template:"layouts/vkUser", collection: vkUserList, var: 'user') ;
    }
}
