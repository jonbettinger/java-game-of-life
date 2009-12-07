package life;

import junit.framework.TestCase;

public class GameTest extends TestCase {

	private Game game;

	public void setUp() {
		game = new Game();
	}

	public void testANewGameHasNoCells() {
		assertEquals(0, game.getLivingCellLocations().size());
	}

	public void testCellsAddedToGameAreMaintained() {
		Coordinates liveCellCoordinates = new Coordinates(2, 3);
		game.addLiveCellAt(liveCellCoordinates);
		assertTrue(game.getLivingCellLocations().contains(liveCellCoordinates));
	}

	public void testASingleCellDiesAfterFirstGeneration() {
		Coordinates liveCellCoordinates = new Coordinates(2, 3);
		game.addLiveCellAt(liveCellCoordinates);
		assertEquals(0, game.countOfLiveCellsIn(liveCellCoordinates.neighboringCoordinates()));
		game.runGeneration();
		assertFalse(game.getLivingCellLocations().contains(liveCellCoordinates));
	}

	public void testACellWith2NeighborsSurvivesFirstGeneration() {
		Coordinates liveCellCoordinates = new Coordinates(2, 3);
		game.addLiveCellAt(liveCellCoordinates);
		game.addLiveCellAt(new Coordinates(2, 2));
		game.addLiveCellAt(new Coordinates(2, 4));
		assertEquals(8, liveCellCoordinates.neighboringCoordinates().size());
		assertEquals(2, game.countOfLiveCellsIn(liveCellCoordinates.neighboringCoordinates()));
		game.runGeneration();
		assertTrue(game.getLivingCellLocations().contains(liveCellCoordinates));
	}

	public void testACellWith3NeighborsSurvivesFirstGeneration() {
		Coordinates liveCellCoordinates = new Coordinates(2, 3);
		game.addLiveCellAt(liveCellCoordinates);
		game.addLiveCellAt(new Coordinates(2, 2));
		game.addLiveCellAt(new Coordinates(2, 4));
		game.addLiveCellAt(new Coordinates(1, 4));
		assertEquals(3, game.countOfLiveCellsIn(liveCellCoordinates.neighboringCoordinates()));
		game.runGeneration();
		assertTrue(game.getLivingCellLocations().contains(liveCellCoordinates));
	}
	
	public void testADeadCellWith3LiveNeigborsComesToLife() {
		Coordinates deadCellCoordinates = new Coordinates(2, 3);
		game.addLiveCellAt(new Coordinates(2, 2));
		game.addLiveCellAt(new Coordinates(2, 4));
		game.addLiveCellAt(new Coordinates(1, 4));
		assertEquals(3, game.countOfLiveCellsIn(deadCellCoordinates.neighboringCoordinates()));
		game.runGeneration();
		assertTrue(game.getLivingCellLocations().contains(deadCellCoordinates));
	}
}
