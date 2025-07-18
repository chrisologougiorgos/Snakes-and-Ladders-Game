import java.util.ArrayList;

public class HeuristicPlayer extends Player 
{
	ArrayList<Integer[]> path;
	
	HeuristicPlayer()
	{
		super();
		this.path=new ArrayList<Integer[]>();
	}
	
	HeuristicPlayer(int playerId, String name, int score, Board board, ArrayList<Integer[]> path)
	{
		super(playerId, name, score, board);
		this.path= new ArrayList<Integer[]>(path); 
	}
	/*Υπολογίζει τους συνολικούς πόντους που θα αποκομίσει ο παιχτής εάν μετακινηθεί από το τετράγωνο με id currentPos στο τετράγωνο με id currentPos+dice.
	  Στο τελος καλεί την move(currentPos, 0) ώστε ο παίχτης να "επιστρέψει" στην θέση που βρισκόταν αφού δεν συμβάινει πραγματική κίνηση του παιχτη*/
	double evaluate(int currentPos, int dice)
	{
		int prevScore=score;
		int possibleNewSquare=0;
		int possibleNewScore=0;
		
		int []results=new int[4];
		results=move(currentPos,dice,false);
		
		possibleNewSquare=results[0];
		possibleNewScore=score-prevScore;
		
		move(currentPos,0,true);//gia na mhn kineitai pragmatika!!!!
		score=prevScore;//gia na mhn pairnei pragmatika pontous!!!!!
		return (0.65*(possibleNewSquare-currentPos)+0.35*(possibleNewScore));	
	}
	
	/*Επιλέγει και πραγματοποιεί την καλύτερη δυνατή κίνηση, η οπόια υπολογίζεται απο αλλεπάλληλες κλήσεις της Heuristic.evaluate() για όλες τις πιθανές κινήσεις 
	  του παίχτη και σύγκριση αυτών. Κατόπιν, τα δεδομένα της κίνησης αυτής αποθηκεύονται στην μεταβλητή path.*/
	int getNextMove(int currentPos)
	{	
		double max=0;
		int cnt=0;
		int prevScore=score;
		int [] res=new int[4];
		Integer[] res2=new Integer[6];
		
			double[] array1=new double[6];
			
			if(board.getM()*board.getN()-currentPos>=6)
				for(int i=0;i<6;i++)
					array1[i]=evaluate(currentPos,i+1);
			else
			{
				for(int i=0;i<board.getM()*board.getN()-currentPos;i++)
					array1[i]=evaluate(currentPos,i+1);
				
				
				for(int i=board.getM()*board.getN()-currentPos;i<6;i++)
					array1[i]=evaluate(currentPos,board.getM()*board.getN()-currentPos);
					
				
			}
				
			
			
			max=array1[0];
			for(int i=0;i<6;i++)
				if(max<=array1[i])
				{
					max=array1[i];
					cnt=i+1;
				}
			
			res=move(currentPos,cnt,true);
			
			res2[0]=Integer.valueOf(cnt);
			res2[1]=Integer.valueOf(score-prevScore);
			res2[2]=Integer.valueOf(res[0]-currentPos);
			res2[3]=Integer.valueOf(res[3]);
			res2[4]=Integer.valueOf(res[1]);
			res2[5]=Integer.valueOf(res[2]);
			path.add(res2);
			
			
			return res[0];		
	}
	
	/*Εκτυπώνει τα δεδομένα κάθε κίνησης του Heuristic Player τα οποία έχουν αποθηκευτεί στην μεταβλητη path και κατόπιν
	  εκτυπώνει τους συνολικόυς αριθμούς φιδιών, σκαλών και δώρων στα οποία "έπεσε" ο Heuristic Player*/
	void statistics()
	{
		int sumOfSnakes=0;
		int sumOfLadders=0;
		int sumOfPresents=0;
	
		for(int i=0;i<path.size();i++)
		{
			System.out.println("Round "+(i+1)+":");
			System.out.println("DICE: "+path.get(i)[0]);
			System.out.println("Points Gained: "+path.get(i)[1]);
			System.out.println("Steps: "+path.get(i)[2]);
			System.out.println("Presents Earned: "+path.get(i)[3]);
			System.out.println("Snakes bitten by: "+path.get(i)[4]);
			System.out.println("Ladders climbed: "+path.get(i)[5]);
			System.out.println();
			System.out.println("-------------------");
			System.out.println();
			
			sumOfSnakes+=path.get(i)[4];
			sumOfLadders+=path.get(i)[5];
			sumOfPresents+=path.get(i)[3];
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Heuristic total stats: ");
		System.out.println("Total number of Snakes bitten by: "+sumOfSnakes);
		System.out.println("Total number of Ladders climbed: "+sumOfLadders);
		System.out.println("Total numbers of Presents earned: "+sumOfPresents);
		System.out.println();
		
	}
	
}
