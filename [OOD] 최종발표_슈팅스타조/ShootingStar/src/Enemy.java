public class Enemy {
    int x;
    int y;
    int speed;
    
    public Enemy(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void move() {
        x -= speed;
    }
}