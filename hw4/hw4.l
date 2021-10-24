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

(defun my-length (x) 
    (cond
        ((null x) 0) 
        ((atom x) 1)
        ((listp x) (+ (my-length (car x)) (my-length (cdr x))))
    )
)
