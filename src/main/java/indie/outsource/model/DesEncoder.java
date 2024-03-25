package indie.outsource.model;

import java.util.Arrays;
import java.util.Collections;

public class DesEncoder {
    public static byte[] encode(byte[] data, byte[] key) {
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        return encrypt(data, subkeys);
    }

    public static byte[] decode(byte[] data, byte[] key) {
        byte[][] subkeys = KeyGenerator.get_subkeys(key);
        Collections.reverse(Arrays.asList(subkeys));
        return encrypt(data, subkeys);
    }

    public static byte[] encrypt(byte[] data, byte[][] subkeys) {
        //permutacja IP1
        data = permute(data, PermutationTables.hardware_start_permutation_table);

        //dzielimy dane na prawą i lewą cześć
        //które są arugmentami rund algorytmu DES
        byte[][] data_halves = ByteOperations.split_byteArr_in_half(data);

        //wykonanie 16 rund
        for (int i = 0; i < 16; i++) {
            data_halves = des_round(data_halves, subkeys[i]);
        }

        //po ostatniej rundzie łączymy części na odwrót
        byte[] output = ByteOperations.join_tables(data_halves[1], data_halves[0]);

        //permutacja IP1-1
        output = permute(output, PermutationTables.hardware_end_permutation_table);
        return output;
    }


    private static byte[] permute(byte[] data, int[] permutation_table) {
        return ByteOperations.get_bits_at_positions(data, permutation_table);
    }


    private static byte[][] des_round(byte[][] data_halves, byte[] subkey) {
        byte[] left_data = data_halves[0];

        //nowa lewa część to stara prawa część
        data_halves[0] = data_halves[1];
        byte[] right_data = data_halves[1];

        //permutacja prawej częsci dająca 48 bitów
        right_data = permute(right_data, PermutationTables.r_permutation_table);
        //xor prawej cześci z podkluczem
        right_data = ByteOperations.byte_arr_xor(right_data, subkey);

        //sBlock prawej części danych
        byte[] new_right_data = sBlock(right_data);

        //permutacja po sboksie tabelą P
        new_right_data = permute(new_right_data, PermutationTables.p_permutation_table);

        //nowa prawa część to stara lewa część ^ f(stara prawa część)
        data_halves[1] = ByteOperations.byte_arr_xor(left_data, new_right_data);

        return data_halves;
    }

    public static byte[] sBlock(byte[] right_data) {
        //każde 6-bitów z prawej części danych stworzy
        //klucz do odczytu danych z sboksa
        //prawy i lewy skrajny bit dają indeks wiersza
        //pozostałe bity dają indeks kolumny

        //wartości odczytane z sboksa mają 4 bity
        //zatem 48 bitów na wejściu -> 32bity na wyjściu
        byte[] sbox_result = new byte[8];

        //odczytujemy wartości z sboksa dla 8 grup po 6 bitów z prawej częsci danych
        for (int j = 0; j < 8; j += 1) {
            byte[] row_indexes = ByteOperations.get_bits_at_positions(right_data, new int[]{j * 6, j * 6 + 5});
            byte[] column_indexes = ByteOperations.get_bits_at_positions(right_data, new int[]{j * 6 + 1, j * 6 + 2, j * 6 + 3, j * 6 + 4});
            sbox_result[j] = PermutationTables.sBox[j][row_indexes[0]][column_indexes[0]];
        }
        //łaczymy odczytanie 8 grup 4 bitów w pełnie bajty
        byte[] new_right_data = new byte[4];
        for (int j = 0; j < 8; j += 2) {
            new_right_data[j / 2] = ByteOperations.join_bytes(sbox_result[j], 4, sbox_result[j + 1]);
        }

        return new_right_data;
    }

}
