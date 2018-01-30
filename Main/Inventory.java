package Main;
import java.io.*;
import java.util.*;
/**
 * The class which handles and holds tools and allows access to them through the main function.
 * 
 * @author Louis
 * @version 1.1
 * @since January 29, 2017
 */
public class Inventory {
	/**
	 * The index used to access elements within the Orderlist.
	 */
	private static int linkedIndex;
	/**
	 * An ArrayList of tools which will be initialized once data is read
	 * and Inventory object is constructed.
	 */
	private ArrayList<Tool> Tools;
	/**
	 * The order object which will be constucted upon check of stock,
	 * or if there is an order placed
	 */
	private Orders order;
	
	/**
	 * Constructor inializes Tools when a list of tools is passed to the Inventory object.
	 * @param ToolData
	 */
	public Inventory(ArrayList<Tool> ToolData) 
	{
		Tools = new ArrayList<Tool>(ToolData);
	}
	/**
	 * Add a tool object to the Tools array, appending to the end.
	 * @param tool
	 */
	public void add(Tool tool)
	{
		Tools.add(tool);
	}
	/**
	 * Removes a tool object from the Tools array, if it exists.
	 * @param tool
	 */
	public void delete(Tool tool)
	{
			if (Tools.contains(tool))
				Tools.remove(tool);
	}
	/**
	 * scans the Tools array for a tool with a matching name,
	 * if it doesn't, print message that it was not found.
	 * @param name
	 */
	public void search(String name)
	{
		Iterator<Tool> cycle;
		cycle = Tools.iterator();
		int index = 0;
		while (cycle.hasNext())
		{
			if (cycle.next().getName().equals(name))
			{
				Tools.get(index).print();
				return;
			}
			index++;
		}
		System.out.print("tool not found");
	}
	/**
	 * same as method above, just searching with toolid instead.
	 * @param id
	 */
	public void search(int id)
	{
		Iterator<Tool> check;
		check = Tools.iterator();
		int index = 0;
		while (check.hasNext())
		{
			if (check.next().getID() == id)
			{
				Tools.get(index).print();
				return;
			}
			index++;
		}
		System.out.println("tool not found");
	}
	/**
	 * scans through all Tools, and creates an order list
	 * if any tool has stock below 40. Prints the name and stock
	 * of all tools by calling the print() method within the Tools class.
	 */
	public void quantity()
	{
		Iterator<Tool> list;
		list = Tools.iterator();
		int index = 0;
		while (list.hasNext())
		{
			if (list.next().getStock() < 40)
			{
				if (order == null)
				{
					order = new Orders();
					order.add(Tools.get(index), 50 - Tools.get(index).getStock());
					linkedIndex = 0;
				}
				else
				{
					order.add(Tools.get(index), 50 - Tools.get(index).getStock());
					linkedIndex++;					
				}

			}
			Tools.get(index).print();
				index++;
		}
	}
	/**
	 * Method to order a specific amount of a tool, which will be added
	 * to the order list. Only the tool name needs to be passed.
	 * @param name
	 * @param Amount
	 */
	public void orderTool(String name, int Amount)
	{
		Iterator<Tool> check;
		check = Tools.iterator();
		int index = 0;
		while (check.hasNext())
		{
			if (check.next().getName().equals(name))
			{
				break;
			}
			index++;
		}
		if (order == null || order.getDate() != Orders.DATE)
		{
			order = new Orders();
			order.add(Tools.get(index), Amount);
			linkedIndex = 0;
		}
		else
		{
			order.add(Tools.get(index), Amount);
			linkedIndex++;
		}
	}
	/**
	 * Takes the current working order and appends to it unless the file has not been created,
	 * in which case it creates it in the directory. Write data from orderlist to orders.txt.
	 */
	public void printOrder ()
	{
		Writer writer = null;
		File tmpDir = new File("C:\\Users\\louis\\Documents\\UNIVERSITY\\ENSF 409\\Lab_2\\Retail_Store\\orders.txt");

		try {
			writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("orders.txt"), "utf-8"));
			if (!(tmpDir.exists()))
			{
			for (int i = 0; i < 64; i++)
				writer.write('*');
			writer.write("\nORDER ID:\t\t\t");
			String ID = Integer.toString(order.getOrderID());
			writer.write(ID);
			writer.write("\nDate Ordered:\t\t");
			writer.write(order.getDate());
			}
			for (int i = 0; i <= linkedIndex; i++)
			{
				writer.write("\n\nItem description:\t");
				writer.write(order.getItem(i));
				writer.write("\nAmount Ordered:\t\t");
				writer.write(Integer.toString(order.getStock(i)));
				writer.write("\nSupplier:\t\t\t");
				writer.write(order.getSupplier(i));
			}



			
		} catch (IOException ex) {
		} finally {
			try {writer.close();} catch (Exception ex) {/*stuff*/}
		}
	}
	
	/**
	 * NOTE: T.A must change the directory of input.txt and suppliers.txt
	 * appropriately in order to copy data.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// Below is the lengthy code to read the files into constructors of supplier and tools, and link them
		BufferedReader read_items = new BufferedReader (new FileReader("C:\\Users\\louis\\Documents\\UNIVERSITY\\ENSF 409\\Lab_2\\Retail_Store\\Main\\items.txt"));
		BufferedReader read_suppliers = new BufferedReader (new FileReader("C:\\Users\\louis\\Documents\\UNIVERSITY\\ENSF 409\\Lab_2\\Retail_Store\\Main\\suppliers.txt"));
		String originalI, originalS, toolid, name, stock, price, id, comp_name, address, salesname;
		ArrayList<Supplier> supparr = new ArrayList<Supplier>();
		try {
			while ((originalS = read_suppliers.readLine()) != null)
			{
				String [] values = originalS.split(";");		
					id = values[0];
					comp_name = values[1];
					address = values[2];
					salesname = values[3];
					Supplier supplier = new Supplier(Integer.parseInt(id), comp_name, address, salesname);
					supparr.add(supplier);
			}
			} finally	{
				read_suppliers.close();
			}
		ArrayList<Tool> ToolList = new ArrayList<Tool>();
		try {
		while ((originalI = read_items.readLine()) != null)
		{
			String [] values = originalI.split(";");		
	
				toolid = values[0];
				name = values[1];
				stock = values[2];
				price = values[3];
				id = values[4];
				int i = 0;
				while (supparr.get(i) != null)
				{
				if (Integer.parseInt(id) == supparr.get(i).getSId())
					{
						break;
					}
						
					i++;
				}
					
				Tool tool = new Tool(Integer.parseInt(toolid), name, Integer.parseInt(stock), Double.parseDouble(price), supparr.get(i));
				ToolList.add(tool);
		}
		} finally	{
			read_items.close();
		}
		Inventory initializer = new Inventory(ToolList);
		/* Let's modify the inventory:
		*/
		Supplier testSupplier = new Supplier(9999, "The Test Supplier", "nowhere avenue", "Joe Fresh");
		Tool magicTool = new Tool(0000, "magic", 1, 9999.90, testSupplier);
		initializer.add(magicTool);
		/* Let's search for our new tool by name */
		initializer.search("magic");
		/* Now let's get rid of this phoney tool */
		initializer.delete(magicTool);
		/* Check if it's really gone, using id */
		initializer.search(0000);
		/* display the current quantity of all tools
 		 * and automatically generates an order list for all stock below 40
 		 * */
		initializer.quantity();
		/*finally, add custom order with specific amount, just enter toolname and stock */
		//initializer.orderTool("Thingies", 100);
		initializer.printOrder();
	}

}
