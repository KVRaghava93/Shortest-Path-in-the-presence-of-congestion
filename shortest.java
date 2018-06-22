package test1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
class shortest{
	
	static String[][] a;
	int len=0;
	
	final static int INF=9999, threshold = 170;
	
	
	public float[][] floydWarshall(float[][] EdgeMatrix,int[][] path){
		
		float distance[][] = new float[EdgeMatrix.length][EdgeMatrix.length];
		
		
		
		for(int i=0;i<EdgeMatrix.length;i++){
			for(int j=0;j<EdgeMatrix.length;j++){
				distance[i][j] = EdgeMatrix[i][j];
				if (EdgeMatrix[i][j] != INF && i != j) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
				
			}
			
		}
		
		for(int k=0; k<EdgeMatrix.length;k++){
			for(int i=0;i<EdgeMatrix.length;i++){
				for(int j=0;j<EdgeMatrix.length;j++){
					if(distance[i][k] == INF || distance[k][j] == INF){continue;}
					if(distance[i][j] > distance[i][k] + distance[k][j]){
						distance[i][j] = distance[i][k] + distance[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}/*
		System.out.println("Path Matrix");
		for(int i=0;i<EdgeMatrix.length;i++){
			for(int j =0; j<EdgeMatrix.length;j++){
				System.out.print(path[i][j] + "\t");
			}
			System.out.println("");
		}*/
		for(int i=0;i<EdgeMatrix.length;i++){
			for(int j =0; j<EdgeMatrix.length;j++){
				actualPath(path,i,j);
			}
			
		}
		
		
		
		return distance;
	}
	
	public void actualPath(int[][] path, int start, int end) {
        
		int s = start;
		int e = end;
       
        Deque<Integer> stack = new LinkedList<>();
        
        stack.addFirst(end);
        while (true) {
            end = path[start][end];
            if(end == -1) {
                break;
            }
            
            stack.addFirst(end);
            if(end == start) {
                break;
            }
        }
        
       String temp = "";
       
        while (!stack.isEmpty()) {
        		
        	temp = temp+ " " + (int)(stack.pollFirst() + 1);
        	}
        a[s][e] = temp;
        
        
        
    }
	
	public float[][] CalculateLoad(String[][] a, float[][] FlowMatrix, float[][] EdgeWeight ){
		float[][] Load = new float[FlowMatrix.length][FlowMatrix.length];
		
		ArrayList<Point> t1 = new ArrayList<>();
		
		for(int i = 0;i<a.length;i++){
			for(int j =0; j<a.length;j++){
				if(a[i][j].length() == 2){
					Load[i][j] = 0;
				}else if (a[i][j].length() == 4){
					if(EdgeWeight[i][j] == INF){
						Load[i][j] = INF;
					}else{
							
								float sum =0;
								t1 = finddup(a[i][j],a);
								for(Point p: t1){
									sum = sum +FlowMatrix[p.x][p.y];
								}
								Load[i][j] = sum;
							
					}
				}
			}
		}
		
		float c=0;
		
		for(int i = 0;i<a.length;i++){
			for(int j =0; j<a.length;j++){
				c=0;
				if(a[i][j].length() > 4){
					if(EdgeWeight[i][j] == INF){
						//System.out.println("HH");
						Load[i][j] = INF;
					}else{
						int count =0;
						String m = Integer.toString(i+1)+" "+Integer.toString(j+1);
						count = exist(m,a);
						if(count == 0){
							//System.out.println("EXIST "+ exist("1 4",a));
							Load[i][j] =0;
						}else{
							String[] ba = a[i][j].split(" ");
							for(int k =0; k <ba.length-1;k++){
								try{
								//ba= a[i][j].replaceAll("[^0-9.]", "");
								//ba = a[i][j].substring(k, k+1).replaceAll("[^0-9.]", "");
								//ca = a[i][j].substring(k+1, k+2).replaceAll("[^0-9.]", "");
								c= c+ Load[Integer.parseInt(ba[k])-1][Integer.parseInt(ba[k+1])-1];
								}catch(StringIndexOutOfBoundsException | NumberFormatException e){
									
								}
							}
							Load[i][j] = c;
						
						}
					}
					
				}
			}
		}

		return Load;
		
	}
	

public float[][] CalculateLoad(String[][] a, float[][] FlowMatrix){
float[][] Load = new float[FlowMatrix.length][FlowMatrix.length];
	
	ArrayList<Point> t1 = new ArrayList<>();
	
	for(int i = 0;i<a.length;i++){
		for(int j =0; j<a.length;j++){
			if(a[i][j].length() == 2){
				Load[i][j] = 0;
			}else if (a[i][j].length() == 4){
					float sum =0;
					t1 = finddup(a[i][j],a);
					for(Point p: t1){
						sum = sum +FlowMatrix[p.x][p.y];
					}
					Load[i][j] = sum;
						
				}
			}
		}
	
	
	float c=0;
	
	for(int i = 0;i<a.length;i++){
		for(int j =0; j<a.length;j++){
			c=0;
			if(a[i][j].length() > 4){
				String[] ba1 = a[i][j].split(" ");
				
						for(int k =0; k < ba1.length - 1;k++){
							try{
							
							//System.out.println("BA "+ba);
							c= c+ Load[Integer.parseInt(ba1[k])-1][Integer.parseInt(ba1[k+1])-1];
							}catch(StringIndexOutOfBoundsException | NumberFormatException e){
								
							}
						}
						Load[i][j] = (float)(Math.round((c *100))) / 100;
					
					}
				}
				
			}
		
	

	return Load;
	
}

public int exist(String m, String[][] a){
	//System.out.println("M - "+m);
	int t = 0;
	for(int i=0;i<a.length;i++){
		for(int j=0;j<a.length;j++){
			if(a[i][j].indexOf(m)!=-1){
				t++;
			}
		}
	}
	//System.out.println("T -"+t);
	return t;
	
	
}
public ArrayList<Point> finddup(String num ,String[][] a){
	ArrayList<Point> temp = new ArrayList<>();
	for(int i=0;i<a.length;i++){
		for(int j =0; j<a.length;j++){
			if(a[i][j].indexOf(num) != -1){
				temp.add(new Point(i,j));
			}
		}
	}
	return temp;
}

public float[][] CalculateG(float[][] LoadMatrix, float[][] CapacityMatrix, float[][] EdgeMatrix){
	float[][] G = new float[EdgeMatrix.length][EdgeMatrix.length];
	
	float temp = 0;
	for(int i=0;i<LoadMatrix.length;i++){
		for(int j =0; j <LoadMatrix.length;j++){
			if(EdgeMatrix[i][j] == INF){
				G[i][j] = INF;
			}
			else if(EdgeMatrix[i][j] != INF && i!=j){
				if(LoadMatrix[i][j] <= CapacityMatrix[i][j]){
					temp = (((float)CapacityMatrix[i][j] + 1) / ((float)CapacityMatrix[i][j] + 1 - (float)LoadMatrix[i][j] )) * (float)EdgeMatrix[i][j];
					G[i][j] = (float)(Math.round((temp *100))) / 100;
				}
				else if(LoadMatrix[i][j] > CapacityMatrix[i][j]){
					G[i][j] = -1;
					//SG.add(new Point(i,j));

						}
				}
			}
		
			
		}
	return G;	
}
public static void print(float[][] matrix, String name){
	System.out.println(name);
	for(int i=0;i<matrix.length;i++){
		for(int j=0;j<matrix.length;j++){
			System.out.print(matrix[i][j]+"\t");
		}
		System.out.println("");
	}
	System.out.println("\n");
}

	
	public static void main(String args[]){
		

		
		
		
		BufferedReader br = null;
		try {
			String sCurrentLine;
			String firstLine;
			br = new BufferedReader(new FileReader("C:\\Users\\mahid\\Desktop\\input1.txt"));
			
			firstLine=br.readLine();
			firstLine = firstLine.replaceAll("\\s+",""); 
			//System.out.println("first line is"+firstLine);
			String []s= firstLine.split("\\,");
			int eMat=Integer.parseInt(s[0]);
			System.out.println("Dimension of EdgeMatrix is "+eMat);
			int snode=Integer.parseInt(s[1]);
			System.out.println("Start node is "+snode);
			int dnode=Integer.parseInt(s[2]);
			
			float[][] eArr = new float[eMat][eMat];
			float[][] cArr = new float[eMat][eMat];
			float[][] fArr = new float[eMat][eMat];
			
			
			System.out.println("Destination node is "+dnode);
			
			for(int j=0;j<eMat;j++)
			{
				for(int k=0;k<eMat;k++)
				{
					eArr[j][k]=INF;
					cArr[j][k]=INF;
					fArr[j][k]=0;	
				}
			}
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.length() > 0) {
					//System.out.println("line is"+sCurrentLine);
					String lineWithoutSpaces = sCurrentLine.replaceAll("\\s+","");            
				String []val= lineWithoutSpaces.split("\\,");
				//System.out.println("my first value is"+val[0]+"second"+val[1]+"third"+val[2]);
				if(val[0].equals("E"))
				{
					eArr[Integer.parseInt(val[1])-1][Integer.parseInt(val[2])-1]=Float.parseFloat(val[3]);
				}
				if(val[0].equals("C"))
				{
					cArr[Integer.parseInt(val[1])-1][Integer.parseInt(val[2])-1]=Float.parseFloat(val[3]); //Double.parseDouble(val[3]);
				}
				if(val[0].equals("F"))
				{
					fArr[Integer.parseInt(val[1])-1][Integer.parseInt(val[2])-1]=Float.parseFloat(val[3]);// Double.parseDouble(val[3]);
				}
			}}
			System.out.println("");
			System.out.println("E Matrix");
			for(int j=0;j<eMat;j++)
			{
				for(int k=0;k<eMat;k++)
				{
					System.out.print(eArr[j][k]+"\t");
					
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("F Matrix");
			for(int j=0;j<eMat;j++)
			{
				for(int k=0;k<eMat;k++)
				{
					System.out.print(fArr[j][k]+"\t");
					
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("C Matrix");
			for(int j=0;j<eMat;j++)
			{
				for(int k=0;k<eMat;k++)
				{
					System.out.print(cArr[j][k]+"\t");
					
				}
				System.out.println("");
			}
			shortest f = new shortest();
			f.len = eArr.length;
			a = new String[f.len][f.len];
			int[][] path = new int[f.len][f.len];
			float[][] allPairsShortestPath = f.floydWarshall(eArr,path);
			System.out.println("");
			shortest.print(allPairsShortestPath, "Floyd Warshall All Pairs shortest path Matrix");
			
			System.out.println("Actual Path Length from "+snode+" to "+ dnode+" is "+a[snode-1][dnode-1]);
			
			String[][] b = a;
			
			float[][] LoadMatrix = f.CalculateLoad(a, fArr, eArr);
			shortest.print(LoadMatrix, "Load Matrix");
			
			float[][] G = f.CalculateG(LoadMatrix, cArr, eArr);
			shortest.print(G, "G Matrix");
			int[][] path1 = new int[f.len][f.len];
			float[][] aPSP = f.floydWarshall(G,path1);
			float[][] LMG = f.CalculateLoad(a, fArr, G);
			float[][] G1 = f.CalculateG(LMG, cArr, G);
			float[][] actualPathDelay = f.CalculateLoad(b, G1);
			
			
			System.out.println("Shortest Paths");
			for(int i=0;i<b.length;i++){
				for(int j =0;j<b.length;j++){
					System.out.print(a[i][j]+"\t");
				}
				System.out.println("");
			}
			int[][] hop = new int[f.len][f.len];
			String[] counts = null;
			for(int i =0;i<hop.length;i++){
				for(int j=0;j<hop.length;j++){
					counts = a[i][j].split(" ");
					hop[i][j] = (counts.length) -2;
				}
			}
			
			String SP = a[snode-1][dnode-1];
			//String[] SE = SP.split(" ");
			System.out.println("");
			System.out.println("Shortest Predicted Path Length from "+snode+" to "+dnode+" is "+SP);
			System.out.println("Hop Count is "+hop[snode-1][dnode-1]);
		

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}


		}
	
}