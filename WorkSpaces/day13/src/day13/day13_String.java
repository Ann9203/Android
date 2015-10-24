package day13;

public class day13_String {
	public static void main(String[] args) {
		int arr[]={2,3,6,8,1,9};
		for (int i = 0; i < arr.length; i++) {
			if(i%3==1)
			{
				arr[i]*=3;
			}
			System.out.println(arr[i]);
		}
	}

}
