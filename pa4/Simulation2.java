//-----------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// Simulation.java
// Given a batch of m jobs, each with specific arrival times and durations, 
// determine (1) the total wait time (i.e. the total time spent by all m jobs 
// waiting in queues), (2) the maximum wait time (i.e. the longest wait any of 
// the m jobs endured) and (3) the average wait time over all m jobs. 
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation2{
    public static Job getJob(Scanner in){
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]); 
        return new Job(a,d);
    }

    public static void main(String[] args) throws IOException{
        int m;
        int time = 0;
        int totalWait=4;
        int maxWait=3;
        double averageWait = totalWait/maxWait;
        int currentTime = 0;
        int copytime = 0;
        int jobsLeft;

        // checking command line
        if(args.length < 1|| args.length > 1){
            throw new IOException("Usage: Simulation <input file>");
            
        }else{
            Scanner in = null;
            PrintWriter report = null;
            PrintWriter trace = null; 

            //open files for reading and writing
            in = new Scanner (new File(args[0]));
            report = new PrintWriter (new FileWriter(args[0]+".rpt"));
            trace = new PrintWriter (new FileWriter(args[0]+".trc"));

            //reading lines from in for header of report and trace
            m = in.nextInt();
            jobsLeft = m;
            Job []A = new Job[m+1];
            Queue B = new Queue();
            Queue C = new Queue();
            A[0] = null;
            report.println("Report file: "+args[0]+".rpt");
            trace.println("Trace file: "+args[0]+".rpt");
            report.println(m+" Jobs:");
            trace.println(m+" Jobs:");
           

            //saving jobs in an array
            for(int i=1;i<=m;i++){
                A[i] =  new Job(in.nextInt(),in.nextInt());
                B.enqueue(A[i]);
                report.print(A[i]+" ");
                trace.print(A[i]+" ");
            }
            report.println(" ");
            trace.println(" ");
            report.println("*****************************");
            

            for (int i=1;i<=m-1;i++){
                report.println(i+" processor: totalWait="+totalWait+", maxWait="+maxWait+", averageWait="+averageWait);
            }

            //for trace processors
            for (int i=1; i<=m-1; i++){
                trace.println("*****************************");
                trace.println(i+" processor: "); 
                trace.println("*****************************");

                while( time == 0 ){

                    for(int a=1;a<A.length; a++){
                        trace.println("time="+time);
                        trace.println("0:"+B);
                        trace.println("1:"+C);
                        C.enqueue(B.peek());
                        B.dequeue();
                        time = A[a].getArrival();
                        copytime = time;
                        A[a].computeFinishTime(time);
                        if((A[a].getFinish())<time){
                            trace.println("time="+A[a].getFinish());
                            trace.println("0:"+B);
                            trace.println("1:"+C);
                            B.enqueue(C.peek());
                            C.dequeue();
                        }

                        time = A[a].getArrival();
                        
                    }
                    
                }
                
            }
           

            //close files
            in.close();
            report.close();
            trace.close();

        }
        
    }

    
}