package fifo;
import java.util.*;
public class fifo1 {
	public static void main(String args[]) 
    { 
        int[] pages = new int[10];
        Random rand = new Random();
        
        for(int i = 0; i < pages.length; i++) {
        	pages[i] = rand.nextInt(1001);
        }
        
        int capacity = 6; 
        System.out.println("Page numbers: " + Arrays.toString(pages));
        System.out.println("Number of Page Faults: " + pageFaults(pages, pages.length, capacity)); 
    } 
	
	public static int pageFaults(int pages[], int lenght, int capacity) {
		int pageFaults = 0;
		HashSet<Integer> hash = new HashSet<>(capacity);
		Queue<Integer> q = new LinkedList<>();
		
		
		for(int i = 0; i < lenght; i++) {
			if(hash.size() < capacity) { // checks if it has overgrown the capacity
				if(!hash.contains(pages[i])) { //check if the number is in there
					hash.add(pages[i]); //adds if not
					q.add(pages[i]); //adds to queue
					pageFaults++;
				}
			}
			else { // capacity not reached
				if(!hash.contains(pages[i])) { //adds the page number
					int x = q.peek(); // look at first page number
					q.poll(); // retrive it
					hash.remove(x); //remove the first page number
					hash.add(pages[i]); // add the current one
					q.add(pages[i]);// add to queue too
					pageFaults++;
				}
			}
		
		}
		return pageFaults;
	}
}
// all code has been inspired after hours of searching how to do fifo and clock from multiple website and youtube videos
//assumed for simplicity that n = 1000, as in max number of pages is 1000