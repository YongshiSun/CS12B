
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

public class SimulationOFF {
    public static void main(String[] args) throws IOException {
        int m;
        int time = 0;
        int totalWait = 4;
        int maxWait = 3;
        double averageWait = totalWait / maxWait;
        int currentTime = 0;
        int copytime = 0;
        int jobsLeft;
        int processor;

        // checking command line
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

            Queue[] N = new Queue[processor];
            Job Backup[] = new Job[m];
            Queue zero = new Queue();
            Queue newZero = new Queue();

            for (int i = 0; i < processor; i++) {
                N[i] = new Queue();

            }

            // adding jobs to 0th processor as a queue
            for (int i = 0; i < m; i++) {
                Job j = new Job(in.nextInt(), in.nextInt());
                zero.enqueue(j);
                newZero.enqueue(j);
                Backup[i] = j;
            }

            // header for trace
            trace.println("Trace file: " + args[0] + ".trc");
            trace.println(m + " Jobs:");
            trace.println(zero);

            // header for report

            // run simulation with n processors for n = 1 to n=m-1
            for (int n = 1; n < processor + 1; n++) {
                jobsLeft = m;

                trace.println("*****************************");
                trace.println(n + " processors:");
                trace.println("*****************************");

                while (jobsLeft > 0) {
                    trace.println("time=" + time);
                    trace.println(0 + ":" + zero);

                    for (int j = 0; j < n; j++) {
                        trace.println(j + 1 + ":" + N[j]);

                        Job Zpeek = (Job) zero.peek();
                        time = Zpeek.getArrival();
                        // int copyTime = time;
                        Job Zdequeue = (Job) zero.dequeue();

                        N[j].enqueue(Zpeek);

                        Job jpeek = (Job) N[j].peek();

                        if (Zpeek == jpeek) {

                            jpeek.computeFinishTime(time);
                        }
                        // zero.dequeue();

                        zero.enqueue(Zdequeue);

                        // zero.peek(Zdequeue());

                        // trace.println(j + 1 + ":" + N[j]);

                    }

                    trace.println(" ");
                    // Job jempty = (Job) N[j].dequeueAll();

                    jobsLeft--;
                }
                time = 0;
                for (int r = 0; r < n; r++) {
                    N[r].dequeueAll();
                }

            }

            // close files
            in.close();
            report.close();
            trace.close();

        }

    }

}