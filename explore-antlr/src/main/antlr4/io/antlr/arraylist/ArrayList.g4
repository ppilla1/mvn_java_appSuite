grammar ArrayList;

/**
    A rule called init to match comma-separated values between
    {...}
*/

init: '{' value (separator value)* '}' ;
value: init
     | INT
     ;
separator: ',';

INT: [0-9]+;
WS: [ \t\n\r]+ -> skip;
