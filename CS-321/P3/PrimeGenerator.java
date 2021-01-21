
public class PrimeGenerator {
	public int P;
	public PrimeGenerator() {
		P=findsize();
	}
	private static int findsize() {
		int prime = 95501;
		int p1=0;
		for(int i = prime;i<96000;i+=2) {
			boolean result=false;
			for(int j=0;j<3;j++) {
				result=isprime(i);
			}
			if(result) {
				if(i-p1==2) {
					p1=i;
					return i;
				}else {
					p1=i;
				}
			}
			
			
		}
		return -2;
		
	}

	private static boolean isprime(int prime) {
		String B = Integer.toBinaryString(prime-1);
		int a =(int) (Math.random()*100+2);
		int result=a;
		for(int i=1;i<B.length();i++) {
			if(B.charAt(i)=='1') {
				result=(int) (((Math.pow(result,2))*a)%prime);
			}else {
				result=(int) (((Math.pow(result,2)))%prime);
			}
		}
		if(result==1) {
			return true;
		}else {
			return false;
		}
		
	}
}
