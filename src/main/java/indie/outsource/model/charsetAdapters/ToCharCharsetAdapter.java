package indie.outsource.model.charsetAdapters;

import indie.outsource.model.CharsetAdapter;
import indie.outsource.model.ByteOperations;

public class ToCharCharsetAdapter implements CharsetAdapter {
    @Override
    public String byte_to_string(byte[] bytes) {

        //bytes.length >> 1 = floor(bytes.length.2)
        //we do this because char has 2 bytes
        char[] chars = new char[bytes.length >> 1];
        int position = 0;
        for (int i = 0; i < bytes.length; i += 2) {
            chars[position++] = combine_2_bytes(bytes[i], bytes[i + 1]);
        }
        return new String(chars);
    }

    public static char combine_2_bytes(byte byte_1, byte byte_2) {
        return (char) ((((byte_1 << 8) & (0xFF00)) + ((byte_2) & 0x00FF)));
    }

    @Override
    public byte[] string_to_byte(String string) {
        //From each char we will get 2 bytes
        byte[] bytes = new byte[string.length() * 2];
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            bytes[2 * i] = (byte) ((chars[i] & 0xFF00) >> 8);
            bytes[2 * i + 1] = (byte) ((chars[i] & 0x00FF));
        }
        return bytes;
    }
}
