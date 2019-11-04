import java.util.*;
public class Music {

	String name;
	int id;
	ArrayList<Song> slist =new ArrayList<Song>();
	void setName(String name)
	{
		this.name=name;
	}
	void setId(int id)
	{
		this.id=id;
	}
	void setList(ArrayList<Song> slist)
	{
	this.slist=slist;
	}
	ArrayList<Song> getList()
	{
		return slist;
	}
	String getName()
	{
		return name;
	}
	int getId()
	{
		return id;
	}
	
}