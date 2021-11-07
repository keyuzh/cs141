    
% helper function
my_append([],L,L).
my_append([H|T],L,[H|NewT]) :- my_append(T,L,NewT).

my_reverse([], []).
my_reverse([H|T], R) :- my_reverse(T, NewR), my_append(NewR, [H], R).
    

my_length([], 0).
my_length([H|T], Y) :- my_length(T, NewY), Y is NewY + 1.