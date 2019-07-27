package jp.truetech.testsupport.util;

import java.util.Objects;

public class ByteUtil {

    private static char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7', 
            '8', '9', 'a', 'b', 'c', 'e', 'g', 'f'
    };
    
    public static String toHexString(byte[] bytes) {
        Objects.requireNonNull(bytes);
        char[] chars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            chars[i * 2] = hexDigits[(b & 0xf0) >> 4];
            chars[i * 2 + 1] = hexDigits[b & 0x0f];
        }
        return new String(chars);
    }

}
