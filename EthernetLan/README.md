Run the simulation program
[root@localhost~]# ns lab3.tcl
Here “ns” indicates network simulator. We get the topology shown in the
snapshot.
Now press the play button in the simulation window and the simulation will
begins.
After simulation is completed run awk file to see the output ,
[root@localhost~]# awk –f lab3.awk file1.tr > a1
[root@localhost~]# awk –f lab3.awk file2.tr > a2
[root@localhost~]# xgraph a1 a2\
Here we are using the congestion window trace files i.e. file1.tr and file2.tr and we
are redirecting the contents of those files to new files say a1 and a2 using output
redirection operator (>).