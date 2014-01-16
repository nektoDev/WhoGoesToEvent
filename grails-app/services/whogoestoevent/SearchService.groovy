package whogoestoevent

class SearchService implements Serializable{
    static scope = 'session'

    def vkApiService;

    List<VkUser> vkUserList;
    List<VkUser> filteredList;

    Filter filter;

    Integer offset = 0;

    def initUserList(Filter filter) {
        vkUserList = new ArrayList<>();
        filteredList = new ArrayList<>();
        offset = 0;

        def response = vkApiService.groupsGetMembers(filter.eventID, 1);

        Integer count = response.count as Integer;
        def i;
        while (vkUserList.size() < count) {
            i = count - vkUserList.size();
            Integer loadingCount = i > 1000 ? 1000 : i;

            List<VkUser> members = vkApiService.getGroupsMembers(filter.eventID, loadingCount, vkUserList.size())
            for (VkUser member : members) {
                if (member.compareWithFilter(filter)) {
                    filteredList.add(member);
                }
            }
            vkUserList.addAll(members);
            Thread.sleep(350);
        }
    }

    public List<VkUser> getNextPage(Integer count = 15) {
        List<VkUser> result = new ArrayList<VkUser>();
        def to = offset + count;
        if (filteredList.size() > offset) {
            to = filteredList.size() < to ? filteredList.size() : to
            result.addAll(filteredList[offset..to-1])
        };
        offset += result.size();
        return result;
    }
}
