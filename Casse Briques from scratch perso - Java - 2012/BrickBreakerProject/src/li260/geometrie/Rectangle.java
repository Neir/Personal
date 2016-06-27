package li260.geometrie;

public class Rectangle{
	private Vecteur v1, v2;

	public Rectangle(Vecteur v1, Vecteur v2) {
		super();
		if(v1.getX()<v2.getX()){
		this.v1 = v1;
		this.v2 = v2;
		} else {
			this.v1 = v2;
			this.v2 = v1;
		}
	}
	
	public Rectangle(Vecteur v1, int width, int height) {
		super();
		this.v1 = v1;
		this.v2 = new Vecteur(v1.getX() + width, v1.getY() + height);
	}

	public Vecteur getV1() {
		return v1;
	}

	public void setV1(Vecteur v1) {
		this.v1 = v1;
	}

	public Vecteur getV2() {
		return v2;
	}

	public void setV2(Vecteur v2) {
		this.v2 = v2;
	}
	
	public int getWidth(){
		return (int) (v2.getX() - v1.getX());
	}

	public int getHeight(){
		return (int) (v2.getY() - v1.getY());
	}
	
	public boolean isRectangle(Vecteur v){
		if(		v.getX()>=v1.getX()&&
				v.getX()<=v2.getX()&&
				v.getY()>=v1.getY()&&
				v.getY()<=v2.getY())
			return true;
		return false;
	}
}
