package indie.outsource.model.ViewEncodersAdapters;

public interface ViewEncoder {
    public byte[] encode(byte[][] keys,byte[] msg);
    public byte[] decode(byte[][] keys,byte[] msg);
}
