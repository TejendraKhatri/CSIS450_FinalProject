Once the files have been downloaded, open a terminal and navigate to the folder where the front.java file has been saved.
Once there type in "javac front.java" in the terminal and press enter.
I have also provided a front.in file as a sample. If you wish to use it please place in the same folder as the front.java file.
Type "java front" in the terminal window and press enter.
The results shall be displayed on the terminal window itself.

For RDParser2, open a terminal and navigate to the folder where the front.java file has been saved.
Once there type in "javac RDParser2.java" in the terminal and press enter.
I have also provided a front.in file as a sample. If you wish to use it please place in the same folder as the front.java file.
Type "java RDParser2" in the terminal window and press enter.
The results shall be displayed on the terminal window itself.
P.S. CLASSPATH and PATH are already set as default.


RDParser2 has been implemented based on the following EBNF which has been made by modifying the EBNF description
from Assignment 5 and the precedence of operations of Java.

<stmt> -> <expr>{( = | += | -= | *= | /= ) <expr>}
<expr> -> <expr1>{ ||  <expr1>}
<expr1> -> <expr2>{ && <expr2>}
<expr2> -> <expr3> {( == | != ) <expr3>}
<expr3> -> <expr4>{ ( < | <= | > | >= ) <expr4>}
<expr4> -> <expr5>{( << | >> | >>> ) <expr5>}
<expr5> -> <term> {( + | -) <term>}
<term> -> <factor>{( * | / | %) <expr1>}
<factor> -> <id> | int_const | (stmt)



