//Dhruv Susheelkar
//Mr. Jan
//Period: 2
public class MergeSort {
    //b at start will be 0 and c will be array's length minus 1
    void Mergesort(int a[], int b, int c)
    {
        if (b < c) {
            //Finds the middle number
            int d =b+ (c-b)/2;

            //Sorts each half
            Mergesort(a, b, d);
            Mergesort(a, d + 1, c);
            //Merging two halves
            int tempnum1 = d - b + 1;
            int tempnum2 = c - d;
            int temparr1[] = new int[tempnum1];
            int temparr2[] = new int[tempnum2];
            for (int i = 0; i < tempnum1; ++i) {
                temparr1[i] = a[b + i];
            }
            for (int j = 0; j < tempnum2; ++j) {
                temparr2[j] = a[d + 1 + j];
            }
            int i = 0, j = 0;
            int k = b;
            while (i < tempnum1 && j < tempnum2) {
                if (temparr1[i] <= temparr2[j]) {
                    a[k] = temparr1[i];
                    i++;
                }
                else {
                    a[k] = temparr2[j];
                    j++;
                }
                k++;
            }
            while (i < tempnum1) {
                a[k] = temparr1[i];
                i++;
                k++;
            }
            while (j < tempnum2) {
                a[k] = temparr2[j];
                j++;
                k++;
            }
        }
    }
}