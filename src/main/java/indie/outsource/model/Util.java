package indie.outsource.model;

import indie.outsource.model.ViewEncodersAdapters.ViewEncoder;

public class Util {

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
        byte[][] packages = split_into_packages(content, (content.length / 8) + 1);
        add_padding(packages[packages.length - 1], content.length % 8);

        for (int i = 0; i < packages.length; i++) {
            packages[i] = encoder.encode(packages[i], keys);
        }
        return ByteOperations.join_byte_arr(packages);
    }
    public static byte[][] split_into_packages(byte[] content, int length) {
        byte[][] packages = new byte[length][8];

        for  (int i = 0; i < content.length; i++) {
            packages[i / 8][i % 8] = content[i];
        }

        return packages;
    }
    public static byte[] add_padding(byte[] data, int supplement){
        for (int j = supplement; j < 8; j++) {
            data[j] = (byte) (8 - supplement);
        }
        return data;
    }

    public static byte[] decrypt_and_unpack(ViewEncoder encoder, byte[][] keys, byte[] content) {
        byte[][] packages = split_into_packages(content, content.length / 8);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = encoder.decode(packages[i], keys);
        }
        return remove_padding(packages);
    }


    public static byte[] remove_padding(byte[][] packages) {
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
