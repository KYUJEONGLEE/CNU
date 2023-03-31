public class Missile {
    int _x;
    int _y;
    int _speed;
    int _angle;
    int _who;
    int _power;
    
    public Missile(int x, int y, int angle,int speed,int who) {
        this._x = x;
        this._y = y;
        this._speed = speed;
        this._angle = angle;
        this._who = who;
    }
    
    public void move() {
       this._x += Math.cos(Math.toRadians(this._angle))*this._speed;
       this._y += Math.sin(Math.toRadians(this._angle))*this._speed;
    }
}