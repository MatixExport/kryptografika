package indie.outsource.model;

public class Util {
    public static byte[] remove_n_first_elements(byte[] arr,int n){
        byte[] output = new byte[arr.length-n];
        System.arraycopy(arr,n,output,0,arr.length-n);
        return output;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static String genereateHexString(int len){
        String digits = "0123456789ABCDEF";
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < len; i++) {
            hex.append(digits.charAt(getRandomNumber(0,15)));
        }
        return hex.toString();
    }
}
