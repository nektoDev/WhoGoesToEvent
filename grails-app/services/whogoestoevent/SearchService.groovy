package whogoestoevent

import grails.transaction.Transactional

@Transactional
class SearchService {

    def vkApiService;
    private List<VkUser> vkUserList;

    List<VkUser> searchUsers(Filter filter) {
        List<VkUser> result = new ArrayList<VkUser>();

        vkUserList = new ArrayList<VkUser>();
        vkUserList = groupsGetAllMembers(filter.eventID)

        return getUsersAt([0..14]);
    }

    List<VkUser> groupsGetAllMembers(String eventID) {
        List<VkUser> vkUserList = new ArrayList<VkUser>();

        def response = vkApiService.groupsGetMembers(eventID, 1);
        print(response)
        Integer count = response.count as Integer;
        def usersJSON = response.users;
        def i = count - vkUserList.size();
        while (vkUserList.size() < count) {
            i = count - vkUserList.size();
            Integer loadingCount = i > 1000 ? 1000 : i;
            response = vkApiService.groupsGetMembers(eventID, loadingCount, vkUserList.size());

            response.users.each() {
                vkUserList.add(new VkUser(it.toString()));
            }

            Thread.sleep(350);
        }
        print(vkUserList.size())
        return vkUserList
    }

    List<VkUser> getUsersAt(Range range) {
        List<VkUser> result = new ArrayList<VkUser>();

        vkUserList[0..14].each() {
            it = vkApiService.usersGet(it.getId(), it);
            result.add(it);
            Thread.sleep(350);
        };

        return result;
    }
}
