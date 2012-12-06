import java.util.Scanner; //So that we can get keyboard input

public class PlayFillGame {
    //Single method
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); //Declare and initialise the keyboard variable
        FillGame fg = new FillGame(); //Declare and initialise the map
        
        //Declare other variables
        boolean completed; 
        int counter = 0;
        do {
            System.out.println(counter + " turns so far"); //Display message
            fg.displayMap(); //Display current map
            
            String s = keyboard.nextLine(); //Get user input
            if ("0123q".contains(s.subSequence(0, 1))){ //Check user input
                System.out.println(s.subSequence(0, 1)); //Show user input
                if (s.subSequence(0, 1).equals("q")) { //Quit if requested
                    System.out.println("Quitting");
                    System.exit(0);
                }
                completed = fg.updateMap(Integer.parseInt(s)); //Update map using user input
                counter++; //Increment turn counter
            } else { 
                //Alert that input is not valid
                System.out.println("Not valid");
                completed = false;
            }
        } while (!completed); //Loop exit condition
        fg.displayMap(); //Show completed map
        System.out.println("You completed it in " + counter + " turns!"); //Display completion message
    }
}
