/**
 * 
 */
package Main;

/**
 * Supplier class holds the attributes of a Company, include its name, address,
 * the salesperson and the supplier id.
 * @author Louis
 * @version 1.1
 * @since January 29, 2017
 */
public class Supplier {
	/**
	 * the Suppliers id.
	 */
	private int id;
	/**
	 * the company name.
	 */
	private String comp_name;
	/**
	 * the address of the company.
	 */
	private String address;
	/**
	 * the salesperson's name.
	 */
	private String sales_name;
	/**
	 * a getter function to retrieve the supplier's id.
	 * @return id
	 */
	public int getSId()
	{
		return id;
	}
	/**
	 * a getter function to return the company name
	 * @return comp_name
	 */
	public String getComp() {
		return comp_name;
	}
	public Supplier() {
		id = 0;
		comp_name = null;
		address = null;
		sales_name = null;
	}
	public Supplier(int id, String comp_name, String address, String sales_name) {
		this.id = id;
		this.comp_name = comp_name;
		this.address = address;
		this.sales_name = sales_name;
	}
}
