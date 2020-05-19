/* *****************************************************************************
 *  Name:    Eli Ji
 *  Date:    5-3-20
 *
 *  Description:  Stacker class / tetris bot.
 **************************************************************************** */

// 1-T 2-S 3-Z 4-L 5-J 6-O 7-I
public class Stacker {

    int[][] board;
    int imbalance = 10;

    public Stacker(){
        board = new int[20][10]; //default value 0
    }

    // updates board with the placment of the piece
    public void placePiece(int piece){
        int rotation = 0;
        Location[] pieceLocations = new Location[4];
        getRotation(piece, rotation, pieceLocations);

        boolean legalDrop = false;

        while(!legalDrop) {
            int[][] tempBoard = drop(piece, pieceLocations);
            //if try works
            //int newImbalance = imbalanceMetric(tempBoard);
            //if (isLegal(tempBoard) && (newImbalance < imbalance || Math.abs(newImbalance - imbalance) < 6)) {
            if (isLegal(tempBoard)) {
                //copy board
                for (int r = 0; r < 20; r++) {
                    for (int c = 0; c < 10; c++) {
                        board[r][c] = tempBoard[r][c];
                    }
                }
                legalDrop = true;
            }
            else {
                //at right end
                if(getRightmost(pieceLocations) == 9){
                    rotation++;
                    getRotation(piece, rotation, pieceLocations);
                }
                //if not at end, move every block over by 1
                else {
                    for (int i = 0; i < 4; i++) {
                        pieceLocations[i].setX(pieceLocations[i].getX() + 1);
                    }
                }
            }
        }
    }

