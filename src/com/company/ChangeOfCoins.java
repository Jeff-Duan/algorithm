package com.company;

/**
 * 硬币找零最少数量
 */
public class ChangeOfCoins {

    private int price = 9;

    private int minCount = Integer.MIN_VALUE;

    private int[] sourceData = new int[]{1, 3, 5};

    private int[] priceStateArray = new int[price + 1];

    private void backtracking(int n, int count) {


    }

    /*private void dynamicProgramming() {
        for (int i = 0; i < price; i++) {

        }
    }*/

    /**
     * 动态规划（状态转移方程法-递归备忘）
     *
     * @param n
     * @return
     */
    private int dynamicProgramming(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 3) {
            return 3;
        }
        if (n == 5) {
            return 5;
        }

        if (priceStateArray[n] > 0) {
            return priceStateArray[n];
        }

        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE;

        if (n - 1 > 0) {
            i = dynamicProgramming(n - 1);
        }
        if (n - 3 > 0) {
            j = dynamicProgramming(n - 3);
        }
        if (n - 5 > 0) {
            k = dynamicProgramming(n - 5);
        }

        int p = Math.min(i, j);
        int q = Math.min(p, k);

        int v = 1 + q;
        priceStateArray[n] = v;
        return v;
    }

    /**
     * 动态规划（状态转移方程法-迭代递推）
     */
    private void dynamicProgramming() {
        priceStateArray[1] = 1;
        priceStateArray[2] = 2;
        priceStateArray[3] = 3;
        priceStateArray[4] = 2;
        priceStateArray[5] = 5;
        for (int i = 6; i <= price; i++) {
            int p = Math.min(priceStateArray[i - 1], priceStateArray[i - 3]);
            int q = Math.min(p, priceStateArray[i - 5]);
            priceStateArray[i] = 1 + Math.min(p, q);
        }
        minCount = priceStateArray[price];
    }

    public static void main(String[] args) {
        ChangeOfCoins changeOfCoins = new ChangeOfCoins();

        //changeOfCoins.minCount = changeOfCoins.dynamicProgramming(changeOfCoins.price);

        changeOfCoins.dynamicProgramming();

        System.out.println(changeOfCoins.minCount);
    }

}
