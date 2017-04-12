package com.xiwi.utils;

/**
 * Created by mango on 2017/4/12.
 */
public class Luhn {

    /**
     * Validate a number string using Luhn algorithm
     *
     * @param numberString
     * @return
     */
    public static boolean validate(String numberString) {
        return checkSum(numberString) == 0;
    }

    /**
     * Generate check digit for a number string. Assumes check digit or a place
     * holder is already appended at end of the string.
     *
     * @param numberString
     * @return
     */
    public static int checkSum(String numberString) {
        return checkSum(numberString, false);
    }

    /**
     * Generate check digit for a number string.
     *
     * @param numberString
     * @param noCheckDigit
     *            Whether check digit is present or not. True if no check Digit
     *            is appended.
     * @return
     */
    public static int checkSum(String numberString, boolean noCheckDigit) {
        int sum = 0, checkDigit = 0;

        if(!noCheckDigit)
            numberString = numberString.substring(0, numberString.length()-1);

        boolean isDouble = true;
        for (int i = numberString.length() - 1; i >= 0; i--) {
            int k = Integer.parseInt(String.valueOf(numberString.charAt(i)));
            sum += sumToSingleDigit((k * (isDouble ? 2 : 1)));
            isDouble = !isDouble;
        }

        if ((sum % 10) > 0)
            checkDigit = (10 - (sum % 10));

        return checkDigit;
    }

    private static int sumToSingleDigit(int k) {
        if (k < 10)
            return k;
        return sumToSingleDigit(k / 10) + (k % 10);
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i=0; i<cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for(int i=cardNoArr.length-2;i>=0;i-=2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i]/10 + cardNoArr[i]%10;
        }
        int sum = 0;
        for(int i=0;i<cardNoArr.length;i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    public static void main(String[] args){
        Luhn luhn = new Luhn();
        System.out.print(luhn.validate(""));
    }

}
