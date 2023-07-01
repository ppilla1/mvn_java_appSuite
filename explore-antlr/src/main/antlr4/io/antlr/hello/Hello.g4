grammar Hello; // Define a grammar called Hello

// Parser rules
r : 'hello' ID; // Match keyword hello followed by an identifier

// Lexer rules
ID : [a-z]+; // Match lower-case identifiers
WS : [ \t\n\r] -> skip; // Skip spaces, tabs, newlines, return char (Windows)

