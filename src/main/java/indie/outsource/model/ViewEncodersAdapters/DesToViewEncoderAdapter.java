package indie.outsource.model.ViewEncodersAdapters;
import indie.outsource.model.DesEncoder;
public class DesToViewEncoderAdapter implements ViewEncoder {
    @Override
    public byte[] encode(byte[][] keys, byte[] msg) {
        System.out.println("DES JAK  COS");
        return DesEncoder.encode(keys[0],msg);
    }

    @Override
    public byte[] decode(byte[][] keys, byte[] msg) {
        return DesEncoder.decode(keys[0],msg);
    }
}
