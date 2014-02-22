package edu.towson.cis.cosc490.horan.lab4;

public class TUDoItem {

	private String m_StrTUDoItem;
		
	public TUDoItem(){
		setTUDoItem("");
	}
		
	public TUDoItem(String strTUDoItem){
		setTUDoItem(strTUDoItem);
	}
		
	void setTUDoItem(String strTUdDoItem){
		this.m_StrTUDoItem = strTUdDoItem;
	}
		
	String getTUDoItem(){
		return this.m_StrTUDoItem;
	}
}
