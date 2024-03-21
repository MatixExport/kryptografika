package indie.outsource.model;

public class KeyGenerator {
    public static byte[][] get_subkeys(byte[] key){
        final int[] first_key_filter = {56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42
                , 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60,
                52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
        final int[] second_key_filter = {13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19,
                12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31};
        final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

//        byte[] active_part_of_key = ByteOperations.get_bytes_at_positions(key,first_key_filter);
        byte[] active_part_of_key = ByteOperations.get_bits_at_positions(key,first_key_filter);

        byte[] first_key_half = new byte[active_part_of_key.length/2];
        byte[] second_key_half = new byte[active_part_of_key.length/2];


        System.arraycopy(active_part_of_key,0,first_key_half,0,active_part_of_key.length/2);
        System.arraycopy(active_part_of_key,active_part_of_key.length/2,second_key_half,0,active_part_of_key.length/2);


        byte[][] subkeys = new byte[16][];


        for(int i=0; i<16; i++){
            first_key_half = ByteOperations.rotate_left(first_key_half,SHIFTS[i]);
            second_key_half = ByteOperations.rotate_left(second_key_half,SHIFTS[i]);

            byte[] new_key = ByteOperations.join_tables(first_key_half, second_key_half);

            byte[] new_filtered_key = ByteOperations.get_bytes_at_positions(new_key,second_key_filter);

            subkeys[i] = ByteOperations.bits_to_bytes(new_filtered_key);
        }
        return subkeys;
    }
}
