
public class Player 
{
	protected int playerId;
	protected String name;
	protected int score;
	protected Board board;
	
	Player()
	{
		this.playerId=0;
		this.name=" ";
		this.score=0;
		this.board=new Board();
	}
	
	Player(int playerId, String name, int score, Board board)
	{
		this.playerId=playerId;
		this.name=name;
		this.score=score;
		this.board=new Board(board);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	/*Πραγματοποιεί την επόμενη κίνηση του παίχτη μετακινόντας των κατά dice τετράγωνα και ελέγχοντας το τετράγωνο στο οποίο καταλήγει. Εάν αυτό περιέχει κάτω
	  μέρος σκάλας ή κεφάλι φιδιού, πραγματοποιεί εκ νέου μετακίνηση του πάιχτη και ελέγχει το νέο τετράγωνο με τον ίδιο τρόπο, ενώ έαν αυτό περιέχει δώρο, προσθέτει στο score 
	  του παίχτη τους πόντους του δώρου.*/
	/*Πρόσθεσα την μεταβλητή real ώστε να υπάρχει "διαχωρισμός" μεταξύ των κλήσεων της συνάρτησης για κινήσεις που πράγματι σημειώνονται και των κλήσεων της συνάρτησης
	  που γίνονται μέσω της Heuristic.evaluate() ώστε να υπολογιστεί η καλύτερη δυνατή κίνηση για τον Heuristic Player.*/
	int[] move(int id, int dice, boolean real)
	{
		int[] res=new int[4];
		int nextSquare=id+dice;
		int numSn=0;
		int numLa=0;
		int numPr=0;
		
		boolean checkSn=false,checkLa=false;
		while(checkSn==false || checkLa==false)
		{
			checkSn=true;
			checkLa=true;
			
			int i=0,j=0,k=0;
			
			for(i=0;i<board.getN();i++)
			{
				boolean b=false;
				
				for(j=0;j<board.getM();j++)
					if(i<board.getN()&& j<board.getM())
						if(board.getSquares()[i][j]==nextSquare && nextSquare<=board.getM()*board.getN())
						{
							b=true;
							break;
						}
				if(b==true)
					break;
			}
			
			
			for(k=0;k<board.getSnakes().length;k++)
				if(i<board.getN()&& j<board.getM())
					if(board.getSquares()[i][j]==board.getSnakes()[k].getHeadId() && nextSquare<=board.getM()*board.getN())
					{
						numSn++;
						if(real==true)
							System.out.println("You have been bitten by a Snake!!!");
						checkSn=false;
						break;
					}
			
			if(checkSn==false)
				nextSquare=board.getSnakes()[k].getTailId();
			
			
			
			for(k=0;k<board.getLadders().length;k++)
				if(i<board.getN()&& j<board.getM())
					if(board.getSquares()[i][j]==board.getLadders()[k].getBottomSquareId() && nextSquare<=board.getM()*board.getN())
					{
						if(board.getLadders()[k].isBroken()==false)
						{	
							numLa++;
							if(real==true)
							{	board.getLadders()[k].setBroken(true);
								System.out.println("You have climbed a Ladder!!!");
							}
							checkLa=false;
						}
					break;
					}
			
			if(checkLa==false)
				nextSquare=board.getLadders()[k].getTopSquareId();	
			
			
			for(k=0;k<board.getPresents().length;k++)
				if(i<board.getN()&& j<board.getM())
					if(board.getSquares()[i][j]==board.getPresents()[k].getPresentSquareId())
					{
						if(board.getPresents()[k].getPoints()!=0)
						{
							numPr++;
							score=score+board.getPresents()[k].getPoints();
							if(real==true)
							{	System.out.println("You have earned a Present!!!");
								board.getPresents()[k].setPoints(0);
							}
						}
					break;
				}	
		}
		
		res[0]=nextSquare;
		res[1]=numSn;
		res[2]=numLa;
		res[3]=numPr;
		
		return res;	
	}
}
