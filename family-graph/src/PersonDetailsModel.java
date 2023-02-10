public class PersonDetailsModel {
    private String personName;
    private String personEmailId;


    public PersonDetailsModel(String personName, String personEmailId) {
        this.personName = personName;
        this.personEmailId = personEmailId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmailId() {
        return personEmailId;
    }
}
