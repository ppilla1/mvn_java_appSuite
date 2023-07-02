grammar ArrayList;

/**
    A rule called init to match comma-separated values between
    {...}
*/

init: '{' value (',' value)* '}' ;
value: init
     | INT
     ;

INT: [0-9]+;
WS: [ \t\n\r]+ -> skip;
