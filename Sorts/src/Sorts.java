//Dhruv Susheelkar
//Mr. Jan
//P: 2
public class Sorts {
    public static void InsertionSort(int a[]) {
        int tempnum = a.length;
        for (int i = 1; i < tempnum; ++i) {
            int key = a[i];
            int k = i-1;
            while ( (k > -1) && ( a[k] > key ) ) {
                a[k+1] = a[k];
                --k;
            }
            a[k+1] = key;
        }
    }
    public static void SelectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[index]) {
                    index = j;
                }
            }
            int lowernum = a[index];
            a[index] = a[i];
            a[i] = lowernum;
        }
    }
}
