package indie.outsource.model;


import static indie.outsource.model.ByteOperations.*;

public class KeyGenerator {
    public static byte[][] get_subkeys(byte[] key){
        //dzielenie kluczna na 16 podkluczy

        final int[] first_key_filter = {56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42
                , 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60,
                52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
        final int[] second_key_filter = {13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19,
                12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31};
        final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

        //z klucza usuwamy bity parzystości zatem
        //64 bity -> 56 bitów
        byte[] active_part_of_key = get_bits_at_positions(key,first_key_filter);


        //dzielimy klucz na 2 części po 28 bitów
        byte[] first_key_half = split_byteArr_in_half(active_part_of_key)[0];
        byte[] second_key_half = split_byteArr_in_half(active_part_of_key)[1];

        byte[][] subkeys = new byte[16][];

        //tworzenie 16 podkluczy
        for(int i=0; i<16; i++){
            //obie części klucza rotujemy w lewo o jedną lub dwie pozycje
            first_key_half = rotate_left(first_key_half,SHIFTS[i]);
            second_key_half = rotate_left(second_key_half,SHIFTS[i]);

            //łaczymy częsci klucza w 1
            byte[] new_key = join_tables(first_key_half, second_key_half);

            //dokonujemy permutacji kompresującej
            byte[] new_filtered_key = get_bytes_at_positions(new_key,second_key_filter);

            subkeys[i] = bits_to_bytes(new_filtered_key);
        }
        return subkeys;
    }

    public static byte[] get_bytes_at_positions(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            output[pos++] = get_byte_at(data, position);
        }
        return output;
    }
    public static byte get_byte_at(byte[] data, int position) {
        return data[position];
    }
    public static byte[] get_bits_at_positions(byte[] data, int[] positions) {
        byte[] output = new byte[positions.length];
        int pos = 0;
        for (int position : positions) {
            //tymczasowo i tak cała ta funkcja jest do
            //wywalenia
            //ale teraz jestem w pociągu i się
            //telepie wszystek to nie chce mi się
            //za wiele zmieniać
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

}
