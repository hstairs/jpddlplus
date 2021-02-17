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
	return round_k_digits(n,2);
}

double assign_x_event_set_x_0() {
	 return round_k_digits(0.00000,2); 
}

double increase_x_action_in_crease_x(double x ) {
	 return round_k_digits(x+(1.00000),2); 
}

