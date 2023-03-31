
public class User {
	int x, y;
	int heart;
	int gold;
	int firespeed;
	int score;

	User() {
		x = 100;
		y = 100;
		heart = 10;
		gold = 100;
		firespeed = 20;
		score = 0;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int Heart() {
		return this.heart;
	}

	public void setHeart(int aHeart) {
		this.heart = aHeart;
	}

	public int Gold() {
		return this.gold;
	}

	public int Firespeed() {
		return this.firespeed;
	}

	public void setFirespeed(int aFirespeed) {
		this.firespeed = aFirespeed;
	}

	public void setGold(int aGold) {
		this.gold = aGold;
	}

	public int Score() {
		return this.score;
	}

	public void setScore(int aScore) {
		this.score = aScore;
	}
}