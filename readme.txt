TETRIS PLACEMEMENT ALGORITHM/BOT

Eli Ji 5-19-20


FILES:

Runner.java - runs the graphics of the stacker. In order to change algorithm used change placePiece/placePiece2 code in repaint(). In order to place just one specified block change parameter of placePiece() to the desired number. 1 - T, 2 - S, 3 - Z, 4 - L, 5 - J, 6 - O, 7 - I.

SevenPieceBag.java - my implementation of a seven piece bag, which is used in modern Tetris games. It randomly generates an order of blocks in sets of seven. For more: https://tetris.fandom.com/wiki/Random_Generator

Stacker - There are two placement algorithms. placePiece() is the brute force algorithm. and placePiece2() is the pattern checking algorithm.

Location - x,y coordinates


I did not get to finish implementing the board balancing for both algorithms. Also, the pattern checking algorithm is only implemented for the T piece.

