/**
*
* Grammar PlayerAIShots
* describes behavior of an AI player in the game Battleship
*/

grammar PlayerAIShots;
file : row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row EOF ;
//row : (sentence)* value (sentence)* (LineBreak | EOF) ;
row : START (SPACE RANDOM)? (SPACE direction)? Dot (LineBreak | EOF);
//value: (randomshot|specificshot) ;

//randomshot: 'Zufall' ;
SPACE : ' ' ;
randomshot: RANDOM ;
direction : DIRECTION ;
//specificshot : SPECIFICSHOT ;
//SPECIFICSHOT : [A-G][1-7] ;

//direction : DIRECTION ;
//begin :  BEGIN ;

RANDOM : 'zufaellig' ;
DIRECTION : ('linkslastig'|'rechtslastig'|'zentral') ;
START : 'Der Computer schiesst' ;



//sentence : SENTENCE ;
//SENTENCE : ('A'..'Z'|'a'..'z'|'.'|'/'|'') ;

Dot : '.' ;

// line break
LineBreak : '\r'?'\n' | '\r';

WS : [\t\r\n]+ -> skip ; // skip tabs, newlines
