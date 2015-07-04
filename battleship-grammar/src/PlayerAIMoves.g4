/**
 * Define a grammar called PlayerAIMoves
 * describes moves of an AI player in the game Battleship
 */
grammar PlayerAIMoves;
file : row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row EOF ;
//row : Letters value Letters ('.') (LineFeed | EOF) ;
row : (sentence)* value (sentence)* (LineBreak | EOF) ;
//row : value (LineBreak | EOF) ;
//row: Letters value Letters (LineBreak | EOF);

//value : (RANDOMSHOT|SpecificShot|Letters) (Letters)*;
value: (randomshot|specificshot) ;

randomshot: 'Zufall' ;
specificshot : SPECIFICSHOT ;
SPECIFICSHOT : [A-G][1-7] ;
//Letters : ([a-zA-Z ])* ;
// allow characters
//Letters : [a-zA-Z. ]* ;

sentence : SENTENCE ;
SENTENCE : ('A'..'Z'|'a'..'z'|'.'|'/'|'') ;


// line break
LineBreak : '\r'?'\n' | '\r';

WS : [ \t\r\n]+ -> skip ; // skip tabs, newlines