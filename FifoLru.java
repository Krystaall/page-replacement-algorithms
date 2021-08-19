package fifo_lru;

import java.util.*;
class PageReplacement{
	Scanner sc= new Scanner(System.in);
    int maxframes, index = 0, hit = 0, fault = 0,len;
    int frame[];
    int sequence[];
    int table[][];
	
	void accept() {
		
	
        
        System.out.println("Please enter the number of Frames: ");
        maxframes = sc.nextInt();
        
        System.out.println("Please enter the length of the sequence: ");
        len =  sc.nextInt();
        
        sequence = new int[len];
        table = new int[len][maxframes];
        frame = new int[maxframes];
        for(int j = 0; j < maxframes; j++)
                frame[j] = -1;        //initializing all elements
        
        System.out.println("Please enter the sequence: ");
        for(int i = 0; i < len; i++)
        {
            sequence[i] =sc.nextInt();
        }
		
	}

   void fifo() {
	   fault=0;     //to clear previous data
	   hit=0;
       for(int j = 0; j < maxframes; j++)
           frame[j] = -1;        //initializing all elements again to clear fifo data
        
        System.out.println();
        for(int i = 0; i < len; i++)
        {
         int search = -1;
         for(int j = 0; j < maxframes; j++)
         {
          if(frame[j] == sequence[i])
          {
           search = j;
           hit++;
           break;
          } 
         }
         if(search == -1)    //no element present in the frame
         {
          frame[index] = sequence[i];   //directly assign element of sequence to the frame
          fault++;                       //new element brought from main memory
          index++;                       //move to next index(frame)
          if(index == maxframes)           
        	  index = 0;                 //if we reach maximum number of frames then start again from 0
         }
            for(int j = 0; j < maxframes; j++)
                table[i][j] = frame[j];         //storing current frame contents to display table
        }
        
        for(int i = 0; i < maxframes; i++)
        {
            for(int j = 0; j < len; j++)
                System.out.printf("%3d ",table[j][i]);   //displaying table(j i reversed)
            System.out.println();
        }
        
        System.out.println("Number of Page Hits: " + hit);
        System.out.println("Number of Page Faults: " + fault);
}
   
   void lru(){
	   fault=0;     //to clear previous data
	   hit=0;  //to clear previous data
       for(int j = 0; j < maxframes; j++)
           frame[j] = -1;        //initializing all elements again to clear previous data
	   
	   Boolean isFull = false;
       ArrayList<Integer> indx_array = new ArrayList<Integer>();
 
       System.out.println();
       for(int i = 0; i < len; i++)
       {
           if(indx_array.contains(sequence[i]))
           {
            indx_array.remove(indx_array.indexOf(sequence[i]));
           }
           indx_array.add(sequence[i]);
           
           int search = -1;
           for(int j = 0; j < maxframes; j++)
           {
               if(frame[j] == sequence[i])
               {                            
                   search = j;
                   hit++;       //if element already present in one of the frames
                   break;
               }
           }
           if(search == -1)     //if element not present in any frame
           {
            if(isFull)         //if index array is full then we have to use page replacement
            {
             int min_loc = len;      //min_loc=minimum location for least recently used
                   for(int j = 0; j < maxframes; j++)
                   {
                    if(indx_array.contains(frame[j]))
                       {
                           int temp = indx_array.indexOf(frame[j]);
                           if(temp < min_loc)
                           {
                               min_loc = temp;
                               index = j;
                           }
                       }
                   }
            }
               frame[index] = sequence[i];
               fault++;
               index++;
               if(index == maxframes)
               {
                index = 0;        //if index of frames reached last then wrap around first
                isFull = true;
               }
           }
           for(int j = 0; j < maxframes; j++)
               table[i][j] = frame[j];
       }
       
       for(int i = 0; i < maxframes; i++)
       {
           for(int j = 0; j < len; j++) 
               System.out.printf("%3d ",table[j][i]);
    
           System.out.println();
       }
       System.out.println("The number of Page Faults: " + fault);
       System.out.println("The number of Page Hits: " + hit);
       
   
   }
}

public class FifoLru{
    
public static void main(String[] args) {
	
	int ch;
	Scanner sc= new Scanner(System.in);

	PageReplacement p=new PageReplacement();
	p.accept();
	do {
	System.out.println("\n-----------------MENU------------------");
	System.out.println("\n1.FIFO\n2.LRU\n3.EXIT");
	System.out.println("Enter your choice: ");
	ch=sc.nextInt();
	
	switch(ch) {
	
	case 1:
	p.fifo();
	break;
	case 2:
	p.lru();
	break;
	case 3:
		System.out.println("EXIT!");
	break;
	default:
		System.out.println("Invalid choice try again!");
	break;
	}
	
	}while(ch!=3);
	}
}

