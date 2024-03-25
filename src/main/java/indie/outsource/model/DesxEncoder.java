package indie.outsource.model;

import java.util.Arrays;
import java.util.Collections;

import indie.outsource.model.DesEncoder;
import indie.outsource.model.ByteOperations;
/*
            Autorzy
NAZWISKO    IMIE    INDEX
Giełczyński Mateusz 247662
Kubiś       Jakub   247712
 */

public class DesxEncoder {

    private static byte[] encrypt(byte[][] subkeys, byte[] k1, byte[] k2, byte[] data) {
        //DESX polega na rozszerzeniu klucza DES'a poprzez
        //wykonanie operacji XOR używając dwóch dodatkowych 64-bitowych kluczy
        //na danych przed i po szyfrowaniu standardowym DES'em

        data = ByteOperations.byte_arr_xor(data, k1);
        data = DesEncoder.encrypt(data, subkeys);
        return ByteOperations.byte_arr_xor(data, k2);
    }

    public static byte[] encode(byte[] key, byte[] k1, byte[] k2, byte[] data) {
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        return encrypt(subkeys, k1, k2, data);
    }

    public static byte[] decode(byte[] key, byte[] k1, byte[] k2, byte[] data) {
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        Collections.reverse(Arrays.asList(subkeys));
        return encrypt(subkeys, k2, k1, data);
    }
}
