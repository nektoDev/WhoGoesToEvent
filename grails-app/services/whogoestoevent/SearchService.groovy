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

        def response = vkApiService.groupsGetMembers(filter.eventID, 1);
        Integer count = response.count as Integer;
        def i;
        while (vkUserList.size() <= count) {
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

        result.addAll(filteredList[offset..offset+count-1]);
        println result;
        offset += result.size();
        return result;
    }
}
