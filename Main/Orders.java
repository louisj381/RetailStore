/**
 * 
 */
package Main;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

/**
 * Orders is a linked list, which also contains a class Node to
 * hold the data in the order.
 * @author Louis
 * @version 1.1
 * @since January 29, 2017
 */
public class Orders {
	/**
	 * order_id contains the unique id to differentiate different orders
	 */
	private int order_id;
	/**
	 * Date will contain the current date that the orderlist was created.
	 */
	private String Date;
	/**
	 * head is the initial link, will be null to start. 
	 */
	private Node head;
	/**
	 * The current date.
	 */
	public static String DATE = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	/**
	 * Node class will contain objects to be linked to other objects within
	 * the Orders object, and contain all the essential order data.
	 * This data includes the Tool name, the amount requested,
	 * the company, and a Node object to link.
	 * @author Louis
	 * @version 1.1
	 * @since January 29, 2017
	 */
	public class Node {
		/**
		 * Item is the tool name.
		 */
		private String Item;
		/**
		 * Amount is the required stock requested in order.
		 */
		private int Amount;
		/**
		 * This is the company name.
		 */
		private String Supplier;
		/**
		 * this is the Node object that will act as a pointer and link to
		 * the previous node object, or head if it is the first.
		 */
		private Node next;
		
		public Node() {
			Item = "";
			Amount = 0;
			Supplier = "";
			next = null;
		}
	}
	public Orders() {
		head = null;
		Random number = new Random();
		order_id = number.nextInt(90000) + 10000;
		Date = DATE;
	}
	/**
	 * getter function to retrieve the orderID.
	 * @return the order_id
	 */
	public int getOrderID()
	{
		return order_id;
	}
	/**
	 * getter function to retrieve the Head
	 * @return head
	 */
	public Node getHead()
	{
		return head;
	}
	/**
	 * getter function to get the date when the Order was created.
	 * @return Date
	 */
	public String getDate()
	{
		return Date;
	}
	/**
	 * this method creates a new node object and inserts the parameters as well as the items
	 * supplier into that object. The object is appended to the end of the orderlist.
	 * @param item
	 * @param Amount
	 */
	public void add (Tool item, int Amount)
	{
		Node new_node = new Node();
		new_node.Item = item.getName();
		new_node.Amount = Amount;
		new_node.Supplier = item.getSupplier();
		if (head == null)
		{
			new_node.next = head;
			head = new_node;
		}
		else
		{
			Node ptr = new Node();
			ptr = head;
			while (ptr.next != null)
				ptr = ptr.next;
			ptr.next = new_node;
			new_node.next = null;
		}
		
	}
	/**
	 * retrieves the name of the tool from the specified index in the list.
	 * @param n - the index of the order list
	 * @return Item - Tool name at index
	 */
	public String getItem(int n)
    {
    	if (n < 0)
    	{
    		System.out.print("\n Illegal Access.");
    		return null;
    	}
    	Node p = new Node();
    	p = head;
    	for (int i = 0; i < n; i++)
    		p = p.next;

    	return p.Item;
    }
	/**
	 * retrieves the value of the amount ordered from the specified index in the list.
	 * @param n - the index of the order list
	 * @return Amount at index
	 */
	public int getStock(int n)
    {
    	if (n < 0)
    	{
    		System.out.print("\n Illegal Access.");
    		return 0;
    	}
    	Node p = new Node();
    	p = head;
    	for (int i = 0; i < n; i++)
    		p = p.next;

    	return p.Amount;
    }
	/**
	 * retrieves the name of the Supplier from the specified index in the list.
	 * @param n - the index of the order list
	 * @return Supplier at index
	 */
	public String getSupplier(int n)
    {
    	if (n < 0)
    	{
    		System.out.print("\n Illegal Access.");
    		return null;
    	}
    	Node p = new Node();
    	p = head;
    	for (int i = 0; i < n; i++)
    		p = p.next;

    	return p.Supplier;
    }
	
	
}
