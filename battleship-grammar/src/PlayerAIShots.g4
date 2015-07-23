/**
*
* Grammar PlayerAIShots
* describes behavior of an AI player in the game Battleship
*/

grammar PlayerAIShots;
file : row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row row EOF ;
row : START (SPACE RANDOM)? (SPACE direction)? Dot (LineBreak | EOF);


SPACE : ' ' ;
randomshot: RANDOM ;
direction : DIRECTION ;

RANDOM : 'zufaellig' ;
DIRECTION : ('linkslastig'|'rechtslastig'|'zentral') ;
START : 'Der Computer schiesst' ;

Dot : '.' ;

// line break
LineBreak : '\r'?'\n' | '\r';

WS : [\t\r\n]+ -> skip ; // skip tabs, newlines
