grammar Math;

prog : formula
     ;

formula: formula op=('*'|'/') formula      # mulDiv
| formula op=('+'|'-') formula             # addSub
| INT                                      # int
| DEC                                      # dec
| '(' formula ')'                          # parens
| BLANK                                    # blank
;

MUL : '*' ; // 乘法
DIV : '/' ; // 除法
ADD : '+' ; // 加法
SUB : '-' ; // 减法
INT : [0-9]+ ; // 整数
DEC : INT'.'INT ; // 小数
BLANK:'\r' ? '\n' ? '\s'; // 空