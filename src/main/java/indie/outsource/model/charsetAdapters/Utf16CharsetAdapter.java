package indie.outsource.model.charsetAdapters;

import indie.outsource.model.ByteOperations;
import indie.outsource.model.CharsetAdapter;

import java.nio.charset.StandardCharsets;
import indie.outsource.model.Util;

public class Utf16CharsetAdapter implements CharsetAdapter {
    @Override
    public String byte_to_string(byte[] bytes) {
        System.out.println("Recived bytes to string");
        System.out.println(ByteOperations.byte_arr_to_string(bytes));
        return new String(bytes, StandardCharsets.UTF_16);
    }

    @Override
    public byte[] string_to_byte(String string) {
        System.out.println("Original converted string");
        System.out.println(ByteOperations.byte_arr_to_hex(Util.remove_n_first_elements(
                string.getBytes(StandardCharsets.UTF_16),
                2
        )));
        return Util.remove_n_first_elements(
                string.getBytes(StandardCharsets.UTF_16),
                2
        );
    }
}
