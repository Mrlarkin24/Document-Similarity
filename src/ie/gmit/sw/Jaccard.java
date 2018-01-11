package ie.gmit.sw;

import java.util.ArrayList;

public class Jaccard {
	private static ArrayList<Integer> HashCodeA = new ArrayList<Integer>();
	private static ArrayList<Integer> HashCodeB = new ArrayList<Integer>();
	private static int A, B, inter, done, jaccardIndex;

	public static void calculations() {
		inter = intersection();

		// System.out.println("\nTest3: " + (inter/((HashCodeA.size() + HashCodeB.size()) - inter))); // used for debugging
		jaccardIndex = (inter * 100) / (HashCodeA.size() + HashCodeB.size() - inter);

		System.out.println("\n-------------------------------------------------------------\n" + "Similarity: "
				+ jaccardIndex + "%\n-------------------------------------------------------------\n");
	}

	public static void setHashCodeA(int shingleHashCode) {
		HashCodeA.add(shingleHashCode);
	}

	public static void setHashCodeB(int shingleHashCode) {
		HashCodeB.add(shingleHashCode);
	}

	public static int intersection() {
		int i, j, intersect = 0, hA, hB;
		A = HashCodeA.size();
		B = HashCodeB.size();

		// System.out.println("\n\nA size: "+ A + "\nB size: "+ B); // used for debugging

		for (i = 0; i <= (A - 1); i++) {

			for (j = 0; j <= (B - 1); j++) {
				hA = HashCodeA.get(i);
				hB = HashCodeB.get(j);

				if (hB == hA) {
					intersect++;
				}

			}

		}

		return intersect;
	}

	public static int getDone() {
		return done;
	}

	public static void setDone() {
		Jaccard.done++;
		// System.out.println("\n----------Done----------\n"); // used for debugging
	}

	public static void setDone(int done) {
		Jaccard.done = done;
		HashCodeA.clear();
		HashCodeB.clear();
	}

}
