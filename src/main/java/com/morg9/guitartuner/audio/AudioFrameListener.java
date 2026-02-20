package com.morg9.guitartuner.audio;

public interface AudioFrameListener {

	void onAudioFrame(byte[] buffer, int bytesRead);
}
