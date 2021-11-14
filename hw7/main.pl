door_between(a,b).
door_between(a,c).
door_between(a,d).
door_between(b,c).
door_between(b,e).
door_between(d,c).
door_between(d,e).
door_between(e,c).

path_exists(A, B) :- door_between(A, B).
path_exists(A, B) :- door_between(B, A).

% same room
path_from_helper(A, A, Visited, []).

path_from_helper(A, B, Visited, R) :-
    A \== B,
    path_exists(A, Reach),
    % write(Reach),
    \+(member(Reach, Visited)),
    append([Reach], Visited, NewVisited),
    path_from_helper(Reach, B, NewVisited, NewR),
    append([Reach], NewR, R).


path_from(A, A, []).
path_from(A, B, R) :-
    A \== B,
    path_from_helper(A, B, [A], NewR),
    \+(NewR = []),
    append([A], NewR, R).


male(klefstad).
male(bill).
male(mark).
male(issac).
male(fred).
female(emily).
female(heidi).
female(beth).
female(susan).
female(jane).

speaks(klefstad, english).
speaks(bill, english).
speaks(emily, english).
speaks(heidi, english).
speaks(issac, english).
speaks(beth, french).
speaks(mark, french).
speaks(susan, french).
speaks(issac, french).
speaks(klefstad, spanish).
speaks(bill, spanish).
speaks(susan, spanish).
speaks(fred, spanish).
speaks(jane, spanish).

first_element([H|T], H).
last_element([H], H).
last_element([H|T], R) :-
    last_element(T, R).

speaksSame(A, B) :-
    speaks(A, Lang),
    speaks(B, Lang).

validGender(P1, P2) :- female(P1), male(P2).
validGender(P1, P2) :- male(P1), male(P2).
validGender(P1, P2) :- male(P1), female(P2).

seating([H|T], R) :- 
    male(H), 
    validSeating([H], T, [], R).
seating([H|T], R) :- 
    female(H), 
    validSeating([H], T, [], R).

validSeating([H], [T|L], Seated, R) :- 
    length(Seated, 10),
    first_element(Seated, Head),
    last_element(Seated, Tail),
    speaksSame(Head, Tail),
    validGender(Head, Tail), 
    % writeln(Seated),
    R = Seated.

validSeating([H], [T|L], Seated, R) :- 
    \+(member(H, Seated)),
    speaksSame(H, T),
    validGender(H, T), 
    append([H], Seated, NewSeated),
    validSeating([T], L, NewSeated, R).

party_seating(L) :- 
    seating([H|T], L).




der_helper(H, 0) :- number(H).

der_helper(H, 1) :- atomic(H).
    
der_helper(B^P, Ans) :-
    NewP is P - 1,
    (NewP = 1 ->
        Ans = P*B;
        Ans = P*B^NewP
    ).

der_helper(A+B, Ans) :-
    der_helper(A, Ans1),
    der_helper(B, Ans2),
    Ans = Ans1+Ans2.

der_helper(A-B, Ans) :-
    der_helper(A, Ans1),
    der_helper(B, Ans2),
    Ans = Ans1-Ans2.

der_helper(A*B, Ans) :-
    der_helper(A, DerA),
    der_helper(B, DerB),
    Ans = A * DerB + B * DerA.

der_helper(A/B, Ans) :-
    der_helper(A, DerA),
    der_helper(B, DerB),
    Ans = (B * DerA - A * DerB)/(B^2).


eval(N, N) :- number(N).
eval(N, N) :- atomic(N).

eval(N+0, N).
eval(0+N, N).
eval(N*0, 0).
eval(0*N, 0).
eval(N*1, N).
eval(1*N, N).

eval(X*N, N*X) :- number(N), atomic(X).
eval(X/X, 1) :- atomic(X).

eval(X*X, R) :- 
    atomic(X),
    R = X^2.

eval(X+Y, R) :- number(X), number(Y), R is X+Y.
eval(X*Y, R) :- number(X), number(Y), R is X*Y.
eval(X-Y, R) :- number(X), number(Y), R is X-Y.
eval(X/Y, R) :- number(X), number(Y), R is X/Y.

eval(X+Y, R) :- eval(X, XR), eval(Y, YR), R = XR+YR.
eval(X*Y, R) :- eval(X, XR), eval(Y, YR), R = XR*YR.
eval(X-Y, R) :- eval(X, XR), eval(Y, YR), R = XR-YR.
eval(X/Y, R) :- eval(X, XR), eval(Y, YR), R = XR/YR.

eval(X, X).

deriv(Exp, Res) :-
    % eval(Exp, S),
    % writeln(S),
    der_helper(Exp, Ans),
    % writeln(Ans),
    eval(Ans, Res),
    !.
