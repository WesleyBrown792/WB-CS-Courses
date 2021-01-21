#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').

%use the code from meetone here
meettime(time(_,_,am),time(_,_,pm).

meettime(time(FHour, Minute, Period),time(EHour, Minute, Period)) :-
         FHour<EHour.

meettime(time(Hour, FMinute, Period),time(Hour, EMinute, Period)) :-
         FMinute=<EMinute.
         
%next is the use of meetone
meetone(Person, slot(Begin, End)) :-
        free(Person, slot(FTime,STime)),
        meetime(FTime,Begin), meettime(End,STime). 

%check the over lapping types
match(slot(FBegin,FEnd),slot(SBegin,SEnd),slot(SBegin,SEnd)) :-
    meettime(FBegin,SBegin),
    meettime(SBegin,FEnd),
    meettime(SEnd,FEnd),
    SBegin\==SEnd.

match(slot(FBegin,FirstEnd),slot(SBegin,SEnd),slot(SBegin,FEnd)) :-
    meettime(FBegin,SBegin),
    meettime(SBegin,FEnd),
    meettime(FEnd,SEnd),
    SBegin\==FEnd.


%runs match an puts the corrects matches into a meetingslot
meetCreate(FSlot,SSlot,MSlot) :-
    match(FSlot,SSlot,MSlot).

meetCreate(FSlot,SSlot,MSlot) :-
    match(SSlot,FSlot,MSlot).


%collects all of the MSlots from meetcheck
meetCollect([S|T],FSlot,Slot) :-
    free(S,SSlot),
    meetCreate(FSlot,SSlot,Slot1),
    meetCollect(T,Slot1,Slot).
    
meetCollect([],Slot,Slot).

%creates meetings
Meetings([F|T],Slot) :-
    free(F,FSlot),
    meetCollect(T,FSlot,Slot).
    
%creates slots using people and meettimes
M(Slot) :-
    people(People),
    meetTimes(People,Slot).


people([wesley,bob,carla]).

main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).