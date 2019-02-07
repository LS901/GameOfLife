import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {

    private boolean[][] cellArray;

    protected Grid(boolean[][] i){

        this.cellArray = i;
        setBackground(Color.BLACK);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        double width;
        double height;

        width = (double)this.getWidth() / cellArray[0].length;
        height = (double)this.getHeight() / cellArray.length;
        g.setColor(Color.DARK_GRAY);
        for(int x = 0; x < cellArray[0].length; x++){
            g.drawLine((int)Math.round(x*width),0,(int)Math.round(x*width),this.getHeight());
        }
        for(int y = 0; y < cellArray.length; y++){
            g.drawLine(0,(int)Math.round(y*height),this.getWidth(),(int)Math.round(y*height));
        }
        g.setColor(Color.WHITE);
        for (int i = 0; i < cellArray.length; i++) {
            for (int j = 0; j < cellArray[0].length; j++) {
                if (cellArray[i][j]){
                    g.fillRect((int)(j*width),(int)(i*height),(int)width,(int)height);
                }
            }
        }
    }
}
