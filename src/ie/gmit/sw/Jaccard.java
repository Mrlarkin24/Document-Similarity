package ie.gmit.sw;

import java.util.ArrayList;


public class Jaccard {
	private static ArrayList<Integer> HashCodeA = new ArrayList<Integer>();
	private static ArrayList<Integer> HashCodeB = new ArrayList<Integer>();
	//private int[] HashCodeA;
	//private int[] HashCodeB;
	private static int A, B, inter, done;
	private static double jaccardIndex; 
	//private static int ;
	
	public Jaccard(){
		super();
		HashCodeA.clear();
		HashCodeB.clear();
		done = 0;
	}
	
	public static void calculations(){
		inter = intersection();
		
		System.out.println("inter: " + inter + " hash A: " + HashCodeA.size() + " hash B: " + HashCodeB.size());
		System.out.println("\nTest1: " + (HashCodeA.size() + HashCodeB.size()));
		System.out.println("\nTest2: " + ((HashCodeA.size() + HashCodeB.size()) - inter));
		System.out.println("\nTest3: " + (inter/((HashCodeA.size() + HashCodeB.size()) - inter)));
		jaccardIndex = (inter*100)/(HashCodeA.size() + HashCodeB.size() - inter);
		
		System.out.println("\n-------------------------------------------------------------\n"
				+ "Similarity: " + jaccardIndex + "%\n-------------------------------------------------------------\n");
	}

	public static void setHashCodeA(int shingleHashCode) {
		System.out.println("\n\ncheck: " + shingleHashCode);
		HashCodeA.add(shingleHashCode);
		
		System.out.println("check 2: " + HashCodeA.size());
		System.out.println("check 3: " + HashCodeA.toString());
		//this.A++;
	}

	public static void setHashCodeB(int shingleHashCode) {
		System.out.println("\n\ncheck: " + shingleHashCode);
		System.out.println("check 2: " + HashCodeB.size());
		HashCodeB.add(shingleHashCode);
		System.out.println("check 3: " + HashCodeB);
		//B++;
	}
	
	public static int intersection() {
		int i, j, intersect = 0, test=0,hA,hB;
		A = HashCodeA.size();
		B = HashCodeB.size();
				
		System.out.println("\n\nA size: "+ A + "\nB size: "+ B);
		//test++;
		for(i = 0; i <= (A - 1); i++){
			//test++;
			for(j = 0; j <= (B - 1); j++){
				//test++;
				//System.out.println(test);
				//System.out.println(HashCodeB.get(j) + " VS " + HashCodeA.get(i));
				hA=HashCodeA.get(i);
				hB=HashCodeB.get(j);
				
				if(hB == hA){
					intersect++;
				}
				
			}
			
		}
		//System.out.println(test);		
		return intersect;
	}

	public static int getDone() {
		return done;
	}

	public static void setDone() {
		Jaccard.done++;
		System.out.println("\n----------Done----------\n");
	}
	public static void setDone(int done) {
		Jaccard.done=done;
		HashCodeA.clear();
		HashCodeB.clear();
		
	}
	
}
