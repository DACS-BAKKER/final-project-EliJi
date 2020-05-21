/* *****************************************************************************
 *  Name:    Eli Ji
 *  Date:    5-6-20
 *
 *  Description:  Implementation of a seven piece tetris bag. Each piece
 *                corresponds to the following numbers: 1-T 2-S 3-Z 4-L 5-J
 *                6-O 7-I
 *
 **************************************************************************** */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SevenPieceBag {

    private int index;
    private Integer[] bag = {1, 2, 3, 4, 5, 6, 7};;

    public SevenPieceBag(){
        index = 0;
        randomizeBag();
    }



    public int getNextPiece(){
        int nextPiece = bag[index];
        index++;
        if(index == 6){
            randomizeBag();
            index = 0;
        }
        return nextPiece;
    }

    private void randomizeBag(){
        List<Integer> bagList = Arrays.asList(bag);
        Collections.shuffle(bagList);
        bag = bagList.toArray(bag);
    }

    public int getIndex() {
        return index;
    }

    public Integer[] getBag() {
        return bag;
    }

    //testing
    public static void main(String[] args) {
        SevenPieceBag spg = new SevenPieceBag();
        for(int i=0; i<7; i++){
            System.out.println(spg.getNextPiece());
        }
        for(int i=0; i<7; i++){
            System.out.println(spg.getNextPiece());
        }
    }
}
