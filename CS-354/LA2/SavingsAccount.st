Account subclass: SavingsAccount[
  |i|
  
  SavingsAccount class [
        new: n customer:c balance:b [
            ^super new init
        ]
  ]

        init[
            i:=0
        ]
        
        accrue: rate[
        i:= i+(b*rate).
        b:= b+(b*rate)
        ]
]