package com.morg9.guitartuner.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.morg9.guitartuner.util.Constants;

public class AudioCapture {
	
	private TargetDataLine line;
	private volatile boolean running = false;
	
	private AudioFrameListener listener;

	public void setListener(AudioFrameListener listener) {
		this.listener = listener;
	}
	
	public void start() {
		try {
			AudioFormat format = createAudioFormat();
			line = getTargetDataLine(format);
			line.start();
			
			running = true; 
			
			Thread captureThread = new Thread(this::captureLoop, "Audio-Capture-Thread");
			captureThread.start();
			System.out.println("Audio capture started...");
			
		}	catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		running = false;
		
		if (line != null) {
			line.stop();
			line.close();
		}
		System.out.println("Audio capture stopped.");
	}
	
	private void captureLoop() {
		byte[] buffer = new byte[Constants.BUFFER_SIZE];
		
		while (running) {
			int bytesRead = line.read(buffer, 0, buffer.length);
			
			if (bytesRead > 0) {
				if (listener != null) {
					byte[] copy = new byte[bytesRead];
					System.arraycopy(buffer, 0, copy, 0, bytesRead);
					listener.onAudioFrame(copy, bytesRead);
				}
				
				System.out.println("Read " + bytesRead + " bytes"); // Temporary confirmation of received audio
			}
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
