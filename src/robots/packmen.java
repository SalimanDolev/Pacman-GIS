package robots;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import geom.*;

import javax.imageio.ImageIO;

import Coords_converter.coordsToPixel;
import Geom.Point3D;
import Pixel.PointPixel;

public class packmen {
	public BufferedImage PackmenIcon;
	private PointPixel pixelP;
	private geom.Point3D gpsP;
	private double speed;
	private double radius;

	private ArrayList<Point3D> d3path;
	private ArrayList<PointPixel> Pixelpath;
	private ArrayList<Point3D> fullPath;
		
		public packmen() {
			try {
				this.PackmenIcon = ImageIO.read(new File("C:\\Pictures_For_Ex3\\packman-png-8.png"));
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
		}

		public packmen(int x, int y) {
			this.pixelP.setPointpixel(x, y);
			coordsToPixel con = new coordsToPixel();
			this.gpsP = con.convertFromPixelToGPS(this.pixelP.GetX(),this.pixelP.GetY());
			this.speed = 1;
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

	//////////////////////////////////////////////////////////////////////////////////
		// the GPS path
		public void add3Dpath(Point3D p) {
			this.d3path.add(p);
		}

		public Iterator<Point3D> pathIterator() {
			return this.d3path.iterator();
		}

		public Point3D getStep(int i ) {
			return this.d3path.get(i);
		}
	/////////////////////////////////////////////////////////////
		//The pixel path
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
			return this.Pixelpath.size();
		}
		public int getPixelPathSize() {
			return this.Pixelpath.size();
		}
		
	/////////////////////////////////////////////////////////////
		//full gps path
		public void add3Dfullpath(Point3D p) {
			this.fullPath.add(p);
		}

		public Iterator<Point3D> fullpathIterator() {
			return this.fullPath.iterator();
		}

		public Point3D getfullStep(int i ) {
			return this.fullPath.get(i);
		}
		public int getfullPathSize () {
			return this.fullPath.size();
		}

	}