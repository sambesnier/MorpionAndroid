package com.example.sambesnier.morpion;

/**
 * Created by Administrateur on 07/09/2017.
 */

public class Gameplay {
    boolean playerA;
    boolean finished;
    int[][] map;
    int tours;

    public Gameplay() {
    }

    public void newGame() {
        setPlayerA(true);
        setFinished(false);
        setTours(0);
        initMap();
    }

    private void initMap() {
        if (map == null)
            map = new int[3][3];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = -1;
            }
        }
    }

    public boolean checkMap() {
        if (tours == 9) {
            return true;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != -1) {
                    if (i == 0 && j == 0) {
                        if (map[0][0] == map[1][0] && map[1][0] == map[2][0]) {
                            return true;
                        }
                        if (map[0][0] == map[0][1] && map[0][1] == map[0][2]) {
                            return true;
                        }
                        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                            return true;
                        }
                    }
                    if (i == 0 && j == 1) {
                        if (map[0][1] == map[1][1] && map[1][1] == map[2][1]) {
                            return true;
                        }
                        if (map[0][0] == map[0][1] && map[0][1] == map[0][2]) {
                            return true;
                        }
                    }
                    if (i == 0 && j == 2) {
                        if (map[0][0] == map[0][1] && map[0][1] == map[0][2]) {
                            return true;
                        }
                        if (map[0][2] == map[1][2] && map[1][2] == map[2][2]) {
                            return true;
                        }
                        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                            return true;
                        }
                    }
                    if (i == 1 && j == 0) {
                        if (map[0][0] == map[1][0] && map[1][0] == map[2][0]) {
                            return true;
                        }
                        if (map[1][0] == map[1][1] && map[1][1] == map[1][2]) {
                            return true;
                        }
                    }
                    if (i == 1 && j == 1) {
                        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                            return true;
                        }
                        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                            return true;
                        }
                        if (map[0][1] == map[1][1] && map[1][1] == map[2][1]) {
                            return true;
                        }
                        if (map[1][0] == map[1][1] && map[1][1] == map[1][2]) {
                            return true;
                        }
                    }
                    if (i == 1 && j == 2) {
                        if (map[0][2] == map[1][2] && map[1][2] == map[2][2]) {
                            return true;
                        }
                        if (map[1][0] == map[1][1] && map[1][1] == map[1][2]) {
                            return true;
                        }
                    }
                    if (i == 2 && j == 0) {
                        if (map[0][0] == map[1][0] && map[1][0] == map[2][0]) {
                            return true;
                        }
                        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                            return true;
                        }
                        if (map[2][0] == map[2][1] && map[2][1] == map[2][2]) {
                            return true;
                        }
                    }
                    if (i == 2 && j == 1) {
                        if (map[0][1] == map[1][1] && map[1][1] == map[2][1]) {
                            return true;
                        }
                        if (map[2][0] == map[2][1] && map[2][1] == map[2][2]) {
                            return true;
                        }
                    }
                    if (i == 2 && j == 2) {
                        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                            return true;
                        }
                        if (map[0][2] == map[1][2] && map[1][2] == map[2][2]) {
                            return true;
                        }
                        if (map[2][0] == map[2][1] && map[2][1] == map[2][2]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isOccuped(int x, int y) {
        if (map[x][y] != -1) {
            return true;
        }
        return false;
    }

    public void setValue(int x, int y) {
        if (playerA) {
            map[x][y] = 1;
        } else {
            map[x][y] = 2;
        }
        tours++;
    }

    public boolean isPlayerA() {
        return playerA;
    }

    public void setPlayerA(boolean playerA) {
        this.playerA = playerA;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getTours() {
        return tours;
    }

    public void setTours(int tours) {
        this.tours = tours;
    }
}
