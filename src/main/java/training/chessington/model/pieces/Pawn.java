package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }
    int BOARD_SIZE = 7;

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves =  new ArrayList<>();

        int direction = board.get(from).getColour().equals(PlayerColour.WHITE) ? -1  : 1;

        if (Math.floorMod(direction, BOARD_SIZE) == from.getRow()) {
            moves.add(new Move(from, from.plus(direction*2, 0)));
        }

        if (!isAtEndOfBoard(from, direction) && notBlocked(from, board, direction)) {
            moves.add(new Move(from, from.plus(direction, 0)));
        }

//        Coordinates testCoord = from.plus(direction, 1);
        if (isInBoard(from.plus(direction, 1)) && canCapture(from, from.plus(direction, 1), board)) {
            moves.add(new Move(from, from.plus(direction, 1)));
        }
        if (isInBoard(from.plus(direction, -1)) && canCapture(from, from.plus(direction, -1), board)) {
            moves.add(new Move(from, from.plus(direction, -1)));
        }

        return moves;
    }

    private boolean canCapture(Coordinates from, Coordinates to, Board board) {
        return board.get(to) != null && !board.get(from).getColour().equals(board.get(to).getColour());
    }

    private boolean isAtEndOfBoard(Coordinates from, int direction) {
        return (direction == -1 & from.getRow() == 0) || (direction == 1 & from.getRow() == 7);
    }

    private boolean notBlocked(Coordinates from, Board board, int direction) {
        return board.get(from.plus(direction, 0)) == null;
    }

    private boolean isInBoard(Coordinates coords) {
        return coords.getRow() < BOARD_SIZE && coords.getRow() >= 0 && coords.getCol() < BOARD_SIZE && coords.getCol() >= 0;
    }
}
