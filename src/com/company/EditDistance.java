package com.company;

public class EditDistance {

    private char[] sourceData = new char[]{'m', 'i', 't', 'c', 'm', 'u'};

    private char[] targetData = new char[]{'m', 't', 'a', 'c', 'n', 'u'};

    private void backtracking(int x, int y) {
        if (sourceData[x] == sourceData[y]) {
            backtracking(x + 1, y + 1);
        }

        if (){

        }

    }

    public static void main(String[] args) {

    }

}
