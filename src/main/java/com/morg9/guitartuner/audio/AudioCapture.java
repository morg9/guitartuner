package com.morg9.guitartuner.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.morg9.guitartuner.util.Constants;

public class AudioCapture {
	
	private TargetDataLine line;
	
	public void start() {
		try {
			AudioFormat format = createAudioFormat();
			line = getTargetDataLine(format);
			line.start();
			System.out.println("Audio capture started...");
		}	catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
	
	private AudioFormat createAudioFormat() {
		return new AudioFormat(Constants.ENCODING, Constants.SAMPLE_RATE, Constants.SAMPLE_SIZE, Constants.CHANNELS, (Constants.SAMPLE_SIZE/8)*Constants.CHANNELS, Constants.SAMPLE_RATE, Constants.BIG_ENDIAN);
	}
	
	private TargetDataLine getTargetDataLine(AudioFormat format) throws LineUnavailableException {
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		
		if (!AudioSystem.isLineSupported(info)) {
			throw new LineUnavailableException("Microphone not supported");
		}
		TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
		line.open(format);
		return line;
	}
}
