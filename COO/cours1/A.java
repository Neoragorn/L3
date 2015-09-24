package cours1;

import java.util.*;

public class A
{	
	public A()
	{

	}

	public void fu()
	{
		g();
	}

	public void g()
	{
		System.out.println("Hey");
	}

	public void Function(A a)
	{
		System.out.println("A.f(A)");
	}

	public static void main(String[] args)
	{
		A a = new B();
		B b =  new B();

		a.Function(a);
		b.Function(b);
		List<A> Alist = new ArrayList<>();
		Alist.add(b);
		(new A()).fu();
		(new B()).fu();
	} 
}