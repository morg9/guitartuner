package com.morg9.guitartuner.util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;

public class Constants {
	public static final float SAMPLE_RATE = 44100;
	public static final int SAMPLE_SIZE = 16; // bits per sample
	public static final int CHANNELS = 1; // mono
	public static final boolean BIG_ENDIAN = false;
	
	public static final Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
	
	public static final int FFT_SIZE = 1024; // samples
	public static final int BUFFER_SIZE = FFT_SIZE * 2; // bytes
}
