package day13;

public class AnimalTest {
	public static void main(String[] args) {
		Dog d=new Dog();
		Cat c=new Cat();
		
		d.setAge(2);
		d.setName("小汪汪");
		System.out.println(d.getAge()+"***********"+d.getName());
		c.setAge(2);
		c.setName("小苗苗");
		System.out.println(c.getAge()+"**********8"+c.getName());
		c.run();
		c.eat();
		d.run();
		d.eat();
		
		
	}

}
