package indie.outsource;

import indie.outsource.model.ByteOperations;
import indie.outsource.model.DesEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesEncoderTest {
    @Test
    void test_des(){
        byte [] msg = new byte[]{0,0,0,0,0,0,0,0};

        byte[] key = new byte[]{(byte) 10000000, 00000001, 00000001, 00000001, 00000001, 00000001, 00000001, 00000001};
        assertEquals("95a8d72813daa94d", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,1,0,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("eec1487dd8c26d5", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,1,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("7ad16ffb79c45926", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));


        key = new byte[]{ 0,0,0,1,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("d3746294ca6a6cf3", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,1,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("809f5f873c1fd761", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,0,1,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("c02faffec989d1fc", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("4615aa1d33e72f10", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("2055123350c00858", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,0,0,0,1, 0,1,0,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("df3b99d6577397c8", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,0,0,0,1, 0,0,1,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("31fe17369b5288c9", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

        key = new byte[]{ 0,0,0,0,0,0,0,1, 0,0,0,1,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};
        key = ByteOperations.bits_to_bytes(key);
        assertEquals("dfdd3cc64dae1642", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

    }


}