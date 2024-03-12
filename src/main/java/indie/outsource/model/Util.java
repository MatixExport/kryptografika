package indie.outsource.model;

public class Util {
    public static byte[] remove_n_first_elements(byte[] arr,int n){
        byte[] output = new byte[arr.length-n];
        System.arraycopy(arr,n,output,0,arr.length-n);
        return output;
    }
}
