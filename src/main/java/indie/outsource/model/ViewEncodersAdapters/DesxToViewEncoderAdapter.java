package indie.outsource.model.ViewEncodersAdapters;
import indie.outsource.model.DesxEncoder;
public class DesxToViewEncoderAdapter implements ViewEncoder {
    @Override
    public byte[] encode(byte[][] keys, byte[] msg) {
        return DesxEncoder.encode(keys[0],keys[1],keys[2],msg);
    }

    @Override
    public byte[] decode(byte[][] keys, byte[] msg) {
        return DesxEncoder.decode(keys[0],keys[1],keys[2],msg);
    }
}
