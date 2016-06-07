package ScalaComputations;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class JavaSort {
	private ArrayList<Integer> inputArray;
    
    public ArrayList<Integer> getSortedArray() {
        return inputArray;
    }
 
    public JavaSort(){
    	inputArray = new ArrayList<Integer>();
    	final File folder = new File("saves/");
    	
    	for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().startsWith(".")) continue;
			
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream("saves/"+fileEntry.getName()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
		        byte[] c = new byte[1024];
		        int count = 0;
		        int readChars = 0;
		        boolean empty = true;
		        while ((readChars = is.read(c)) != -1) {
		            empty = false;
		            for (int i = 0; i < readChars; ++i) {
		                if (c[i] == '\n') {
		                    ++count;
		                }
		            }
		        }
		        int lines = (count == 0 && !empty) ? 1 : count;
		       
		        inputArray.add(lines);
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
    	}
    	long startJava = System.nanoTime();
        sortGivenArray();
        System.out.print("Java  Time = ");
		System.out.println(System.nanoTime() - startJava);
        Collections.reverse(inputArray);
    }
     
    public void sortGivenArray(){       
        divide(0, this.inputArray.size()-1);
    }
     
    public void divide(int startIndex,int endIndex){
         
        //Divide till you breakdown your list to single element
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid);
            divide(mid+1, endIndex);        
             
            //merging Sorted array produce above into one sorted array
            merger(startIndex,mid,endIndex);            
        }       
    }   
     
    public void merger(int startIndex,int midIndex,int endIndex){
         
        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        ArrayList<Integer> mergedSortedArray = new ArrayList<Integer>();
         
        int leftIndex = startIndex;
        int rightIndex = midIndex+1;
         
        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(inputArray.get(leftIndex)<=inputArray.get(rightIndex)){
                mergedSortedArray.add(inputArray.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(inputArray.get(rightIndex));
                rightIndex++;
            }
        }       
         
        //Either of below while loop will execute
        while(leftIndex<=midIndex){
            mergedSortedArray.add(inputArray.get(leftIndex));
            leftIndex++;
        }
         
        while(rightIndex<=endIndex){
            mergedSortedArray.add(inputArray.get(rightIndex));
            rightIndex++;
        }
         
        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            inputArray.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

	public ArrayList<Integer> getInputArray() {
		return inputArray;
	}
    
    
}
