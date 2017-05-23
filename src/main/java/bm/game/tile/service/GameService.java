package bm.game.tile.service;

import java.util.Vector;

public class GameService {
	public static double getAverageClickTime(Vector<Double> timeDifferenceBetweenBlackTilesClickedInSeconds) {

		double sumOfSecondsBetweenClicks = 0;

		for (int indexOfVector = 0; indexOfVector < timeDifferenceBetweenBlackTilesClickedInSeconds
				.size(); indexOfVector++) {
			sumOfSecondsBetweenClicks += timeDifferenceBetweenBlackTilesClickedInSeconds.get(indexOfVector);
		}

		return sumOfSecondsBetweenClicks / timeDifferenceBetweenBlackTilesClickedInSeconds.size();

	}
}
