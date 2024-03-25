package indie.outsource.model;

import indie.outsource.model.ViewEncodersAdapters.ViewEncoder;

public class Util {
    public static byte[] remove_n_first_elements(byte[] arr, int n) {
        byte[] output = new byte[arr.length - n];
        System.arraycopy(arr, n, output, 0, arr.length - n);
        return output;
    }



    public static String genereateHexString(int len) {
        String digits = "0123456789ABCDEF";
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < len; i++) {
            hex.append(digits.charAt(getRandomNumber(0, 15)));
        }
        return hex.toString();
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static byte[] pack_and_encode(ViewEncoder encoder, byte[][] keys, byte[] content) {
        byte[][] packages = split_into_packages(content);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = encoder.encode(packages[i], keys);
        }
        return ByteOperations.join_byte_arr(packages);
    }
    public static byte[][] split_into_packages(byte[] content) {
        int len = (content.length / 8) + 1;
        byte[][] packages = new byte[len][8];

        for  (int i = 0; i < content.length; i++) {
            packages[i / 8][i % 8] = content[i];
        }

        int supplement = content.length % 8;
        byte supplement_int = (byte) (8 - supplement);
        for (int j = supplement; j < 8; j++) {
            packages[content.length / 8][j] =  supplement_int;
        }
        return packages;
    }

    public static byte[] decrypt_and_unpack(ViewEncoder encoder, byte[][] keys, byte[] content) {
        byte[][] packages = package_encrypted_msg(content);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = encoder.decode(packages[i], keys);
        }
        return unpackage_msg(packages);
    }

    public static byte[][] package_encrypted_msg(byte[] msg) {
        int len = msg.length / 8;
        byte[][] output = new byte[len][8];
        for (int i = 0; i < len; i++) {
            System.arraycopy(msg, i * 8, output[i], 0, 8);
        }
        return output;
    }

    public static byte[] unpackage_msg(byte[][] packages) {
        int last_byte = packages[packages.length - 1][7];
        int size = (packages.length * 8) - last_byte;
        byte[] output = new byte[size];
        for (int i = 0; i < size; i++) {
            int octet = i / 8;
            output[i] = packages[octet][i % 8];
        }
        return output;
    }


}
