/**
 * Creating the game "stuck in the mud"
 * @author Sami Bouakline
 * @since 11/19/2025
 * @throws nothing
*/

//import libraries
import java.awt.*;  
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

//Stuck in the Mud
public class SITM implements ActionListener
{
    //activating random and creating global variables
    Random random = new Random();
    JFrame frame;
    JButton[] dice = new JButton[5];
    JButton roll,reset;
    JLabel update,score;
    int points = 0;
    int diceValue = 0;
    int stuck = 0;
    
    public SITM ()
    {
        //setting board for dice
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(5, 10, 10, 10));
        for (int i=0;i<dice.length;i++)
        {
            dice[i] = new JButton();
            dice[i].setBackground(Color.blue);
            board.add(dice[i]);
        }
        //creating buttons to roll dice or reset
        JPanel gameFunction = new JPanel(new BorderLayout());
        gameFunction.setBackground(Color.red);
        roll = new JButton("Roll Dice");
        roll.addActionListener(this);
        roll.setActionCommand("rollDice");
        gameFunction.add(roll,BorderLayout.WEST);
        
        //button to reset game
        reset = new JButton("New Game");
        reset.addActionListener(this);
        reset.setActionCommand("reset");
        gameFunction.add(reset,BorderLayout.EAST);
        
        //displaying score and updating status
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(Color.red);
        score = new JLabel("Score: ");
        score.setFont(new Font("Arial",Font.BOLD,30));
        status.add(score,BorderLayout.CENTER);
        
        //instruction
        update = new JLabel("Click 'Roll Dice' to start!");
        update.setFont(new Font("Arial",Font.PLAIN,15));
        status.add(update,BorderLayout.SOUTH);
        
        
        //Create and set up the window.
        JFrame.setDefaultLookAndFeelDecorated (true); 
        frame = new JFrame ("SITM"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize (400, 400);
        frame.setLayout(new BorderLayout(20,20));
        frame.add(board,BorderLayout.CENTER);
        frame.add(gameFunction,BorderLayout.NORTH);
        frame.add(status,BorderLayout.SOUTH);
        frame.setVisible (true);
        
    }//end constructor
    
    //delete if not needed
    public void actionPerformed (ActionEvent e)
    {
        //rolling dice
        if (e.getActionCommand().equals("rollDice"))
        {
            rollDice();
            score.setText("Score: "+String.valueOf(points));
        }
        else if (e.getActionCommand().equals("reset"))
        {
            reset();
        }
        
    }
    
    /**
     * subroutine to roll dice until all are stuck
     * @param diceValue - rolls random value 1-6
     * @return nothing
     * @throws nothing
    */
    public void rollDice()
    {
        for (int i=0;i<dice.length;i++)
        {
            diceValue  = random.nextInt(6)+1;
            
            //only adding dice values that are not 2 or 5
            if (dice[i].isEnabled())
            {
                dice[i].setIcon(createImageIcon("/images/dice-six-faces-"+diceValue+".png"));
                if (diceValue == 2 || diceValue == 5)
                {
                    dice[i].setEnabled(false);
                    stuck++;
                }
                else
                {
                    points += diceValue;
                }
            }
            
        }
        if (stuck == 5)
        {
            //end screen
            JOptionPane.showMessageDialog(frame,"Game over, your total score was "+String.valueOf(points));
            roll.setEnabled(false);
        }
    }
    
    /**
     * subroutine to reset game
     * @param points - resets score to 0
     *        stuck - resets # of stuck dice to 0
     * @return nothing
     * @throws nothing
    */
    public void reset()
    {
        score.setText("Score: 0");
        update.setText("Click 'Roll Dice' to start!");

        points = 0;
        stuck = 0;
        
        for (int i=0;i<dice.length;i++)
        {
            dice[i].setIcon(null);
            dice[i].setEnabled(true);
        }
        roll.setEnabled(true);
    }
    
    //delete if not needed
    static ImageIcon createImageIcon (String path) {
        //change = Starter.class   to the match your .java fle name
        java.net.URL imgURL = SITM.class.getResource (path); 
        if (imgURL != null)
            return new ImageIcon (imgURL); 
        else
            return null;
    }//end ImageIcon
    
    public static void main (String args[])
    {
        new SITM (); 
    }
}

