import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File peopleCsvFilePath = new File("C:/Users/Harsh Gajjar/Documents/Training/familygraph_exercise/familygraph_exercise/src/test/resources/people.csv");
        File relationshipCsvFilePath = new File("C:\\Users\\Harsh Gajjar\\Documents\\Training\\familygraph_exercise\\familygraph_exercise\\src\\test\\resources\\relationships.csv");

        ArrayList<PersonDetailsModel> personDetailList = new ArrayList<>();
        ArrayList<RelationshipsDetailModel> relationShipsDetailList = new ArrayList<>();
        ArrayList<PersonRelationsModel> personRelationsList;

        PersonDetailsModel personDetailsModel;//use in Data representation for personDetailList.
        RelationshipsDetailModel relationsDetailsModel;//use in Data representation for relationsDetailList.
        PersonRelations personRelations;//find the personRelations ,perform work on .:
        PersonRelationsModel personRelationsModel;//use in Data representation for personRelations.

        CsvParser csvParser = new CsvParser();//parser that convert csv into ArrayList.

        ArrayList<String[]> peopleList = csvParser.scanData(peopleCsvFilePath);
        ArrayList<String[]> relationsList = csvParser.scanData(relationshipCsvFilePath);

//        get ArrayList from people.csv file
        for (String[] strings : peopleList) {
            String personName = strings[0],
                    personEmail = strings[1];
            personDetailsModel = new PersonDetailsModel(personName, personEmail);
            personDetailList.add(personDetailsModel);
        }

//        get ArrayList from relationShips.csv file
        for (String[] strings : relationsList){
            String emailIdPerson1 = strings[0],
                    relationType =strings[1],
                    emailIdPerson2 = strings[2];
            relationsDetailsModel = new RelationshipsDetailModel(emailIdPerson1, relationType, emailIdPerson2);
            relationShipsDetailList.add(relationsDetailsModel);
        }

//        get mapped relationShipList ,mapping from personDetails and PersonRelations.
        personRelations = new PersonRelations(personDetailList, relationShipsDetailList);//pass the Lists that from {people.Csv,relationShips.Csv}.
        personRelationsList = personRelations.getRelationships();//get mapped list .

//        Exercise 1:
        System.out.println("\nExercise 1:represents the people in the file and their relationships with each other.");
        for (PersonRelationsModel relationsModel : personRelationsList) {
            personRelationsModel = relationsModel;
            System.out.println(personRelationsModel.getFirstPersonName() + " and " + personRelationsModel.getSecondPersonName() + " are " + personRelationsModel.getTypeRelationship());
        }

//         Exercise 2:
        System.out.println("\nExercise 2:loaded the expected number of people.");
        for (int i = 0; i < personDetailList.size(); i++) {
            personDetailsModel = personDetailList.get(i);
            System.out.println(personDetailsModel.getPersonName() + " = " + (i + 1));
        }

//        Exercise 3:
        System.out.println("\nExercise 3:given people have the correct expected number of connections to other people");
        String[] followingPersons = {"Bob", "Jenny", "Nigel", "Alan"};
        int totalRelations;
        for (String followingPerson : followingPersons) {
            totalRelations = personRelations.getAllRelations(followingPerson);
            System.out.println(followingPerson + " (" + totalRelations + " relationships)");
        }

//        Exercise 4:
        System.out.println("\nExercise 4: ");
        Scanner findName = new Scanner(System.in);
        System.out.print("Enter Name : ");
        String memberName = findName.nextLine();
        int totalMembers = personRelations.getAllFamilyMembers(memberName);
        System.out.println(memberName + " (" + totalMembers + " family members)");
    }
}

