package freeboard.model;


//Freeboard 모델 정보 
public class FreeboardData {
	
	private Freeboard freeboard;

	public FreeboardData(Freeboard freeboard) {
		this.freeboard = freeboard;
	}

	public Freeboard getFreeboard() {
		return freeboard;
	}

	public void setFreeboard(Freeboard freeboard) {
		this.freeboard = freeboard;
	}

	@Override
	public String toString() {
		return "FreeboardData [freeboard=" + freeboard + "]";
	}
	
	

	
}