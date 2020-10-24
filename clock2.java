package clock;
import java.util.*;
import java.io.*;
public class clock2 { 
    public static void main(String args[])throws IOException 
    { 
        int frames = 3; // arbitrary number of frames
        int[] pages = new int[10]; // arbitrary length of the pages
        Random rand = new Random();
        
        for(int i = 0; i < pages.length; i++) { //exponential distribution with lambda of 10, multiplying by 20 to get ints exponentially distributed between 0-20
            int lambda = 10;
        double x = Math.log(1-rand.nextDouble());
             double y = x/(-lambda);
             double z = y * 20;
             int finalint = (int)z;
        	pages[i] = finalint;
        }
        System.out.println("Page numbers: " + Arrays.toString(pages));
        printFaults(pages,frames); 
    } 
    static void printFaults(int[] pages, int frames) 
    { 
        int pointer, x, pageFaults = 0;
        pointer = 0;
        int arr[] = new int[frames]; // frames array to update bit counter
        Arrays.fill(arr,-1); 
        boolean second_chance[] = new boolean[frames]; //whether the bit has been changed
        int len = pages.length; 
    
        for(int i = 0; i < len; i++) 
        { 
            x = pages[i]; 
            if(!find(x,arr,second_chance,frames)) 
            {
                pointer = replace(x,arr,second_chance,frames,pointer); 
                pageFaults++; 
            } 
        } 
        System.out.println("Total page faults were "+ pageFaults); 
    }
 
    static boolean find(int x,int arr[], boolean second_chance[],int frames)//find the number from pages in the reference and checks if the bit is 1 or 0
    { 
        for(int i = 0; i < frames; i++) 
        { 
            if(arr[i] == x) 
            {
                second_chance[i] = true; 
                return true; 
            } 
        } 
        return false; 
    } 
    static int replace(int x,int arr[],boolean second_chance[],int frames,int pointer) // replaces the bit with 0 reference with the new page number
    { 
        while(true) 
        { 
            if(!second_chance[pointer]) 
            { 
                arr[pointer] = x; 
                return (pointer+1)%frames; // change the victim reference pointer
            }
            second_chance[pointer] = false; 
            pointer = (pointer+1)%frames; // change the victim reference pointer
        } 
    } 
} 