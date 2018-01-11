package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ie.gmit.sw.Interface;
import ie.gmit.sw.FileParser;

public class Runner {

	public static void main(String[] args) {
		Interface ui = new Interface();
		
		do{
			ui.show();
			
			if(ui.getOption() == 1){
				
				BlockingQueue<Shingles> q = new LinkedBlockingQueue<>();
		
				Thread t1 = new Thread(new FileParser(q, ui.getFileNameA(), ui.getShingleSize()),"A");
				Thread t2 = new Thread(new FileParser(q, ui.getFileNameB(), ui.getShingleSize()),"B");
		
				t1.start();
				t2.start();
		
			}
		}while(!(ui.getOption()==2));
		
	}

}
