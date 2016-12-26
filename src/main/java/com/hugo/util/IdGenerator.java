package com.hugo.util;

import java.util.Random;

/**
 * Created by ohj on 2016/12/8.
 */
public class IdGenerator {
    private static Random strGen = new Random();;
    private static Random numGen = new Random();;
    private static char[] numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();;
    private static char[] numbers = ("0123456789").toCharArray();;
    /** * 产生随机字符串 * */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[strGen.nextInt(35)];
        }
        return new String(randBuffer);
    }

    /** * 产生随机数值字符串 * */
    public static final String randomNumStr(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbers[numGen.nextInt(9)];
        }
        return new String(randBuffer);
    }

    public static void main(String[] args) {
        System.out.println(randomString(25));
        System.out.println("copyrightType".toUpperCase());
    }
}
