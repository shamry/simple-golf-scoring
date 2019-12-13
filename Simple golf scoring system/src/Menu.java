import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Menu {
    private static HashMap<String, Integer> player = new HashMap<String,Integer>(); // initializing HashMap
    private static HashMap<String, Integer> Recover = new HashMap<String,Integer>(); // Recovering HashMap
    private static HashMap<String, Integer> Undo = new HashMap<String,Integer>(); // Undo Entry
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to Springfield Golf Club.");
        int option=0;
        do {
            try {


                    System.out.println("Select Option:");
                    System.out.println("1)	Enter Scores");
                    System.out.println("2)	Find Golfer");
                    System.out.println("3)	Display Scoreboard");
                    System.out.println("4)	Exit Program");
                    System.out.println("5)  Edit an entered player");
                    Scanner sc = new Scanner(System.in);
                    option = sc.nextInt();      // Getting input from user for case selection


                    switch (option) {
                        case 1:
                            enterScore();       // Method for getting score
                            break;
                        case 2:
                            findGolfer();       // Method for finding golfer name in HashMap
                            break;
                        case 3:
                            displayScoreboard();  // Method for displaying entries
                            break;
                        case 4:
                            System.exit(0); // quit
                            break;
                        case 5:
                            EditPlayer(); // Method for Removing and Recovering player
                            break;

                        default:
                            System.out.println("Invalid Option!!! Reenter...");
                    }



            } catch (Exception e) {         // Exception for invalid string input
                System.out.println("Invalid");

            }
        }while(option!=4);
    }


    private static void displayScoreboard() {
        Map<String,Integer> sorted=player;      //Sorting values , keys for the ascending order
        sorted = player
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        System.out.println("Name" +"\t\t"+ " Score: ");
        for (String i : sorted.keySet()) {

            System.out.println(i+"\t\t"+sorted.get(i));
        }


    }
    private static void findGolfer() {
        System.out.println("Enter player name to search");
        String find = sc.next();
        if(player.containsKey(find)){                       // searching input player name in HashMap
            System.out.println(find+" "+player.get(find));      // Display player name and score
        }else{
            System.out.println("Not found");
        }

    }

    private static void enterScore() {
        int score = -5; // initializing score to the value to -5

        String name = null;
        String name1 = null;
        int count = -5;
        do {
            try {
                System.out.println("How many golfers in the group :");
                Scanner sc1 = new Scanner(System.in);
                count = sc1.nextInt();
                if (count < 0) {
                    System.out.println("Invalid amount");
                }

            } catch (Exception e) {
                System.out.println("Invalid input!! Enter a valid number of players ");
            }

        } while (count == -5 || count < 0);

        for (int k = 0; k < count; k++) {        // creating a loop for repetition
            do {
                System.out.println("Enter Player Name ");
                name = sc.next();

                for (String detail : player.keySet()) {
                    if (name.equals(detail)) {              // Checking entered name is already recorded or not
                        System.out.println("Name already exists");
                        System.out.println("1 - New player");
                        System.out.println("2 - continue with the score");
                        int choice = 0;
                        do {
                            try {
                                Scanner sc2 = new Scanner(System.in);
                                choice = sc2.nextInt();
                                if (choice == 1) {          // adding new player to the list
                                    System.out.print("Input player name:");
                                    name = sc.next();
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input");
                            }
                        } while (choice != 1 && choice != 2);  // Breaking the loop

                    }
                }
                name1 = name;
            } while (name1 == null);
            int countX=1;
            do {
                try {
                    System.out.println("Enter Player Score");
                    Scanner Enter_score = new Scanner(System.in);
                    score = Enter_score.nextInt();
                    if (score < 18 || score > 108) {
                        System.out.println("Re-enter!!");
                    }else{countX=0;}
                } catch (Exception e) {             // Exception for string value inputs
                    System.out.println("Invalid : Re-Enter");
                }




            } while (score < 18 || score > 108 || countX==1); // Breaking the loop
            player.put(name1, score); // Putting name and score records into HashMap
        }

    }


    private static void EditPlayer(){       //Method for removing and recovering players
        int select =0;
        Scanner sc_3 = new Scanner(System.in);
        try{
            System.out.println("1- Remove an entered player");  //options to select
            System.out.println("2- Recover a  player");
            select=sc_3.nextInt();
            if(select==1) {
                System.out.println("Enter player name to remove");

                String remove_player = sc_3.next();
                Recover.put(remove_player,player.get(remove_player));      //Moving entered player to "Recover" HashMap
                player.remove(remove_player);
            }else if(select==2) {
                try{System.out.println("Recover ");
                System.out.println("Enter player name to Recover");
                String Recover_player=sc_3.next();
                player.put(Recover_player,Recover.get(Recover_player));     // Moving entered player to "player" HashMap
                Recover.remove(Recover_player);}
                catch(Exception e){
                    System.out.println("Error Recovery!! ");
                }
            }
        }catch(Exception e){
            System.out.println("Invalid");
        }
    }

}
