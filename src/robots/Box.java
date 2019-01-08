package robots;

import Coords.coords_converter;
import Coords_converter.coordsToPixel;
import Coords_converter.myCoords;
import Pixel.PointPixel;
import geom.Point3D;

public class Box {

	private geom.Point3D leftDownCorner;
	private geom.Point3D rightUpCorner;
	private PointPixel leftDowncornerPi;
	
	//////////////////////////////////////////////////////////////////////////////////////
	//define veriables
	private int width;
	private int height;
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//constructors
	public Box() {
		this.leftDownCorner = new Point3D(0, 0, 0);
		this.rightUpCorner = new Point3D(0, 0, 0);
		this.leftDowncornerPi = new PointPixel();
	}
	public Box(geom.Point3D leftdowncorner,geom.Point3D rightupcorner) {
		this.leftDownCorner = new Point3D(leftdowncorner);
		this.rightUpCorner = new Point3D(rightupcorner);
		coordsToPixel l = new coordsToPixel();
		this.leftDowncornerPi = new PointPixel(l.converterFromCoordsToPixel(leftdowncorner));
		PointPixel rightup = new PointPixel(l.converterFromCoordsToPixel(rightupcorner));
		this.width = (int) Math.abs(this.leftDowncornerPi.GetX() - rightup.GetX());
		this.height = (int) Math.abs(this.leftDowncornerPi.GetY() - rightup.GetY());
	}
	public Box(double a , double b , double c , double d) {
		this.leftDownCorner = new Point3D(a, b, 0);
		this.rightUpCorner = new Point3D(c, d, 0);
		coordsToPixel l = new coordsToPixel();
		this.leftDowncornerPi = new PointPixel(l.converterFromCoordsToPixel(this.leftDownCorner));
		PointPixel rightup = new PointPixel(l.converterFromCoordsToPixel(this.rightUpCorner));
		this.width = (int) Math.abs(this.leftDowncornerPi.GetX() - rightup.GetX());
		this.height = (int) Math.abs(this.leftDowncornerPi.GetY() - rightup.GetY());
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//// getters
	public PointPixel getPointPixel() {
		return this.leftDowncornerPi;
	}
	public int getPixelX() {
		return (int) this.leftDowncornerPi.GetX();
	}
	public int getPixelY() {
		return (int) this.leftDowncornerPi.GetY();
	}
	public int getWidth(){
		return this.width;
	}
	public int getHight(){
		return this.height;
	}
	
}
