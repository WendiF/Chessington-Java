package training.chessington.model.pieces;

import org.junit.Before;
import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {
    private Piece movingPiece;
    private Coordinates movingCoords;
    private Board board;
    private List<Move> moves;

    @Before
    public void initialise() {
        // Arrange
        board = Board.empty();
        movingPiece = new Rook(PlayerColour.WHITE);
        movingCoords = new Coordinates(2, 3);
        board.placePiece(movingCoords, movingPiece);

        Piece takingPiece = new Rook(PlayerColour.BLACK);
        Coordinates takingCoords = new Coordinates(2, 5);
        board.placePiece(takingCoords, takingPiece);

        Piece blockingPiece = new Rook(PlayerColour.WHITE);
        Coordinates blockingCoords = new Coordinates(5, 3);
        board.placePiece(blockingCoords, blockingPiece);

        // Act
        moves = movingPiece.getAllowedMoves(movingCoords, board);
    }


    @Test
    public void RookCanMoveHorizontally() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(1, 3)));
    }

    @Test
    public void RookIsBlockedByOwnPlayersPiece() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(4, 3))).
                doesNotContain(new Move(movingCoords, new Coordinates(5, 3)));
    }

    @Test
    public void RookCanTakeOpponentsPiece() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(2, 5)));
    }

    @Test
    public void RookCannotMoveOffBoard() {
        // Assert
        assertThat(moves).contains(new Move(movingCoords, new Coordinates(2, 0)))
                .doesNotContain(new Move(movingCoords, new Coordinates(2, -1)));
    }
    
}