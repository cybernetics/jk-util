/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jk.sound;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.jk.util.JKIOUtil;

import javazoom.jl.player.Player;

/**
 * The Class MP3Player.
 *
 * @author Jalal Kiswani
 */
public class MP3Player {
	
	/**
	 * Play.
	 *
	 * @param inputStream
	 *            the input stream
	 * @throws SoundException
	 *             the sound exception
	 */
	public static void play(final InputStream inputStream) throws SoundException {
		final MP3Player mp3 = new MP3Player(inputStream);
		mp3.play();
	}

	/**
	 * Play.
	 *
	 * @param fileName
	 *            the file name
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws SoundException
	 *             the sound exception
	 */
	public static void play(final String fileName) throws FileNotFoundException, SoundException {
		final InputStream inputStream = JKIOUtil.getInputStream(fileName);
		play(inputStream);
	}

	private String filename;

	private Player player;

	private final InputStream in;

	/**
	 * Instantiates a new MP 3 player.
	 *
	 * @param fileInputStream
	 *            the file input stream
	 */
	public MP3Player(final InputStream fileInputStream) {
		this.in = fileInputStream;
	}

	/**
	 * Instantiates a new MP 3 player.
	 *
	 * @param filename
	 *            the filename
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	// constructor that takes the name of an MP3 file
	public MP3Player(final String filename) throws FileNotFoundException {
		this(JKIOUtil.getInputStream(filename));
		this.filename = filename;

	}

	/**
	 * Close.
	 */
	public void close() {
		if (this.player != null) {
			this.player.close();
		}
	}

	/**
	 * Play.
	 *
	 * @throws SoundException
	 *             the sound exception
	 */
	// play the MP3 file to the sound card
	public void play() throws SoundException {
		try {
			final BufferedInputStream bis = new BufferedInputStream(this.in);
			this.player = new Player(bis);
		} catch (final Exception e) {
			throw new SoundException(e);
		}

		// run in new thread to play in background
		new Thread() {
			@Override
			public void run() {
				try {
					MP3Player.this.player.play();
				} catch (final Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

}
