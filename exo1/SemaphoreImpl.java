/**
 * @authors Quentin LE GOFF, Yassine CHERIF
 */
package exo1;

public class SemaphoreImpl implements SemaphoreInterface{
	private int mCounter ;

	public SemaphoreImpl(){
		// int instance variables are always initiated with 0
		//mCounter = 0; 
	}

	synchronized public void up(){
		++mCounter;
		if(mCounter <= 0)
			notify();
	}

	synchronized public void down(){
		--mCounter;
		if (mCounter < 0){ // if instead of while cause thread already took its place
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// an extension that unblocks all threads waiting on the semaphore
	// returns the number of threads that have just been unblocked
	synchronized public int  releaseAll(){
		int ret = 0;
		if(mCounter<0){ // there are waiting threads
			ret = mCounter*(-1); // get actual waiting threads
			notifyAll();
			mCounter = 0; 
		}
		return ret;
	}
}