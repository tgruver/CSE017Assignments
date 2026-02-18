
public class gcd {
    
public static int gcd_1(int m, int n){ //O(n) assuming n < m
   	int divisor = 1;
   	for(int i=2; i<m && i<n; i++){
    	  if(m%i == 0 && n%i == 0)
        	divisor = i;
    	}
     return divisor;
} 

public static int gcd_2(int m, int n){ // O(n) assuming n < m

  int divisor = 1;
     for(int i=n; i>=1; i--){
    	   if(m%i == 0 && n%i == 0){
        	divisor = i; 
        	break;
     	   }
   }
   return divisor;
}


public static int gcd_3(int m, int n) { //O(n)
  	int divisor = 1;
  	if(m%n == 0) 
    		return n;
  	for(int i = n/2; i >= 1; i--){
     		if(m%i == 0 && n%i == 0){
        		divisor=i; 
        		break;
      	}
  	}
  	return divisor;
}


public static int gcd_4(int m, int n){// O(log(n)) assuming n<m 
   if(m%n == 0) 
    	return n;
   else
    	return gcd_4(n, m%n);
}


public static void compareExecutionTImes(){
    for(int i = 0;i<20;i++){
        int n = (int)(Math.random() * 1000000);
        int m = (int)(Math.random() * 1000000);
        long start = System.nanoTime();
        gcd_1(m, n);
        long end = System.nanoTime();
        long one = end - start;
        

        start = System.nanoTime();
        gcd_2(m, n);
        end = System.nanoTime();
        long two = end - start;

        
        start = System.nanoTime();
        gcd_3(m, n);
        end = System.nanoTime();
        long three = end - start;


        start = System.nanoTime();
        gcd_4(m, n);
        end = System.nanoTime();
        long four = end - start;
        System.out.printf("%-10d\t%-10d\t%-10d\t%-10d\t%-10d\t%-10d\t",n,m, one, two,three,four);
    }

}
    public static void main(String[] args){
        compareExecutionTImes();
    }


}
