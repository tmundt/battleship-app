/**
 * Define a grammar Battlefield
 */
grammar Test;
file : row row row row row row row row EOF;
row : value Separator value Separator value Separator value Separator value Separator value Separator value  (LineBreak | EOF) ;
value : SimpleValue ;
Separator : ';' ;

// line break
LineBreak : '\r'?'\n' | '\r';

// line feed
//LineFeed : '\n';

// w or s is allowed
SimpleValue : ('s'|'w');
//SimpleValue : ('s'|'w'|'\n'|~','|~';'|~'\r'|~'\n'|~'"')+ ;
//SimpleValue : ('s'|'w')+ ~(','|';'|'\r'|'\n'|'"')+ ;

WS : [ \t\r]+ -> skip ; // skip spaces, tabs