package li260.geometrie;


public class Vecteur  {
	private double x,y;

	public Vecteur(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vecteur diff(Vecteur a, Vecteur b){
		Vecteur v = new Vecteur(a.getX()- b.getX(), a.getY()-b.getY());
		return v;
	}
	
	public double prodScal(Vecteur v){
		return x*v.getX()+y*v.getY();
	}

	public static double prodScal(Vecteur v1, Vecteur v2){
		return v1.getX()+v2.getX()+v1.getY()+v2.getY();
	}
	public boolean estOrtho(Vecteur v){
		return (prodScal(v)==0);
	}
	
	public double prodVector(Vecteur v){
		return x*v.getY()-y*v.getX();
	}
	
	public boolean compoEstPositive(Vecteur v){
		return (prodVector(v)>0);
	}
	
	public Vecteur clonage(){
		Vecteur v = new Vecteur(x,y);
		return v;
	}
	
	public double norme(){
		return Math.sqrt(x*x+y*y);
	}
	
	public Vecteur normalise(){
		Vecteur v = new Vecteur(x/norme(), y/norme());
		return v;
	}
	
	public void add(Vecteur v){
		x = x+v.getX();
		y = y+v.getY();
	}
	
	public Vecteur add(Vecteur v1, Vecteur v2){
		Vecteur v = new Vecteur(v1.getX()+v2.getX(),v1.getY()+v2.getY());
		return v;
	}
	
	public void multScalCourant(double scal){
		x = x*scal;
		y = y*scal;
	}
	
	public Vecteur multScal(double scal){
		Vecteur v = new Vecteur(x*scal,y*scal);
		return v;
	}
	
	public void rotation(double angle){
		double tmp = x;
		x = tmp*Math.cos(angle) - y*Math.sin(angle);
		y = tmp*Math.sin(angle) + y*Math.cos(angle);
	}
	
	public Vecteur fact(double vitesse){
		Vecteur v = new Vecteur(x*vitesse,y*vitesse);
		return v;
	}
	
	public double dist(Vecteur v1, Vecteur v2){
		double a = v1.getX()-v2.getX();
		double b = v1.getY() - v2.getY();
		
		return Math.sqrt((a*a)-(b*b));
	}
	

	public static double getAngle(Vecteur a, Vecteur b){
		a.normalise();
		b.normalise();
		double theta = Math.acos(prodScal(a, b)/*/(norme(a)*norme(b))*/);
		if( Double.isNaN(theta)){
			theta=0;
		}
		if(a.prodVector(b)<0)
			return -theta;
		else
			return theta;
	} 
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}

	

	public boolean equals(Vecteur obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (this.x != other.x || this.y != other.y)
			return false;
		return true;
	}
	
	

}
