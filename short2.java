package test1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;



public class short2 {
		static int INF = 9999;
		static String[][] a;
		int len=0;
		//static int path[][];
	static ArrayList<Point> SG = new ArrayList<>();
	public float[][] floydWarshall(float[][] EdgeMatrix, int[][] path){
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
		System.out.println("\n");
		for(int i=0;i<EdgeMatrix.length;i++){
			for(int j =0; j<EdgeMatrix.length;j++){
				actualPath(path,i,j);
			}
			
		}
		
		System.out.println("Actual Paths Matrix");
		for(int i=0;i<EdgeMatrix.length;i++){
			 for(int j=0; j < EdgeMatrix.length; j++){
	    
				 System.out.print(a[i][j]+"\t");
			 }
			 System.out.println("");
			}
		
		System.out.println("\n");
		
		
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
					Load[i][j] = -1;
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
					Load[i][j] = -1;
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
				G[i][j] = -1;
			}
			else if(EdgeMatrix[i][j] != INF && i!=j){
				if(LoadMatrix[i][j] <= CapacityMatrix[i][j]){
					temp = (((float)CapacityMatrix[i][j] + 1) / ((float)CapacityMatrix[i][j] + 1 - (float)LoadMatrix[i][j] )) * (float)EdgeMatrix[i][j];
					G[i][j] = (float)(Math.round((temp *100))) / 100;
				}
				else if(LoadMatrix[i][j] > CapacityMatrix[i][j]){
					G[i][j] = INF;
					SG.add(new Point(i,j));

						}
				}
			}
		
			
		}
	return G;	
}
public ArrayList<float[][]> funcG1(float[][] LoadMatrix,float[][] CapacityMatrix){
	ArrayList<float[][]> temp1 = new ArrayList<>();
	float[][] L1 = new float[LoadMatrix.length][LoadMatrix.length];
	//float[][] C1 = new float[CapacityMatrix.length][CapacityMatrix.length];
	for(Point p: SG){
		L1[p.x][p.y] = LoadMatrix[p.x][p.y]-CapacityMatrix[p.x][p.y];
	}
	temp1.add(L1);
	temp1.add(CapacityMatrix);
	return temp1;
	
}
public ArrayList<float[][]> funcG2(float[][] LoadMatrix,float[][] CapacityMatrix, float[][] EdgeMatrix){
	ArrayList<float[][]> temp2 = new ArrayList<>();
	float[][] L2 = new float[LoadMatrix.length][LoadMatrix.length];
	float[][] C2 = new float[CapacityMatrix.length][CapacityMatrix.length];
	float[][] G2 = new float[EdgeMatrix.length][EdgeMatrix.length];
	float temp=0;
	
	for(int i=0;i<CapacityMatrix.length;i++){
		for(int j=0;j<CapacityMatrix.length;j++){
			if(CapacityMatrix[i][j]>LoadMatrix[i][j]){
				C2[i][j] = CapacityMatrix[i][j] - LoadMatrix[i][j];
			}
		}
	}
	
	for(Point p: SG){
		L2[p.x][p.y] = LoadMatrix[p.x][p.y]-CapacityMatrix[p.x][p.y];
		C2[p.x][p.y] = 0;
		
	}
	for(int i=0;i<EdgeMatrix.length;i++){
		for(int j=0;j<EdgeMatrix.length;j++){
			if(CapacityMatrix[i][j]>LoadMatrix[i][j]){
				temp = ((C2[i][j]+1)/(C2[i][j]+1-L2[i][j])) * EdgeMatrix[i][j];
				G2[i][j] = (float)(Math.round((temp *100))) / 100;
			}
		}
	}
	for(Point p: SG){
		
		G2[p.x][p.y] = INF;
		
	}
	temp2.add(L2);
	temp2.add(C2);
	temp2.add(G2);
	
	return temp2;
	
}
	public float[][] funcG3(float[][] LoadMatrix,float[][] CapacityMatrix, float[][] EdgeMatrix){
		float[][] L3 = new float[EdgeMatrix.length][EdgeMatrix.length];
		for(Point p: SG){
			L3[p.x][p.y] = CapacityMatrix[p.x][p.y];
		}
		return L3;
		
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

	
	public static void main(String[] args){
		
		
		
		
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
			short2 f = new short2();
			f.len = eArr.length;
			a = new String[f.len][f.len];
			int[][] path = new int[f.len][f.len];
			float[][] allPairsShortestPath = f.floydWarshall(eArr,path);
			short2.print(allPairsShortestPath, "Floyd Warshall All Pairs shortest path Matrix");
			
			System.out.println("Actual Path Length from "+snode+" to "+ dnode+" is "+a[snode-1][dnode-1]);
			
			String[][] b = a;
			
			float[][] LoadMatrix = f.CalculateLoad(a, fArr, eArr);
			short2.print(LoadMatrix, "Load Matrix");
			
			float[][] G = f.CalculateG(LoadMatrix, cArr, eArr);
			short2.print(G, "G Matrix");
			
			ArrayList<float[][]> T1 = f.funcG1(LoadMatrix,cArr); 
			ArrayList<float[][]> T2 = f.funcG2(LoadMatrix,cArr, eArr);
			float[][] newG = G;
			
			float[][] G1 =f.CalculateG(T1.get(0), T1.get(1), eArr);
			
			
			float[][] G2 =T2.get(2);
			//short2.print(G2, "G2 Matrix");
			
			float[][] T3 = f.funcG3(LoadMatrix, cArr, eArr);
			float[][] G3 = f.CalculateG(T3, cArr, eArr);
			float[][] G4 = new float[eArr.length][eArr.length];
			for(int i=0;i<eArr.length;i++){
				for(int j =0; j< eArr.length;j++){
					G4[i][j] = G3[i][j] + G1[i][j];
				}
			}
			
			
			
			int[][] path1 = new int[f.len][f.len];
			
			
			float[][] allPairsShortestPath1 = f.floydWarshall(G2,path1);
			//short2.print(G1, "G Matrix with only extra Load");
			//short2.print(allPairsShortestPath1, "G Weights between Edges with Delay to find alternate routes");
			
				for(Point p:SG){
					if(allPairsShortestPath1[p.x][p.y]>0){
						if(G4[p.x][p.y] < allPairsShortestPath1[p.x][p.y]){
							newG[p.x][p.x] = G4[p.x][p.y];
						}else if( G4[p.x][p.y] > allPairsShortestPath1[p.x][p.y]){
							newG[p.x][p.y] = allPairsShortestPath1[p.x][p.y];
							b[p.x][p.y] = a[p.x][p.y];
						}
						}
				}
				
			
			
			//short2.print(G3, "G Matrix with Load=Capacity");
			//short2.print(G4, "G Matrix with Delay Time = G for Extra Load + G for Load=Capacity");
			short2.print(newG, "Final G");
			System.out.println("Adjusted Shortest Paths");
			for(int i=0;i<b.length;i++){
				for(int j =0;j<b.length;j++){
					System.out.print(b[i][j]+"\t");
				}
				System.out.println("");
			}
			int[][] hop = new int[f.len][f.len];
			String[] counts = null;
			for(int i =0;i<hop.length;i++){
				for(int j=0;j<hop.length;j++){
					counts = b[i][j].split(" ");
					hop[i][j] = (counts.length) -2;
				}
			}
			String SP = b[snode-1][dnode-1];
			String[] SE = SP.split(" ");
			System.out.println("");
			System.out.println("Shortest Predicted Path Length from "+snode+" to "+dnode+" is "+SP);
			System.out.println("Hop Count is "+hop[snode-1][dnode-1]);
			
			/*System.out.println("\nHop Count");
			for(int i =0;i<hop.length;i++){
				for(int j=0;j<hop.length;j++){
					System.out.print(hop[i][j]+"\t");
				}
				System.out.println("");
			}*/
			 System.out.println("");
			 
			 
			 
			float[][] actualPathDelay = f.CalculateLoad(b, G);
			short2.print(actualPathDelay, "Actual Path Delay");
			
			
			
			

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
