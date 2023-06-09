package HW05_C107110257_V1_JAVA;

public class Math {
	double a,b;
	public Math(String as,String bs) {
		a=Double.valueOf(as);
		b=Double.valueOf(bs);
	}
	public String GCD() {//最大公因數
		return String.valueOf(gcd(a,b));
	}
	public String LCM() {//最小公倍數
		return String.valueOf(lcm(a,b));
	}
	public String IS(){
		if(gcd(a,b)==1.0)
			return "有互質";
		else
			return "沒互質";
	}
	//------------------------------------------------------------
	public double gcd(double m, double n) { 
		return n == 0 ? m: gcd(n, m % n);
	}
	public double lcm(double m, double n) { 
		return (m * n) / gcd(m, n);
	}
}
