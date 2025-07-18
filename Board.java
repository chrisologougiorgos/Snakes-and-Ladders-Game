//Η κλάση του ταμπλού του παιχνιδιού
public class Board 
{
	private int N, M;
	private int [][] squares;
	private Snake [] snakes;
	private Ladder [] ladders;
	private Present [] presents;
	
	Board()
	{
		N=0;
		M=0;
		squares= new int[N][M];
		snakes= new Snake[0];
		ladders= new Ladder[0];
		presents= new Present[0];
	}
	
	Board(int n, int m, int s, int l, int p)
	{
		N=n;
		M=m;
		squares= new int[N][M];
		snakes= new Snake[s];
		ladders= new Ladder[l];
		presents= new Present[p];
	}
	
	Board( Board bo)
	{
		this.N=bo.N;
		this.M=bo.M;
		
		squares= new int[N][M];
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				this.squares[i][j]=bo.squares[i][j];
		
		snakes= new Snake[bo.snakes.length];
		for(int i=0;i<this.snakes.length;i++)
		{
			this.snakes[i]=new Snake(bo.snakes[i]);
		}
			
		
		ladders= new Ladder[bo.ladders.length];
		for(int i=0;i<this.ladders.length;i++)
		{
			this.ladders[i]=new Ladder(bo.ladders[i]);
		}
		
		presents= new Present[bo.presents.length];
		for(int i=0;i<this.presents.length;i++)
		{
			this.presents[i]=new Present(bo.presents[i]);
		}
	}
	
	public Ladder[] getLadders() {
		return ladders;
	}
	
	public int getM() {
		return M;
	}
	
	public int getN() {
		return N;
	}
	
	public Present[] getPresents() {
		return presents;
	}
	
	public Snake[] getSnakes() {
		return snakes;
	}
	
	public int[][] getSquares() {
		return squares;
	}
	
	public void setLadders(Ladder[] ladders) {
		this.ladders = ladders;
	}
	
	public void setM(int m) {
		M = m;
	}
	
	public void setN(int n) {
		N = n;
	}
	
	public void setPresents(Present[] presents) {
		this.presents = presents;
	}
	
	public void setSnakes(Snake[] snakes) {
		this.snakes = snakes;
	}
	
