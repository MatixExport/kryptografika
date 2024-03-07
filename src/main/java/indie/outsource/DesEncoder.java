package indie.outsource;

import java.util.Arrays;

public class DesEncoder {
    private byte[] key;
    private byte[] data;

    public DesEncoder(byte[] key) {
        this.key = key;
//        this.data = data;
//        System.out.println(Arrays.toString(key));
    }

    public byte[][] get_subkeys(){
        final int[] first_key_filter = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
        final int[] second_key_filter = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
        final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

        byte[] active_part_of_key = ByteOperations.get_bites_at_positions(key,first_key_filter);
        System.out.println(active_part_of_key.length);
        byte[] first_key_half = new byte[active_part_of_key.length/2];
        byte[] second_key_half = new byte[active_part_of_key.length/2];
        System.out.println("Aktiv");
        System.out.println(ByteOperations.byte_arr_to_string(active_part_of_key));

        System.arraycopy(active_part_of_key,0,first_key_half,0,active_part_of_key.length/2);
        System.arraycopy(active_part_of_key,active_part_of_key.length/2,second_key_half,0,active_part_of_key.length/2);


        byte[][] subkeys = new byte[16][];

        for(int i=0; i<16; i++){
            System.out.println("FIrst key half");
            System.out.println(ByteOperations.byte_arr_to_string(first_key_half));
            System.out.println("Second key half");
            System.out.println(ByteOperations.byte_arr_to_string(second_key_half));
            first_key_half = ByteOperations.rotate_left(first_key_half,SHIFTS[i]);
            second_key_half = ByteOperations.rotate_left(second_key_half,SHIFTS[i]);

            byte[] new_key = ByteOperations.join_tables(first_key_half, second_key_half);

            byte[] new_filtered_key = ByteOperations.get_bites_at_positions(new_key,second_key_filter);

            subkeys[i] = ByteOperations.bites_to_bytes(new_filtered_key);
        }


        return subkeys;
    }

}
