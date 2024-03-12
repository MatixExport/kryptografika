package indie.outsource.model.charsetAdapters;

import indie.outsource.model.CharsetAdapter;
import java.util.Base64;

public class Base64CharsetAdapter implements CharsetAdapter {
    @Override
    public String byte_to_string(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public byte[] string_to_byte(String string) {
        return Base64.getDecoder().decode(string);
    }
}
