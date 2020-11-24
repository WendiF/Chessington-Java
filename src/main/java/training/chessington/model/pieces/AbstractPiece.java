package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.PlayerColour;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    boolean canCapture(Coordinates from, Coordinates to, Board board) {
        return board.get(to) != null && !board.get(from).getColour().equals(board.get(to).getColour());
    }

    boolean isAtEndOfBoard(Coordinates from, int direction) {
        return (direction == -1 & from.getRow() == 0) || (direction == 1 & from.getRow() == 7);
    }

    boolean notBlocked(Coordinates from, Coordinates to, Board board) {
        return board.get(to) == null || board.get(to).getColour().equals(board.get(from).getColour());
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }
}
