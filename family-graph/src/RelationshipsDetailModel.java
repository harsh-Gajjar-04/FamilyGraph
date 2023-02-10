public class RelationshipsDetailModel {
    private final String firstPersonEmailId;
    private final String typeRelationship;
    private final String secondPersonEmailId;

    public RelationshipsDetailModel(String firstPersonEmailId, String typeRelationship, String secondPersonEmailId) {
        this.firstPersonEmailId = firstPersonEmailId;
        this.typeRelationship = typeRelationship;
        this.secondPersonEmailId = secondPersonEmailId;
    }

    public String getFirstPersonEmailId() {
        return firstPersonEmailId;
    }

    public String getTypeRelationship() {
        return typeRelationship;
    }

    public String getSecondPersonEmailId() {
        return secondPersonEmailId;
    }


}
