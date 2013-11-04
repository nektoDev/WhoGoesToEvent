package whogoestoevent

import grails.transaction.Transactional

@Transactional
class SearchService {

    def vkApiService;

    List<VkUser> searchUsers(Filter filter) {
        List<VkUser> vkUserList = new ArrayList<VkUser>();

        def response = vkApiService.groupsGetMembers(filter.getEventId());
        def count = response.count;
        def usersJSON = response.users;

        usersJSON.each() {
            Thread.sleep( 500);
            vkUserList.add(vkApiService.usersGet(it));
        };

        return vkUserList;
    }
}
