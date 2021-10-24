; defun
; cond
; cons
; car
; cdr
; operators +, -, <, and >
; null
; eq
; listp
; atom
; symbolp

(defun my-length (L) 
    (cond
        ((null L) 0) 
        ((listp L) (+ 1 (my-length (cdr L))))
    )
)

(defun my-memq (A L)
    (cond 
        ((null L) nil)
        ((eq A (car L))  L)
        (T (my-memq A (cdr L)))
    )
)

(defun my-append (L1 L2)
    (cond
        ((null L1) L2)
        ((null (cdr L1)) (cons (car L1) L2))
        (T (cons (car L1) (my-append (cdr L1) L2)))
    )
)

(defun my-attach (O L)
    (cond
        ((null L) (cons O nil))
        ; ((null (cdr L)) (cons (car L) O))
        (T (cons (car L) (my-attach O (cdr L))))
    )
)

(defun my-assoc (A L)
    (cond 
        ((null L) nil)
        ((eq A (car (car L))) (car L))
        (T (my-assoc A (cdr L)))
    )
)

(defun freq (A L)
    (cond 
        ((null L) 0)
        ((atom L) 
            (cond
                ((eq A L) 1)
                (T 0)
            )
        )
        ((listp L)
            (+ 
            (freq A (car L))
            (freq A (cdr L))
            )
        )
    )
)

(defun mapping (L val)
    (cond
        ((null L) nil)
        ((< (car (car L)) val) 
            (cons (car (cdr (car L))) (mapping (cdr L) val))
        )
        (T (mapping (cdr L) val))
    )
)

(defun my-last-helper (A L ans)
    (cond
        ((null L) ans)
        ((eq A (car L)) (my-last-helper A (cdr L) (cons (car L) (cdr L))))
        (T (my-last-helper A (cdr L) ans))    
    )
)

(defun my-last (A L)
    (my-last-helper A L nil)
)

(defun my-reverse (L)
    (cond
        ((null L) nil)
        ((null (cdr L)) (cons (car L) nil))
        (T (my-attach (car L) (my-reverse (cdr L))))
    )
)

(defun my-index-helper (L x acc)
    (cond
        ((null L) nil)
        ((eq x (car L)) acc)
        (T (my-index-helper (cdr L) x (+ 1 acc)))
    )
)

(defun my-index (L x)
    (my-index-helper L x 0)
)

(defun find-all-helper (A L result)
    (cond
        ((null L) result)
        ((atom (car L)) (cond
            ((eq T (car result)) 
                (find-all-helper A L (cons nil (my-attach (car L) (cdr result)))))
            ((eq A (car L))
                (find-all-helper A (cdr L) (cons T (cdr result))))
            (T (find-all-helper A (cdr L) (cons nil (cdr result))))
        ))
        ((listp (car L))
            (find-all-helper A (cdr L) (find-all-helper A (car L) result)))
    )
)

(defun find-all (A L)
    (cdr (find-all-helper A L nil))
)

(defun my-fold-helper (f aggre L acc)
    (cond
        ((null L) acc)
        (T (my-fold-helper f aggre (cdr L) (funcall f acc (car L))))
    )
)

(defun my-fold (f aggre L)
   (my-fold-helper f aggre (cdr L) (funcall f aggre (car L)))
)
