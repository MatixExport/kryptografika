package indie.outsource.model.charsetAdapters;

import indie.outsource.model.CharsetAdapter;

public class Utf32CharsetEncoder implements CharsetAdapter {
    @Override
    public String byte_to_string(byte[] bytes) {
        return null;
    }

    @Override
    public byte[] string_to_byte(String string) {
        return new byte[0];
    }
}
