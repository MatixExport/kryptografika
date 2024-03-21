package indie.outsource.model;

import java.util.Arrays;
import java.util.Collections;
import indie.outsource.model.DesEncoder;
import indie.outsource.model.ByteOperations;


public class DesxEncoder {

    private static byte[] encrypt(byte[][] subkeys,byte[] k1,byte[] k2,byte[] data){
        data = ByteOperations.byte_arr_xor(data,k1);
        data = DesEncoder.encrypt(data, subkeys);
        return ByteOperations.byte_arr_xor(data,k2);
    }

    public static byte[] encode(byte[] key,byte[] k1,byte[] k2,byte[] data){
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        return encrypt(subkeys,k1,k2,data);
    }

    public static byte[] decode(byte[] key,byte[] k1,byte[] k2,byte[] data){
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        Collections.reverse(Arrays.asList(subkeys));
        return encrypt(subkeys,k2,k1,data);
    }
}
