import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.applet.*;
    public class EspinalProj7 extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private int money;
    private int cX;
    private int cY;
    private long startTime,startTime2,startTime3,startTime4, currTime, currTime2, currTime3, currTime4;
    private int table1X, table1Y, table2X, table2Y, table3X, table3Y;
    private int table4X, table4Y, rW, rH;
    private int cookX, cookY, cookW, cookH;
    private boolean tableCollideCook;
    private boolean haveFood;
    
    private int customerX;
    private int customerY;
    private int diam;
    
    private AudioClip inGameMusic;
    
    //customer coordinates when at a table
    private int table1XCust;
    private int table1YCust;
    private int table2XCust;
    private int table2YCust;
    private int table3XCust;
    private int table3YCust;
    private int table4XCust;
    private int table4YCust;
    
    private boolean table1CustPres;
    private boolean table1Collide;
    private boolean table2Collide;
    private boolean table2CustPres;
    private boolean table3Collide;
    private boolean table3CustPres;
    private boolean table4Collide;
    private boolean table4CustPres;
    
    private boolean trash;
    private int trashX;
    private int trashY;
    
    private boolean rules;
    
    private int winVx;
    private int winVy;
    private int winX;
    private int winY;
    
    private boolean table1Pres,custPres;
    private boolean table2Pres;
    private boolean table3Pres;
    private boolean table4Pres;
    private boolean startup,youWin,pickUpCust;
    
    //wait time
    private long timeCust1Sits;
    private long timeCust2Sits;
    private long timeCust3Sits;
    private long timeCust4Sits;
    //Default Constructor
    public EspinalProj7()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        money = 150;
        cX = 500;
        cY = 300;
        rW = 50;
        rH = 100;
        table1X = 250;
        table1Y = 100;
        table2X = 250;
        table2Y = 300;
        table3X = 750;
        table3Y = 100;
        table4X = 750;
        table4Y = 300;
        diam = 30;
        
        rules = false;
        
        trash = false;
        trashX = 25;
        trashY = 400;
        
        winVx = 5;
        winVy = 5;
        winX = (WIDTH/2)-150;
        winY = HEIGHT/2;
        cookX= 0;
        cookY= 300;
        cookW = 100;
        cookH = 50;
        tableCollideCook = false;
        haveFood = false;
        
        
        inGameMusic = Applet.newAudioClip(this.getClass().getResource("Spider-Man_2_-_The_Game_Pizza_Theme-czTksCF6X8Y.wav"));
        table1XCust = 215;
        table1YCust = 130;
        table2XCust = 215;
        table2YCust = 335;
        table3XCust = 800;
        table3YCust = 130;
        table4XCust = 800;
        table4YCust = 330;
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Project 7"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

         /*If after you finish everything, you can declare your buttons or other things
          *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
          */

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
        startup = false;
        custPres = false;
        customerX = WIDTH/2;
        customerY = 0;
        pickUpCust = false;
        startTime = System.currentTimeMillis();
        startTime2 = System.currentTimeMillis();
        startTime3 = System.currentTimeMillis();
        startTime4 = System.currentTimeMillis();
        currTime = System.currentTimeMillis();
        currTime2 = System.currentTimeMillis();
        currTime3 = System.currentTimeMillis();
        currTime4 = System.currentTimeMillis();
        table1Collide = false;
        table1CustPres = false;
        table2Collide = false;
        table2CustPres=false;
        table3Collide=false;
        table3CustPres=false;
        table4Collide=false;
        
        //wait times
        timeCust1Sits = -1;
        timeCust2Sits = -1;
        timeCust3Sits = -1;
        timeCust4Sits = -1;
        
        inGameMusic.play();
        inGameMusic.loop();
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {

        int key = e.getKeyCode();
        System.out.println(key);
        //moving the rectangle
        if(key == 32)
        {
            startup = true;
        }
        if(key == 27)
        {
            rules = true;
        }
        if(key == 38)
        {
            cY = cY-10;
        }
        else if(key==40)
        {
            cY = cY + 10;
        }
        else if(key==37)
        {  
            cX = cX - 10;
        }
        else if(key==39)
        {
            cX = cX + 10;
        }
        
    }   
    //All your GUI drawing goes in here
    public void paintComponent(Graphics g)
    {
        if(startup==false)
        {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            Font start = new Font("Arial", Font.BOLD,30);
            g.setFont(start);
            g.setColor(Color.BLACK);
            g.drawString("Press Space to Start or press esc to read the rules", (WIDTH/2)-325, HEIGHT/2);
            Font f6 = new Font("Arial", Font.BOLD,30);
            g.setFont(f6);
            g.setColor(Color.yellow);
            g.drawString("Time:" + currTime/1000, 0, 50);
            startTime = System.currentTimeMillis();
            currTime = 0;
        }
        if(rules == true)
        {
            g.setColor(Color.yellow);
            g.fillRect(0,0,WIDTH,HEIGHT);
            Font rule1 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule1);
            g.setColor(Color.black);
            g.drawString("Rule 1: when a customer comes in, seat him/her",0,100);
            Font rule2 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule2);
            g.setColor(Color.black);
            g.drawString("Rule 2: you cannot pick up a customer while holding food",0,135);
            Font rule3 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule3);
            g.setColor(Color.black);
            g.drawString("Rule 3: you will get $50 dollars anytime a customer recieves its food",0,170);
            Font rule4 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule4);
            g.setColor(Color.black);
            g.drawString("Rule 4: if a customer waits 15 seconds for its food, you lose $50",0,205);
            Font rule5 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule5);
            g.setColor(Color.black);
            g.drawString("Rule 5: if you hit $0, you lose",0,240);
            Font rule6 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule6);
            g.setColor(Color.black);
            g.drawString("Rule 6: if you hit $1000, you win",0,275);
            Font rule7 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule7);
            g.setColor(Color.black);
            g.drawString("Rule 7: use the arrow keys to move around",0,305);
            Font rule8 = new Font("Arial",Font.BOLD,30);
            g.setFont(rule8);
            g.setColor(Color.black);
            g.drawString("Rule 8: have fun!",0,340);
            Font startGame = new Font("Arial",Font.BOLD,30);
            g.setFont(startGame);
            g.setColor(Color.black);
            g.drawString("Press Space to Start",(WIDTH)/2-150,400);
            
        }
        
        if(startup == true)
        {
 
            g.setColor(Color.black);
            g.fillRect(0,0,WIDTH,HEIGHT);
            Font amount = new Font("Arial", Font.BOLD,30);
            g.setFont(amount);
            g.setColor(Color.YELLOW);
            g.drawString("Money: $" + money, (WIDTH/2)+250,(HEIGHT/2)+-200);
            
            g.setColor(Color.red);
            g.fillRect(table1X,table1Y,rW,rH);
            if(table1Collide == true && pickUpCust==true&& table1CustPres==false) //over here needs to say && I have a customer!
            {
                table1CustPres = true;
                timeCust1Sits=currTime;
                pickUpCust = false;
            }
            if(table1CustPres == true&& haveFood==true&&table1Collide == true)
            {
                table1CustPres = false;
                haveFood = false;
                money+=100;
            }
            if(table1CustPres == true)
            {
                g.setColor(Color.yellow);
                g.fillOval(table1XCust,table1YCust, diam, diam);
            }
            Font f = new Font("Arial", Font.BOLD,30);
            g.setFont(f);
            g.setColor(Color.yellow);
            g.drawString("Table 1", 250, 100);

            g.setColor(Color.RED);
            g.fillRect(table2X,table2Y, 50, 100);
            if(table2Collide == true && pickUpCust==true&& table2CustPres==false) //over here needs to say && I have a customer!
            {
                table2CustPres = true;
                pickUpCust = false;
                timeCust2Sits=currTime;
            }
            if(table2CustPres == true&& haveFood==true&&table2Collide == true)
            {
                table2CustPres = false;
                haveFood = false;
                money+=100;
            }
            if(table2CustPres == true)
            {
                g.setColor(Color.yellow);
                g.fillOval(table2XCust,table2YCust, diam, diam);
            }
            Font f1 = new Font("Arial", Font.BOLD,30);
            g.setFont(f1);
            g.setColor(Color.yellow);
            g.drawString("Table 2", 250, 300);
            
            g.setColor(Color.red);
            g.fillRect(table3X,table3Y, 50, 100);
            if(table3Collide == true && pickUpCust==true&& table3CustPres==false)
            {
                table3CustPres=true;
                pickUpCust=false;
                timeCust3Sits=currTime;
            }
            if(table3CustPres == true && haveFood==true && table3Collide == true)
            {
                table3CustPres = false;
                haveFood = false;
                money+=100;
            }
            if(table3CustPres==true)
            {
                g.setColor(Color.yellow);
                g.fillOval(table3XCust, table3YCust, diam, diam);
                
            }
            Font f2 = new Font("Arial", Font.BOLD,30);
            g.setFont(f2);
            g.setColor(Color.yellow);
            g.drawString("Table 3", 750, 100);

            g.setColor(Color.red);
            g.fillRect(table4X,table4Y, rW, rH);
            if(table4Collide == true && pickUpCust==true&& table4CustPres==false)
            {
                table4CustPres = true;
                pickUpCust = false;
                timeCust4Sits=currTime;
            }
            if(table4CustPres==true)
            {
                g.setColor(Color.yellow);
                g.fillOval(table4XCust, table4YCust, diam,diam);
            }
            if(table4Collide == true && pickUpCust == true && table4CustPres == true)
            {
                pickUpCust = true;
            }
            if(table4CustPres == true && haveFood == true && table4Collide == true)
            {
                table4CustPres = false;
                haveFood = false;
                money+=100;
            }
            
            Font f3 = new Font("Arial", Font.BOLD,30);
            g.setFont(f3);
            g.setColor(Color.yellow);
            g.drawString("Table 4", 750, 300);

            g.setColor(Color.RED);
            g.fillRect(0,300, 100, 50);
            Font f4 = new Font("Arial", Font.BOLD,30);
            g.setFont(f4);
            g.setColor(Color.yellow);
            g.drawString("Cooks table",0, 300);

            Font f5 = new Font("Arial", Font.BOLD,30);
            g.setFont(f5);
            g.setColor(Color.yellow);
            g.drawString("Entrance", 450, 60);
            //Drawing Hello World!! at the center of the GUI

            //Drawing the user-controlled rectangle
            

            Font f6 = new Font("Arial", Font.BOLD,30);
            g.setFont(f6);
            g.setColor(Color.yellow);
            g.drawString("Time:" + currTime/1000, 0, 50);


            
            
            //table1collision
            if(haveFood == true && pickUpCust == true)
            {
                pickUpCust = false;
                custPres = true;
            }
            if(table1Collide == true)
            {
                g.setColor(Color.BLUE);
                g.fillRect(table1X,table1Y, rW, rH);
            }
            if(table2Collide==true)
            {
                g.setColor(Color.blue);
                g.fillRect(table2X,table2Y,rW,rH);
            }
            if(table3Collide==true)
            {
                g.setColor(Color.blue);
                g.fillRect(table3X,table3Y,rW,rH);
            }
            if(table4Collide==true)
            {
                g.setColor(Color.blue);
                g.fillRect(table4X,table4Y,rW,rH);
            }
            if(tableCollideCook == true)
            {
                haveFood=true;
                g.setColor(Color.blue);
                g.fillRect(cookX,cookY,cookW,cookH);
            }
            if(custPres == true && pickUpCust == false)
            {
                g.setColor(Color.yellow);
                g.fillOval(customerX, customerY, 30, 30);
            }
            
            if(pickUpCust == true)
            {
                g.setColor(Color.BLUE);
                g.fillOval(cX, cY, 30, 30);
            }
            if(pickUpCust == true)
            {    
                if(haveFood==true)
                {
                    haveFood = false;
                }
            }
            if(pickUpCust == false)
            {
                if(haveFood == true)
                {
                    g.setColor(Color.PINK);
                    g.fillOval(cX, cY, diam, diam);
                }
            }
            if(table1Pres == true && pickUpCust == true && table1Collide == true)
            {
                pickUpCust = true;
            }
            if(haveFood == false && pickUpCust==false)
            {
                g.setColor(Color.yellow);
                g.fillOval(cX, cY, 30, 30);
            }
            g.setColor(Color.yellow);
            g.fillOval(trashX,trashY,30,30);

            Font trashText = new Font("Comic Sans", Font.BOLD,30);
            g.setFont(trashText);
            g.setColor(Color.yellow);
            g.drawString("Trash", 0, 400);
            if(haveFood == true && trash == true)
            {
                haveFood = false;
            }
            if(money <=0)
            {
                g.setColor(Color.black);
                g.fillRect(0,0,WIDTH,HEIGHT);
                Font youLose = new Font("Comic Sans", Font.BOLD,30);
                g.setFont(youLose);
                g.setColor(Color.yellow);
                g.drawString("You lose :(", (WIDTH/2)-150, HEIGHT/2);
                
                inGameMusic.stop();
            }
            if(money>=1000)
            {
                g.setColor(Color.black);
                g.fillRect(0,0,WIDTH,HEIGHT);
                Font youLose = new Font("Comic Sans", Font.BOLD,30);
                g.setFont(youLose);
                g.setColor(Color.yellow);
                g.drawString("You win :)", winX, winY);
            }
        }
        //Drawing a Blue Rectangle to be the background
        
        
        //Drawing the autonomous circle

    }
    public double distance(int x1, int y1, int x2, int y2)
    {
        double output = 0.0;
        double first = x2-x1;
        double last = y2-y1;
        double newFirst = first*first;
        double newLast = last*last;
        double sub = newFirst+newLast;
        double sqr = Math.sqrt(sub);
        output = sqr;
        return output;
    }
    public boolean detectCollisionCust()
    {
        boolean output = false;
        int radius = diam/2;
        int myCentX = cX + radius;
        int myCentY = cY + radius;
        int custX = customerX + radius;
        int custY = customerY + radius;
        double dist = distance(myCentX, myCentY, custX, custY);
        if(dist<=radius+radius)
        {
            output = true;
        }
        return output;
    }
    public boolean detectCollisionTable1()
    {
        boolean output = false;
        int radius = diam/2;
        int centerX = (2*cX+diam)/2;
        int centerY = (2*cY+diam)/2;
        double dist = distance(centerX,centerY,table1X, table1Y);
        for(int x = table1X; x<=table1X+rW;x++)
        {
            for(int y = table1Y; y<=table1Y+rH;y++)
            {
                dist = distance(centerX,centerY,x, y);
                if(dist<diam/2)
                {
                    output = true;
                    //table1Collide = true;
                }
            }
        }
        return output;
    }
    public boolean detectCollisionTable2()
    {
        boolean output = false;
        int radius = diam/2;
        int centerX = (2*cX+diam)/2;
        int centerY = (2*cY+diam)/2;
        double dist = distance(centerX,centerY,table2X, table2Y);
        for(int x = table2X; x<=table2X+rW;x++)
        {
            for(int y = table2Y; y<=table2Y+rH;y++)
            {
                dist = distance(centerX,centerY,x, y);//you forgot to call this inside of the loop, but you were close!
                if(dist<diam/2)
                {
                    output = true;
                    //table1Collide = true;
                }
            }
        }
        return output;
    }
    public boolean detectCollisionTable3()
    {
        boolean output = false;
        int radius = diam/2;
        int centerX = (2*cX+diam)/2;
        int centerY = (2*cY+diam)/2;
        for(int x = table3X; x<=table3X+rW;x++)
        {
            for(int y = table3Y; y<=table3Y+rH;y++)
            {
                double dist = distance(centerX,centerY,x, y);
                if(dist<diam/2)
                {
                    output = true;
                }
            }
        }
        return output;
    }
    public boolean detectCollisionTable4()
    {
        boolean output = false;
        int radius = diam/2;
        int centerX = (2*cX+diam)/2;
        int centerY = (2*cY+diam)/2;
        for(int x = table4X; x<=table4X+rW;x++)
        {
            for(int y = table4Y; y<=table4Y+rH;y++)
            {
                double dist = distance(centerX,centerY,x, y);
                if(dist<diam/2)
                {
                    output = true;
                }
            }
        }
        return output;
    }
    public boolean detectCollisionCook()
    {
        boolean output = false;
        int radius = diam/2;
        int centerX = (2*cX+diam)/2;
        int centerY = (2*cY+diam)/2;
        for(int x = cookX; x<=cookX+cookW;x++)
        {
            for(int y = cookY; y<=cookY+cookH;y++)
            {
                double dist = distance(centerX,centerY,x, y);
                if(dist<diam/2)
                {
                    output = true;
                }
            }
        }
        return output;
    }
    public boolean detectCollisionTrash()
    {
        boolean output = false;
        int radius = diam/2;
        int myCentX = cX + radius;
        int myCentY = cY + radius;
        int centerTrashX = trashX + radius;
        int centerTrashY = trashY + radius;
        double dist = distance(myCentX, myCentY, centerTrashX, centerTrashY);
        if(dist<=radius+radius)
        {
            output = true;
        }
        return output;
    }
    public void loop()
    {
        if(startup==true)
        {

            currTime = System.currentTimeMillis()-startTime;
            currTime2 = System.currentTimeMillis()-startTime2;
            currTime3 = System.currentTimeMillis()-startTime3;
            currTime4 = System.currentTimeMillis()-startTime4;
            System.out.println(timeCust1Sits);
            
            //handling how long cust1 will stay seated
            //table 1 wait time
            
            if(table1CustPres&&currTime >= timeCust1Sits + 15000)
            {
                table1CustPres = false;
                money-=50;
            }
            
            //table 2 wait time
            if(table2CustPres&&currTime2 >= timeCust2Sits + 15000)
            {
                table2CustPres = false;
                money-=50;
            }
            
            //table 3 wait time
            if(table3CustPres&&currTime3 >= timeCust3Sits + 15000)
            {
                table3CustPres = false;
                money-=50;
            }
            
            //table 4 wait time
            if(table4CustPres&&currTime4 >= timeCust4Sits + 15000)
            {
                table4CustPres = false;
                money-=50;
            }
            
            
            
            if(currTime/1000==5&&pickUpCust==false)//this means first 5 seconds
            {
                custPres=true;
            }
            if(currTime/1000%5==0)
            {    
                
                double r = Math.random();//random number from 0 to 1
                if(r < .1)
                {
                    System.out.println("spawn");
                    custPres = true;
                }
            }
            table1Collide = detectCollisionTable1();
            
            table2Collide = detectCollisionTable2();
            
            table3Collide = detectCollisionTable3();
            
            table4Collide = detectCollisionTable4();
            
            tableCollideCook = detectCollisionCook();
            
            trash = detectCollisionTrash();
            
            
            if(custPres == true && detectCollisionCust())
            {
                custPres = false;
                pickUpCust = true;
            }
            
        winX+=winVx;
        winY +=winVy;
        int nextX;
        int nextY;
        nextX = winX + winVx;
        nextY = winY+winVy;
        
        if(nextY > HEIGHT)
        {
            winVy = winVy*-1;
        }
        else if(nextX+ 150> WIDTH)
        {
            winVx = winVx*-1;
        }
        else if(nextY - 30<0)
        {
            winVy = winVy*-1;
        }
        else if(nextX  <0)
        {
            winVx = winVx*-1;
        }
            
            
            
        }
        
         
        //do not write below this
        repaint();
    }
    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e) 
    {
    }
    public void keyReleased(KeyEvent e) 
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }

    public static void main(String[] args)
    {
        EspinalProj7 g = new EspinalProj7();
        g.start(60);
    }
}