package com.morg9.guitartuner.signal;

import com.morg9.guitartuner.audio.AudioDataListener;

public class AudioFrameConverter implements AudioDataListener {

	private AudioSampleListener listener;
	
	public void setListener(AudioSampleListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void onAudioFrame(byte[] buffer, int bytesRead) {
		double[] samples = convert(buffer, bytesRead);
		
		if (listener != null) {
			listener.onSamples(samples);
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
