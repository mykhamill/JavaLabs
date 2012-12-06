//Imports for swing and awt classes
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

//Class inherits from JPanel
public class FillGamePanel extends JPanel {
    //Class attribute declarations
    private FillGame fg;
    private int counter;
    
    //Declaration and initialisation using literal value
    private Color[] boardColors = {Color.RED, Color.GREEN, Color.BLUE,
                                   Color.CYAN, Color.YELLOW, Color.MAGENTA};
    
    //Base constructor
    public FillGamePanel() {
        fg = new FillGame(); //Initialising class attribute
        this.setPreferredSize(new Dimension(350, 350)); //Setting the preferred size of the panel (Might not be adhered to when resized)
        counter = 0; //Set counter
    }
    
    //Overriding parent method
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Call parent method
        this.setBackground(Color.WHITE); //Set background to white
        
        //Set local variables
        int mapSize = fg.getMapSize();
        int height = this.getHeight();
        int width = this.getWidth();
        int value = 0;
        
        for (int y = 0; y < mapSize; y++) { //Loop over rows of the map
            for (int x = 0; x < mapSize; x++) { //Loop over columns of the current row
                value = fg.getMapAt(x, y); //Get the value of the current location on the map
                g.setColor(boardColors[value]); //Set the color based on the value of the location
                
                //Draw a rectangle on the board using the currently set color
                g.fillRect(x * (width / mapSize), y * (height / mapSize), width / mapSize, height / mapSize);
            }
        }
    }
    
    //Method to capture the value of a button press
    public void updateBoard(int value) {
        boolean completed = fg.updateMap(value); //Captures if the map is completed
        this.updateUI(); //Updates the panel graphics
        //If the map is completed give the user the option to have another go
        if (completed) {
            int answer = JOptionPane.showOptionDialog(null, "Well done! Would you like to reset the game?",
                                        "Completed!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (answer == JOptionPane.YES_OPTION) {
                fg.resetGame();
                this.updateUI();
            }
        }
        counter++; //Increments the turn counter
    }
    
    //Get the number of turns counted
    public int getCounter() {
        return counter;
    }
    
    //Gets the number of colors/values used on the map
    public int getNumberOfColors() {
        return fg.getNumberOfColors();
    }
    
    //Gets all the possible colors that can be used on the board
    public Color[] getColors() {
        return boardColors;
    }
}
