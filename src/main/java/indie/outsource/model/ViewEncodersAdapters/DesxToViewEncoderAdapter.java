package indie.outsource.model.ViewEncodersAdapters;
import indie.outsource.model.DesxEncoder;
public class DesxToViewEncoderAdapter implements ViewEncoder {
    @Override
    public byte[] encode(byte[] msg, byte[][] keys) {
        return DesxEncoder.encode(keys[0],keys[1],keys[2],msg);
    }

    @Override
    public byte[] decode(byte[] msg, byte[][] keys) {
        return DesxEncoder.decode(keys[0],keys[1],keys[2],msg);
    }
}
