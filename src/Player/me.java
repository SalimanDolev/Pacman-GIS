package Player;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import Coords_converter.coordsToPixel;
import Coords_converter.myCoords;
import Geom.Point3D;
import Pixel.PointPixel;
import robots.Box;

public class me {
	public BufferedImage meIcon;
	private PointPixel pixelP;
	private geom.Point3D gpsP;
	private double speed;
	private double radius;
	private int score;

	private ArrayList<Point3D> d3path;
	private ArrayList<PointPixel> Pixelpath;
	private ArrayList<Point3D> fullPath;

	public me() {
		try {
			this.meIcon = ImageIO.read(new File("C:\\Pictures_For_Ex3\\player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		this.pixelP = new PointPixel();
		this.gpsP = new geom.Point3D(0, 0, 0);
		this.speed = 1;
		this.d3path = new ArrayList<Point3D>();
		this.Pixelpath = new ArrayList<PointPixel>();
		this.fullPath = new ArrayList<Point3D>();
		this.score = 0;
	}

	public me(int x, int y) {
		this.pixelP.setPointpixel(x, y);
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(this.pixelP.GetX(),this.pixelP.GetY());
		this.speed = 1;
		this.score = 0;
		this.d3path = new ArrayList<Point3D>();
		this.Pixelpath = new ArrayList<PointPixel>();
		this.fullPath = new ArrayList<Point3D>();
	}

	public void setPackmenPosition(geom.Point3D p ) {
		this.gpsP = p;
		coordsToPixel con = new coordsToPixel();
		this.pixelP = con.converterFromCoordsToPixel(p);
	}

	public void setPackmenPosition(PointPixel p ) {
		this.pixelP = p;
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(p.GetX(),p.GetY());
	}

	public void setPackmenPosition(double x , double y ) {
		this.pixelP.setPointpixel(x, y);
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(this.pixelP.GetX(),this.pixelP.GetY());
	}

	public geom.Point3D get3Dpoint() {
		return this.gpsP;
	}
	public PointPixel getPixelPoint() {
		return this.pixelP;
	}

	////////////////////////////////////////////////////////////////////	
	public void setSpeed(double s) {
		this.speed = s;
	}
	public double getSpeed() {
		return this.speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	/////////////////////////////////////////////////////////////////////////////////
	//the player movment
	public void movePlayer(double azimuth) {

		myCoords l = new myCoords();
		this.gpsP.setPoint(l.AddAzimuthAndVector(gpsP, azimuth, 10.0));
		coordsToPixel con = new coordsToPixel();
		this.pixelP = con.converterFromCoordsToPixel(this.gpsP);
	}
	//set the score of the player
	public void Addscore(int s){
		this.score = this.score +s;
	}
	public int getScore() {
		return this.score;
	}
	public boolean IfInsideBox(ArrayList<Box> b) {
		PointPixel lu;
		PointPixel ld;
		PointPixel ur;
		PointPixel ud;
		for (int i = 0; i < b.size(); i++) {
			lu = new PointPixel(b.get(i).getPointPixel().GetX(), b.get(i).getPointPixel().GetY()+b.get(i).getHight());
			ld = new PointPixel(b.get(i).getPointPixel());
			ur = new PointPixel(b.get(i).getPointPixel().GetX()+b.get(i).getWidth(),b.get(i).getPointPixel().GetY()+b.get(i).getHight());
			ud = new PointPixel(b.get(i).getPointPixel().GetX()+b.get(i).getWidth(), b.get(i).getPointPixel().GetY());
			if(this.pixelP.GetX() > b.get(i).getPointPixel().GetX() && this.pixelP.GetY() > b.get(i).getPointPixel().GetY() &&
					this.pixelP.GetX() < ud.GetX() && this.getPixelPoint().GetY() < lu.GetY()) {
				this.score = this.score -1;
				return true;
				
			}
		}
		return false;
	}
}	

