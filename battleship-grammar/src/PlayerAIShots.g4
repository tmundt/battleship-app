/**
*
* Grammar PlayerAIShots
* describes behavior of an AI player in the game Battleship
*/

grammar PlayerAIShots;
file : row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row EOF ;

//row : (sentence)* value (sentence)* (LineBreak | EOF) ;
row : beginning (randomshot)? direction (LineBreak | EOF);
//value: (randomshot|specificshot) ;

beginning: 'Der Computer schießt';
//randomshot: 'Zufall' ;
randomshot: 'zufällig' ;
//specificshot : SPECIFICSHOT ;
//SPECIFICSHOT : [A-G][1-7] ;

direction : DIRECTION ;
DIRECTION : ('linkslastig'|'rechtslastig'|'zentral') ;

//sentence : SENTENCE ;
//SENTENCE : ('A'..'Z'|'a'..'z'|'.'|'/'|'') ;


// line break
LineBreak : '\r'?'\n' | '\r';

WS : [ \t\r\n]+ -> skip ; // skip tabs, newlines
