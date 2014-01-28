package whogoestoevent

import grails.transaction.Transactional

import javax.annotation.PostConstruct

@Transactional
class RelationService {
    static scope = "singleton"

    private List<Relation> relations

    @PostConstruct
    def init() {
        relations = new ArrayList<>()
        relations.add(new Relation(1, "не женат/не замужем"));
        relations.add(new Relation(2, "есть друг/есть подруга"));
        relations.add(new Relation(3, "помолвлен/помолвлена"));
        relations.add(new Relation(4, "женат/замужем"));
        relations.add(new Relation(5, "всё сложно"));
        relations.add(new Relation(6, "в активном поиске"));
        relations.add(new Relation(7, "влюблён/влюблена"));
    }

    public Relation getRelationById(String id) {
        for (Relation rel in relations) {
            if (rel?.getCode()?.toString()?.equalsIgnoreCase(id)) {

                return rel
            }
        }

        return new Relation(id as Integer, "Неизвестно")
    }
}
