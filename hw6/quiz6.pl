% last_element(List, Result).
last_element([H], H).
last_element([H|T], R) :-
    last_element(T, R).

% insert_at(List, Element, Position, ReturnedList).
insert_at(L, E, 0, [E|L]).
insert_at([H|T], E, P, [H|RL]) :- 
    P > 0,
    NewP is P - 1,
    insert_at(T, E, NewP, RL).

% list_length(List, ResultLength)
list_length([], 0).
list_length([H|T], L) :-
    list_length(T, NewL),
    L is NewL + 1.