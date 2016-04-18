package thermal_oxidation;

import java.awt.Point;

public class oxigen_mix {
	Point posicion1, posicion2;
	
	public oxigen_mix(Point posicion1,Point posicion2){
		this.posicion1 = posicion1;
		this.posicion2 = posicion2;
	}
	public Point getPosicion1() {
		return posicion1;
	}
	public void setPosicion1(Point posicion) {
		this.posicion1 = posicion;
	}
	public Point getPosicion2() {
		return posicion2;
	}
	public void setPosicion2(Point posicion2) {
		this.posicion2 = posicion2;
	}
	
}
