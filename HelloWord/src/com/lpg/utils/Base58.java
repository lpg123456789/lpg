package com.lpg.utils;

public class Base58 {

    private static final char[] ALPHABET = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ"
            .toCharArray();

    private static int size = ALPHABET.length;


    public static String encode(long number){

        StringBuilder sb = new StringBuilder();

        for(int i = 0;i<1000;i++){

            int last = (int) (number % size);

            char c = ALPHABET[last];

            sb.append(c);

            if(number<size){
                break;
            }

            number /= size;

        }

        return sb.reverse().toString();

    }

    public static long decode(String str){

        int length = str.length();

        long number = 0;

        int rate = 1;

        for(int i = length-1;i>=0;i--){

            char c = str.charAt(i);

            long num = -1;

            for(int j = 1;j<=ALPHABET.length;j++){
                if(c == ALPHABET[j-1]){
                    num = j-1;
                    break;
                }
            }

            if(rate>=size) {
                num *= rate;
            }
            rate *= size ;

            number+= num;

        }

        return number;
    }



    public static void main(String args[]){

        //long a = ID.getId(23333);


//        long a = 6757950730468130817L;

        long a = 817;

        System.out.println("gFQu3dYogB8");

        System.out.println(a);

        String encode = encode(a);

        System.out.println(encode);

        long b = decode(encode);

        System.out.println(b);

    }


}
