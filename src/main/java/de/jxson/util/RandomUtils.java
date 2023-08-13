package de.jxson.util;

import java.util.Random;

public class RandomUtils {

    private static final String VALID_CHARS = "abcdefghiklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#!\"§$%&/()=?.-_,;:'*`][{}]";

    public static String generateRandomString(int length)
    {
        String string = "";
        for(int i = 0; i < length; i++)
        {
            string += VALID_CHARS.toCharArray()[new Random().nextInt(VALID_CHARS.length())];
        }
        return string;
    }

}
