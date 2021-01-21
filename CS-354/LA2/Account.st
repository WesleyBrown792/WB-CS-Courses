Object subclass: Account[
  |n c TS|
  |b|
  
  accrue: rate[
    ^self subclassResponsibility
  ]

  Account class[
    new: n customer: c balance: b[
      ^self new init: n customer: c balance: b
    ]
  ]

    balance[
      ^b
     ]
  
    deposite: amount[
      b:= b+amount.
   ]
  
    withdraw: amount[
      b:= b-amount.
    ]
  
    toString[
      ^TS:= n+':'+c+':'+b
    ]

  init: n customer: c balance: b[
    ^self subclassResponsibility
  ]
]