package net.konzult.adventcalendar2018.day9;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.magicwerk.brownies.collections.GapList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MarbleGame {
    public static final int SPECIAL_MARBLE = 23;
    public static final int REMOVE_FROM = 7;
    private List<Integer> marbles;
    private final long[] playerScore;
    private int marbleWorth = 0;
    private int currentMarble = 0;
    private int currentPlayer = 0;
    private int marblesOnCircle = 0;

    public MarbleGame(int playerCount) {
        this.playerScore = new long[playerCount];
        marbles = new ArrayList<>();
        marbles.add(0);
        marblesOnCircle++;
    }

    public long getMaxScore() {
        return Longs.max(playerScore);
    }

    public void play(int maxMarbleWorth) {
        marbles = new GapList<>();
        marbles.add(0);
        marblesOnCircle = 1;
        while (marbleWorth < maxMarbleWorth) {
            turn();
        }
    }

    public void turn() {
        marbleWorth++;
        currentPlayer = (currentPlayer + 1 < playerScore.length ? currentPlayer + 1 : 0);
        if (marbleWorth % SPECIAL_MARBLE == 0) {
            takeMarble();
        } else {
            placeMarble();
        }
    }

    private void placeMarble() {
        int placeAt = currentMarble + 2;
        if (placeAt > marblesOnCircle) {
            placeAt += -marblesOnCircle;
        }
        marbles.add(placeAt, marbleWorth);
        currentMarble = placeAt;
        marblesOnCircle++;
    }

    private void takeMarble() {
        int takeFrom = currentMarble - REMOVE_FROM;
        if (takeFrom < 0) takeFrom += marblesOnCircle;
        playerScore[currentPlayer] += marbles.remove(takeFrom) + marbleWorth;
        currentMarble = takeFrom;
        marblesOnCircle--;
    }

    public List<Integer> getMarbles() {
        return marbles;
    }

    public long[] getPlayerScore() {
        return playerScore;
    }

    public int getMarbleWorth() {
        return marbleWorth;
    }

    public int getCurrentMarble() {
        return currentMarble;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