	public void setSquares(int[][] squares) {
		this.squares = squares;
	}
	/*Δημιουργεί τυχαία φίδια, σκάλες και δώρα σε τυχαία σημεία του ταμπλού. Κατόπιν πραγματοποιεί όλους τους ελέγχους(π.χ ελέγχει αν δύο φίδια έχουν ίδιο κεφάλι) και 
	  θέτει εκ νέου τιμές στα στοιχεία(φίδια, σκάλες, δώρα) που δεν τηρούν τις προυποθέσεις που έχουν οριστεί*/
	void createBoard()
	{
		for(int i=0;i<snakes.length;i++)
		{	
			snakes[i]=new Snake();
			snakes[i].setSnakeId(i+1);
			snakes[i].setTailId((int)(Math.random()*((N-1)*M)+1));
			snakes[i].setHeadId((int)(Math.random()*(N*M-1-snakes[i].getTailId()-M+1)+snakes[i].getTailId()+M));			
		}
		
		for(int i=0;i<ladders.length;i++)
		{
			ladders[i]=new Ladder();
			ladders[i].setLadderId(i+1);
			ladders[i].setBottomSquareId((int)(Math.random()*((N-1)*M)+1));
			ladders[i].setTopSquareId((int)(Math.random()*(N*M-1-ladders[i].getBottomSquareId()-M+1)+ladders[i].getBottomSquareId()+M));
			ladders[i].setBroken(false);
		}
		
		for(int i=0;i<presents.length;i++)
		{
			presents[i]= new Present();
			presents[i].setPresentId(i+1);
			presents[i].setPresentSquareId((int)(Math.random()*(N*M)+1));
			
			while(presents[i].getPresentSquareId()==1)
			{
				presents[i].setPresentSquareId((int)(Math.random()*(N*M)+1));
			}
			
			
			int max = 10;
	        int min = -10;
	        int range = max - min +1;  
	        
	        presents[i].setPoints((int)(Math.random() * range) + min);
	      
		}
		
		boolean checksn=true,checkla=true,checkpr=true;
		do
		{
			checksn=true;
			checkla=true;
			checkpr=true;
			
			int var=0;
			int countsn=0;
			int countla=0;
			int countpr=0;
			
			//elegxos gia snakes
			for(int i=0;i<snakes.length;i++)
			{
				
				if(snakes[i].getHeadId()>=(N*M+1))
				{
					countsn=i;
					checksn=false;
				}
				if(checksn==false)
					break;
				
				var=snakes[i].getHeadId();//kefali
				for(int j=0;j<snakes.length;j++)
					if(i!=j)
						if(var==snakes[j].getHeadId() || var==snakes[j].getTailId())
							{
								countsn=i;
								checksn=false;
								break;
							}
				if(checksn==false)
					break;
				
				var=snakes[i].getTailId();//oura
				for(int j=0;j<snakes.length;j++)
					if(i!=j)
						if(var==snakes[j].getHeadId() || var==snakes[j].getTailId())
						{
							countsn=i;
							checksn=false;
							break;
						}
				if(checksn==false)
					break;
			}
			
			//elegxos gia ladders
			for(int i=0;i<ladders.length;i++)
			{
				
				if(ladders[i].getTopSquareId()>=(N*M+1))
				{
					countla=i;
					checkla=false;
				}
				if(checkla==false)
					break;
				
				var=ladders[i].getTopSquareId();//kefali
				for(int j=0;j<ladders.length;j++)
					if(i!=j)
						if(var==ladders[j].getTopSquareId() || var==ladders[j].getBottomSquareId())
							{
								countla=i;
								checkla=false;
								break;
							}
				if(checkla==false)
					break;
				
				var=ladders[i].getBottomSquareId();//oura
				for(int j=0;j<ladders.length;j++)
					if(i!=j)
						if(var==ladders[j].getTopSquareId() || var==ladders[j].getBottomSquareId())
						{
							countla=i;
							checkla=false;
							break;
						}
				if(checkla==false)
					break;
			}
			
			//elegxos gia presents
			for(int i=0;i<presents.length;i++)
			{
				for(int j=0;j<presents.length;j++)
					if(i!=j)
						if(presents[i].getPresentSquareId()==presents[j].getPresentSquareId())
						{
							countpr=i;
							checkpr=false;
							break;
						}
				if(checkpr==false)
					break;
			}
			
			//elegxos gia kefali snake me bottom ladder
			for(int i=0;i<snakes.length;i++)
				for(int j=0;j<ladders.length;j++)
					if(snakes[i].getHeadId()==ladders[j].getBottomSquareId())
						checksn=false;
			
			
			if(checksn==false)
			{	snakes[countsn].setTailId((int)(Math.random()*((N-1)*M)+1));
				snakes[countsn].setHeadId((int)(Math.random()*(N*M-1-snakes[countsn].getTailId()-M+1)+snakes[countsn].getTailId()+M));
			}
			
			if(checkla==false)
			{
				ladders[countla].setBottomSquareId((int)(Math.random()*((N-1)*M)+1));
				ladders[countla].setTopSquareId((int)(Math.random()*(N*M-1-ladders[countla].getBottomSquareId()-M+1)+ladders[countla].getBottomSquareId()+M));
			}
			
			if(checkpr==false)
			{
				presents[countpr].setPresentSquareId((int)(Math.random()*(N*M-1)+1));
			}
			
		}while(checksn==false || checkla==false || checkpr==false);
			
	
	
		int count=1;
		int c=1;
		
		for(int i=N-1;i>=0;i--)	
			if(c==1)
			{
				for(int j=0;j<=M-1;j++)
					squares[i][j]=count++;
				c=0;
			}
			else 
				for(int j=M-1;j>=0;j--)
				{
					squares[i][j]=count++;
					c=1;
				}
	}
	/*Δημιουργεί και εκτυπώνει άπο έναν δισδιάστατο πίνακα τύπου String για καθένα απο τα φίδια, τις σκάλες και τα δώρα ώστε ο χρήστης να μπορεί
	  να δει σε ποια τετράγωνα του ταμπλού βρίσκονται τα παραπάνω στοιχεία.*/
	void CreateElementBoard()
	{
		String[][] elementBoardSnakes=new String[N][M];
		String[][] elementBoardLadders=new String[N][M];
		String[][] elementBoardPresents=new String[N][M];
		
		//SNAKES
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				elementBoardSnakes[i][j]="___";
		for(int cnt=0;cnt<snakes.length;cnt++)
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					 if(squares[i][j]==snakes[cnt].getTailId())
						elementBoardSnakes[i][j]="ST"+snakes[cnt].getSnakeId();
					 else if(squares[i][j]==snakes[cnt].getHeadId())
						 elementBoardSnakes[i][j]="SH"+snakes[cnt].getSnakeId();
					
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
				System.out.print(elementBoardSnakes[i][j]+" ");
			System.out.println(" ");
		}
		System.out.println();
				
		
		//LADDERS
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				elementBoardLadders[i][j]="___";
		for(int cnt=0;cnt<ladders.length;cnt++)
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					if(squares[i][j]==ladders[cnt].getTopSquareId())
						elementBoardLadders[i][j]="LU"+ladders[cnt].getLadderId();
					else if(squares[i][j]==ladders[cnt].getBottomSquareId())
						elementBoardLadders[i][j]="LD"+ladders[cnt].getLadderId();
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
				System.out.print(elementBoardLadders[i][j]+" ");
			System.out.println();
		}
		System.out.println();
				
		
		//PRESENTS
		
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				elementBoardPresents[i][j]="___";
		
		for(int cnt=0;cnt<presents.length;cnt++)
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					if(squares[i][j]==presents[cnt].getPresentSquareId())
						elementBoardPresents[i][j]="PR"+presents[cnt].getPresentId();
						
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
				System.out.print(elementBoardPresents[i][j]+" ");
			System.out.println(" ");
		}
		System.out.println();
	}
	
}
