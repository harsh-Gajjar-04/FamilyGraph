import java.util.ArrayList;

public class PersonRelations {
    private PersonRelationsModel relationModel;
    private final String constantRelationType = "FAMILY";
    private ArrayList<PersonDetailsModel> personDetailList;
    private ArrayList<RelationshipsDetailModel> relationshipsDetailList;
    private ArrayList<PersonRelationsModel> personRelationshipsList = new ArrayList<>();

//   Arraylist for extended family
    private ArrayList<String> familyMembersNameList =new ArrayList<>();

    public PersonRelations(ArrayList<PersonDetailsModel> personDetailsList, ArrayList<RelationshipsDetailModel> relationshipsDetailsList) {
        this.personDetailList = personDetailsList;
        this.relationshipsDetailList = relationshipsDetailsList;
        mapRelationships();
    }//getting the data from both files for finding relation.

     ArrayList<PersonRelationsModel> getRelationships(){
        return personRelationshipsList;
     }

     private void mapRelationships(){
        for (RelationshipsDetailModel checkRelations : relationshipsDetailList) {
            mapRelationship(checkRelations.getFirstPersonEmailId(), checkRelations.getSecondPersonEmailId(), checkRelations.getTypeRelationship());
        }
    }

    private void mapRelationship(String firstPersonEmailId , String secondPersonEmailId, String typeRelationship){

        PersonDetailsModel personDetails;
        String person1Name = " ",person2Name = " ";

        for (PersonDetailsModel personDetailsModel : personDetailList) {
            personDetails = personDetailsModel;
            // find first person's name from given emailId:
            if (personDetails.getPersonEmailId().equals(firstPersonEmailId)) {
                person1Name = personDetails.getPersonName();
            }
            //find second person's name from given emailId:
            else if (secondPersonEmailId.equals(personDetails.getPersonEmailId())) {
                person2Name = personDetails.getPersonName();
            }
        }
        relationModel =new PersonRelationsModel(person1Name,person2Name,typeRelationship);
        personRelationshipsList.add(relationModel);
    }

    int getAllRelations(String personName){
        int totalRelations = 0;
        for (PersonRelationsModel relationsShipWith : personRelationshipsList) {
            if (personName.equals(relationsShipWith.getFirstPersonName()) || personName.equals(relationsShipWith.getSecondPersonName())) {
                totalRelations++;
            }
        }
        return totalRelations;
    }

    int getAllFamilyMembers(String memberName){
        String tempFamilyMemberName;
        int totalExtendedMembers = 0;
        int AllMembers = 0;
        familyMembersNameList.add(memberName);
        ArrayList<FamilyMembersModel> tempFamilyMembersList = new ArrayList<>();

        // find the total family members from given memberName and store in list
        for (PersonRelationsModel memberRelation : personRelationshipsList) {

            if ((memberName.equals(memberRelation.getFirstPersonName()) || memberName.equals(memberRelation.getSecondPersonName())) &&
                    memberRelation.getTypeRelationship().equals(constantRelationType)) {
                FamilyMembersModel tempFamilyMembers;
                if ((memberName.equals(memberRelation.getFirstPersonName()))) {
                    tempFamilyMembers = new FamilyMembersModel(memberRelation.getSecondPersonName());
                } else {
                    tempFamilyMembers = new FamilyMembersModel(memberRelation.getFirstPersonName());
                }
                tempFamilyMembersList.add(tempFamilyMembers);
                tempFamilyMemberName = tempFamilyMembers.getMemberName();
                familyMembersNameList.add(tempFamilyMemberName);
            }
        }
        //extended Family member's : relation = "FAMILY"
        for (FamilyMembersModel tempFamilyMembers : tempFamilyMembersList) {
            //extendedRelations from familyMembers.
            totalExtendedMembers = extendedMemberRelations(tempFamilyMembers.getMemberName());
            if(AllMembers < totalExtendedMembers){
                AllMembers = totalExtendedMembers;
            }
        }
        return familyMembersNameList.size() + AllMembers;
    }
    private int extendedMemberRelations(String extendedMemberName){
        int extendedMembers = 0;

        ArrayList<FamilyMembersModel> extendedMemberFamilyList =new ArrayList<>();
        //find total family members from extendedMember
        for (PersonRelationsModel extendedPersonRelations : personRelationshipsList) {

            if ((extendedMemberName.equals(extendedPersonRelations.getFirstPersonName()) || extendedMemberName.equals(extendedPersonRelations.getSecondPersonName())) &&
                    extendedPersonRelations.getTypeRelationship().equals(constantRelationType)) {
                FamilyMembersModel extendedTempFamilyMember;
                if ((extendedPersonRelations.getFirstPersonName().equals(extendedMemberName))) {
                    extendedTempFamilyMember = new FamilyMembersModel(extendedPersonRelations.getSecondPersonName());
                } else {
                    extendedTempFamilyMember = new FamilyMembersModel(extendedPersonRelations.getFirstPersonName());
                }
                extendedMemberFamilyList.add(extendedTempFamilyMember);
            }
        }
//       compare Member that exist in Family Members list:

        for (FamilyMembersModel extendedMemberFamilyPerson : extendedMemberFamilyList) {

            String checkMember = extendedMemberFamilyPerson.getMemberName();

            if(!familyMembersNameList.contains(checkMember)){
                extendedMembers++;
            }
        }
        return extendedMembers;
    }
}

