/**
 * @authors Quentin LE GOFF, Yassine CHERIF
 */
package exo2;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class PingPong{
	
	public Exchanger<String> exchanger = new Exchanger<String>();
	private String mName1 = null;
	private String mName2 = null;
	private Random mRand = null;
	
	public PingPong(String name1, String name2){
		this.mName1 = name1;
		this.mName2 = name2;
		this.mRand = new Random();
	}

	class Personne1 implements Runnable{

		@Override
		public void run() {

			String currentStr = "Ping";
			int nbrIteration = 0;
			try {
				while(nbrIteration < 3){
					System.out.println("Iteration: "+nbrIteration+" " + mName1 + " has " + currentStr);
					System.out.println("Iteration: "+nbrIteration+" " + mName1 + " going to sleep.");

					Thread.sleep(mRand.nextInt(5000));
					
					System.out.println("Iteration: "+nbrIteration+" " + mName1 + " ready to exchange");
					currentStr = exchanger.exchange(currentStr);

					System.out.println("Iteration: "+nbrIteration+" " + mName1 + " exchange completed.");
					++nbrIteration;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Personne2 implements Runnable{

		@Override
		public void run() {

			String currentStr = "Pong";
			int nbrIteration = 0;
			try {
				while(nbrIteration < 3){
					System.out.println("Iteration: "+nbrIteration+" " + mName2 + " has " + currentStr);
					System.out.println("Iteration: "+nbrIteration+" " + mName2 + " going to sleep.");

					Thread.sleep(mRand.nextInt(5000));
					
					System.out.println("Iteration: "+nbrIteration+" " + mName2 + " ready to exchange");
					currentStr = exchanger.exchange(currentStr);

					System.out.println("Iteration: "+nbrIteration+" " + mName2 + " exchange completed.");
					++nbrIteration;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start(){
		new Thread(new Personne1(),mName1).start();
		new Thread(new Personne2(),mName2).start();
	}
}