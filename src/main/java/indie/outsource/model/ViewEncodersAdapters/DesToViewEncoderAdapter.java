package indie.outsource.model.ViewEncodersAdapters;
import indie.outsource.model.DesEncoder;
public class DesToViewEncoderAdapter implements ViewEncoder {
    @Override
    public byte[] encode(byte[] msg, byte[][] keys) {
        System.out.println("DES JAK  COS");
        return DesEncoder.encode(msg, keys[0]);
    }

    @Override
    public byte[] decode(byte[] msg, byte[][] keys) {
        return DesEncoder.decode(keys[0],msg);
    }
}
