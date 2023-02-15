//package animation_d;

import java.util.Random;

public class random {
	private int score;
	
	public random() {
		this.score = 0;
	}
	
	public void change(int a) {
		this.score  = a;
	}
	
	public void hasardes() {
		int score;
		Random rand = new Random();
		score = rand.nextInt(6) + 1;
		this.change(score);
	}
	
	public int aff() {
		return this.score;
	}
	
	
			
	}

