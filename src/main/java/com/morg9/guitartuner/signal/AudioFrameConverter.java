package com.morg9.guitartuner.signal;

import com.morg9.guitartuner.audio.AudioFrameListener;

public class AudioFrameConverter implements AudioFrameListener {

	@Override
	public void onAudioFrame(byte[] buffer, int bytesRead) {
		double[] samples = convert(buffer, bytesRead);
		
		// TODO: forward samples to next stage, e.g., frequency detector
		// Just printing first 5 samples (w/ 5 decimal places) for verification for now
		for (int i = 0; i < Math.min(5, samples.length); i++) {
			System.out.printf("Sample[%d]: %.5f%n", i, samples[i]);
		}
	}

	private static double[] convert(byte[] buffer, int bytesRead) { // Converts raw PCM bytes to normalised doubles
		int sampleCount = bytesRead / 2; // 16-bit PCM = 2 bytes per sample -> divide bytes by 2 to get number of samples
		double[] samples = new double[sampleCount]; // allocate array that will hold normalised values
		
		for (int i = 0; i < sampleCount; i++) { // loop through each sample in the byte array
			int low = buffer[2*i] & 0xff; // low byte of the sample, treated as unsigned
			int high = buffer[2*i+1]; // high byte of the sample, left as a signed byte
			int sample = (high << 8) | low; // combines high and low bytes into single 16-bit integer
			samples[i] = sample / 32768.0; // normalise 16-bit signed PCM to [-1.0, 1.0]
		}
		return samples;
	}
	
}
