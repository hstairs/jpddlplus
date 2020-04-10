domain: file "domain2.pddl"
problem: file "instance_2.pddl"
message: " Time Discretisation = 0.1"
message: " Digits for representing the integer part of a real =  5"
message: " Digits for representing the fractional part of a real =  4"
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :time "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: Undeclared requirement :fluents "
message: "parsing_file.pddl: Warning: PDDL Objects have to be typed for being correctly parsed: requirement :typing "
type
	 real_type: real(7,4);

	 TIME_type: real(7,2);


const 
	 T:0.1;


var 
	all_event_true: boolean;
	 TIME[pddlname:"upmurphi_global_clock";]:TIME_type;
	x[pddlname:"x";] :  real_type;


	activea[pddlname: "activea";] :  boolean;
	activeb[pddlname: "activeb";] :  boolean;
	activep[pddlname: "activep";] :  boolean;
	goal[pddlname: "goal";] :  boolean;
	befor_egoal[pddlname: "beforegoal";] :  boolean;


-- External function declaration 

externfun ext_assignment(value : real_type) : real_type;
externfun assign_x_event_set_x_0(): real_type "domain2.h" ;
externfun increase_x_action_in_crease_x(x : real_type ; ): real_type ;
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

procedure set_befor_egoal(  value : boolean);
BEGIN
	befor_egoal := value;
END;

function get_befor_egoal(): boolean;
BEGIN
	return 	befor_egoal;
END;


function set_x_0 () : boolean; 
BEGIN
IF (((( x) > (0.00000)))) THEN 
x := assign_x_event_set_x_0();
		 return true; 
 	 ELSE return false;
	 ENDIF;

END;



procedure event_check();
 var -- local vars declaration 
   event_triggered : boolean;
   set_x_0_triggered :  boolean;
BEGIN
 event_triggered := true;
   set_x_0_triggered := false;
while (event_triggered) do 
 event_triggered := false;
   if(! set_x_0_triggered) then 
   set_x_0_triggered := set_x_0();
   event_triggered := event_triggered | set_x_0_triggered; 
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
 end_while : boolean;BEGIN
 process_updated := false; end_while := false;
while (!end_while) do 
 IF (!process_updated) then
	 end_while:=true;
 else process_updated:=false;
endif;END; -- close while loop 
END;

action rule " in_crease_x " 
((( x) >= (0.00000))) ==> 
pddlname: " increase_x"; 
BEGIN
x := increase_x_action_in_crease_x(x  );

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

   set_befor_egoal(false);

   x := 0.0 ;

activea:= true; 
x := 0.00000;
all_event_true := true;
END; -- close startstate

goal "enjoy" 
 ((( x) >= (2.00000)))& !DAs_ongoing_in_goal_state(); 

invariant "todo bien" 
 all_event_true & !DAs_violate_duration();
metric: minimize;


