package indie.outsource;

import indie.outsource.model.ByteOperations;
import indie.outsource.model.DesEncoder;
import indie.outsource.model.DesxEncoder;
import indie.outsource.model.StolenEncoder;
import org.junit.Assert;
import java.util.Base64;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.basic.BasicButtonUI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import indie.outsource.View.ViewMainController;

import static org.junit.jupiter.api.Assertions.*;

class ByteOperationsTest {

    @Test
    void test(){
        assertEquals(0, ByteOperations.get_bit_at(new byte[]{10},0));
        assertEquals(1, ByteOperations.get_bit_at(new byte[]{10},1));
        assertEquals(0, ByteOperations.get_bit_at(new byte[]{10},2));
        assertEquals(1, ByteOperations.get_bit_at(new byte[]{10},3));
    }
    @Test
    void test2(){
        byte[] tested = ByteOperations.get_bits_at_positions(new byte[]{10},new int[]{0,1,2,3});
        assertEquals(0, tested[0]);
        assertEquals(1, tested[1]);
        assertEquals(0, tested[2]);
        assertEquals(1, tested[3]);
    }
    @Test
    void test3(){
        byte[] tested = ByteOperations.get_bits_at_positions(new byte[]{10},new int[]{0,1,2,3});
        byte[] tested2 = ByteOperations.get_bits_at_positions(new byte[]{10},new int[]{0,1,2,3});

        System.out.println(Arrays.toString(tested));
        System.out.println(Arrays.toString(ByteOperations.join_tables(tested, tested2)));
        System.out.println((ByteOperations.join_tables(tested, tested2))[0]);

    }
    @Test()
    void test5(){
        String wasd = "0000111100101010101010";
        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.byte_string_to_byte(wasd)));
    }
    @Test()
    void test_packaging(){
        byte[] msg = ByteOperations.bits_to_bytes(
                new byte[]{1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,

                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,
                        1,1,1,1,0,0,0,0,















                      }
        );
        byte[][] packages  = ByteOperations.split_into_packages(msg);
        System.out.println(ByteOperations.byte_arr_to_string(packages[packages.length-1]));
        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.unpackage_msg(packages)));
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
        ByteOperations.set_bit_at_index(bytesArray,5,0);
        System.out.println(ByteOperations.byte_to_string(bytesArray[0]));
    }
    @Test
    void get_bits_at_positions_test(){
        int[] positions = new int[] {31,0,1,2,3,4,5,6,7,31};
        byte[] data = new byte[]{
                0,0,0,0, 0,0,0,1, 0,0,1,0, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,0, 0,1,1,1,1,0,0,0, 1,0,0,1, 1,0,1,0, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,0, 1,1,1,1

        };
        byte[] data_in_bytes = ByteOperations.bits_to_bytes(data);
        System.out.println(ByteOperations.byte_arr_to_string(data_in_bytes));
        System.out.println("UWAGA");
        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.get_bits_at_positions_berlin(data_in_bytes,positions)) );
        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.bits_to_bytes_little_endian(ByteOperations.get_bits_at_positions_berlin(data_in_bytes,positions))));
        byte[] bits_in_positions = ByteOperations.get_bits_at_positions(data_in_bytes,positions);

        byte[] data2 = new byte[]{0,0,0,0,1,1,1,1};

        byte[] data2_in_bytes = ByteOperations.bits_to_bytes(data2);

//        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.get_bits_at_positions_berlin(data2_in_bytes,positions)));


