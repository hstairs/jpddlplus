#include <cmath>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>

double round_k_digits(double n, unsigned k){
	double prec = pow(0.1,k);
	double round = (n>0) ? (n+prec/2) : (n-prec/2);
	return round-fmod(round,prec);
}

double ext_assignment(double n){
	return round_k_digits(n,2.000000);
}

double increase_x_process_p(double x, double T ) {
	 return round_k_digits(x+(( T ) * (1.00000)),2.000000); 
}

