package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.Shingles;
import ie.gmit.sw.Jaccard;

/**
 * The Type FileParser. A FileParser is a{@link Runnable} implementation
 */
public class FileParser implements Runnable {
	private BlockingQueue<Shingles> queue;
	private String fileName;
	private int shingleSize;
	private Deque<String> buffer = new LinkedList<>();
	private int docId;

	/**
	 * Instantiates a new FileParser
	 * 
	 * @param queue
	 *            a BlockingQueue of Shingle Objects
	 * @param fileName
	 *            the name of the file to be parsed
	 * @param shingleSize
	 *            the specified size of the shingles
	 */
	public FileParser(BlockingQueue<Shingles> queue, String fileName, int shingleSize, int docId) {
		super();
		this.queue = queue;
		this.fileName = fileName;
		this.shingleSize = shingleSize;
		this.docId = docId;
	}

	@Override
	public void run() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("File " + fileName + " not found");

		}
		String line = "";

		try {
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {// skip blank lines
					String uLine = line.toUpperCase();

					// Separate words by blank space (adjust to remover ,.!)
					String[] words = uLine.split("\\s+");

					// Add array of words to buffer
					addWordsToBuffer(words);

				}
			} // while

			/*
			 * Iterate through buffer until buffer is emptied (buffer.size>0)
			 * With every iteration a Shingle s is created and added to queue
			 */
			while (buffer.size() != 0) {

				Shingles s = getNextShingle();
				queue.put(s); // Blocking method. Add is not a blocking

				// System.out.print("\tShingle hash:" + s.getShingleHashCode()
				// + "\tbuffer size:" + buffer.size() + " i="+ i + " DocumentId: " + this.docId +"\n"); // used for debugging

				if (this.docId == 0) {
					Jaccard.setHashCodeA(s.getShingleHashCode());
				} else {
					Jaccard.setHashCodeB(s.getShingleHashCode());
				}

			}
			// }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Jaccard.setDone();

	}// run

	/**
	 * Adds every word in Array to buffer
	 * 
	 * @param words
	 *            contains a string array of words
	 */
	private void addWordsToBuffer(String[] words) {
		for (String s : words) {
			buffer.add(s);
		}

	}

	/**
	 * Retrieves and removes the head of the buffer queue and appends the string
	 * to StringBuffer sb This is done until the value shingleSize is met The
	 * values are then removed from the StringBuffer
	 * 
	 * @return a new Shingle object
	 */
	private Shingles getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;

		while (counter < shingleSize) {
			
			if (buffer.peek() != null) {
				// System.out.print(buffer.peek() + " "); // Used for debugging
				sb.append(buffer.poll());
				counter++;
			} else if (sb != null) {
				counter++;
			}
			
		}

		if (sb.length() > 0) {
			if (sb.length() == shingleSize) {
				sb.delete(0, sb.length() - 1);
			}
			return (new Shingles(docId, sb.toString().hashCode()));
		} else {
			return (null);
		}
	}

}