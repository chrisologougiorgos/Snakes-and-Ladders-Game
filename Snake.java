//Η κλάση των φιδιών του παιχνιδιού
public class Snake 
{
	private int snakeId;
	private int headId;
	private int tailId;
	
	Snake()
	{
		snakeId=0;
		headId=0;
		tailId=0;
	}
	
	Snake(int snakeId, int headId, int tailId)
	{
		this.snakeId=snakeId;
		this.headId=headId;
		this.tailId=tailId;
	}
	
	Snake(Snake sn)
	{
		this.snakeId=sn.getSnakeId();
		this.headId=sn.getHeadId();
		this.tailId=sn.getTailId();
	}
	
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	
	public int getHeadId() {
		return headId;
	}
	
	public int getSnakeId() {
		return snakeId;
	}
	
	public int getTailId() {
		return tailId;
	}
	
	public void setSnakeId(int snakeId) {
		this.snakeId = snakeId;
	}
	
	public void setTailId(int tailId) {
		this.tailId = tailId;
	}
	
}
