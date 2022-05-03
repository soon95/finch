// Generated from /Users/soon/MyFiles/Code/finch/finch-parse/src/main/java/org/leon/finch/parse/math/Math.g4 by ANTLR 4.9.2
package org.leon.finch.parse.math;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface MathVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link MathParser#prog}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitProg(MathParser.ProgContext ctx);

    /**
     * Visit a parse tree produced by the {@code parens}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParens(MathParser.ParensContext ctx);

    /**
     * Visit a parse tree produced by the {@code dec}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDec(MathParser.DecContext ctx);

    /**
     * Visit a parse tree produced by the {@code blank}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBlank(MathParser.BlankContext ctx);

    /**
     * Visit a parse tree produced by the {@code addSub}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddSub(MathParser.AddSubContext ctx);

    /**
     * Visit a parse tree produced by the {@code int}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInt(MathParser.IntContext ctx);

    /**
     * Visit a parse tree produced by the {@code mulDiv}
     * labeled alternative in {@link MathParser#formula}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulDiv(MathParser.MulDivContext ctx);
}