package pobj.arith;

import java.util.Arrays;

public class EnvEval {
	private double[] variables;
	
	public EnvEval(int taille){
		variables = new double[taille];
	}

	public double getVariables(int index) {
		return variables[index];
	}

	public void setVariables(int index, double variable) {
		variables[index] = variable;
	}

	@Override
	public String toString() {
		return "EnvEval [variables=" + Arrays.toString(variables) + "]";
	}
	
}
