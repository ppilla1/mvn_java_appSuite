grammar Drools;

drl: rule_stat*;

rule_stat: 'rule' '"' rule_name '"' 'dialect' '"' dialect_name '"' when_stat then_stat end_stat;

when_stat: 'when' when_expr+;
when_expr: EXPR;
then_stat: 'then' then_expr+;
then_expr: EXPR;
end_stat: 'end';

rule_name: NAME;
dialect_name: 'mvel'
            | 'java'
            ;

EXPR: [a-zA-Z:.=,()0-9]+([ ] [a-zA-Z:.=,()0-9]+)*;
NAME: [a-zA-Z]+([ ] [a-zA-Z]+)*;
WS: [ \t\r\n] -> skip;
