
package com.mygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Md. Talal Wasim
 * https://github.com/mdtalalwasim
 * Java SE | Java EE | 
 */
public class TicTacToe extends JFrame implements ActionListener{

    JLabel heading, clockLabel;
    Font font = new Font("",Font.BOLD,40);
    
    JPanel mainPanel;//place 9buttons into this panel
    
    /*for storeing 9Button.. we need an Array of Buttons
    1 | 2 | 3         o | x | x
    4 | 5 | 6  like   o | x | o  [Example Like This] and this is set on the top of JPanel(mainPanel)
    7 | 8 | 9         x | o | o
    */
    JButton []buttons = new JButton[9]; //9Button array
    
    
    //game instance variable
    
    /**default value of all button asumes  2
     * 0 1 2 3 4 5 6 7 8 (these are position of Buttons)=9 button...
     * 2 2 2 2 2 2 2 2 2 =9 button
     * 0th value = 2, 1th position button value =2, 2th=2 3th=2 4th=2 5th=2 6th=2 7th=2 8th=2. 
     * (default value of all buttons are 2(mean before someones press))
     * if all buttons value remain 2, means no one press any button.
     * if 0th position buttons value value change, mean someone is pressed 0th button.
   
    */
    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0; //by default '0' is player one.
    
    //winning position array...the value of below position of the player is winner...
    int winningPositions[][] = {             // winning logic like this
        {0, 1, 2},              //  0 1 2   (0-8) our tictactoe board element position
        {3, 4, 5},              //  3 4 5  and left side is the combinition for winner position    
        {6, 7, 8},              //  6 7 8
        {0, 3, 6},              // above is our tic tac toe position
        {1, 4, 7},              //
        {2, 5, 8},               //
        {0, 4, 8},              //
        {2, 4, 6}  
    };
    
    int winner = 2; //by default assigning 2, means no one is won.
    boolean gameOver = false; //by default false means game is still on going. not over.


    
    
    public TicTacToe() {//constructor
        System.out.println("Creating Instance of Game...");
        setTitle("Tic Tac Toe Game");
        //setSize(850,850);//Frame Size.
        setSize(700,700);//Frame Size.
        
        ImageIcon icon = new ImageIcon("src/img/icon.png");
        setIconImage(icon.getImage());//it needs ImageIcon object.
        
        createGUI();//GUI method call
        
        setLocationRelativeTo(null);//Game or frame body will appear in center position.
        setVisible(true);//frame visivility.
        
        //when we exit our frame, program will close also.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        
    }
    
    //Border Layout Set...
    
    private void createGUI(){
    
        //this.getContentPane().setBackground(Color.BLUE);
        this.getContentPane().setBackground(Color.ORANGE);
        
        this.setLayout(new BorderLayout());//Layout is BorderLayout
        
        //north heading...(BorderLayout Has 4Side->(EastSide, WestSide,NorthSide, SouthSide))
        //heading set to North Side
        heading = new JLabel("Tic Tac Toe");
        //JLabel j = new JLabel("Tic Tac Toe");
        heading.setFont(font);
        heading.setForeground(Color.black);
        
        heading.setIcon(new ImageIcon("src/img/icon22.png"));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(heading,BorderLayout.NORTH);//add to north section
        
        //creating obj of JLabel for Clock...
        clockLabel = new JLabel("Clock!");
        clockLabel.setFont(font);
        clockLabel.setForeground(Color.BLACK);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel,BorderLayout.SOUTH);
        
