// Generated from /Users/soon/MyFiles/Code/finch/finch-parse/src/main/java/org/leon/finch/parse/math/Math.g4 by ANTLR 4.9.2
package org.leon.finch.parse.math;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MathParser}.
 */
public interface MathListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link MathParser#prog}.
     *
     * @param ctx the parse tree
     */
    void enterProg(MathParser.ProgContext ctx);

    /**
     * Exit a parse tree produced by {@link MathParser#prog}.
     *
     * @param ctx the parse tree
     */
    void exitProg(MathParser.ProgContext ctx);

    /**
     * Enter a parse tree produced by the {@code parens}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void enterParens(MathParser.ParensContext ctx);

    /**
     * Exit a parse tree produced by the {@code parens}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void exitParens(MathParser.ParensContext ctx);

    /**
     * Enter a parse tree produced by the {@code dec}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void enterDec(MathParser.DecContext ctx);

    /**
     * Exit a parse tree produced by the {@code dec}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void exitDec(MathParser.DecContext ctx);

    /**
     * Enter a parse tree produced by the {@code blank}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void enterBlank(MathParser.BlankContext ctx);

    /**
     * Exit a parse tree produced by the {@code blank}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void exitBlank(MathParser.BlankContext ctx);

    /**
     * Enter a parse tree produced by the {@code addSub}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void enterAddSub(MathParser.AddSubContext ctx);

    /**
     * Exit a parse tree produced by the {@code addSub}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void exitAddSub(MathParser.AddSubContext ctx);

    /**
     * Enter a parse tree produced by the {@code int}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void enterInt(MathParser.IntContext ctx);

    /**
     * Exit a parse tree produced by the {@code int}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void exitInt(MathParser.IntContext ctx);

    /**
     * Enter a parse tree produced by the {@code mulDiv}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void enterMulDiv(MathParser.MulDivContext ctx);

    /**
     * Exit a parse tree produced by the {@code mulDiv}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     */
    void exitMulDiv(MathParser.MulDivContext ctx);
}