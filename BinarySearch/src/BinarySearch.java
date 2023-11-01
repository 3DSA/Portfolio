public class BinarySearch {
    public static void BinarySearch(int[] a, int b) {
        int c = a.length/2;
        if(a.length == 2) {
            c = 0;
        }
        int[] temp = new int[c+1];
        if(a[c] == b) {
            System.out.println("Found");
        }
        else if(b < a[c]) {
            for(int i = 0; i < c; ++i) {
                temp[i] = a[i];
            }
            BinarySearch(temp, b);
        }
        else if( b > a[c]) {
            for(int i = 0; i < c; ++i) {
                temp[i] = a[i+c];
            }
            BinarySearch(temp, b);
        }
    }
}
