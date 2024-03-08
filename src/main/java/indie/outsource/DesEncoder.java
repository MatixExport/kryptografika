package indie.outsource;

public class DesEncoder {
//    private byte[] key;
    static final byte[][][] sBox =
            {
                    {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, // S1
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},
                    {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10}, // S2
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},
                    {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, // S3
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},
                    {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, // S4
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},
                    {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,}, // S5
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},
                    {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,}, // S6
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},
                    {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,}, // S7
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},
                    {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,}, // S8
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}
            };
    //to przepisałem manualnie z prezentacji więc pewnie jest coś źle(np kierunek
    //bo nie odróżniam lewej od prawej)
    static final int[] r_permutation_table = {31,0,1,2,3,4,3,4,5,6,7,8,7,8,9,10,11,12,11,
    12,13,14,15,16,15,16,17,18,19,20,19,20,21,22,23,24,23,24,25,26,27,28,27,28,29,30,
    31,0};
    private byte[] data;

    public DesEncoder(byte[] key) {
//        this.key = key;
//        this.data = data;
//        System.out.println(Arrays.toString(key));
    }

    public static byte[][] get_subkeys(byte[] key){
        final int[] first_key_filter = {56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42
                , 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60,
                52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
        final int[] second_key_filter = {13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19,
                12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31};
        final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

        byte[] active_part_of_key = ByteOperations.get_bytes_at_positions(key,first_key_filter);
//        System.out.println(active_part_of_key.length);
        byte[] first_key_half = new byte[active_part_of_key.length/2];
        byte[] second_key_half = new byte[active_part_of_key.length/2];
//        System.out.println("Aktiv");
//        System.out.println(ByteOperations.byte_arr_to_string(active_part_of_key));

        System.arraycopy(active_part_of_key,0,first_key_half,0,active_part_of_key.length/2);
        System.arraycopy(active_part_of_key,active_part_of_key.length/2,second_key_half,0,active_part_of_key.length/2);


        byte[][] subkeys = new byte[16][];


        for(int i=0; i<16; i++){
//            System.out.println("FIrst key half");
//            System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.bites_to_bytes( first_key_half)));
//            System.out.println("Second key half");
//            System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.bites_to_bytes(second_key_half)));

            first_key_half = ByteOperations.rotate_left(first_key_half,SHIFTS[i]);
            second_key_half = ByteOperations.rotate_left(second_key_half,SHIFTS[i]);

            byte[] new_key = ByteOperations.join_tables(first_key_half, second_key_half);

            byte[] new_filtered_key = ByteOperations.get_bytes_at_positions(new_key,second_key_filter);

            subkeys[i] = ByteOperations.bits_to_bytes(new_filtered_key);
//            System.out.println("KEY:"+i);
//            System.out.println(ByteOperations.byte_arr_to_string(subkeys[i]));
        }
        //JEST RACZEJ DOBRZE
        return subkeys;
    }

//    public static byte[] sBlock() {
//
//    }
    public static byte[] encode(byte[] key,byte[] data){
        byte[][] subkeys = get_subkeys(key);
        byte[] left_data = new byte[data.length/2];
        byte[] right_data = new byte[data.length/2];

        
        System.arraycopy(data,0,left_data,0,data.length/2);
        System.arraycopy(data,data.length/2,right_data,0,data.length/2);

        left_data = ByteOperations.bits_to_bytes(left_data);
        right_data = ByteOperations.bits_to_bytes(right_data);

//        System.out.println("Just Split:");
//        System.out.println(ByteOperations.byte_arr_to_string(right_data));

        for (int i = 0; i < 16; i++) {
            System.out.println("Round:" + i);
            byte[] temp_right_data = right_data;

            right_data = ByteOperations.get_bites_at_positions(right_data,r_permutation_table);
            System.out.println("Sblock we:");
            System.out.println(ByteOperations.byte_arr_to_string(right_data));
            right_data = ByteOperations.byte_arr_xor(ByteOperations.bits_to_bytes(right_data) , subkeys[i]);

            byte[] sbox_result = new byte[8];

            for (int j = 0; j < 8; j += 1) {
                byte[] row = ByteOperations.get_bites_at_positions(right_data,new int[] {j*6, j*6 + 5});
                row = ByteOperations.bits_to_bytes(row);
                byte[] column = ByteOperations.get_bites_at_positions(right_data,new int[] {j*6 + 1, j*6 + 2,j*6 + 3,j*6 + 4});
                column = ByteOperations.bits_to_bytes(column);
                sbox_result[j] = sBox[j][row[0]][column[0]];
            }
//            System.out.println(ByteOperations.byte_arr_to_string(sbox_result));
            byte[] new_right_data =new byte[4];
            for (int j = 0; j < 8; j+=2) {
                new_right_data[j/2] = ByteOperations.join_bytes(sbox_result[j],4,sbox_result[j+1]);
            }
//            if((i == 0)||(i == 1)){
//                System.out.println(ByteOperations.byte_arr_to_string(new_right_data));
//            }
            right_data = ByteOperations.byte_arr_xor(left_data , new_right_data);
            left_data = temp_right_data;
        }

        byte [] output = new byte[8];
        System.arraycopy(right_data,0,output,0,4);
        System.arraycopy(left_data,0,output,4,4);
        return output;
    }




}
