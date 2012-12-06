//Imports for swing and awt classes
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiFillGame {
    //Class attribute declarations
    private JFrame frame;
    private FillGamePanel board;
    private JPanel buttonPanel;
    private JLabel counterLabel;
    
    //Main method to get it running
    public static void main(String[] args) {
         GuiFillGame guiFillGame = new GuiFillGame();
    }
    
    //Base constructor
    public GuiFillGame() {
        frame = new JFrame("Fill Game"); //Initialises the frame with a title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the default close operation
        frame.setSize(new Dimension(400, 400)); //Sets the size of the frame
        frame.setLocation(400, 200); //Sets the location of the frame
        frame.setLayout(new BorderLayout()); //Sets the layout manager for the frame
        board = new FillGamePanel(); //Initialises the board
        board.setSize(new Dimension(350, 350)); //Sets the board size (not the same thing as map size)
        counterLabel = new JLabel(board.getCounter() + " turns so far"); //Initialises the counterlabel and set the text
        buttonPanel = new JPanel(); //Initialises the buttonPanel the default layout manager is the FlowLayout 
        Color[] colors = board.getColors(); //Gets the all possible colors the board could use
        int numOfColors = board.getNumberOfColors(); //Gets the number of colors used on the board
        
        // Loop to create a button for each color used
        for (int i = 0; i < numOfColors; i++) {
            //Using an subclass of JButton to add a value and color
            ColorButton button = new ColorButton(i, colors[i]);
            //Add an action listener to the button for when it is pressed
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ColorButton button = (ColorButton)e.getSource(); //Cast the source to a ColorButton object
                    FillGamePanel b = GuiFillGame.this.board; //Get the board object
                    JLabel l = GuiFillGame.this.counterLabel; //Get the counterLabel object
                    b.updateBoard(button.getValue()); //Update the board using the value of the button associated with the color
                    l.setText(b.getCounter() + " turns so far"); //Update the text of the counterLabel to show how many turns have been used
                }
                
            });
            
            buttonPanel.add(button); //Add the button to the buttonPanel
        }
        frame.add(counterLabel, BorderLayout.NORTH); //Add the counterLabel to the top (NORTH) of the frame
        frame.add(board, BorderLayout.CENTER); //Add the board to the the center (CENTER) of the frame
        frame.add(buttonPanel, BorderLayout.SOUTH); //Add the buttonPanel to the bottom (SOUTH) of the frame
        frame.pack(); //Pack the frame ready for displaying
        frame.setVisible(true); //Set to visible to show the frame.
    }
}

//Private class to extend JButton
class ColorButton extends JButton {
    //Additional attribute declarations
    private int value;
    private Color color;
    
    //Two parameter constructor
    public ColorButton(int val, Color col) {
        super(); //Using base constructor of the parent class
        this.setPreferredSize(new Dimension(35, 35)); //Set the preferred size (Might not be adhered to in resizing)
        
        //Set attributes with arguments passed
        value = val; 
        color = col;
    }
    
    //Override the parent class method
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Call the parent method
        g.setColor(color); //Sets the color of the pen for the next paint operation
        g.fillRect(5, 5, 25, 25); //Draws a rectangle on the button of the color/value it represents
    }
    
    //returns the value of the button
    public int getValue() {
        return value; 
    }
}