    // USES ALGORITHM 2 (Pattern checking), only implemented for T block.
    public void placePiece2(int piece) {
        int[] contour = new int[10];
        int smallest = 21;
        for (int i = 0; i < 10; i++) {
            if (getHeight(i) < smallest) {
                smallest = getHeight(i);
            }
            contour[i] = getHeight(i);
        }
        for (int i = 0; i < 10; i++) {
            contour[i] = contour[i] - smallest;
        }

        int current = contour[1];
        int last = contour[0];
        int lastlast = -1;
        Location[] placement = new Location[4];

        boolean placed = false;

        for (int i = 1; i < 10; i++) {
            if(!placed) {
                //current one higher/lower
                if (current == last + 1) {
                    getRotation(1, 1, placement);
                    for (int j = 0; j < 4; j++) {
                        placement[j].setX(placement[j].getX() + i - 1);
                    }
                    placed = true;
                }
                else if (current == last - 1) {
                    getRotation(1, 3, placement);
                    for (int j = 0; j < 4; j++) {
                        placement[j].setX(placement[j].getX() + i - 1);
                    }
                    placed = true;
                }
                else if (lastlast != -1) {
                    if (current == last && last == lastlast) {
                        getRotation(1, 0, placement);
                        for (int j = 0; j < 4; j++) {
                            placement[j].setX(placement[j].getX() + i - 2);
                        }
                        placed = true;
                    }
                    if (current == lastlast && current == last + 1) {
                        getRotation(1, 2, placement);
                        for (int j = 0; j < 4; j++) {
                            placement[j].setX(placement[j].getX() + i - 2);
                        }
                        placed = true;
                    }
                }

                if (i < 9) {
                    lastlast = last;
                    last = current;
                    current = contour[i + 1];
                }
            }
        }
        int[][] tempBoard = drop(piece, placement);
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 10; c++) {
                board[r][c] = tempBoard[r][c];
            }
        }
    }


    // returns the board after dropping piece specified by locations (at top)
    private int[][] drop(int piece, Location[] locations){
        // copy board to tempBoard
        int[][] tempBoard = new int[20][10];
        for(int r = 0; r< 20; r++){
            for (int c = 0; c < 10; c++){
                tempBoard[r][c] = board[r][c];
            }
        }

        boolean dropped = false;

        //Drop
        while(!dropped) {
            //Check if the piece has dropped all the way
            for (int i = 0; i < 4; i++) {
                Location current = locations[i];
                //check every location, can move down? bottom or other block
                if (current.getY() == 19 || tempBoard[current.getY() + 1][current.getX()] != 0) {
                    dropped = true;
                }
            }
            if (dropped) {
                break;
            }
            else {
                //If not dropped, move every location down one
                for (int i = 0; i < 4; i++) {
                    Location current = locations[i];
                    current.setY(current.getY() + 1);
                }
            }
        }
        //add piece to board
        for(int i=0; i<4; i++){
            Location current = locations[i];
            tempBoard[current.getY()][current.getX()] = piece;
        }
        return tempBoard;
    }

    // changes pieceLocations to have the locations of the specified piece with the specified rotation
    private void getRotation(int piece, int rotation, Location[] pieceLocations){
        if(piece == 1){ // T
            if(rotation == 4){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = TRotations[rotation][i].getX();
                int y = TRotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        } else if(piece == 2){ // S
            if(rotation == 2){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = SRotations[rotation][i].getX();
                int y = SRotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        } else if(piece == 3){ // Z
            if(rotation == 2){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = ZRotations[rotation][i].getX();
                int y = ZRotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        } else if(piece == 4){ // L
            if(rotation == 4){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = LRotations[rotation][i].getX();
                int y = LRotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        } else if(piece == 5){ // J
            if(rotation == 4){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = JRotations[rotation][i].getX();
                int y = JRotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        } else if(piece == 6){ // O
            if(rotation == 1){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = ORotations[rotation][i].getX();
                int y = ORotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        } else if(piece == 7){ // I
            if(rotation == 2){
                System.out.println("No more rotations");
                return;
            }
            for(int i=0; i<4; i++) {
                int x = IRotations[rotation][i].getX();
                int y = IRotations[rotation][i].getY();
                pieceLocations[i] = new Location(x,y);
            }
        }
    }

    // prints out the current board
    private void test(){
        System.out.println("This is a test:");
        for(int r=0; r < 20; r++){
            for(int c=0; c<10; c++){
                System.out.print(board[r][c] + " ");
            }
            System.out.println("");
        }
    }

    // returns false if there are holes in a board
    private boolean isLegal(int[][] testBoard){
        //if there ever is a covered opening
        for(int r=19; r > 0; r--){
            boolean clear = true;
            for(int c = 0; c < 10; c++){
                //if 0 and 1 above (covers)
                if(testBoard[r][c] == 0 && testBoard[r - 1][c]!=0){
                    return false;
                }
                if(testBoard[r][c]!=0){
                    clear = false;
                }
            }
            // if entire row is zeroes
            if(clear) {
                return true;
            }
        } return true; //at top
    }

    // returns the greatest x val in the array
    private int getRightmost(Location[] locations){
        int rightMostVal = -1; // min 0
        int rightMostIndex = -1;
        for(int i=0; i<4; i++){
            if(locations[i].getX() > rightMostVal){
                rightMostVal = locations[i].getX();
                rightMostIndex = i;
            }
        }
        return rightMostVal;
    }

    // Imbalance of board, sum of differences between each col height with left most height.
    private int imbalanceMetric(int[][] board){
        //get left height
        int leftHeight = getHeight(0);

        int sum = 0;
        for(int c=1; c < 10; c++){
            int h = getHeight(c);
            int absDiff = Math.abs(leftHeight - h);
            sum = sum + absDiff;
        }
        return sum;
    }


    // returns height of specified col
    private int getHeight(int col){
        int height = 0;
        int row = 19;
        int current = board[row][col];
        while(current != 0){
            height++;
            row--;
            current = board[row][col];
        }
        return height;
    }

    private int[][] getBoard(){
        return board;
    }

    private Location[][]TRotations  = {{new Location(1, 0),new Location(0, 1),new Location(1, 1),new Location(2, 1)},
                                       {new Location(0, 0),new Location(0, 1),new Location(0, 2),new Location(1, 1)},
                                       {new Location(0, 0),new Location(1, 0),new Location(2, 0),new Location(1, 1)},
                                       {new Location(0, 1),new Location(1, 0),new Location(1, 1),new Location(1, 2)}};

    private Location[][]SRotations  = {{new Location(1, 0),new Location(2, 0),new Location(0, 1),new Location(0, 2)},
                                       {new Location(0, 0),new Location(0, 1),new Location(1, 1),new Location(1, 2)}};

    private Location[][]ZRotations  = {{new Location(0, 0),new Location(1, 0),new Location(1, 1),new Location(2, 1)},
                                       {new Location(1, 0),new Location(1, 1),new Location(0, 1),new Location(0, 2)}};

    private Location[][]LRotations  = {{new Location(2, 0),new Location(0, 1),new Location(1, 1),new Location(2, 1)},
                                       {new Location(0, 0),new Location(0, 1),new Location(0, 2),new Location(1, 2)},
                                       {new Location(0, 0),new Location(1, 0),new Location(2, 0),new Location(0, 1)},
                                       {new Location(0, 0),new Location(1, 0),new Location(1, 1),new Location(1, 2)}};

    private Location[][]JRotations  = {{new Location(0, 0),new Location(0, 1),new Location(1, 1),new Location(2, 1)},
                                       {new Location(0, 0),new Location(1, 0),new Location(0, 1),new Location(0, 2)},
                                       {new Location(0, 0),new Location(1, 0),new Location(2, 0),new Location(2, 1)},
                                       {new Location(0, 2),new Location(1, 0),new Location(1, 1),new Location(1, 2)}};

    private Location[][]ORotations  = {{new Location(0, 0),new Location(1, 0),new Location(0, 1),new Location(1, 1)}};

    private Location[][]IRotations  = {{new Location(0, 0),new Location(1, 0),new Location(2, 0),new Location(3, 0)},
                                       {new Location(0, 0),new Location(0, 1),new Location(0, 2),new Location(0, 3)}};


    public static void main(String[] args) {
        Stacker myStacker = new Stacker();
        myStacker.placePiece(1);
        myStacker.test();

        /*int[][] myBoard = myStacker.getBoard();

        for(int r=0; r < 20; r++){
            for(int c=0; c<10; c++){
                System.out.print(myBoard[r][c] + " ");
            }
            System.out.println("");
        }*/
    }
}
