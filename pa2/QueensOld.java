//-------------------------
//Yongshi Sun ID:1619410
// Queens.java
// will find all the solutions to the n-Queens problem for 1<=n<=15
//-----------------------

class QueensOld{
   public static void main (String[] args)throws Exception{

      int sum=0;

      if (args.length==2){
     // String o = args[0];



      //return and print all solutions if they put -v
        int  m = Integer.parseInt(args[1]);
         if ( m>=1 && m<=15){
            //initiallizing this array to all zeros bc no queens are placed on board yet
            int[][] X = new int [m+1][m+1];
            for(int i=0;i<X.length;i++){
                for (int j=0;j<X.length;j++){
                   X[i][j]=0;
                }
             }
 
 
             //calling findSolution
             findSolution(X,1,args[0]);
 
 
          }
       }
    }
    


            System.out.println(m+"-Queens has "+s+" solutions");
         }else{
            System.out.println("Usage: Queens [-v] number");
            System.out.println("Option: -v verbose output, print all solutions");
         }
      // return solution but dont print solution if they did not add -v
      }else if(o != v){
         if(isInt(args[0])){
         n = Integer.parseInt(o);
         if (n>=1 && n<=15){
            int[][] Y = new int[n+1][n+1];
            for(int i=0;i<Y.length;i++){
               for(int j=0;j<Y.length;j++){
                  Y[i][j]=i;
               }
            }

            //change
         while (Y[0]==0){
            nextPermutation(Y);
            if (isSolution(Y)==true){
               s=s+1;
            }
         }




         System.out.println(n+"-Queens has "+s+" solutions");
         }return;

}else{System.out.println("Usage: Queens [-v] number");System.out.println("Option: -v verbose output, print all solutions");}}else{System.out.println("Usage: Queens [-v] number");System.out.println("Option: -v verbose output, print all solutions");}

}else{System.out.println("Usage: Queens [-v] number");System.out.println("Option: -v verbose output, print all solutions");}}