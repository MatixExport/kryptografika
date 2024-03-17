package indie.outsource.model.charsetAdapters;

import indie.outsource.model.CharsetAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import indie.outsource.model.ByteOperations;

public class ToCharCharsetAdapter implements CharsetAdapter {
    @Override
    public String byte_to_string(byte[] bytes) {

        //bytes.length >> 1 = floor(bytes.length.2)
        //we do this because char has 2 bytes
        char[] chars = new char[bytes.length >> 1];
        int position = 0;
        for (int i = 0; i < bytes.length ; i+=2) {
            chars[position++] = ByteOperations.combine_2_bytes(bytes[i],bytes[i+1]);
        }
        return new String(chars);

    }

    @Override
    public byte[] string_to_byte(String string) {
        //From each char we will get 2 bytes
        byte[] bytes = new byte[string.length() << 1];
        int pos = 0;
        char[] chars = string.toCharArray();
        int i = 0;
        while (i < chars.length) {
            bytes[pos] = (byte) ((chars[i] & 0xFF00) >> 8);
            bytes[pos+1] = (byte) ((chars[i] & 0x00FF));
            pos+=2;
            i++;
        }
        return bytes;
    }
}
