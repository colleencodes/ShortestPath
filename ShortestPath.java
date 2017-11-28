/* Author: Colleen M. Stock 
 * Date: 6 November 2015
 * Shortest Path Project
 * 
 * The purpose of this program is, given a specified number locations, the locations, and the connections between the places, to find the 
 * shortest path between the starting and target location. This was achieved through a two step process:
 * 1) creating an adjacency list to represent the undirected graph
 * 2) performing a breadth first search until the target location was reached
 */

import java.util.*; 
import java.io.*;

class Function {  
	
	String ShortestPath(String[] strArr) { 
		//counter
		int i;
		 
		//process the number of locations
		int n=Integer.parseInt(strArr[0]);
		
		//check to see if there are any existing connections, if not, terminate
		if ((strArr.length-1)==n){
			return "-1";
		}//if
		
		//process what will be the starting location and target location
		String starting_node=strArr[1];
		String target_node=strArr[n];
		
		boolean target_found=false;
		
		//****Step 1: create the adjacency list****
		//gather all of the connections 
		String [] connections=Arrays.copyOfRange(strArr, n+1, strArr.length);
		
		//create the HashMap which will be the adjacency list to represent the graph
		//each list will have the form: [distance, parent, connected nodes] where distance denotes how far from the starting node is
		//from the current node and parent will be filled in as the path are discovered
		Map<String, List<String>> adj_list=new HashMap<String, List<String>>();
		  
		//create a list for each location
		for(i=0; i<n; i++){
			adj_list.put(strArr[i+1], new LinkedList<String>());
			adj_list.get(strArr[i+1]).add("inf"); //defining an initial distance to be infinity
			adj_list.get(strArr[i+1]).add(null);  //defining an initial parent to be null
		}//for
		
		//this fills each list with the connections; since the graph is undirected the connection "x-y" is reported in the "x"
		//list as a connection to "y" and is reported in the "y" list as a connection to "x"
		for (i=0; i<connections.length; i++){
			String connect=connections[i];
			String start=connect.substring(0, connect.indexOf("-"));
			String end=connect.substring(connect.indexOf("-")+1);
			List<String> start_list=adj_list.get(start);
			List<String> end_list=adj_list.get(end);
			start_list.add(end);
			end_list.add(start);
		}//for
		
		//****Step 2: look for a possible shortest path between the starting and target location****
		//establish the queue for the breadth first search
		Queue Q=new LinkedList();
		//collect the information for the starting node
		List<String> starting_node_info=adj_list.get(starting_node); 
		//set the starting node's distance as "0"
		starting_node_info.set(0, "0");
		//add the starting node to the queue so shortest paths can be looked for
		Q.add(starting_node);
			
		//as long as information remains in Q or the target location hasn't been found yet, remove the topmost element
		//from the queue, and look at it's connected nodes
		//if these connected nodes have a distance of infinity, update it to be the distance of the previous node + 1 to indicate one
		//more step into the graph, and set it's parent to be the previous node and add these nodes to the queue
		while (Q.isEmpty()==false && target_found==false){
			String considered=(String)Q.remove();
			List<String>connected_nodes=adj_list.get(considered);
			for(i=2; i<connected_nodes.size();i++){
				if (adj_list.get(connected_nodes.get(i)).get(0).compareTo("inf")==0){
					adj_list.get(connected_nodes.get(i)).set(0, Integer.toString(Integer.parseInt(adj_list.get(considered).get(0))+1));
					adj_list.get(connected_nodes.get(i)).set(1, considered);
					Q.add(connected_nodes.get(i));
				}//if
				//if the target has been found, flip the flag and break out
				if(considered.equals(target_node))
					target_found=true;
			}//if
		}//while
		
		//if the target has been found, print out the path to it, beginning from the starting node, otherwise return "-1"
		if (target_found==true){
			int k=Integer.parseInt(adj_list.get(target_node).get(0));
			String [] path= new String[k+1];
			path[k]=target_node;
			k--;
			String parent=target_node;
			while (k>=0){
				if (k==1)
					path[k]=adj_list.get(parent).get(1);
				else
					path[k]=adj_list.get(parent).get(1);
				
				parent=adj_list.get(parent).get(1);
				k--;
			}//while
			String shortestPath="";
			for(i=0; i<path.length; i++)
				if (i==(path.length-1))
					shortestPath+=path[i];
				else
					shortestPath+=path[i]+"-";
		    return shortestPath;
		}//if
		else
			return "-1";
		
  }//ShortestPath
  
  public static void main (String[] args) {  
    // keep this function call here     
    Scanner  s = new Scanner(System.in);
    Function c = new Function();
    System.out.print(c.ShortestPath(s.nextLine())); 
  }   
  
}
