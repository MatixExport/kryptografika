package indie.outsource;
import java.math.BigInteger;

import static java.lang.Math.ceil;

public class ByteOperations {

    public static byte get_byte_at(byte[] data, int position) {
        return data[position];
    }
    public static byte get_bit_at(byte[] data, int position) {
        int byte_position = position / 8;
        int bit_position = position % 8;
        byte selected_byte = data[byte_position];
        int bit_value = selected_byte >> bit_position & 1;
        return (byte) bit_value;
    }

    //ja już nie wiem jak to ma działać
    //zakładamy że bit 0 jest od lewej
    //tak ci z berlina zrobili
    //?chyba?
    public static byte get_bit_at_berlin(byte[] data, int position) {
        int byte_position = position / 8;
        int bit_position = position % 8;
        byte selected_byte = data[byte_position];
        int bit_value = selected_byte >> (7 - bit_position) & 1;
        return (byte) bit_value;
    }

    public static void set_bit_at_index(byte[] data, int position,int value) {
        int byte_position = position / 8;
        int bit_position = position % 8;
        byte selected_byte = data[byte_position];
        if(value == 0){
            data[byte_position]  &= (byte) ~(1 << bit_position);
            return;
        }
        data[byte_position] |= (byte) (1 << bit_position);
    }

    public static byte[] get_bytes_at_positions(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            output[pos++] = get_byte_at(data, position);

        }
        return output;
    }
    public static byte[] get_bits_at_positions(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            output[pos++] = get_bit_at(data, position);
        }
        return output;
    }
    public static byte[] get_bits_at_positions_berlin(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            output[pos++] = get_bit_at_berlin(data, position);
        }
        return output;
    }

    public static byte[] rotate_left(byte[] data, int step) {
        byte[] output = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            output[i] = data[ (i + step ) % (data.length)];
        }

        return output;
    }

    public static byte[] join_tables(byte[] in1, byte[] in2) {
        byte[] output = new byte[in1.length + in2.length];
        System.arraycopy(in1, 0, output, 0, in1.length);
        System.arraycopy(in2, 0, output, in1.length, in2.length);
        return output;
    }

    public static byte[] bits_to_bytes(byte[] in){
        //teraz bity są ustawione tak jak się je czyta
        //czyli najmniejsze są po prawo
        //zrobiłem tak bo wcześniej java traktowała
        //nasze przekonwertowane {0,0,0,0,0,0,0,1}
        //jako -128 zamiast 1
        int new_len = (int) ceil((double) in.length / 8);

        byte[] output = new byte[new_len];

        for (int i=0;i<in.length;i++){
            set_bit_at_index(output,in.length -i -1,in[i]);
        }
        //jest 4 rano i już nie chce mi się nawet myśleć
        //jak to optymalnie zrobić
        output = reverseArr(output);

        return output;
    }
    public static byte[] reverseArr(byte[] arr){
        byte temp;
        for (int i = 0; i < (int) (arr.length / 2); i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }
    public static String byte_to_string(byte value){
        return  String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }
    public static String byte_arr_to_string(byte[] value){
        StringBuilder bits = new StringBuilder();
        for (byte i: value) {
            bits.append(byte_to_string(i));
            bits.append('\n');
        }
        return bits.toString();
    }
    public static byte[] byte_string_to_byte(String byte_str){
        int len = (int) ceil((double) byte_str.length() / 8);
        byte[] output = new byte[len];
        int position = 0;

        for (char ch: byte_str.toCharArray()) {

            set_bit_at_index(output,position++,ch == '1' ? 1:0);
        }
        return output;
    }
    public static byte[] byte_arr_xor(byte[] in1, byte[] in2){
        byte[] output = new byte[in1.length];
        for (int i = 0; i < in1.length; i++) {
            output[i] = (byte) (in1[i] ^ in2[i]);

        }
        return output;
    }
    public static byte join_bytes(byte in1,int len1, byte in2){
        byte output;
        output = (byte) ( in1 << len1);
        output += in2;

        return output;
    }
    public static String byte_arr_to_hex(byte[] in){
        return new BigInteger(1, in).toString(16);
    }


}
