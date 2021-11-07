    
% helper function
my_append([],L,L).
my_append([H|T],L,[H|NewT]) :- my_append(T,L,NewT).

my_reverse([], []).
my_reverse([H|T], R) :- my_reverse(T, NewR), my_append(NewR, [H], R).
    

my_length([], 0).
my_length([H|T], Y) :- my_length(T, NewY), Y is NewY + 1.

my_subset(R, [], []).
my_subset(R, [H|T], Y) :- 
    my_subset(R, T, NewY),
    Term =.. [R, H],
    (Term -> 
        append([H], NewY, Y);
        Y = NewY
    ).


my_member(Y, [Y|T]).
my_member(Y, [H|T]) :-
    Y \== H,
    my_member(Y, T).

my_intersect([H1|T1], [], []).
my_intersect([], [H2|T2], []).
my_intersect([], [], []).
my_intersect([H1|T1], [H2|T2], R) :-
    my_intersect(T1, [H2|T2], NewR),
    (my_member(H1, [H2|T2]) -> 
        append([H1], NewR, R);
        R = NewR
    ).

% compute_change(Money, Quarter, Dime, Nickle, Penny).
% base cases, no/exact change
compute_change(0, 0, 0, 0, 0).

compute_change(Money, Q, D, N, P) :-
    (Money >= 25 ->
        NewM is Money - 25,
        compute_change(NewM, NewQ, D, N, P),
        Q is NewQ + 1;
        (Money >= 10 -> 
            NewM is Money - 10,
            compute_change(NewM, Q, NewD, N, P),
            D is NewD + 1;
            (Money >= 5 -> 
                NewM is Money - 5,
                compute_change(NewM, Q, D, NewN, P),
                N is NewN + 1;
                NewM is Money - 1,
                compute_change(NewM, Q, D, N, NewP),
                P is NewP + 1
            )
        )
    ).

compose([], [], []).
compose([], L2, L2).
compose(L1, [], L1).
compose([H1|T1], [H2|T2], L) :-
    compose(T1, T2, NewL),
    append([H1, H2], NewL, L).


% palindrome(Base, Result)
palindrome([], []).
palindrome([H], [H, H]).
palindrome([H|T], R) :-
    palindrome(T, NewR),
    append([H], NewR, Temp),
    append(Temp, [H], R).