        //creating Thread for Clock!
        //using Anonymous Class for Overriding Thread Methods.
        Thread t = new Thread(){
            public void run(){
                try {
                     while (true) {//loop will run infinite...[for our Clock]
                         
                        String dateTime = new Date().toLocaleString();
                        
                        clockLabel.setText(dateTime);//set the Date and Time to our clockLabel.
                    
                        //thread sleep in 1sec.. again wakeup 
                        //because of Staying within WhileLoop to show the Date and Time.
                        Thread.sleep(1000);
                        }
                    
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
               
            }
        };
        t.start();//thread start.
        
        
        /////........Panel Section.......
        //creaing obj of JPanel() and assign it to mainPanel
        mainPanel = new JPanel(); 
        
        //mainPanel Layout is set to GridLayout (because we need row and colm for out button).
        mainPanel.setLayout(new GridLayout(3, 3));
        
        //each time each button will add to mainPanel[1to9 btn will add to mainPanel]
        for (int i = 1; i <=9; i++) {
            // ""-> no text in button..or dont pass any parameter through constructor of JButton()
            JButton btn = new JButton("");
            //btn.setIcon(new ImageIcon("src/img/o80.png"));
            
            //btn.setIcon(new ImageIcon("src/img/x80.png"));
            
            btn.setBackground(Color.blue);
            btn.setFont(font);
            mainPanel.add(btn);
            buttons[i-1] = btn;//store btn object into array buttons[] for further use.
            btn.addActionListener(this);//each time button[1-9button] is clicked actionPerformed() will invoked.
            
            //uniquely identified which button is pressed.
            //(i-1)means start with its name = 0 1 2 3 4 5 6 7 8 =total 9button //here 0-8 is unique name which is string.
            btn.setName((i-1)+"");//one way
            btn.setName(String.valueOf(i-1));//another way
            
        }//end loop
        
        //add mainPanel to our main Frame or TicTacToe Frame 
        this.add(mainPanel,BorderLayout.CENTER);
        
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this -> means both TicTacToe Frame and ActionListener 
       // System.out.println("Button Clicked!");
       
       //e.getSource() return the current object of button , which button is pressed!
       //then we received the return object of JButton by currenPressButton reference and 
       //need to TypeCast to JButton Type like below.
       JButton currentPressButton=(JButton) e.getSource();
       
       
       //getName() return String..so, we need to receive it throug String variable
       String nameStr = currentPressButton.getName();
       
       //Player Turn Identifying...Someone is started playing...
       
       int name = Integer.parseInt(nameStr.trim());//convert nameStr to int//trim() omit all space and others from nameStr
       
        
        //before checking anything we have to check is game is over or not?
        //if over then...
        if (gameOver==true) {
            JOptionPane.showMessageDialog(this, "Game Already Over!");
            return; //means no need to execute the below code ...
        }
       //if game is already over, the below code will not execute...
       
       
       // if Game is not over then execute below code.
       //find the gameCaneces...
       if (gameChances[name]==2) {//if value is 2{here {gaemChances[] = {2,2,2,2,2,2,2,2,2},.....assuming...is default }
            if (activePlayer==1) {
                currentPressButton.setIcon(new ImageIcon("src/img/x80.png"));
                
                gameChances[name]= activePlayer;
                
                //change the active player(turn change to other player)
                activePlayer=0;
            }
            else{
                currentPressButton.setIcon(new ImageIcon("src/img/o80.png"));
                
                gameChances[name] = activePlayer;
                activePlayer=1;
            }
            
            
        }
        //if value is not set to 2
        else{
            JOptionPane.showMessageDialog(this, "Position already occupied!");
        }
        
        
        
        //Find the Winner....
        for (int[] temp : winningPositions) {
            
            if ((gameChances[temp[0]]==gameChances[temp[1]]) &&(gameChances[temp[1]]==gameChances[temp[2]]) && (gameChances[temp[2]]!=2) ) {
                
                winner = gameChances[temp[0]];//get the winner
                //
                gameOver=true; //after getting the winner we need to change the value of gameOver is true; 
                
                JOptionPane.showMessageDialog(null, "Player "+winner+" has won the game");
                
                int i = JOptionPane.showConfirmDialog(this, "do you want to play more??");
                
                //if user press yes then value of yes is =0, No = 1, Cancel==2
                if (i==0) {
                    //mean user press Yes...
                    this.setVisible(false); //existing game board invisible...
                    new TicTacToe(); //new instance of TicTacToe is call... new board is come..game starts again from beginning
                    
                
                }
                else if (i==1) { //if user press No
                    //no need to do anything just close the program
                    System.exit(0);
                        
                }
                else{//if User press cancel button...
                //no need to do anything
                }
                System.out.println(i);
                break;
            }
            
        }
        
        //............
        //draw logic.......
        int counter = 0;
        
        //we fetch single vaule each time from gameChances[] array. and check the value is still 2 avaliable in gameChances[]?
        //if we get 2, means at least 1Chance is still ramining...to play the game... 
        //means someone has still chance to play its turn...
        for (int x : gameChances) {
            if (x==2) {
                counter++;
                break;
            }
            
        }
        if (counter==0 && gameOver==false) {//if counter value is 0, means there is no (no 2 in gameChances[]means no chance left) chance left...
            //means both player complete their trun. no turn is left
            //and still game is not over! then exucute below line of if block
            JOptionPane.showMessageDialog(null, "It's Draw");
            int i = JOptionPane.showConfirmDialog(this, "Play more??");
            if (i==0) {//press yes
                this.setVisible(false);
                new TicTacToe();
            }
            else if (i==1) {//press No
                System.exit(0);
            }
            else{//press Cancel 
            //user does not want to do anything...so left else block blank.
            }
            
            gameOver= true; //if game is Draw...then game is Over...
            
        }
        
        
        //................
       
       
       
    }
    
}
