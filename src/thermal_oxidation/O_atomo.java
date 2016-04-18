package thermal_oxidation;
import java.awt.Point;

public class O_atomo {
	protected Point posicion;
	protected int anchoContenedor;
	protected int altoContenedor;
	public O_atomo(int ancho, int alto, Point point){
		this.anchoContenedor = ancho;
		this.altoContenedor = alto;
		this.posicion = point;
	}
	public Point getPosicion() {
		return posicion;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	public int getAnchoContenedor() {
		return anchoContenedor;
	}
	public void setAnchoContenedor(int anchoContenedor) {
		this.anchoContenedor = anchoContenedor;
	}
	public int getAltoContenedor() {
		return altoContenedor;
	}
	public void setAltoContenedor(int altoContenedor) {
		this.altoContenedor = altoContenedor;
	}
	public void mueve(){
		// TODO Auto-generated method stub
		this.posicion.x =(int) (Math.random() * (this.anchoContenedor-50)+50);
		this.posicion.y = (int)(Math.random()* (this.altoContenedor-50)+50);
	}
}
