package FileInPutStreamDemo;

public class StudentDemo {

		private String name;
		private int age;
		private int score;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public StudentDemo() {
			super();
		}

		public StudentDemo(String name, int age, int score) {
			super();
			this.name = name;
			this.age = age;
			this.score = score;
		}
		
		public int hashCode(String name,int age,int score){
			
			return this.getName().hashCode()+this.getAge()*2+this.getScore()*3;
		}
		
		public boolean equals(Object obj) {
			if (this==obj) {
				return true;
			}else if (!(obj instanceof StudentDemo)) {
				return false;
			}else{
				StudentDemo student = (StudentDemo) obj;
				
				return this.getName().equals(student.name)&&this.getAge()==student.age&&this.getScore()==student.score;
			}
		
		}
}
