public class PersonRelationsModel {

    private  String firstPersonName;
    private String secondPersonName;
    private String typeRelationship;

    public PersonRelationsModel(String firstPersonName, String secondPersonName, String typeRelationship) {
        this.firstPersonName = firstPersonName;
        this.secondPersonName = secondPersonName;
        this.typeRelationship = typeRelationship;

    }

    public String getFirstPersonName() {
        return firstPersonName;
    }

    public String getSecondPersonName() {
        return secondPersonName;
    }

    public String getTypeRelationship() {
        return typeRelationship;
    }


}
