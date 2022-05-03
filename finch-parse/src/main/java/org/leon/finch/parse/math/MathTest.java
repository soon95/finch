package org.leon.finch.parse.math;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Leon Song
 * @date 2022-05-03
 */
public class MathTest {


    @Test
    public void test1() {

        String formula = "(1+2/2)*3-4";

        ParseTree parseTree = this.buildParseTree(formula);

        CustomMathVisitor mathVisitor = new CustomMathVisitor();

        Number result = mathVisitor.visit(parseTree);

        System.out.println(formula + " = " + result);

        Assert.assertEquals(2, result);

    }

    @Test
    public void test2() {

        String formula = "(1.2+5/2)*3.7-9.9";

        ParseTree parseTree = this.buildParseTree(formula);

        CustomMathVisitor mathVisitor = new CustomMathVisitor();

        Number result = mathVisitor.visit(parseTree);

        System.out.println(formula + " = " + result);

        Assert.assertEquals(3.79f, ((Float) result), 0.001);

    }


    @Test(expected = ArithmeticException.class)
    public void test3() {

        String formula = "(3.7+5.5)/(3-6/2)";

        ParseTree parseTree = this.buildParseTree(formula);

        CustomMathVisitor mathVisitor = new CustomMathVisitor();

        Number result = mathVisitor.visit(parseTree);

        System.out.println(formula + " = " + result);

    }

    @Test
    public void test4() {

        String formula = "";

        ParseTree parseTree = this.buildParseTree(formula);

        CustomMathVisitor mathVisitor = new CustomMathVisitor();

        Number result = mathVisitor.visit(parseTree);

        System.out.println(formula + " = " + result);

        Assert.assertEquals(0, result);
    }


    private ParseTree buildParseTree(String formula) {

        ANTLRInputStream inputStream = new ANTLRInputStream(formula);

        MathLexer mathLexer = new MathLexer(inputStream);

        CommonTokenStream tokenStream = new CommonTokenStream(mathLexer);

        MathParser mathParser = new MathParser(tokenStream);

        return mathParser.prog();
    }

}
