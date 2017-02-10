package Metier;



/**
 * @author Cyril or Will30 (GitHub) or Will15 (GitLab)

 * class Led : Light Emitting Diode
 * ID = 1 --> black color (unable to communicate or Strategic Point down)
 * ID = 2 --> red color (maximal alert triggered)
 * ID = 3 --> orange color (minimal alert triggered)
 * ID = 4 --> green color (no problem detected) 
 */
public class Led 
{
	private byte ID = 1;
	private String color = "black";
	private String UNC = "/images/voyant/black.png";
	
	private Solution solution;
	
	
	public Led()
	{
		
	}
	

/////////////************************    Getters and Setters *************************
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
		

	public String getUNC() {
		return UNC;
	}


	public void setUNC(String uNC) {
		UNC = uNC;
	}


	public byte getID() {
		return ID;
	}


	public void setID(byte iD) {
		ID = iD;
	}


	public Solution getSolution() {
		return solution;
	}


	public void setSolution(Solution solution) {
		this.solution = new Solution();
	}

}
