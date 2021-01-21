
  Object subclass: Bank[

  |accounts r|
  
  add: account[
  accounts add account.
  ]
  
  accrue: rate[ 
  accounts do [ :v | v:= 0].
  ]
  
  
  toString[
  accounts do [ :v | r:= v toString].
  ^r
  ]
]
 
 
|bank c CA SA|
bank:= Bank new.
c:= Customer new: 'Ann'.
CA:= CheckingAccount new: '01001' customer: c balance: 100.00.
SA:= SavingsAccount new: '01002' customer: c balance: 200.00.
(Bank add: CA).
(Bank add: SA).
(Bank accrue: 0.02).
(bank:= Bank toString) printNl