//        System.out.println(ByteOperations.byte_arr_to_string(data2_in_bytes));
//        System.out.println(ByteOperations.get_bit_at(data2_in_bytes,7));
//        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.get_bits_at_positions(data2_in_bytes,positions)));
//
//        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.bits_to_bytes(ByteOperations.get_bits_at_positions(data2_in_bytes,positions))));
//        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.bits_to_bytes(ByteOperations.get_bits_at_positions_berlin(data2_in_bytes,positions))));


        //coś tu jest nie tak



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
        valid_right_data = ByteOperations.bits_to_bytes(valid_right_data);
        right_data = ByteOperations.bits_to_bytes(right_data);
        right_data = ByteOperations.get_bits_at_positions(right_data,r_permutation_table);
        System.out.println(ByteOperations.byte_arr_to_string(ByteOperations.bits_to_bytes(right_data)));
        for (int i = 0; i < valid_right_data.length ; i++) {
//            assertEquals(valid_right_data[i],right_data[i]);

        }
    }
    @Test
    void byte_conversion_test(){
        byte[] test = new byte[]{0b00001111, (byte) 0b100011110, (byte) 0b10001111,0b01111111,0b00011111,0b00001111,0b00001111,0b00001111,8,8,8,8,8,8,8,8};
        System.out.println(ByteOperations.byte_arr_to_string(new String(test, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8)));
    }
    @Test
    void test_subkey_generation() throws Exception {

        byte[] uf = new byte[]{0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,1,1,1};


        byte[] secretKey = "qwertyui".getBytes();

        byte[] xd = new byte[]{0,0,0,1, 0,0,1,1, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,1, 0,1,1,1, 1,0,0,1, 1,0,0,1, 1,0,1,1, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,1, 1,1,1,1, 0,0,0,1};
        byte[] xd2 = new byte[]{1,1,0,1, 0,0,1,1, 0,0,1,1, 0,0,1,1, 0,1,0,1, 0,1,1,1, 0,1,1,1, 1,0,0,1, 0,1,1,1, 1,0,1,1, 1,0,1,1, 1,0,0,1, 1,1,0,1, 1,1,0,0, 1,1,1,1, 0,0,0,1};
        byte[] xd3 = new byte[]{1,0,0,1, 1,0,1,1, 0,0,1,1, 0,0,1,1, 0,1,0,1, 1,1,0,0, 0,1,0,1, 1,0,0,1, 0,1,1,1, 1,0,1,1, 1,0,0,1, 1,0,1,1, 1,1,0,1, 1,1,0,0, 1,1,1,1, 0,0,0,1};





        byte [] og_msg = ByteOperations.bits_to_bytes(new byte[]{
                      1,1,1,1, 1,1,1,1, 0,0,1,0, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,0, 0,1,1,1, 1,0,0,0, 1,0,0,1, 1,0,1,0, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,0, 1,1,1,1});
        System.out.println("Oryginalna Wiadomość");
        System.out.println(ByteOperations.byte_arr_to_hex(og_msg));
        System.out.println(ByteOperations.byte_arr_to_string(og_msg));
        byte[] redemption = DesEncoder.encode(xd,og_msg)  ;
            System.out.println("Zaszyfrowana wiadomość:");
            System.out.println(ByteOperations.byte_arr_to_hex(redemption));
              redemption = DesEncoder.decode(xd,redemption);
              System.out.println("Odszyfrowana wiadomość:");
              System.out.println(ByteOperations.byte_arr_to_string(redemption));



        System.out.println("DESX OG");
        System.out.println(ByteOperations.byte_arr_to_string(og_msg));
        byte[] k1 = ByteOperations.bits_to_bytes(xd2);
        byte[] k2 = ByteOperations.bits_to_bytes(xd3);
        byte[] redemption2 = DesxEncoder.encode(xd,k1,k2,og_msg);
        System.out.println("Zaszyfrowana wiadomość:");
        System.out.println(ByteOperations.byte_arr_to_hex(redemption2));
        redemption2 = DesxEncoder.decode(xd,k1,k2,redemption2);
        System.out.println("Odszyfrowana wiadomość:");
        System.out.println(ByteOperations.byte_arr_to_string(redemption2));

    }
    @Test
    void test_des(){
        byte [] msg = new byte[]{0,0,0,0,0,0,0,0};
        byte[] xd = new byte[]{1,0,0,0,0 ,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        assertEquals("95a8d72813daa94d",ByteOperations.byte_arr_to_hex(DesEncoder.encode(xd,msg)));
    }
    @Test
    void test_cipher_to_text_conversion(){
        byte test = (byte) 0b10100000;
        byte [] msg = new byte[]{0,0,0,0,0,0,(byte) 0b10000100,0};
        byte[] xd = new byte[]{1,0,0,0,0 ,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
//        byte [] msg_converted = new String(msg,StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8);
        byte [] msg_converted = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(msg));

        System.out.println(ByteOperations.byte_arr_to_hex(msg));
        System.out.println(ByteOperations.byte_arr_to_string(msg));
        System.out.println(ByteOperations.byte_arr_to_hex(msg_converted));
//        System.out.println(ByteOperations.byte_to_string(test));

    }

}