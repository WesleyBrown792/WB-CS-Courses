#!/bin/gprolog --consult-file

:- include('data.pl').


meettime(time(_,_,am),time(_,_,pm).

meettime(time(FHour, Minute, Period),time(LHour, Minute, Period)) :-
         FHour<LHour.

meettime(time(Hour, FMinute, Period),time(Hour, LMinute, Period)) :-
         FMinute=<LMinute.


meetone(Person, slot(First,Last))) :- free(Person,slot(Begin,End)),
                                      meettime(Begin,First),meettime(End,Last).

main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).
