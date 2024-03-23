package indie.outsource.model.ViewEncodersAdapters;
import indie.outsource.model.DesEncoder;
public class DesToViewEncoderAdapter implements ViewEncoder {
    @Override
    public byte[] encode(byte[] msg, byte[][] keys) {
        return DesEncoder.encode(msg, keys[0]);
    }

    @Override
    public byte[] decode(byte[] msg, byte[][] keys) {
        return DesEncoder.decode(msg,keys[0]);
    }
}
