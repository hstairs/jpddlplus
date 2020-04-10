domain: file "domain.pddl"
problem: file "instance_1.pddl"
message: " Time Discretisation = 1.000000"
message: " Digits for representing the integer part of a real =  5.000000"
message: " Digits for representing the fractional part of a real =  4"
type
	 real_type: real(7,4);
	integer: -1000..1000;

	 TIME_type: real(12,7);


const 
	 T:1.000000;


var 
	all_event_true: boolean;
	 h_n: integer;
	 g_n: integer;
	 f_n: integer;
	 TIME[pddlname:"upmurphi_global_clock";]:TIME_type;
	x[pddlname:"x";] :  real_type;


	activea[pddlname: "activea";] :  boolean;
	activeb[pddlname: "activeb";] :  boolean;
	activep[pddlname: "activep";] :  boolean;
	goal[pddlname: "goal";] :  boolean;


-- External function declaration 

externfun ext_assignment(value : real_type) : real_type;
externfun increase_x_process_p(x : real_type ; T : real_type ; ): real_type "domain.h" ;
procedure set_activea(  value : boolean);
BEGIN
	activea := value;
END;

function get_activea(): boolean;
BEGIN
	return 	activea;
END;

procedure set_activeb(  value : boolean);
BEGIN
	activeb := value;
END;

function get_activeb(): boolean;
BEGIN
	return 	activeb;
END;

procedure set_activep(  value : boolean);
BEGIN
	activep := value;
END;

function get_activep(): boolean;
BEGIN
	return 	activep;
END;

procedure set_goal(  value : boolean);
BEGIN
	goal := value;
END;

function get_goal(): boolean;
BEGIN
	return 	goal;
END;


procedure process_p (); 
BEGIN
IF ((activep)) THEN 
x := increase_x_process_p(x , T  );

ENDIF ; 

END;

function a () : boolean; 
BEGIN
IF ((activea)) THEN 
activep:= true; 
activea:= false; 
		 return true; 
 	 ELSE return false;
	 ENDIF;

END;

function b () : boolean; 
BEGIN
IF (((( x) > (0.00000))) & (activep)) THEN 
goal:= true; 
activep:= false; 
		 return true; 
 	 ELSE return false;
	 ENDIF;

END;



procedure event_check();
 var -- local vars declaration 
   event_triggered : boolean;
   a_triggered :  boolean;
   b_triggered :  boolean;
BEGIN
 event_triggered := true;
   a_triggered := false;
   b_triggered := false;
while (event_triggered) do 
 event_triggered := false;
   if(! a_triggered) then 
   a_triggered := a();
   event_triggered := event_triggered | a_triggered; 
   endif;

   if(! b_triggered) then 
   b_triggered := b();
   event_triggered := event_triggered | b_triggered; 
   endif;

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
 end_while : boolean;   process_p_enabled :  boolean;
BEGIN
 process_updated := false; end_while := false;
   process_p_enabled := false;
while (!end_while) do 
    if ((activep) &  !process_p_enabled) then
   process_updated := true;
   process_p();
   process_p_enabled := true;
   endif;

IF (!process_updated) then
	 end_while:=true;
 else process_updated:=false;
endif;END; -- close while loop 
END;

action rule " start_action " 
((( x) = (0.00000))) ==> 
pddlname: " start-action"; 
BEGIN
activea:= true; 

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
set_activea(false);

   set_activeb(false);

   set_activep(false);

   set_goal(false);

   x := 0.0 ;

x := 0.00000;
all_event_true := true;
g_n := 0;
h_n := 0;
f_n := 0;
END; -- close startstate

goal "enjoy" 
 (goal)& !DAs_ongoing_in_goal_state(); 

invariant "todo bien" 
 all_event_true & !DAs_violate_duration();
metric: minimize;


