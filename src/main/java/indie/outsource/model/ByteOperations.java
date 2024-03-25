package indie.outsource.model;

import java.math.BigInteger;

import static java.lang.Math.ceil;

public class ByteOperations {


    public static byte get_bit_at_index(byte[] data, int position) {
        int byte_position = position / 8;
        int bit_position = position % 8;
        byte selected_byte = data[byte_position];
        int bit_value = (selected_byte >> (7 - bit_position)) & 1;
        return (byte) bit_value;
    }

    public static void set_bit_at_index(byte[] data, int position, int value) {
        int byte_position = position / 8;
        int bit_position = position % 8;
        if (value == 0) {
            data[byte_position] &= (byte) ~(1 << bit_position);
            return;
        }
        data[byte_position] |= (byte) (1 << bit_position);
    }

    public static byte[] get_bits_at_positions(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            output[pos++] = get_bit_at_index(data, position);
        }
        return bits_to_bytes(output);
    }

    public static byte[] bits_to_bytes(byte[] in) {
        int new_len = (int) ceil((double) in.length / 8);

        byte[] output = new byte[new_len];

        for (int i = 0; i < in.length; i++) {
            set_bit_at_index(output, in.length - i - 1, in[i]);
        }
        output = reverseArr(output);
        return output;
    }


    public static byte[] reverseArr(byte[] arr) {
        byte temp;
        for (int i = 0; i < (arr.length / 2); i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }


    public static byte[] byte_arr_xor(byte[] in1, byte[] in2) {
        byte[] output = new byte[in1.length];
        for (int i = 0; i < in1.length; i++) {
            output[i] = (byte) (in1[i] ^ in2[i]);
        }
        return output;
    }


    public static String byte_arr_to_hex(byte[] in) {
        return new BigInteger(1, in).toString(16);
    }


    public static byte[] join_byte_arr(byte[][] arrays) {
        byte[] output = new byte[arrays.length * arrays[0].length];
        for (int i = 0; i < arrays.length * arrays[0].length; i++) {
            output[i] = arrays[i / 8][i % 8];
        }
        return output;
    }


    public static byte[] join_tables(byte[] in1, byte[] in2) {
        byte[] output = new byte[in1.length + in2.length];
        System.arraycopy(in1, 0, output, 0, in1.length);
        System.arraycopy(in2, 0, output, in1.length, in2.length);
        return output;
    }


    public static byte join_bytes(byte in1, int len1, byte in2) {
        byte output;
        output = (byte) (in1 << len1);
        output += in2;

        return output;
    }

    public static byte[][] split_byteArr_in_half(byte[] data) {
        byte[][] output = new byte[2][data.length / 2];

        System.arraycopy(data, 0, output[0], 0, data.length / 2);
        System.arraycopy(data, data.length / 2, output[1], 0, data.length / 2);

        return output;
    }


}
