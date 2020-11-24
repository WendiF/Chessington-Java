package training.chessington.model.pieces;

import org.junit.Before;
import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {
    private Piece movingPiece;
    private Coordinates movingCoords;
    private Board board;
    private List<Move> moves;

    @Before
    public void initialise() {
        // Arrange
        board = Board.empty();
        movingPiece = new Bishop(PlayerColour.WHITE);
        movingCoords = new Coordinates(6, 4);
        board.placePiece(movingCoords, movingPiece);

        Piece takingPiece = new Bishop(PlayerColour.BLACK);
        Coordinates takingCoords = new Coordinates(4, 6);
        board.placePiece(takingCoords, takingPiece);

        Piece blockingPiece = new Bishop(PlayerColour.WHITE);
        Coordinates blockingCoords = new Coordinates(3, 1);
        board.placePiece(blockingCoords, blockingPiece);

        // Act
        moves = movingPiece.getAllowedMoves(movingCoords, board);

    }

    @Test
    public void bishopCanMoveDiagonally() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(7, 3)));
    }

    @Test
    public void bishopIsBlockedByOwnPlayersPiece() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(4, 2))).
                doesNotContain(new Move(movingCoords, new Coordinates(3, 1)));
    }

    @Test
    public void bishopCanTakeOpponentsPiece() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(4, 6)));
    }

    @Test
    public void bishopCannotMoveOffBoard() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(7, 5)))
                .doesNotContain(new Move(movingCoords, new Coordinates(8, 6)));
    }
}
