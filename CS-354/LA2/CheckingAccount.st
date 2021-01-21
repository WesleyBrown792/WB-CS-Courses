Account subclass: CheckingAccount[ 
     
  CheckingAccount class [
        new: n customer:c balance:b [
            ^super new init
        ]
  ]            
        accrue: rate[]
]