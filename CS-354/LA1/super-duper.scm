(define (super-duper source count)
        (define (R-loop source R)              
                (if (zero? R)
                    (super-duper (cdr source) count)
                    ;;this would be if the source had printed enough times then will send the cdr through main
                    (cons (super-duper (car source) count) (R-loop source (- R 1)))))
                    ;;this would keep the source in the r-loop with r being decrimented
                    
                    
        (if (pair? source)
            (cons (super-duper (car source) count) (R-loop source (- count 1)))
            ;;this part will run the source, using left to right, through this again and then into the r-loop
            source))
            ;;this is the else and should send back the source    

(display (super-duper '(4 5 6 a b) 2))


;; I also included a list of the test cases which I used to run though my program.