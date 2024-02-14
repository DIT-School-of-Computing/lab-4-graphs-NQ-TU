package ie.tudublin;

import javazoom.jl.converter.RiffFile;
import processing.core.PApplet;

public class Arrays extends PApplet {
	String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	float[] rainfall = { 500, 450, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420 };

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
		size(900, 600);
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

	public void draw() {

		switch (mode) {
			case 0: { // Rainfall Bar Chart
				float color_val = 0; // Change colors :)
				graphTemplate(); // Draw our graph template
				text("Rainfall Bar Chart", width / 2, height * 0.05f);

				for (int i = 0; i < months.length; i++) {
					float x = map1(i, 0, months.length, width * 0.1f, width * 0.9f);
					float barHeight = map1(rainfall[i], 0, 500, 0, height * 0.8f);
					fill(color_val, 360, 360);
					strokeWeight(2);
					stroke(0, 0, 360);
					rect(x, height * 0.9f - barHeight, (width * 0.8f) / months.length, barHeight);
					color_val += (360 / months.length);
				}
				break;
			}
			case 1: { // Rainfall Trend Chart
				graphTemplate();
				// gridTemplate();
				text("Rainfall Trend Chart", width / 2, height * 0.05f);
				// Begin line at middle of each months 'section'.
				float middlePoint = (width * 0.8f) / (months.length * 2);
				for (int i = 1; i <= months.length - 1; i++) {
					float startX = map1(i, 0, months.length, width * 0.1f + middlePoint, width * 0.9f + middlePoint);
					float prevX = map1(i - 1, 0, months.length, width * 0.1f + middlePoint, width * 0.9f + middlePoint);
					float startY = map(rainfall[i], 0, 500, height * 0.9f, height * 0.1f);
					float prevY = map(rainfall[i - 1], 0, 500, height * 0.9f, height * 0.1f);
					strokeWeight(2);
					stroke(360);
					line(startX, startY, prevX, prevY);
				}
				break;
			}
			case 2: {
				float slices = months.length;
				text("Rainfall Pie Chart", width / 2, height * 0.05f);

				break;
			}
			default:
				break;
		}
	}

	public void graphTemplate() {
		// Draws the graphs template, for x and y.
		background(0);
		strokeWeight(3);
		stroke(0, 0, 360);
		fill(0, 0, 360);
		line(width * 0.1f, height * 0.9f, width * 0.1f, height * 0.1f);
		line(width * 0.1f, height * 0.9f, width * 0.9f, height * 0.9f);

		// Draws the Y axis, 0 to 500 in increments of 50.
		for (int i = 0; i <= 500; i += 50) {
			float y = map1(i, 0, 500, height * 0.9f, height * 0.1f);
			line(width * 0.1f, y, (width * 0.1f) - 10, y); // Draw ticks.
			textSize(20);
			textAlign(RIGHT, CENTER);
			text(i, width * 0.08f, y); // Places text slightly away from initial line.
		}
		// Draws the X axis, each month in our months array.
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

	public void gridTemplate() {
		// Draws a grid, used to check if bars + points displayed correctly.
		for (int i = 0; i <= 500; i += 50) {
			float y = map1(i, 0, 500, height * 0.9f, height * 0.1f);
			strokeWeight(1);
			stroke(50);
			line(width * 0.1f, y, (width * 0.9f), y); // Draw ticks.
		}
		for (int i = 0; i < months.length + 1; i += 1) {
			float x = map1(i, 0, months.length, width * 0.1f, width * 0.9f);
			strokeWeight(1);
			stroke(50);
			line(x, height * 0.1f, x, (height * 0.9f)); // Draw ticks.
		}
	}
}
