package fff;

public class product 
{
	private String name;
	private String code;
	private String price;
	private int num;
	public product(String name, String code, String price, int num) 
	{
        this.name = name;
        this.code = code;
        this.price = price;
        this.num = num;
    }
	public void setname(String name)
	{
		this.name = name;
	}
	public void setcode(String code)
	{
		this.code = code;
	}
	public void setprice(String price)
	{
		this.price = price;
	}
	public void setnum(int num)
	{
		this.num = num;
	}
	public String getname()
	{
		return name;
	}
	public String getcode()
	{
		return code;
	}
	public String getprice()
	{
		return price;
	}
	public int getnum()
	{
		return num;
	}
}
