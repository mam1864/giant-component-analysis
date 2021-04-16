/* Michael Mironidis
 * Giant Component Analysis
 * 4/16/2021 
 */
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GC2 {
	public static void main(String[] args) throws IOException {
		int k = 6; //degree
		int n = (int) Math.pow(10, k); //number of vertices
		int[] name = new int[n]; //name of vertices (aka component)
		ArrayList<LinkedList<Integer>> list = new ArrayList<>(); //list of lists of vertices 0,1,...,n-1
		int[] max_ = new int[3*n]; //maxes at each edge add
		/**
		 * 
		 */
		
		for(int i = 0; i<n; i++) {  //for each vertex
			name[i] = i; //set name to itself
			LinkedList<Integer> singleton = new LinkedList<>(); //create a component
			singleton.addFirst(i); //add vertex i to the component
			list.add(singleton); //add this singleton to the list
			/* Note: Java's LinkedList maintains its own SIZE[] field
			 *       which is accessed using .size() and has time O(1). 
			 */
			
		}
		int max = 1; //max is 1
		
		for(int i=0; i<3*n;i++) {
			//bounds of random generator
			int upper = n-1;
			int lower = 0;
			//random edge
			int x = (int) ((Math.random()*(upper-lower))+lower); //generate random int from lower to upper
			int y = (int) ((Math.random()*(upper-lower))+lower);
			if(name[x]==name[y]) {
				//do nothing
			}
			else {
				x = name[x]; //set x to the designated vertex of its component
				y = name[y]; //set y to designated vertex of its component
				
				if(list.get(x).size()>list.get(y).size()) { //if x component is larger than y component
					int temp = x; //switch x and y
					x = y;
					y = temp;
				}
				//merge smaller x into larger y
				for(int z : list.get(x)) name[z] = y; //set NAME[z]=y for all z in LIST[x]
				
				list.get(y).addAll(list.get(x)); //append LIST[x] into LIST[y]
				
				//if this new component is bigger than the previous max, update max
				if(max<list.get(y).size()) max = list.get(y).size();
			}
			max_[i] = max; //save max
		}
		
		//output
		PrintWriter output = new PrintWriter(new FileWriter("max.csv"));
		
		for(int i=0;i<max_.length;i++) {
			output.println(i + "," + max_[i]);
		}

		
		output.flush();
		output.close();
		
		System.out.println("---------END PROGRAM----------");
	}
}

