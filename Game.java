import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game 
{
	private int round;
	
	Game()
	{
		this.round=0;
	}
	
	Game(int round)
	{
		this.round=round;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	Map<Integer,Integer> setTurns(ArrayList<Object> players)
	{
		Map<Integer, Integer> m=new HashMap<Integer,Integer>();
		
		for(int i=0;i<players.size();i++)
			m.put(i+1, (int)(Math.random()*6+1));
		return m;	
	}
	
	public static void main(String[] args)
	{
		Game g= new Game(1);
		int counter=1;
		Board b=new Board(10,20,3,3,6);
		ArrayList<Integer[]> a=new ArrayList<Integer[]>();
		b.createBoard();
		
		Player p= new Player(1,"ΑΑΑΑ", 0, b);
		int[] parray=new int[4];//Πίνακας στον οποίο αποθηκεύεται ο πίνακας ίδιων θέσεων που επιστρέφει η συνάρτηση Player.move().
		parray[0]=1;
		HeuristicPlayer pH= new HeuristicPlayer(2,"ΒΒΒΒ", 0, b, a);
		int pHvar=1;//Μεταβλητή στην οποία αποθηκευέται η μεταβλητή ίδιου τύπου που επιστρέφει η συνάρτηση HeuristicPlayer.getNextMove().
		
		ArrayList<Object> arr= new ArrayList<Object>();
		arr.add(p);
		arr.add(pH);
		
		Map<Integer, Integer> m=new HashMap<Integer, Integer>(g.setTurns(arr));
		
		int turn=0;
		if(m.get(p.getPlayerId())>=m.get(pH.getPlayerId()))
			turn=1;
		
		else
			turn=2;
		
		b.CreateElementBoard();
		
		boolean check=false;
		
		while(check==false && g.getRound()<100)
		{
			if(turn==1)
			{
				parray=p.move(parray[0],(int)(Math.random()*6)+1, true);
				turn=2;
			}
			else if(turn==2)
			{
				pHvar=pH.getNextMove(pHvar);
				turn=1;
			}
			
			counter++;
			if((counter%2)==0)
				g.setRound(g.getRound()+1);
			
			if(parray[0]>b.getM()*b.getN())
				check=true;
			else if(pHvar>b.getM()*b.getN())
				check=true;
		}
		
		System.out.println();
		System.out.println("Statistics of Heuristic player: ");
		System.out.println();
		pH.statistics();
		
		System.out.println("Total number of rounds played: "+(g.getRound()-1));
		System.out.println();
		
		double randPlayer=0.65*parray[0]+0.35*p.getScore();
		double heuPlayer=0.65*pHvar+0.35*pH.getScore();
		System.out.println("Total points gathered by the Random Player: "+randPlayer);
		System.out.println("Total points gathered by the Heuristic Player: "+heuPlayer);
			
		System.out.println();
		if(randPlayer==heuPlayer)
		{
			if(parray[0]==pHvar)
				System.out.println("DRAW!!!");
			else if(parray[0]>pHvar)
				System.out.println("THE WINNER IS THE RANDOM PLayer!!!");
			else
				System.out.println("THE WINNER IS THE RANDOM PLayer!!!");
		}
		else if(randPlayer>heuPlayer)
			System.out.println("THE WINNER IS THE RANDOM PLayer!!!");
		else
			System.out.println("THE WINNERS IS THE HEURISTIC PLAYER!!!");
		
		
		
	}
	
	
}
