package indie.outsource.model;

import java.util.Arrays;
import java.util.Collections;

public class DesEncoder {
    public static byte[] encode(byte[] data, byte[] key){
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        return encrypt(data, subkeys);
    }

    public static byte[] decode(byte[] data, byte[] key){
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        Collections.reverse(Arrays.asList(subkeys));
        return encrypt(data, subkeys);
    }

    public static byte[] sBlock(byte[] right_data) {
        byte[] sbox_result = new byte[8];

        for (int j = 0; j < 8; j += 1) {
            byte[] row_indexes = ByteOperations.get_bits_at_positions_berlin(right_data,new int[] {j*6, j*6 + 5});
            byte[] column_indexes = ByteOperations.get_bits_at_positions_berlin(right_data,new int[] {j*6 + 1, j*6 + 2,j*6 + 3,j*6 + 4});
            sbox_result[j] = PermutationTables.sBox[j][row_indexes[0]][column_indexes[0]];
        }
        byte[] new_right_data =new byte[4];
        for (int j = 0; j < 8; j+=2) {
            new_right_data[j/2] = ByteOperations.join_bytes(sbox_result[j],4,sbox_result[j+1]);
        }

        return new_right_data;
    }
    public static byte[] encrypt(byte[] data, byte[][] subkeys){

        data = permute(data,PermutationTables.hardware_start_permutation_table);

        byte[][] data_halves = split_byteArr_in_half(data);

        for (int i = 0; i < 16; i++) {
            data_halves = des_round(data_halves,subkeys[i]);
        }

        byte [] output = join_byteArr(data_halves[1],data_halves[0]);

        output = permute(output,PermutationTables.hardware_end_permutation_table);
        return output;
    }

    private static byte[] permute(byte[] data, int[] permutation_table){
        return ByteOperations.get_bits_at_positions_berlin(data,permutation_table);
    }

    private static byte[][] split_byteArr_in_half(byte[] data){
        byte[][] output = new byte[2][data.length/2];

        System.arraycopy(data,0,output[0],0,data.length/2);
        System.arraycopy(data,data.length/2,output[1],0,data.length/2);

        return output;
    }

    private static byte[][] des_round(byte[][] data_halves, byte[] subkey){
        //ja nie wiedzioł gdzie dac clone a gdzie nie warto
        byte[] left_data  = data_halves[0].clone();
        data_halves[0] = data_halves[1];
        byte[] right_data  = data_halves[1].clone();

        right_data = permute(right_data,PermutationTables.r_permutation_table);
        right_data = ByteOperations.byte_arr_xor(right_data , subkey);

        byte[] new_right_data = sBlock(right_data);

        new_right_data = permute(new_right_data,PermutationTables.p_permutation_table);
        data_halves[1] = ByteOperations.byte_arr_xor(left_data ,new_right_data);

        return data_halves;
    }
    private static byte[] join_byteArr(byte[] array1, byte[] array2){
        byte[] output = new byte[array1.length + array2.length];

        System.arraycopy(array1,0,output,0,array1.length);
        System.arraycopy(array2,0,output,array1.length,array2.length);

        return output;
    }


}
