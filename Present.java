//Η κλάση των δώρων του παιχνιδιού
public class Present 
{
	private int presentId;
	private int presentSquareId;
	private int points;
	
	Present()
	{
		presentId=0;
		presentSquareId=0;
		points=0;
	}
	
	Present(int presentId, int presentSquareId, int points)
	{
		this.presentId=presentId;
		this.presentSquareId=presentSquareId;
		this.points=points;
	}
	
	Present(Present pr)
	{
		this.presentId=pr.getPresentId();
		this.presentSquareId=pr.getPresentSquareId();
		this.points=pr.getPoints();
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getPresentId() {
		return presentId;
	}
	
	public int getPresentSquareId() {
		return presentSquareId;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setPresentId(int presentId) {
		this.presentId = presentId;
	}
	
	public void setPresentSquareId(int presentSquareId) {
		this.presentSquareId = presentSquareId;
	}
}
