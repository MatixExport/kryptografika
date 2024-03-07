package indie.outsource;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ByteOperationsTest {

    @Test
    void test(){
        assertEquals(0, ByteOperations.get_byte_at(new byte[]{10},0));
        assertEquals(1, ByteOperations.get_byte_at(new byte[]{10},1));
        assertEquals(0, ByteOperations.get_byte_at(new byte[]{10},2));
        assertEquals(1, ByteOperations.get_byte_at(new byte[]{10},3));
    }
    @Test
    void test2(){
        byte[] tested = ByteOperations.get_bytes_at_positions(new byte[]{10},new int[]{0,1,2,3});
        assertEquals(0, tested[0]);
        assertEquals(1, tested[1]);
        assertEquals(0, tested[2]);
        assertEquals(1, tested[3]);
    }
    @Test
    void test3(){
        byte[] tested = ByteOperations.get_bytes_at_positions(new byte[]{10},new int[]{0,1,2,3});
        byte[] tested2 = ByteOperations.get_bytes_at_positions(new byte[]{10},new int[]{0,1,2,3});

        System.out.println(Arrays.toString(tested));
        System.out.println(Arrays.toString(ByteOperations.join_tables(tested, tested2)));
        System.out.println((ByteOperations.join_tables(tested, tested2))[0]);

    }
    @Test()
    void test5(){
        String wasd = "0000111100101010101010";
        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.byte_string_to_byte(wasd)));
    }
    @Test
    void test_set_bit_at(){
        byte[] bytesArray = new byte[1];
        byte testByte = 0;
        bytesArray[0] = testByte;
        ByteOperations.set_bit_at_index(bytesArray,3,1);
        System.out.println(ByteOperations.byte_to_string(bytesArray[0]));
        ByteOperations.set_bit_at_index(bytesArray,5,1);
        System.out.println(ByteOperations.byte_to_string(bytesArray[0]));
        ByteOperations.set_bit_at_index(bytesArray,5,0);
        System.out.println(ByteOperations.byte_to_string(bytesArray[0]));
    }
    @Test
    void test_permutation_table(){
       byte[] data = new byte[]{
                0,0,0,0, 0,0,0,1, 0,0,1,0, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,0, 0,1,1,1,
                1,0,0,0, 1,0,0,1, 1,0,1,0, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,0, 1,1,1,1
        };


        byte[] right_data = new byte[data.length/2];
        System.arraycopy(data,data.length/2,right_data,0,data.length/2);


       final int[] r_permutation_table = {31,0,1,2,3,4,3,4,5,6,7,8,7,8,9,10,11,12,11,
                12,13,14,15,16,15,16,17,18,19,20,19,20,21,22,23,24,23,24,25,26,27,28,27,28,29,30,
                31,0};

       byte[] valid_right_data = new byte[]{
               1,1,0,0,0,1
       };

        right_data = ByteOperations.get_bites_at_positions(right_data,r_permutation_table);

        for (int i = 0; i < valid_right_data.length ; i++) {
            assertEquals(valid_right_data[i],right_data[i]);

        }
    }
    @Test
    void test_subkey_generation() {
        byte[] secretKey = "qwertyui".getBytes();

        byte[] xd = new byte[]{0,0,0,1, 0,0,1,1, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,1, 0,1,1,1, 1,0,0,1, 1,0,0,1, 1,0,1,1, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,1, 1,1,1,1, 0,0,0,1};

        System.out.println(Arrays.toString(
                DesEncoder.decode(xd,
                DesEncoder.encode(xd, new byte[]{
                        0,0,0,0, 0,0,0,1, 0,0,1,0, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,0, 0,1,1,1, 1,0,0,0, 1,0,0,1, 1,0,1,0, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,0, 1,1,1,1
        }))));

    }

}