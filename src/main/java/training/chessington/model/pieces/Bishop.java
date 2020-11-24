package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(findVectorMoveRange(1, 1, from, board));
        moves.addAll(findVectorMoveRange(-1, -1, from, board));
        moves.addAll(findVectorMoveRange(1, -1, from, board));
        moves.addAll(findVectorMoveRange(-1, 1, from, board));

        return moves;
    }

    private List<Move> findVectorMoveRange(int up, int right, Coordinates from, Board board) {
        int i = 1;
        ArrayList<Move> moves = new ArrayList<>();
        boolean quit = false;
        do {
            Coordinates to = from.plus(i*up, i*right);
            if (!isInBoard(to, board) || !notBlocked(from, to, board)) {
                quit = true;
            } else if (canCapture(from, to, board) || notBlocked(from, to, board)) {
                moves.add(new Move(from, to));
            }
            i++;
        } while (!quit);
        System.out.println(moves);
        return moves;
    }
}
