

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	int [][] blocks;
	int [] blocks1;
	int N;
	int NUM;
    public Board(int[][] blocks){
    	N=blocks.length;
    	NUM=N*N;
    	this.blocks=new int[N][N];
    	for(int i=0;i<N*N;i++){
    		this.blocks[i/N][i%N]=blocks[i/N][i%N];
    	}  	
    }           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension(){
    	return N;
    }                 // board dimension N
//    public int hamming()                   // number of blocks out of place
    public int manhattan(){
    	int man=0;
    	int m = 0;
    	for(int i=0;i<N;i++){
    		for(int j=0;j<N;j++){
    			if(blocks[i][j]!=0)
    				m=blocks[i][j]%N==0?N:blocks[i][j]%N;
    				man=Math.abs(m-j-1)+Math.abs(blocks[i][j]/N-1-i)+man;
    		}
    	}
    	return man;
    }                // sum of Manhattan distances between blocks and goal
    public boolean isGoal(){
    	for(int i=0;i<blocks.length-1;i++){
    		if(blocks[i/N][i%N]!=i){
    			return false;
    		}    			
    	}
    	return true;
    }              // is this board the goal board?
    public Board twin(){
    	int[][] newblocks=new int[N][N];
    	for(int i=0;i<newblocks.length;i++){
    		newblocks[i/N][i%N]=blocks[i/N][i%N];
    	}
    	if(newblocks[0][0]!=0&&newblocks[0][1]!=0){
    		int tmp=newblocks[0][1];
    		newblocks[0][1]=newblocks[0][0];
    		newblocks[0][0]=tmp;
    	}else{
    		int tmp=newblocks[1][1];
    		newblocks[1][1]=newblocks[1][0];
    		newblocks[1][0]=tmp;
    	}
    	return new Board(newblocks);
    }                    // a board that is obtained by exchanging two adjacent blocks in the same row
    public boolean equals(Object y){
    	return Arrays.equals(this.blocks,((Board)y).blocks);
    }        // does this board equal y?
    public Iterable<Board> neighbors(){
    	int bnum = 0;
    	int j = 0,k = 0;
    	ArrayList<Board> array=new ArrayList<Board>();
    	for(int i=0;i<NUM;i++){
    		j=i/N;k=i%N;
    		if(blocks[j][k]==0){
    			bnum=i;
    			break;
    		} 		
    	}
    	Board boardl=swap(blocks,j,k,j-1,k);
    	Board boardr=swap(blocks,j,k,j+1,k);
    	Board boardup=swap(blocks,j,k,j,k-1);
    	Board boarddown=swap(blocks,j,k,j,k+1);
    	if(boardl!=null) array.add(boardl);
    	if(boardr!=null) array.add(boardr);
    	if(boardup!=null) array.add(boardup);
    	if(boarddown!=null) array.add(boarddown);
    	return array;
    	 	
    }     // all neighboring boards
    public Board swap(int[][] ints,int i,int j,int k,int l){
    	if(i<0||j<0||i>=N||j>=N||k<0||k>=N||l<0||l>=N)
		return null;
    	int[][] newblocks=new int[N][N];
//    	int k=
    	for(int m=0;m<NUM;m++){
    		newblocks[m/N][m%N]=blocks[m/N][m%N];
    	}

    	int tmp=ints[i][j];
    	newblocks[i][j] = newblocks[k][l];
    	newblocks[k][l]=tmp;
    	return new Board(newblocks);
    }
    public String toString(){
    	StringBuilder sb=new StringBuilder();
    	sb.append(N).append("\n");
    	System.out.println(N);
    	for(int i = 0;i<N;i++){
    		for(int j=0;j<N;j++){
    			sb.append((blocks[i][j]+" "));
    		}
    		sb.append("\n");
    	}
    	return sb.toString();
    }               // string representation of this board (in the output format specified below)
//    class N{
//    	int row;
//    	int column;
//    	public N(int n,int N){
//    		row=n/N;
//    		column=n%N;
//    	}
//    }
    public static void main(String[] args){

    } // unit tests (not graded)
}