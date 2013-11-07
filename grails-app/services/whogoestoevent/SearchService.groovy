package whogoestoevent

class SearchService implements Serializable{
    static scope = 'session'

    def vkApiService;

    List<VkUser> vkUserList;

    Filter filter;

    Integer offset = 0;

    List<VkUser> searchUsers(Filter filter) {
        this.filter = filter;
        vkUserList = groupsGetAllMembers(filter.eventID);
        return getNextPage();
    }

    List<VkUser> groupsGetAllMembers(String eventID) {
        List<VkUser> vkUserList = new ArrayList<VkUser>();

        def response = vkApiService.groupsGetMembers(eventID, 1);
        Integer count = response.count as Integer;

        def i;
        while (vkUserList.size() < count) {
            i = count - vkUserList.size();
            Integer loadingCount = i > 1000 ? 1000 : i;
            response = vkApiService.groupsGetMembers(eventID, loadingCount, vkUserList.size());

            response.users.each() {
                vkUserList.add(new VkUser(it.toString()));
            }

            Thread.sleep(350);
        }
        return vkUserList
    }

    public List<VkUser> getUsersAt(Range range) {
        List<VkUser> result = new ArrayList<VkUser>();

        vkUserList.getAt(range).each() {
            it = vkApiService.usersGet(it.getId(), it);
            result.add(it);
            Thread.sleep(350);
        };

        return result;
    }

    public List<VkUser> getNextPage(Integer count = 15) {
        List<VkUser> result = new ArrayList<VkUser>();

        for (Integer i = this.offset; i< vkUserList.size()-1; i++) {
            print i;
            vkUserList.set(i, vkApiService.usersGet(vkUserList.get(i).getId(), vkUserList.get(i)));
            print(vkUserList.get(i).compareWithFilter(this.filter))
            if (vkUserList.get(i).compareWithFilter(this.filter)) {
                result.add(vkUserList.get(i))
            }
            print(result.size() >= count)
            if (result.size() >= count) {
                offset += result.size()
                return result;
            }

            Thread.sleep(350);
        }
        vkUserList.getAt([this.offset..-1]).each() {
            
        }
        offset += result.size()
        return result;
    }
}
