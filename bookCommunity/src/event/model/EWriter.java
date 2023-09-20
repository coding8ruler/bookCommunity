package event.model;

public class EWriter {
	
	private String E_writerid;
	private String E_writername;
	
	public EWriter(String e_writerid, String e_writername) {
		
		E_writerid = e_writerid;
		E_writername = e_writername;
	}

	public String getE_writerid() {
		return E_writerid;
	}

	public String getE_writername() {
		return E_writername;
	}

	@Override
	public String toString() {
		return "Writer [E_writerid=" + E_writerid + ", E_writername=" + E_writername + "]";
	} 
	
}