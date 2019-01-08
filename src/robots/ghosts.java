package robots;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import Coords_converter.coordsToPixel;
import Geom.Point3D;
import Pixel.PointPixel;

public class ghosts {
	public BufferedImage ghostIcon;
	BufferedImage a;
	private PointPixel pixelP;
	private geom.Point3D gpsP;
	private ArrayList<Point3D> d3path;
	private ArrayList<PointPixel> Pixelpath;

	//class builder
	//////////////////////////////////////////////////////////////////////////////	
	public ghosts() {
		try {
			this.ghostIcon = ImageIO.read(new File("C:\\Users\\salim\\Desktop\\ex2-ex4\\ghostIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		this.pixelP = new PointPixel();
		this.gpsP = new geom.Point3D(0,0,0);
		this.d3path = new ArrayList<Point3D>();
		this.Pixelpath = new ArrayList<PointPixel>();
	}
	public ghosts(int x, int y) {
		this.pixelP.setPointpixel(x, y);
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(this.pixelP);
	}
	//////////////////////////////////////////////////////////////////////////

	//Setters	
	//////////////////////////////////////////////////////////////////////////////
	public void setghostPositionFromPixel(PointPixel p ) {
		this.pixelP = p;
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(p);
	}
	public void setghostPosition(double x , double y ) {
		this.pixelP.setPointpixel(x, y);
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(this.pixelP);
	}

	public void setghostPositionFromGps(geom.Point3D p ) {
		this.gpsP = p;
		coordsToPixel con = new coordsToPixel();
		this.pixelP = con.converterFromCoordsToPixel(p);
	}

	/////////////////////////////////////////////////////////////////////////////	

	//Getters
	//////////////////////////////////////////////////////////////////////////////	
	public geom.Point3D get3Dpoint() {
		return this.gpsP;
	}
	public PointPixel getPixelPoint() {
		return this.pixelP;
	}
	public double GetX() {
		return this.pixelP.GetX();
	}

	public double GetY() {
		return this.pixelP.GetY();
	}

	//Arraylist
	//////////////////////////////////////////////////////////////////////////////
	public void add3Dpath(Point3D p) {
		this.d3path.add(p);
	}

	public Iterator<Point3D> pathIterator() {
		return this.d3path.iterator();
	}

	public Point3D getStep(int i ) {
		return this.d3path.get(i);
	}
	public void addPixelPath(PointPixel p ) {
		this.Pixelpath.add(p);
	}
	public Iterator<PointPixel> pixelpathIterator(){
		return this.Pixelpath.iterator();
	}
	public PointPixel getPixelStep(int i) {
		return this.Pixelpath.get(i);
	}
	public int getPathSize () {
		System.out.println(this.Pixelpath.size());
		System.out.println(this.d3path.size());
		return this.Pixelpath.size();
	}
}
