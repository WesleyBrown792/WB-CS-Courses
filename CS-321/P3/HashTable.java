import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HashTable {
	private HashObject[] LTable = new HashObject[new PrimeGenerator().P];
	private HashObject[] DTable = new HashObject[new PrimeGenerator().P];
	private int DS;//data source
	private double LF;//load factor
	private int DL;//debug level
	private int totalkeys;//total number of keys to be added to the tables
	private double Laverage;//total number of probes using linear hashing
	private double Daverage;
	private int Ldups=0;//this increments when a duplicate is found in the linear hashing method
	private int Ddups=0;
	public String data;
	public int size;
	private int Ttype;
	private int notduplicate=0;
	
	
	public HashTable(int input, double load, int debug, int type) throws FileNotFoundException {
		DS=input;
		LF=load;
		DL=debug;
		Ttype = type;
		size=LTable.length;
		Arrays.fill(LTable, null);
		Arrays.fill(DTable, null);
		totalkeys=(int) (load*size);
		doDS(DS);
	}

	public void debug(int debuglevel) throws IOException {
		LprobeAverage();
		DprobeAverage();
		if(debuglevel==0) {
				System.out.println("A good table size was found: "+size);
				System.out.println("Data source type: "+data+"\n" );
				System.out.println("Using Linear Hashing....");
				System.out.println("Input "+totalkeys+" elements, of which "+Ldups+" duplicates" );
				System.out.println("load factor = "+LF+", Avg. no. of probes "+ Laverage+"\n");
				System.out.println("Using Double Hashing....");
				System.out.println("Input "+totalkeys+" elements, of which "+Ddups+" duplicates" );
				System.out.println("load factor = "+LF+", Avg. no. of probes "+ Daverage+"\n");		
		}else {
			if(debuglevel==1) {
				File Ldump = new File("Linear Dump");
				File Ddump = new File("Double Dump");
				if(!Ldump.exists()) {
					Ldump.createNewFile();
				}
				if(!Ddump.exists()) {
					Ddump.createNewFile();
				}
				FileWriter LW = new FileWriter(Ldump);
				FileWriter DW = new FileWriter(Ddump);
				BufferedWriter b1 = new BufferedWriter(LW);
				BufferedWriter b2 = new BufferedWriter(DW);
					System.out.println("A good table size was found: "+size);
					System.out.println("Data source type: "+data+"\n" );
					System.out.println("Using Linear Hashing....");
					System.out.println("Input "+totalkeys+" elements, of which "+Ldups+" duplicates" );
					System.out.println("load factor = "+LF+", Avg. no. of probes "+ Laverage+"\n");
					for(int i=0; i<size;i++) {
						if(LTable[i]!=null) {
							b1.write("table["+i+"]:   "+LTable[i].getKey()+ " " + LTable[i].getD()/2+ " " + LTable[i].getP()+"\n");
						}
					}
					System.out.println("Using Double Hashing....");
					System.out.println("Input "+totalkeys+" elements, of which "+Ddups+" duplicates" );
					System.out.println("load factor = "+LF+", Avg. no. of probes "+ Daverage+"\n");
					for(int i=0; i<size;i++) {
						if(DTable[i]!=null) {
							b2.write("table["+i+"]:   "+DTable[i].getKey()+ " " + DTable[i].getD()/2+ " " + DTable[i].getP()+"\n");
						}
					}
				}else {
				System.out.println("You entered the wrong input for your debug level");
				System.exit(1);
			}
		}
		
	}

	private void doDS(int ds) throws FileNotFoundException {
		if(ds==1) {
			data="random numbers";
			R();
		}else {
			if(ds==2) {
				data="system time";
				S();
			}else {
				if(ds==3) {
					data="word-list";
					W();
				}else {
					System.out.println("You entered the wrong input for your input type");
					System.exit(1);
				}
			}
		}
		
	}
	
	private void LprobeAverage() {
		Laverage=Laverage/(LF*size);
		
	}
	private void DprobeAverage() {
		Daverage=Daverage/(LF*size);
		
	}
	
	private void R() {
		
		while(notduplicate<(LF*size)) {
			int LD=Ldups;
			Random ran = new Random();
			HashObject<Integer> add = new  HashObject<Integer>(ran.nextInt());
			insertL(add);
			insertD(add);
			if(LD==Ldups) {
				notduplicate++;
			}
		}
	}
	
	private void S() {
		while(notduplicate<(LF*size)){
			int LD=Ldups;
			HashObject<Long> add = new HashObject<Long>(System.currentTimeMillis());
				insertL(add);
				insertD(add);
				if(LD==Ldups) {
					notduplicate++;
				}
			
		}
	}
	
	private void W() throws FileNotFoundException {
		File f = new File("word-list");
		Scanner reader = new Scanner(f);
		while(notduplicate<(LF*size)) {
			int LD=Ldups;
			HashObject add = new HashObject(reader.nextLine());
			insertL(add);
			insertD(add);
			if(LD==Ldups) {
				notduplicate++;
			}
		}
		reader.close();
	}
	//linear probe h(k,i) = (h' (k) +i) mod m, for i=0 -> m-1
	private int linearHash(HashObject key,int i) {
		int hash = key.getKey().hashCode() % size;
		if(hash<0) {
			hash+=size;
		}
		return ((hash+i)%size);
	}
	//double probe has two parts
	// h1(k) = k mod m
	// h2(k) = 1+ (k mod m') where m'=m-1 or m-2
	//(h1 + i*h2)mod m
	private int doubleHash(HashObject key,int i) {
		int h1=  key.getKey().hashCode()%size;
		if(h1<0) {
			h1+=size;
		}
		return((h1+(i*h2(key)))%size);
		
	}
	private int h2(HashObject key) {
		int h2 = ((key.getKey().hashCode()%(size-2)));
		if(h2<0) {
			h2+=size-2;
		}
		return (1+h2);
	}
	
	private void insertL(HashObject A) {
		int localP=0;
		int putat = 0;
		for(int i=0;i<size;i++) {
			putat = linearHash(A,i);
			if(LTable[putat]==null) {
				localP++;
				findprobes(A,localP,0);
				LTable[putat]= A;
				return;
			}else {
				if(this.LTable[putat].equals(A)) {
					LTable[putat].dup();
					Ldups++;
					return;
				}else {
					localP++;
				}
			}
		}
		
	}
	
	private void insertD(HashObject A) {
		int localP=0;
		int putat = 0;
		for(int i=0;i<size;i++) {
			putat = doubleHash(A,i);
			if(DTable[putat]==null) {
				localP++;
				findprobes(A,localP,1);
				DTable[putat]= A;
				return;
			}else {
				if(this.DTable[putat].equals(A)) {
					DTable[putat].dup();
					Ddups++;
					return;
				}else {
					localP++;
				}
			}
		}
	}

	private void findprobes(HashObject A,int localP,int type) {
		for(int j=0;j<localP;j++) {
			A.taken();
		}
		if(type==0) {
			Laverage+=localP;
		}else {
			Daverage+=localP;
		}
		
	}

}
