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

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves =  new ArrayList<>();
        int direction = board.get(from).getColour().equals(PlayerColour.WHITE) ? -1  : 1;

        moves.add(new Move(from, from.plus(direction, 0)));

        if (Math.floorMod(direction, 7) == from.getRow()) {
            moves.add(new Move(from, from.plus(direction*2, 0)));
        }
        return moves;
    }
}
