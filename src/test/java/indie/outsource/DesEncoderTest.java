package indie.outsource;

import indie.outsource.model.ByteOperations;
import indie.outsource.model.DesEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesEncoderTest {
    @Test
    void test_des(){
        byte [] msg = new byte[]{0,0,0,0,0,0,0,0};
        byte[] key = new byte[]{1,0,0,0,0 ,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,1};

        assertEquals("95a8d72813daa94d", ByteOperations.byte_arr_to_hex(DesEncoder.encode(msg, key)));

    }


}