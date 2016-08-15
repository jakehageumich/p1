
public class Product {
		
	private int id;
	private String pi_names;
	private String term;
	private String funders;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPi_names() {
		return pi_names;
	}
	public void setPi_names(String pi_names) {
		this.pi_names = pi_names;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getFunders() {
		return funders;
	}
	public void setFunders(String funders) {
		this.funders = funders;
	}
	public Product(int id, String pi_names, String term, String funders) {
		super();
		this.id = id;
		this.pi_names = pi_names;
		this.term = term;
		this.funders = funders;
	}
}
