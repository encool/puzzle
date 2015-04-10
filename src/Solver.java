import java.util.ArrayList;
import java.util.Comparator;



public class Solver {
	public class SerchNode implements Comparable<SerchNode>{
		public SerchNode(int moves,int priority,SerchNode pre,Board board){
			this.moves=moves;
			this.previous=pre;
			this.priority=priority;
			this.board=board;
		}
		int moves;
		SerchNode previous;
		Board board;
		int priority;
		@Override
		public int compareTo(SerchNode o) {
			// TODO Auto-generated method stub
			if(this.priority>o.priority) return 1;
			if(this.priority==o.priority) return 0;
			return -1;
		}
	}
	int moves;
	MinPQ pq=new MinPQ();
    public Solver(Board initial){
    	pq.insert(new SerchNode(0,initial.manhattan(),null,initial));
    }           // find a solution to the initial board (using the A* algorithm)
//    public boolean isSolvable(){
//    	
//    }          // is the initial board solvable?
    public int moves(){
    	return 0;
    }                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution(){
    	
    	SerchNode sn=(SerchNode) pq.delMin();
    	ArrayList array = new ArrayList();
    	while(!sn.board.isGoal()){
    		ArrayList<Board> a=(ArrayList<Board>) sn.board.neighbors();
    		for(Board b:a){
    			if(sn.previous==null||!b.equals(sn.previous.board)){
    				pq.insert(new SerchNode(sn.moves+1,sn.moves+1+b.manhattan(),sn,b));
    			}
    		}
    	}
		while(sn!=null){
			array.add(sn.board);
			sn=sn.previous;
		}		
		return array;
    	
//    	for
    	
    }      // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){
    	int[][] ints={{1,2,3},{4,0,6},{7,5,8}};
    	Board b=new Board(ints);
    	ArrayList<Board> array=(ArrayList<Board>) new Solver(b).solution();
    	for(Board b1:array){
    		b1.toString();
    	}
    }// solve a slider puzzle (given below)
}