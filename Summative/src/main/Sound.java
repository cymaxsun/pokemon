package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sound {

	FloatControl gainControl;
	float range;
	Clip clip;
	URL[] clips = new URL[10];
	URL[] tracks = new URL[ApplicationData.numOfTracks];
	int trackNum ;
	float volume;
	final float muteVol = -80f;
	float unmuteVol;
	boolean muted = false;
	private long clipPos;
	public boolean clipEnded = true;

	public Sound() {
		clips[0] = getClass().getResource("/sound/battle.wav");
		clips[1] = getClass().getResource("/sound/button.wav");
		clips[2] = getClass().getResource("/sound/hit.wav");
		clips[3] = getClass().getResource("/sound/Stat Fall Down.wav");
		clips[4] = getClass().getResource("/sound/victory.wav");
		clips[5] = getClass().getResource("/sound/fainted.wav");
		clips[6] = getClass().getResource("/sound/lowHP.wav");
		clips[7] = getClass().getResource("/sound/heal.wav");
		clips[8] = getClass().getResource("/sound/Stat Rise Up.wav");
		
		tracks[0] = getClass().getResource("/sound/Opening.wav");
		tracks[1] = getClass().getResource("/sound/Pokemon Center.wav");
		tracks[2] = getClass().getResource("/sound/The Road To Veridian ~ From Pallet.wav");
		tracks[3] = getClass().getResource("/sound/Pokemon Gym.wav");
		tracks[4] = getClass().getResource("/sound/Pokemon Theme.wav");
		tracks[5] = getClass().getResource("/sound/Driftveil City.wav");
		tracks[6] = getClass().getResource("/sound/Littleroot Town.wav");

	}
	
	public static String getTrackName(int track) {
        switch (track) {
            case 0:
                return "Opening";
            case 1:
                return "Pokemon Center";
            case 2:
                return "The Road To Veridian ~ From Pallet";
            case 3:
                return "Pokemon Gym";
            case 4:
                return "Pokemon Theme (Anime)";
            case 5:
                return "Driftveil City";
            case 6:
                return "Littleroot Town";
            default:
                return "UNKNOWN";
        }
    }

	public void setFile(int i) {
		AudioInputStream sis;
		try {
			sis = AudioSystem.getAudioInputStream(clips[i]);
			clip = AudioSystem.getClip();
			clip.open(sis);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		} catch (Exception e) {

		}

	}

	public void setFile(URL path) {
		AudioInputStream sis;
		try {
			sis = AudioSystem.getAudioInputStream(path);
			this.clip = AudioSystem.getClip();
			this.clip.open(sis);

			gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {

		}

	}
	
	public void setTrack(int i) {
		AudioInputStream sis;
		
		try {
			sis = AudioSystem.getAudioInputStream(tracks[i]);
			this.clip = AudioSystem.getClip();
			this.clip.open(sis);

			gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
			clip.addLineListener(new LineListener() {


				@Override
				public void update(LineEvent e) {
					System.out.println(e.getType().toString() + " and " + clipEnded);
					if (e.getType() == LineEvent.Type.STOP) {
						if (!clipEnded) {
							ApplicationData.sfx.playFile(1);
							
						} else {
							System.out.println("track: " + getTrackName(ApplicationData.track) + " ended");
							if (ApplicationData.track < tracks.length-1) {
								ApplicationData.track++;
							} else if  (ApplicationData.track >= tracks.length-1	){
								ApplicationData.track = 0;
								
							} 
							System.out.println("track: " + getTrackName(ApplicationData.track) + " started");
							
							playTrack(ApplicationData.track);
							if (ApplicationData.settings.getParent() != null) {
								ApplicationData.settings.updateTrack();
							} 
						}

						
						
					} else {
						
						return;
					}
					
				}
				
			});
			clipEnded = true;
		} catch (Exception e) {

		}
	}

	public void play() {
		gainControl.setValue(volume);
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public void playFile(int i) {
		setFile(i);
		play();

	}

	public void playFile(URL path) {
		setFile(path);
		play();

	}
	
	public void playTrack(int i) {
		setTrack(i);
		play();
	}
	
	public void playSFX(int i) {
		setFile(i);
		play();
		ApplicationData.eventQueue.pop().run();
	}
	
	public void playSFX(URL path) {
		setFile(path);
		play();
		ApplicationData.eventQueue.pop().run();
	}
	
	
	public void setVolume(float f) {

		if (muted) {
			this.unmuteVol = f;
		} else {
			this.volume = f;
			if (clip != null) {
				gainControl.setValue(volume);
			}
		}
	}

	public long getLength() {
		return clip.getMicrosecondLength();
	}

	public void muteSFX() {
		if (!muted) {
			this.unmuteVol = volume;
			setVolume(muteVol);
			muted = true;
		} else {
			muted = false;
			setVolume(unmuteVol);
		}
	}
	
	public void muteTrack() {
		if (!muted) {
			clipPos = clip.getMicrosecondPosition();
			clip.stop();
			muted = true;
		} else {
			muted = false;
			clip.setMicrosecondPosition(clipPos);
			play();
			setVolume(unmuteVol);
			loop();

		}
	}
	

	
}
