package Pixel;

public class PointPixel {
	private double x;
	private double y;
	
	private double rangeLeft;
	private double rangeRight;
	private double rangeUp;
	private double rangeDown;
	
	public PointPixel() {
		this.x = 0;
		this.y = 0;
	}
	
	public PointPixel(double x , double y) {
		this.x = x;
		this.y = y;
	}
	public PointPixel(PointPixel p) {
		this.x = p.GetX();
		this.y = p.GetY();
	}
	
	public void setPointpixel(double x , double y) {
		
		this.x = x;
		this.y = y;
	}
	
	public boolean setRange(double l,double r,double u,double d) {
		this.rangeDown = d;
		this.rangeLeft = l;
		this.rangeRight = r;
		this.rangeUp = u;
		return true;
	}
	
	public double GetX() {
		return this.x;
	}
	
	public double GetY() {
		return this.y;
	}
	
	//this fuction calculate the azimuth between two points
	public double azimuth(PointPixel p , PointPixel p1) {
		double m = (p.GetY() - p1.GetY())/ (p.GetX() - p1.GetX());
		double degree = Math.atan(m);
		return degree;
				
	}

}
