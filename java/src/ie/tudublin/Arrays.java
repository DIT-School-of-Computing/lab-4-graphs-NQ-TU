package ie.tudublin;

import javazoom.jl.converter.RiffFile;
import processing.core.PApplet;

public class Arrays extends PApplet {
	String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	float[] rainfall = { 200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420 };

	int mode = 0;

	public float map1(float a, float b, float c, float d, float e) {
		float r1 = c - b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize() {
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings() {
		size(1600, 800);

		String[] m1 = months;
		print(m1[0]);
		for (int i = 0; i < months.length; i++) {
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		int minIndex = 0;
		for (int i = 0; i < rainfall.length; i++) {
			if (rainfall[i] < rainfall[minIndex]) {
				minIndex = i;
			}
		}

		int maxIndex = 0;
		for (int i = 0; i < rainfall.length; i++) {
			if (rainfall[i] > rainfall[maxIndex]) {
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");

		float tot = 0;
		for (float f : rainfall) {
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10

	}

	public void setup() {
		colorMode(HSB, 360);
		background(0);
		randomize();

	}

	public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
	}

	public void graphTemplate() {
		// Draws the graphs template, for x and y.
		background(0);
		stroke(0, 0, 360);
		// Vertical line.
		line(width * 0.1f, height * 0.9f, width * 0.1f, height * 0.1f);
		// Horizontal line.
		line(width * 0.1f, height * 0.9f, width * 0.9f, height * 0.9f);

		// Draws the vertical axis, 0 - 500 in increments of 50.
		for (int i = 0; i <= 500; i += 50) {
			float y = map1(i, 0, 500, height * 0.9f, height * 0.1f);
			line(width * 0.1f, y, (width * 0.1f) - 10, y); // Draw ticks.
			textSize(20);
			textAlign(RIGHT, CENTER);
			text(i, width * 0.08f, y); // Places text slightly away from initial line.
		}

		for (int i = 0; i < months.length; i++) {
			// check the length of the text, and then add/subtract it to correctly position
			// in the middle of each bar.
			float x = map1(i, 0, months.length - 1, width * 0.1f + textWidth(months[i]) / 2,
					width * 0.9f - textWidth(months[i]) / 2);
			float y = height * 0.92f;
			textSize(18);
			textAlign(CENTER, CENTER);
			text(months[i], x, y);
		}
	}

	public void draw() {

		switch (mode) {
			case 0: {
				background(0);
				float w = width / (float) months.length;
				for (int i = 0; i < months.length; i++) {
					float x = map1(i, 0, months.length, 0, width);
					rect(x, height, w, -rainfall[i]);
				}
				break;
			}
			case 1: {
				int bars = months.length;
				graphTemplate();
				for (int i = 0; i < bars; i++) {

				}

				break;
			}
			case 2: {

				break;
			}
			default:
				break;
		}
	}
}
