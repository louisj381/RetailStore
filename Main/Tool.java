/**
 * 
 */
package Main;

/**
 * This class holds the information credentials of an individual tool.
 * @author Louis
 * @version 1.1
 * @since January 29, 2017
 */
public class Tool {
	/**
	 * this unique id helps identify which tool it is.
	 */
	private int Tool_id;
	/**
	 * the name of the tool.
	 */
	private String name;
	/**
	 * the amount of tool within the inventory.
	 */
	private int stock;
	/**
	 * the cost of that tool
	 */
	private double price;
	/**
	 * the Supplier object that corresponds to the tool, through the supplier id.
	 */
	private Supplier supplier;
	
	public Tool (int Tool_id, String name, int stock, double price, Supplier supplier)
	{
		this.Tool_id = Tool_id;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.supplier = supplier;
	}
	/**
	 * getter function to retrieve the tool name
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * getter function to retrieve the ID
	 * @return Tool_id
	 */
	public int getID()
	{
		return Tool_id;
	}
	/**
	 * getter function to retrieve the stock
	 * @return stock
	 */
	public int getStock()
	{
		return stock;
	}
	/**
	 * getter function to retrieve the supplier
	 * @return supplier, accessed through the supplier object for that tool.
	 */
	public String getSupplier()
	{
		return supplier.getComp();
	}
	/**
	 * prints the toolname and stock to the console, invoked by quantity
	 * @see quantity
	 */
	public void print()
	{
		System.out.print(name + ";" + stock + "\n");		
	}
}
