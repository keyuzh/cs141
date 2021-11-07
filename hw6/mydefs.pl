father(klefstad, hunter).
mother(susan, hunter).
parent(X, Y) :- father(X, Y).
parent(X, Y) :- mother(X, Y).

% spy(father).
% spy(mother).
% spy(parent).


simpson([maggie, bart, lisa, homer, marge]).

% spy(simpson).

% ?- simpsons(L), member(lisa, L).
% ?- simpsons(L), member(X, L).


append([],L,L).
append([H|T],L,[H|NewT]) :- append(T,L,NewT).

% spy(append).

% ?- append([a,b],[c,d], L). % What list is [a,b]+[c,d]?
% ?- append(X, [c,d], [a,b,c,d]). % What list appended to [c,d] is [a,b,c,d]?

