public class algorithm_analysis {
    /**
     * An algorithm with runtime complexity T(N) has a lower bound and an upper bound.

    * Lower bound: A function f(N) that is ≤ the best case T(N), for all values of N ≥ 0
.
    * Upper bound: A function f(N) that is ≥ the worst case T(N), for all values of N ≥ N_0
.
    * Time Complexity: the growth of the execution time as a function of the input size

    * Space Complexity: the growth of the memory space as a function of the input size

    * Recursion tree: A visual diagram of an operation done by a recursive function that separates operations done directly by the function and operations done by recursive calls.



     * Given a function T(N), an infinite number of lower bounds and upper bounds exist --> T(N) has variable N which could represent value. So, for all algos that have non-constant operations there exist infinite represntations of n, infinite lower and upper bounds that satisfy  N_0 = x, where x is a constant. (im confused see zybooks)
     * 
     * c · O(f(N)) ->	O(f(N))
     * c + O(f(N)) ->	O(f(N))
     * g(N) · O(f(N)) ->	O(g(N) · f(N))
     * g(N) + O(f(N))	-> O(g(N) + f(N)) 
     *
     * 
     * 
     * 
     * 
     * 
     * 
     *  
     * Binary search is incredibly efficient in finding an element within a sorted array. During each iteration or step of the algorithm, binary search reduces the search space (i.e., the remaining elements to search within) by about half. The search terminates when the element is found or the search space is empty (element not found). 
        *0(N) = Log_2(N) + 1 
     *
     * 
     */



    //O(n)
    public static int lSearch(int[] list, int key){
        for(int i=0; i<list.length; i++)
            if(list[i] == key)
            return i;
        return -1;
        }

    // O(log_2 (n)) since each time you are reducing search space by n/2
    public static int binarySearch(int[] list, int key){
        int first, last, middle;
        first = 0;
        last = list.length-1;
        while (first <= last){
            middle = (last + first) / 2;
            if (key == list[middle]) {
            return middle;
            }
            else if (key < list[middle])
            last = middle - 1;
            else
            first = middle + 1;
        }
        return -1;
        }

    //broken selection sort
    //O(n^2)
    public static int selectionSort(int[] list) {
        for(int i=0; i<list.length-1; i++){
            int minIndex = i;
            for(int j=i+1; j<list.length; j++){
                if (list[i] < list[minIndex]){
                minIndex = i;
                }
            }
            int temp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = temp;
        }
    return 1;
}

    //O(2^n) since calling n
    /*
 * Visualizing rFibonacci(5)
 * The tree shows the recursive calls. 
 * Note: Base case is n <= 2, so f(2) and f(1) return immediately.
 * * f(5)
 * /        \
 * f(4)        f(3) <----------+
 * /     \       /   \           |
 * f(3) f(2)    f(2)  f(1)        |
 * /   \                             |
 * f(2)  f(1)                          |
 * |
 * Total Operations:                     |
 * 1. Notice f(3) is calculated TWICE. --+
 * 2. f(2) is calculated THREE times.
 * 3. As n grows, this duplication doubles at every level.
 */
    public static int rFibonacci(int n){
        if (n <= 2)
            return 1;
        else
            return rFibonacci(n-1)+
                rFibonacci(n-2);
}


    //20 * n --> O(n)
    public static int nestedSequential(int n){
        int k = 0;
        for(int i=1; i<=n; i++){
            k=k+4;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=20; j++){
                k=k+i+j;
        }
        }
        return k;
    }

    //O(n^2)
    public static int nestedFor( int n){
        int k = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                k=k+i+j;
        }
    }
        return k;
  }

 //O(log_2 (n)) 
 /*

int result = 1;
for(int i=1; i<=n; i++){ <-- O(n) operation
 result *= a; <-- O(1) operation
}
 
 1st iter a  
 2nd iter a^2 <-- a -> a^2 is O(1)
 3rd iter a^3
 .   .   .
 result = a^n, iterating O(1) operation O(n times)

Compare to the method below, which is 
 1st iter: a^2 -> a^(2^1)
 2nd iter: a^4 -> a^(2^2)
 3rd iter: a^4 -> a^(2^3)
        .  .  .
 kth iteration: a^(2^k)

 let 2^k = n,
 log(2^k) = n
 k = log_2(n)

 . . .
confused

 */
   //Problem I am trying to solve: What is k that will allow me to get to n
 public static int lognTime(int a, int n){
   int result = a;
   double k = Math.log(n);
    for(int i=1; i<=k; i++){ //-- O(k)
    result *= result; // <-- O(1)
    }
    return result;
 }
}
