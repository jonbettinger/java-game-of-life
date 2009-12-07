package life;

import java.util.HashSet;
import java.util.Set;

public class Coordinates {
	private static final int SAME = 0;
	private static final int NORTH = -1;
	private static final int SOUTH = 1;
	private static final int WEST = -1;
	private static final int EAST = 1;
	private final int x;
	private final int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Set<Coordinates> neighboringCoordinates() {
		HashSet<Coordinates> neighbors = new HashSet<Coordinates>();
		neighbors.add(getNorthWest());
		neighbors.add(getNorth());
		neighbors.add(getNorthEast());
		neighbors.add(getWest());
		neighbors.add(getEast());
		neighbors.add(getSouthEast());
		neighbors.add(getSouth());
		neighbors.add(getSouthWest());
		return neighbors;
	}

	private Coordinates getNorthWest() {
		return neighbor(WEST, NORTH);
	}

	private Coordinates getNorth() {
		return neighbor(SAME, NORTH);
	}

	private Coordinates getNorthEast() {
		return neighbor(EAST, NORTH);
	}

	private Coordinates getEast() {
		return neighbor(EAST, SAME);
	}

	private Coordinates getWest() {
		return neighbor(WEST, SAME);
	}

	private Coordinates getSouthWest() {
		return neighbor(WEST, SOUTH);
	}

	private Coordinates getSouth() {
		return neighbor(SAME, SOUTH);
	}

	private Coordinates getSouthEast() {
		return neighbor(EAST, SOUTH);
	}

	private Coordinates neighbor(int xDiff, int yDiff) {
		return new Coordinates(x + xDiff, y + yDiff);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
