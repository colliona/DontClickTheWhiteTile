package bm.game.tile.service;

import java.util.Vector;

public class GameService {
	/**
	 * Calculates the average of seconds elapsed between consecutive clicks on
	 * black tiles.
	 * 
	 * @param timeDifferenceBetweenBlackTilesClickedInSeconds
	 *            - vector of elapsed seconds between two consecutive clicks on
	 *            black tiles
	 * @return - the average of seconds elapsed between consecutive clicks on
	 *         black tiles
	 */
	public static double calculateAverageClickTime(Vector<Double> timeDifferenceBetweenBlackTilesClickedInSeconds) {

		double sumOfSecondsBetweenClicks = 0;

		for (int indexOfVector = 0; indexOfVector < timeDifferenceBetweenBlackTilesClickedInSeconds
				.size(); indexOfVector++) {
			sumOfSecondsBetweenClicks += timeDifferenceBetweenBlackTilesClickedInSeconds.get(indexOfVector);
		}

		return sumOfSecondsBetweenClicks / timeDifferenceBetweenBlackTilesClickedInSeconds.size();

	}
}
