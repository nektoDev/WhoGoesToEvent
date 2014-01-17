package whogoestoevent

import grails.transaction.Transactional

import javax.annotation.PostConstruct

@Transactional
class CityService {

    static scope = 'singleton'

    def vkApiService;

    HashMap<Integer, String> cityMap;

    @PostConstruct
    def init() {
        this.cityMap = new HashMap<>();
    }

    String getCityById(String id) {
        return cityMap.get(id as Integer)
    }

    def addCities(String cids) {
        def response = vkApiService.placesGetCities(cids);
        for (def resp : response) {
            cityMap[resp.cid as Integer] = resp.name as String;
        }
    }
}
