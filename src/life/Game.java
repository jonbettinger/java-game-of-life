package life;

import java.util.HashSet;
import java.util.Set;

public class Game {
	private Set<Coordinates> liveCellCoordinates = new HashSet<Coordinates>();;

	public Set<Coordinates> getLivingCellLocations() {
		return liveCellCoordinates;
	}

	public void addLiveCellAt(Coordinates newCell) {
		liveCellCoordinates.add(newCell);
	}

	public void runGeneration() {
		Set<Coordinates> nextGeneration = new HashSet<Coordinates>();
		for (Coordinates coordinate : liveCellCoordinates) {
			int liveNeighborCount = countOfLiveCellsIn(coordinate.neighboringCoordinates());
			if (liveNeighborCount == 2 || liveNeighborCount == 3) {
				nextGeneration.add(coordinate);
			}
			applyRulesToNeighboringCoordinatesWithDeadCells(coordinate, nextGeneration);
		}
		liveCellCoordinates = nextGeneration;
	}

	private void applyRulesToNeighboringCoordinatesWithDeadCells(Coordinates coordinate, Set<Coordinates> nextGeneration) {
		for (Coordinates neighbor : neighboringCoordinatesWithDeadCells(coordinate)) {
			int liveNeighborCount = countOfLiveCellsIn(neighbor.neighboringCoordinates());
			if (liveNeighborCount == 3) {
				nextGeneration.add(neighbor);
			}
		}
	}

	private Set<Coordinates> neighboringCoordinatesWithDeadCells(Coordinates coordinateToCheck) {
		Set<Coordinates> neighboringCoordinatesWithDeadCells = new HashSet<Coordinates>();
		for (Coordinates neighboringCoordinate : coordinateToCheck.neighboringCoordinates()) {
			if (!liveCellCoordinates.contains(neighboringCoordinate)) {
				neighboringCoordinatesWithDeadCells.add(neighboringCoordinate);
			}
		}
		return neighboringCoordinatesWithDeadCells;
	}

	protected int countOfLiveCellsIn(Set<Coordinates> surroundingCoordinates) {
		int count = 0;
		for (Coordinates neighbor : surroundingCoordinates) {
			if (liveCellCoordinates.contains(neighbor)) {
				count++;
			}
		}
		return count;
	}
}
