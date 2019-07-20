package com.mikejack.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioClip {

    private Clip clip;
    private FloatControl gainControl;
    private FloatControl panControl;
    private FloatControl balanceControl;
    
    public AudioClip(String path) {
	try {
	    InputStream audioSrc = AudioClip.class.getResourceAsStream(path);
	    InputStream bufferedIn = new BufferedInputStream(audioSrc);
	    AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
	    AudioFormat baseFormat = ais.getFormat();
	    AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
		    					baseFormat.getSampleRate(), 
		    					16,
		    					baseFormat.getChannels(), 
		    					baseFormat.getChannels() * 2, 
		    					baseFormat.getSampleRate(), 
		    					false);
	    AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
	    
	    clip = AudioSystem.getClip();
	    clip.open(dais);
	    gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	    panControl = (FloatControl) clip.getControl(FloatControl.Type.PAN);
	    balanceControl = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);

	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public void play() {
	if (clip == null) {
	    return;
	}
	stop();
	clip.setFramePosition(0);
	while (!clip.isRunning()) {
	    clip.start();
	}
    }
    
    public void stop() {
	if (clip.isRunning()) {
	    clip.stop();
	}
    }

    public void close() {
	stop();
	clip.drain();
	clip.close();
    }
    
    public void loop() {
	clip.loop(Clip.LOOP_CONTINUOUSLY);
	play();
    }
    
    public void setVolume(float volume) {
	gainControl.setValue(volume);
    }
    
    public void setPan(float pan) {
	panControl.setValue(pan);
	balanceControl.setValue(pan);
    }
    
    public boolean isRunning() {
	return clip.isRunning();
    }
}
