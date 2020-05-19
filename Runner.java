/* *****************************************************************************
 *  Name:    Eli Ji
 *  Date:    5-3-20
 *
 *  Description:  Runs the graphics of tetris bot.
 *
 **************************************************************************** */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Runner extends JPanel implements ActionListener {

    public static void main(String[] args) {

        Runner r = new Runner();

        JFrame f = new JFrame();
        f.setTitle("8 Puzzle Graphics");
        f.setSize(400, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.add(r);
        f.setVisible(true);
    }
    private boolean color1 = true;
    private Timer tm;
    private int xOffset = 100;
    private int yOffset = 100;
    private Stacker myStacker = new Stacker();
    private SevenPieceBag bag = new SevenPieceBag();


    public Runner() {
        tm = new Timer(1000, this);
        tm.setInitialDelay(1000);
        tm.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int rectLength = 20;

        // draw board
        for(int r=0; r<20; r++){
            for(int c=0; c<10; c++) {
                if (myStacker.board[r][c] == 0){
                    g.setColor(Color.black);
                    g.drawRect(xOffset + c * rectLength, yOffset + r * rectLength, rectLength, rectLength);
                }
                else {
                    if (myStacker.board[r][c] == 1) {
                        g.setColor(Color.MAGENTA);
                    }
                    else if (myStacker.board[r][c] == 2) {
                        g.setColor(Color.GREEN);
                    }
                    else if (myStacker.board[r][c] == 3) {
                        g.setColor(Color.RED);
                    }
                    else if (myStacker.board[r][c] == 4) {
                        g.setColor(Color.ORANGE);
                    }
                    else if (myStacker.board[r][c] == 5) {
                        g.setColor(Color.BLUE);
                    }
                    else if (myStacker.board[r][c] == 6) {
                        g.setColor(Color.YELLOW);
                    }
                    else if (myStacker.board[r][c] == 7) {
                        g.setColor(Color.CYAN);
                    }
                    g.fillRect(xOffset + c * rectLength, yOffset + r * rectLength, rectLength, rectLength);
                }
            }
        }

        for(int r=0; r<20; r++){
            for(int c=0; c<10; c++){
                g.setColor(Color.BLACK);
                g.drawRect(xOffset + c * rectLength, yOffset + r * rectLength, rectLength, rectLength);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int piece = bag.getNextPiece();
        //System.out.println(piece);
        myStacker.placePiece(piece);
        repaint();
    }
}