domain: file "domain.pddl"
problem: file "instance_1.pddl"
message: " Time Discretisation = 1.000000"
message: " Digits for representing the integer part of a real =  5.000000"
message: " Digits for representing the fractional part of a real =  4"
type
	 real_type: real(7,4);

	 TIME_type: real(12,7);


const 
	 T:1.000000;


var 
	all_event_true: boolean;
	 TIME[pddlname:"upmurphi_global_clock";]:TIME_type;
	a[pddlname:"a";] :  real_type;
	b[pddlname:"b";] :  real_type;


	true[pddlname: "true";] :  boolean;


-- External function declaration 

externfun ext_assignment(value : real_type) : real_type;
externfun increase_b_process_simple_process_(b : real_type ; T : real_type ; ): real_type "domain.h" ;
externfun increase_a_action_in_crease_a(a : real_type ; ): real_type ;
procedure set_true(  value : boolean);
BEGIN
	true := value;
END;

function get_true(): boolean;
BEGIN
	return 	true;
END;



procedure simple_process_ (); 
BEGIN
IF ((!(true))) THEN 
b := increase_b_process_simple_process_(b , T  );

ENDIF ; 

END;



procedure event_check();
 var -- local vars declaration 
   event_triggered : boolean;
BEGIN
 event_triggered := true;
while (event_triggered) do 
 event_triggered := false;
END; -- close while loop 
END;



 function DAs_violate_duration() : boolean ; 
 var -- local vars declaration 
 DA_duration_violated : boolean;
 BEGIN
 DA_duration_violated := false;

 return DA_duration_violated; 
 END; -- close begin


 function DAs_ongoing_in_goal_state() : boolean ; 
 var -- local vars declaration 
 DA_still_ongoing : boolean;
 BEGIN
 DA_still_ongoing := false;

 return DA_still_ongoing; 
 END; -- close begin


procedure apply_continuous_change();
 var -- local vars declaration 
   process_updated : boolean;
 end_while : boolean;   simple_process__enabled :  boolean;
BEGIN
 process_updated := false; end_while := false;
   simple_process__enabled := false;
while (!end_while) do 
    if ((!(true)) &  !simple_process__enabled) then
   process_updated := true;
   simple_process_();
   simple_process__enabled := true;
   endif;

IF (!process_updated) then
	 end_while:=true;
 else process_updated:=false;
endif;END; -- close while loop 
END;

action rule " in_crease_a " 
(!(true)) ==> 
pddlname: " increase_a"; 
BEGIN
a := increase_a_action_in_crease_a(a  );

END;

clock rule " time passing " 
 (true) ==> 
BEGIN 
 	TIME := TIME + T;

 	 event_check();
	 apply_continuous_change();
	 event_check();
END;


startstate "start" 
BEGIN 
TIME := 0.0;
set_true(false);

   a := 0.0 ;

   b := 0.0 ;

a := 0.00000;
b := 1.00000;
all_event_true := true;
END; -- close startstate

goal "enjoy" 
 (( a) >= (10.0000))& !DAs_ongoing_in_goal_state(); 

invariant "todo bien" 
 all_event_true & !DAs_violate_duration();
metric: minimize;


