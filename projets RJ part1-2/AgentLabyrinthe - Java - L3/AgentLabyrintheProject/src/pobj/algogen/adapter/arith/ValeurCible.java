package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.arith.EnvEval;
import pobj.arith.Expression;

public class ValeurCible implements Environnement {
	private Object valCible;
	private EnvEval env;
	
	public ValeurCible(Object valCible, EnvEval env) {
		super();
		this.valCible = valCible;
		this.env = env;
	}

	@Override
	public double eval(Individu i) {
		return 1/((((Expression)valCible).eval(env) - ((Expression)i.getValeurPropre()).eval(env))*
				  (((Expression)valCible).eval(env) - ((Expression)i.getValeurPropre()).eval(env)));
	}

}

