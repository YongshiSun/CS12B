
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

public class Simulation {
    public static void main(String[] args) throws IOException {
        int m;
        int time = 0;
        int totalWait = 4;
        int maxWait = 3;
        double averageWait = totalWait / maxWait;
        int currentTime = 0;
        int copytime = 0;
        int jobsLeft, jobsDone;
        int processor;

        // checking command line for correct usage
        if (args.length < 1 || args.length > 1) {
            throw new IOException("Usage: Simulation <input file>");

        } else {
            Scanner in = null;
            PrintWriter report = null;
            PrintWriter trace = null;

            // open files for reading and writing
            in = new Scanner(new File(args[0]));
            report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
            trace = new PrintWriter(new FileWriter(args[0] + ".trc"));

            // read in m jobs from input file
            m = in.nextInt();
            processor = m - 1;

            Queue[] N = new Queue[processor + 1]; // making i of queues for i processor
            Job Backup[] = new Job[m]; // backup
            Queue zero = new Queue(); // all the jobs for the header
            Queue newZero = new Queue(); // all the jobs for 0:

            for (int i = 0; i < processor + 1; i++) {
                N[i] = new Queue(); //

            }

            // adding jobs to 0th processor as a queue
            for (int i = 0; i < m; i++) {
                Job j = new Job(in.nextInt(), in.nextInt());
                zero.enqueue(j);
                newZero.enqueue(j);
                Backup[i] = j;
                N[0].enqueue(j);
            }

            // header for trace
            trace.println("Trace file: " + args[0] + ".trc");
            trace.println(m + " Jobs:");
            trace.println(zero);

            // run simulation with n processors for n = 1 to n=m-1
            for (int n = 1; n < processor + 1; n++) {
                jobsLeft = m;
                jobsDone = 0;

                if (n == 1) {
                    trace.println("*****************************");
                    trace.println(n + " processor:");
                    trace.println("*****************************");
                } else {
                    trace.println("*****************************");
                    trace.println(n + " processors:");
                    trace.println("*****************************");
                }
                while (jobsDone != m) {
                    trace.println("time=" + time);

                    // prints out the lines
                    for (int j = 0; j < n + 1; j++) {
                        trace.print(j + ":");
                        trace.println(N[j]);
                    }

                    // for (int y = 1; y < processor; y++
                    if (N[0] != null) {
                        Job Zpeek = (Job) N[0].peek();
                        time = Zpeek.getArrival();
                        N[n].enqueue(N[0].dequeue());
                    }else{
                            jobsDone++; 
                         }
                    jobsDone++;
                    // }
                }

            }

            // header for report

            report.println("Report file: " + args[0] + ".rpt");
            report.println(m + " Jobs:");
            report.println(zero);
            report.println("***********************************************************");
            for (int y = 1; y <= processor; y++) {
                if (y == 1) {
                    report.println(y + " processor: totalWait= " + totalWait + ", maxWait= " + maxWait
                            + ", averageWait=" + averageWait);
                } else {
                    report.println(y + " processors: totalWait= " + totalWait + ", maxWait= " + maxWait
                            + ", averageWait=" + averageWait);
                }
            }

            // close files
            in.close();

            report.close();
            trace.close();

        }

    }

    // functions

}
