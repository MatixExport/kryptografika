package indie.outsource.model.ViewEncodersAdapters;

public interface ViewEncoder {
    public byte[] encode(byte[] msg, byte[][] keys);
    public byte[] decode(byte[] msg, byte[][] keys);
}
