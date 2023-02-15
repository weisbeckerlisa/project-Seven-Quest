

public class Statistic {
	public Statistic(String name, int startingLevel) {
		this.name = name;
		this.level = startingLevel;
	}

	public String getName() {
		return name;
	}

	public void increaseLevel(int amount) {
		level += amount;
	}

	private String name;
	private int level;

}