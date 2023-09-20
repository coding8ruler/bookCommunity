package notice.model.dto;

public class Writer {
	
	private String N_writerid;
	private String N_writername;
	
	public Writer(String n_writerid, String n_writername) {
		
		N_writerid = n_writerid;
		N_writername = n_writername;
	}

	public String getN_writerid() {
		return N_writerid;
	}

	public String getN_writername() {
		return N_writername;
	}

	@Override
	public String toString() {
		return "Writer [N_writerid=" + N_writerid + ", N_writername=" + N_writername + "]";
	} 
	
}