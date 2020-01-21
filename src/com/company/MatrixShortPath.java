package com.company;

/**
 * n阶矩阵最短路径问题
 */
public class MatrixShortPath {

    private int shortPath = Integer.MAX_VALUE;

    private int[][] sourceData = new int[][]{{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

    private int[][] backtrackingStateArray = new int[sourceData.length][sourceData.length];

    private int[][] dynamicProgrammingStatArray = new int[sourceData.length][sourceData.length];

    /**
     * 回溯算法
     *
     * @param x
     * @param y
     * @param path
     */
    private void backtracking(int x, int y, int path) {
        if (backtrackingStateArray[x][y] > 0 && backtrackingStateArray[x][y] < path) {
            return;
        }
        backtrackingStateArray[x][y] = path;

        if (x == 0 && y == 0) {
            backtrackingStateArray[0][0] = sourceData[0][0];
        }
        if (x < sourceData.length - 1) {
            backtracking(x + 1, y, path + sourceData[x][y]);
        }
        if (y < sourceData.length - 1) {
            backtracking(x, y + 1, path + sourceData[x][y]);
        }
        if (x == sourceData.length - 1 && y == sourceData.length - 1) {
            if (path + sourceData[sourceData.length - 1][sourceData.length - 1] < shortPath) {
                shortPath = path + sourceData[sourceData.length - 1][sourceData.length - 1];
            }
        }
    }

    /**
     * 动态规划（状态转移表法）
     */
    private void dynamicProgramming() {
        for (int i = 0; i < sourceData.length; i++) {
            for (int j = 0; j < sourceData.length; j++) {
                if (i == 0 && j == 0) {
                    dynamicProgrammingStatArray[0][0] = sourceData[0][0];
                    continue;
                }
                if (i == 0) {
                    dynamicProgrammingStatArray[i][j] = dynamicProgrammingStatArray[i][j - 1] + sourceData[i][j];
                } else if (j == 0) {
                    dynamicProgrammingStatArray[i][j] = dynamicProgrammingStatArray[i - 1][j] + sourceData[i][j];
                } else {
                    dynamicProgrammingStatArray[i][j] = Math.min(dynamicProgrammingStatArray[i][j - 1], dynamicProgrammingStatArray[i - 1][j]) + sourceData[i][j];
                }
            }
        }
        shortPath = dynamicProgrammingStatArray[sourceData.length - 1][sourceData.length - 1];
    }

    /**
     * 动态规划（状态转移方程法）
     */
    private int dynamicProgramming(int x, int y) {
        if (x == 0 && y == 0) {
            return sourceData[0][0];
        }
        if (dynamicProgrammingStatArray[x][y] > 0) {
            return dynamicProgrammingStatArray[x][y];
        }
        int l = Integer.MAX_VALUE;
        if (x - 1 >= 0) {
            l = dynamicProgramming(x - 1, y);
        }
        int r = Integer.MAX_VALUE;
        if (y - 1 >= 0) {
            r = dynamicProgramming(x, y - 1);
        }
        int v = sourceData[x][y] + Math.min(l, r);
        dynamicProgrammingStatArray[x][y] = v;
        return v;
    }

    public static void main(String[] args) {
        MatrixShortPath matrixShortPath = new MatrixShortPath();

        matrixShortPath.backtracking(0, 0, 0);

        matrixShortPath.dynamicProgramming();

        matrixShortPath.shortPath = matrixShortPath.dynamicProgramming(matrixShortPath.sourceData.length - 1, matrixShortPath.sourceData.length - 1);

        System.out.println(matrixShortPath.shortPath);
    }


}
