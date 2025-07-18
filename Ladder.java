//Η κλάση των σκαλών του παιχνιδιού
public class Ladder 
{
	private int ladderId;
	private int topSquareId;
	private int bottomSquareId;
	private boolean broken;
	
	Ladder()
	{
		ladderId=0;
		topSquareId=0;
		bottomSquareId=0;
		broken=false;
	}
	
	Ladder(int ladderId, int topSquareId, int bottomSquareId, boolean broken)
	{
		this.ladderId=ladderId;
		this.topSquareId=topSquareId;
		this.bottomSquareId=bottomSquareId;
		this.broken=broken;
	}
	
	Ladder(Ladder la)
	{
		this.ladderId=la.getLadderId();
		this.topSquareId=la.getTopSquareId();
		this.bottomSquareId=la.getBottomSquareId();
		this.broken=la.isBroken();
	}
	
	public void setLadderId(int ladderId) {
		this.ladderId = ladderId;
	}
	
	public int getLadderId() {
		return ladderId;
	}
	
	public int getTopSquareId() {
		return topSquareId;
	}
	
	public void setTopSquareId(int topSquareId) {
		this.topSquareId = topSquareId;
	}
	
	public void setBottomSquareId(int bottomSquareId) {
		this.bottomSquareId = bottomSquareId;
	}
	
	public int getBottomSquareId() {
		return bottomSquareId;
	}
	
	public void setBroken(boolean broken) {
		this.broken = broken;
	}
	
	public boolean isBroken() {
		return broken;
	}
}
