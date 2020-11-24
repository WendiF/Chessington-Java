package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

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
        return board.get(to) == null || !board.get(to).getColour().equals(board.get(from).getColour());
    }

    void addOrthogonalMoves(Coordinates from, Board board, List<Move> moves) {
        moves.addAll(findVectorMoveRange(1, 0, from, board));
        moves.addAll(findVectorMoveRange(-1, 0, from, board));
        moves.addAll(findVectorMoveRange(0, -1, from, board));
        moves.addAll(findVectorMoveRange(0, 1, from, board));
    }

    void addDiagonalMoves(Coordinates from, Board board, List<Move> moves) {
        moves.addAll(findVectorMoveRange(1, 1, from, board));
        moves.addAll(findVectorMoveRange(-1, -1, from, board));
        moves.addAll(findVectorMoveRange(1, -1, from, board));
        moves.addAll(findVectorMoveRange(-1, 1, from, board));
    }

    List<Move> findVectorMoveRange(int up, int right, Coordinates from, Board board) {
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
