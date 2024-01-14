package main;

public class Soundtrack extends Thread {

	
	
	
	public void run() {
		ApplicationData.soundtrack.playTrack(ApplicationData.track);
	}
}
