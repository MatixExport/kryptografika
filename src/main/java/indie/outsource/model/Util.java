package indie.outsource.model;

import indie.outsource.model.ViewEncodersAdapters.ViewEncoder;

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

    public static byte[] pack_and_encode(ViewEncoder encoder, byte[][] keys, byte[] content){
        byte[][] packages = ByteOperations.split_into_packages(content);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = encoder.encode(keys,packages[i]);
        }
        return ByteOperations.join_byte_arr(packages);
    }
    public static byte[] decrypt_and_unpack(ViewEncoder encoder,byte[][] keys,byte[] content){
        byte[][] packages = ByteOperations.package_encrypted_msg(content);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = encoder.decode(keys,packages[i]);
        }
        return ByteOperations.unpackage_msg(packages);
    }
}
