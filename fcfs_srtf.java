package FCFS_SRTF;
import java.util.*;


class Fcfs {
	Scanner sc=new Scanner(System.in);

	void fcfs() {
		
		System.out.println("\nEnter no of processes: ");
		int n = sc.nextInt();
	
	int pid[] = new int[n];   // process id
	int ar[] = new int[n];     // arrival times
	int bt[] = new int[n];     // burst or execution times
	int ct[] = new int[n];     // completion times
	int ta[] = new int[n];     // turn around times
	int wt[] = new int[n];     // waiting times 
	int temp;
	float avgwt=0,avgta=0;
	
	

		for(int i = 0; i < n; i++)
		{
			System.out.println("\nEnter process " + (i+1) + " arrival time: ");
			ar[i] = sc.nextInt();
			System.out.println("\nEnter process " + (i+1) + " brust time: ");
			bt[i] = sc.nextInt();
			pid[i] = i+1;
		}
		
	
		//sorting according to arrival times
				for(int i = 0 ; i <n; i++)
				{
					for(int  j=0;  j < n-(i+1) ; j++)
					{
						if( ar[j] > ar[j+1] )
						{
							temp = ar[j];
							ar[j] = ar[j+1];
							ar[j+1] = temp;
							
							temp = bt[j];
							bt[j] = bt[j+1];
							bt[j+1] = temp;
							
							temp = pid[j];
							pid[j] = pid[j+1];
							pid[j+1] = temp;
						}
					}
					System.out.print("| P"+pid[i]+" ");          //displaying gantt chart

				}
				
				// finding completion times
				for(int  i = 0 ; i < n; i++)
				{
					if( i == 0)
					{	
						ct[i] = ar[i] + bt[i];
					}
					else
					{
						if( ar[i] > ct[i-1])
						{
							ct[i] = ar[i] + bt[i];
						}
						else
							ct[i] = ct[i-1] + bt[i];
					}
					ta[i] = ct[i] - ar[i] ;          // turn around time= completion time- arrival time
					wt[i] = ta[i] - bt[i] ;          // waiting time= turn around time- burst time
					avgwt += wt[i] ;               // total waiting time
					avgta += ta[i] ;               // total turn around time
				}
	
		
	
		System.out.println("\nPID  ARRIVAL  BURST  COMPLETE TURN  WAITING");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t"  + wt[i] ) ;
		}
		
		System.out.println("\nAVERAGE TURN AROUND TIME IS:   "+ (avgwt/n));     // printing average waiting time.
		System.out.println("AVERAGE WAITING TIME IS:  "+(avgta/n));    // printing average turn around time.
	}
	
	void srtf(){
		
		

		System.out.println ("\nEnter no of process:");
		int n= sc.nextInt();
		int pid[] = new int[n]; // id of process
		int at[] = new int[n];  // arrival time
		int bt[] = new int[n];  // burst time
		int ct[] = new int[n];  // completion time
		int ta[] = new int[n];  // turn around time
		int wt[] = new int[n];  // waiting time
		int f[] = new int[n];   // f means it is flag it checks process is completed or not
		int k[]= new int[n];    // it is also stores burst time
	    int i, st=0, tot=0;
	    float avgwt=0, avgta=0;
 
	    for (i=0;i<n;i++)
	    {
	    	pid[i]= i+1;
	    	System.out.println ("\nEnter process " +(i+1)+ " arrival time:");
	    	at[i]= sc.nextInt();
	    	System.out.println("\nEnter process " +(i+1)+ " burst time:");
	    	bt[i]= sc.nextInt();
	    	k[i]= bt[i];
	    	f[i]= 0;
	    }
	    
	   //until all processes get completed 
	    while(true){
	    	int min=99,c=n;
	    	if (tot==n)
	    		break;
	    	
	   // Finding process with minimum remaining time among processes that arrives till the current time
	    	for ( i=0;i<n;i++)
	    	{
	    		if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
	    		{	
	    			min=bt[i];
	    			c=i;

	    		}
	    	}
	    	
	    	//if all processes completed
	    	if (c==n) {
	    		st++;
	    	}
	    	
	    	else
	    	{
	    		bt[c]--;
	    		st++;
				System.out.print("| P"+pid[c]+" ");       //displaying for gantt chart

	    		
	    		//if process is completely executed
	    		if (bt[c]==0)
	    		{
	    			ct[c]= st;
	    			f[c]=1;     //this indicates completion
	    			tot++;

	    		}
	    	}

	    }
	    
	    for(i=0;i<n;i++)
	    {
	    	ta[i] = ct[i] - at[i];
	    	wt[i] = ta[i] - k[i];
	    	avgwt+= wt[i];
	    	avgta+= ta[i];

	    }
	    
	    System.out.println("\nPID  ARRIVAL  BURST  COMPLETE TURN WAITING");
	    for(i=0;i<n;i++)
	    {
	    	System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ ta[i] +"\t"+ wt[i]);
	    }
	    
	    System.out.println("\nAVERAGE TURN AROUND TIME IS:  "+ (float)(avgta/n));
	    System.out.println("AVERAGE WAITING TIME IS:  "+ (float)(avgwt/n));
	   
		
	}
}
	


public class fcfs_srtf{
	public static void main(String []args) {
		
		Scanner s=new Scanner(System.in);
		Fcfs f=new Fcfs();
		int no;
		do{
			System.out.println("\n*********************");
			System.out.println("MENU:\n1.FCFS\n2.SRTF\n3.EXIT ");
			System.out.println("*********************");
			System.out.println("\nEnter your choice: ");
			no=s.nextInt();
			

			switch(no){
			case 1:
				f.fcfs();
			break;
			
			case 2:
				System.out.println("\nThe gantt chart is displayed after every 1 second");
				f.srtf();
			break;
			
			case 3:
			break;
			
			default:
				System.out.println("INVALID CHOICE!");
			break;
				
			}
			}while(no!=3);	
		
		
		
	}
	
}
