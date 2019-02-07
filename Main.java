import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Main extends JFrame implements Runnable, ActionListener,MouseListener,MouseMotionListener {

    //variables assigned
    private boolean[][] cellArray = new boolean[150][150];
    private boolean playing=true;
    private int panelChange = 0;

    //appropriate objects created.
    private Grid gameGrid = new Grid(cellArray);
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton clear = new JButton("Clear");
    private JButton rand = new JButton("Random");


    public static void main(String[] args) {
        //Initiate game
        Main sl = new Main();
        sl.setVisible(true);
    }

    private Main() {

        super("Game Of Life");

        Container buttons = new Container();
        JLayeredPane test = getLayeredPane();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final double frameWidth = screenSize.getWidth();
        final double frameHeight = screenSize.getHeight()-50;

        setPreferredSize(new Dimension((int)frameWidth,(int)frameHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //add event listeners for user interaction
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);
        clear.addActionListener(this);
        rand.addActionListener(this);

        //structure and add buttons to container, to be added to the frame.
        buttons.setLayout(new GridLayout(1,4));
        buttons.add(start);
        buttons.add(stop);
        buttons.add(clear);
        buttons.add(rand);

        buttons.setBounds(0,this.getContentPane().getHeight()-20,(int)frameWidth,20);
        gameGrid.setBounds(0,0,this.getContentPane().getWidth(),this.getContentPane().getHeight()-20);

        //add components to layered JFrame
        test.add(gameGrid,new Integer(0));
        test.add(buttons,new Integer(10));
        pack();

    }

    public void run(){
        while(playing){
            cellRules(false);
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void cellRules(boolean randFlag) {

        if (randFlag) {
            for (int i = 0; i < cellArray.length; i++) {
                for (int j = 0; j < cellArray[0].length; j++) {
                    cellArray[i][j]=false;
                }
            }
            Random r = new Random();
            int cellQuantity = (r.nextInt(cellArray.length * cellArray[0].length))/10;
            for(int i = 0; i < cellQuantity; i ++){
                int randX = r.nextInt(cellArray.length);
                int randY = r.nextInt(cellArray[0].length);
                if (!cellArray[randX][randY]) {
                    cellArray[randX][randY]=true;
                }
            }
        } else {
            int[][] neighbourCount = new int[cellArray.length][cellArray[0].length];
            for (int i = 0; i < cellArray.length; i++) {
                for (int j = 0; j < cellArray[0].length; j++) {

                    if (i == 0 && j == 0) {
                        for (int q = i; q < i + 2; q++) {
                            for (int u = j; u < j + 2; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        if(cellArray[cellArray.length-1][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                        if(cellArray[cellArray.length-1][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                        if(cellArray[cellArray.length-1][1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                        if(cellArray[0][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                        if(cellArray[1][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                    } else if (i == cellArray.length - 1 && j == 0) {
                        for (int q = i - 1; q < i + 1; q++) {
                            for (int u = j; u < j + 2; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        if(cellArray[0][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                        if(cellArray[cellArray.length-1][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[cellArray.length-2][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[0][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[0][1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                    } else if (i == 0 && j == cellArray[0].length - 1) {
                        for (int q = i; q < i + 2; q++) {
                            for (int u = j - 1; u < j + 1; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        if(cellArray[cellArray.length-1][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[cellArray.length-1][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[cellArray.length-1][cellArray[0].length-2]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[0][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[1][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                    } else if (i == cellArray.length - 1 && j == cellArray[0].length - 1) {
                        for (int q = i - 1; q < i + 1; q++) {
                            for (int u = j - 1; u < j + 1; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        if(cellArray[0][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[0][cellArray[0].length-1]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[0][cellArray[0].length-2]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[cellArray.length-1][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }if(cellArray[cellArray.length-2][0]){
                            neighbourCount[i][j] = neighbourCount[i][j] + 1;
                        }
                    } else if (i == 0) {
                        for (int q = i; q < i + 2; q++) {
                            for (int u = j - 1; u < j + 2; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        for(int k=j-1; k<j+2; k++){
                            if(cellArray[cellArray.length-1][k]){
                                neighbourCount[i][j] = neighbourCount[i][j] + 1;
                            }
                        }
                    } else if (i == cellArray.length - 1) {
                        for (int q = i - 1; q < i + 1; q++) {
                            for (int u = j - 1; u < j + 2; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        for(int k=j-1; k<j+2; k++){
                            if(cellArray[0][k]){
                                neighbourCount[i][j] = neighbourCount[i][j] + 1;
                            }
                        }
                    } else if (j == 0) {
                        for (int q = i - 1; q < i + 2; q++) {
                            for (int u = j; u < j + 2; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        for(int k=i-1; k<i+2; k++){
                            if(cellArray[k][cellArray[0].length-1]){
                                neighbourCount[i][j] = neighbourCount[i][j] + 1;
                            }
                        }
                    } else if (j == cellArray[0].length - 1) {
                        for (int q = i - 1; q < i + 2; q++) {
                            for (int u = j - 1; u < j + 1; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                        for(int k=i-1; k<i+2; k++){
                            if(cellArray[k][0]){
                                neighbourCount[i][j] = neighbourCount[i][j] + 1;
                            }
                        }
                    } else {
                        for (int q = i - 1; q < i + 2; q++) {
                            for (int u = j - 1; u < j + 2; u++) {
                                if (cellArray[q][u] && !(q == i && u == j)) {
                                    neighbourCount[i][j] = neighbourCount[i][j] + 1;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < cellArray.length; i++) {
                for (int j = 0; j < cellArray[0].length; j++) {
                    if (cellArray[i][j]) {

                        if (neighbourCount[i][j] < 2 || neighbourCount[i][j] > 3) {
                            cellArray[i][j] = false;
                        }
                    } else {
                        if (neighbourCount[i][j] == 3) {

                            cellArray[i][j] = true;
                        }
                    }
                }
            }
        }
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(start)){
            playing=true;
            Thread t = new Thread(this);
            t.start();
        }else if(e.getSource().equals(stop)){
            playing = false;
        }else if(e.getSource().equals(rand)){
            playing = false;
            cellRules(true);
        }else if(e.getSource().equals(clear)) {
            playing = false;
            for (int x = 0; x < cellArray.length; x++) {
                for (int y = 0; y < cellArray[0].length; y++) {
                    cellArray[x][y] = false;
                }
            }
            cellRules(false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){

        double xIndex = Math.ceil((e.getLocationOnScreen().getX()/(double)(getContentPane().getWidth()+panelChange))*cellArray[0].length);
        double yIndex = Math.ceil((e.getLocationOnScreen().getY()/(double)(getContentPane().getHeight()+panelChange))*cellArray.length)-2;
        System.out.println(getContentPane().getWidth());
        int x = (int)xIndex;
        int y = (int)yIndex;
        cellArray[y-1][x-1] = true;

        gameGrid.repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        double xIndex = Math.ceil((e.getPoint().getX()/(double)(getContentPane().getWidth()+panelChange))*cellArray[0].length);
        double yIndex = Math.ceil((e.getPoint().getY()/(double)(getContentPane().getHeight()+panelChange))*cellArray.length)-2;
        System.out.println(e.getLocationOnScreen().getX());
        int x = (int)xIndex;
        int y = (int)yIndex;
        if(cellArray[y-1][x-1]){
            cellArray[y-1][x-1] = false;
        }else{
            cellArray[y-1][x-1] = true;
        }

        gameGrid.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

