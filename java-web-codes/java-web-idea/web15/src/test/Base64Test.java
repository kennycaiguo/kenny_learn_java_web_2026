package test;

import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{97,98,99,100};
//        String base64 = Base64.getEncoder().withoutPadding().encodeToString(bytes); //YWJjZA
        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes); //YWJjZA
        System.out.println(base64);
        byte[] decode = Base64.getDecoder().decode(base64);
        System.out.println(java.util.Arrays.toString(decode)); //[97, 98, 99, 100]
    }
}
