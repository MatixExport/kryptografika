package indie.outsource.model;

import java.math.BigInteger;

import static java.lang.Math.ceil;

public class ByteOperations {


    public static byte get_bit_at_berlin(byte[] data, int position) {
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


    public static byte[] get_bits_at_positions_berlin(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            output[pos++] = get_bit_at_berlin(data, position);
        }
        return bits_to_bytes(output);
    }

    public static byte[] bits_to_bytes(byte[] in) {
        int new_len = (int) ceil((double) in.length / 8);

        byte[] output = new byte[new_len];

        for (int i = 0; i < in.length; i++) {
            set_bit_at_index(output, in.length - i - 1, in[i]);
        }
        reverseArr(output);
        return output;
    }


    public static byte[] reverseArr(byte[] arr) {
        byte temp;
        for (int i = 0; i < (int) (arr.length / 2); i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }

    public static byte[] join_tables(byte[] in1, byte[] in2) {
        byte[] output = new byte[in1.length + in2.length];
        System.arraycopy(in1, 0, output, 0, in1.length);
        System.arraycopy(in2, 0, output, in1.length, in2.length);
        return output;
    }

    public static String byte_to_string(byte value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    public static String byte_arr_to_string(byte[] value) {
        StringBuilder bits = new StringBuilder();
        for (byte i : value) {
            bits.append(byte_to_string(i));
            bits.append('\n');
        }
        return bits.toString();
    }

    public static byte[] byte_arr_xor(byte[] in1, byte[] in2) {
        byte[] output = new byte[in1.length];
        for (int i = 0; i < in1.length; i++) {
            output[i] = (byte) (in1[i] ^ in2[i]);

        }
        return output;
    }

    public static byte join_bytes(byte in1, int len1, byte in2) {
        byte output;
        output = (byte) (in1 << len1);
        output += in2;

        return output;
    }

    public static String byte_arr_to_hex(byte[] in) {
        return new BigInteger(1, in).toString(16);
    }

    public static byte[][] split_into_packages(byte[] content) {
        int len = (content.length / 8) + 1;
        int supplement = content.length % 8;
        byte[][] packages = new byte[len][8];

        int i = 0;
        while (i < content.length) {
            packages[i / 8][i % 8] = content[i];
            i++;
        }
        int supplement_int = 8 - supplement;
        for (int j = i % 8; j < 8; j++) {
            packages[i / 8][j] = (byte) supplement_int;
        }
        return packages;
    }

    public static byte[] join_byte_arr(byte[][] arrays) {
        byte[] output = new byte[arrays.length * arrays[0].length];
        for (int i = 0; i < arrays.length * arrays[0].length; i++) {
            output[i] = arrays[i / 8][i % 8];
        }
        return output;
    }

    public static byte[][] package_encrypted_msg(byte[] msg) {
        int len = msg.length / 8;
        byte[][] output = new byte[len][8];
        for (int i = 0; i < len; i++) {
            System.arraycopy(msg, i * 8, output[i], 0, 8);
        }
        return output;
    }

    public static boolean check_n_last_bytes(byte[] byte_package, int n, int value) {
        int len = byte_package.length;
        boolean valid = true;
        for (int i = len - 1; i > len - n && i > 0; i--) {
            valid = (byte_package[i] == (byte) value);
        }
        return valid;
    }

    public static byte[] unpackage_msg(byte[][] packages) {
        int last_byte = packages[packages.length - 1][7];
        //tymczasowo, to ma znaczenie tylko przy nieprawidłowych
        //komunikatach a teraz utf-16 je zniekształca więc akurat
        if (!check_n_last_bytes(packages[packages.length - 1], last_byte, last_byte)) {
            last_byte = 0;
        }
        int size = (packages.length * 8) - last_byte;
        byte[] output = new byte[size];
        for (int i = 0; i < size; i++) {
            int octet = i / 8;
            output[i] = packages[octet][i % 8];
        }
        return output;
    }

    public static char combine_2_bytes(byte byte_1, byte byte_2) {
        return (char) ((((byte_1 << 8) & (0xFF00)) + ((byte_2) & 0x00FF)));
    }

    public static byte[][] split_byteArr_in_half(byte[] data) {
        byte[][] output = new byte[2][data.length / 2];

        System.arraycopy(data, 0, output[0], 0, data.length / 2);
        System.arraycopy(data, data.length / 2, output[1], 0, data.length / 2);

        return output;
    }


}
