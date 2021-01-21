import java.io.FileNotFoundException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		int caches = getcaches(args[0]);
		Cache FC = new Cache(0,0,"to fill");
		if(Integer.parseInt(args[1])>=Integer.parseInt(args[2])&&caches==2) {
			System.out.println("Please enter a cache 1 size smaller than your cache 2 size");
			System.exit(0);
		}
		if(caches==0) {
			System.out.println("Please enter a 1 or a 2 for your number of caches");
			System.exit(0);
		}else {
			if(caches==1) {
				Cache C1 = new Cache(1,Integer.parseInt(args[1]),args[2]);
				FC = C1;
			}else {
				Cache C2 = new Cache(2,Integer.parseInt(args[1]),Integer.parseInt(args[2]),args[3]);
				FC = C2;
			}
		}
		//print out the needed pieces of information for FC to show on console
		FC.search();
		if(caches == 1) {
			System.out.println("C1 cache with "+ Integer.parseInt(args[1])+" entries has been created");
			System.out.println(". . . . . . . . . . . . . . . . . . .");
			System.out.println("The number of global references: "+FC.getNR(1));
			System.out.println("The number of global hits: "+FC.getNH(1));
			System.out.println("The global hit ratio                  : "+(double)FC.NH/(double)FC.NR);
			System.out.println("");
			System.out.println("The number of 1st-level references: "+FC.NR1);
			System.out.println("The number of 1st-level cache hits: "+FC.NH1);
			System.out.println("The 1st-level cache hit ratio             : "+(double)FC.NH/(double)FC.NR);
		}
		if(caches == 2) {
			System.out.println("C1 cache with "+ Integer.parseInt(args[1])+" entries has been created");
			System.out.println("C2 cache with "+ Integer.parseInt(args[2])+" entries has been created");
			System.out.println(". . . . . . . . . . . . . . . . . . .");
			System.out.println("The number of global references: "+FC.getNR(2));
			System.out.println("The number of global hits: "+FC.getNH(2));
			System.out.println("The global hit ratio                  : "+((double)FC.NH1+(double)FC.NH2)/(double)FC.NR1);
			System.out.println("");
			System.out.println("The number of 1st-level references: "+FC.NR1);
			System.out.println("The number of 1st-level cache hits: "+FC.NH1);
			System.out.println("The 1st-level cache hit ratio             : "+FC.getHR1());
			System.out.println("");
			System.out.println("The number of 2nd-level references: "+FC.NR2);
			System.out.println("The number of 2nd-level cache hits: "+FC.NH2);
			System.out.println("The 1st-level cache hit ratio             : "+FC.getHR2());
		}
	}

	private static int getcaches(String string) {
		if(string.contentEquals("2")) {
			return 2;
		}else {
			if(string.contentEquals("1")) {
				return 1;
			}else {
				return 0;
			}
		}
		
	}
}
