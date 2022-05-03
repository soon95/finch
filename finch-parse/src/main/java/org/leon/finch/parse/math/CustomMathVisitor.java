package org.leon.finch.parse.math;


/**
 * @author Leon Song
 * @date 2022-05-03
 */
public class CustomMathVisitor extends MathBaseVisitor<Number> {

    @Override
    public Number visitParens(MathParser.ParensContext ctx) {
        return this.visit(ctx.formula());
    }

    @Override
    public Number visitBlank(MathParser.BlankContext ctx) {
        return 0;
    }

    @Override
    public Number visitDec(MathParser.DecContext ctx) {
        return Float.valueOf(ctx.DEC().getText());
    }

    @Override
    public Number visitAddSub(MathParser.AddSubContext ctx) {

        Number left = this.visit(ctx.formula(0));
        Number right = this.visit(ctx.formula(1));

        if (left instanceof Integer && right instanceof Integer) {

            if (MathParser.ADD == ctx.op.getType()) {
                return left.intValue() + right.intValue();
            } else {
                return left.intValue() - right.intValue();
            }
        } else {
            if (MathParser.ADD == ctx.op.getType()) {
                return left.floatValue() + right.floatValue();
            } else {
                return left.floatValue() - right.floatValue();
            }
        }
    }

    @Override
    public Number visitInt(MathParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Number visitMulDiv(MathParser.MulDivContext ctx) {
        Number left = this.visit(ctx.formula(0));
        Number right = this.visit(ctx.formula(1));


        if (left instanceof Integer && right instanceof Integer) {

            if (MathParser.MUL == ctx.op.getType()) {
                return left.intValue() * right.intValue();
            } else {
                // 除数需要非零
                if (right.intValue() == 0) {
                    throw new ArithmeticException("/ by zero");
                }

                // 如果能够整除 就接着使用整数
                if (left.intValue() % right.intValue() == 0) {
                    return left.intValue() / right.intValue();
                } else {
                    return left.floatValue() / right.floatValue();
                }
            }
        } else {
            if (MathParser.MUL == ctx.op.getType()) {
                return left.floatValue() * right.floatValue();
            } else {
                // 除数需要非零
                if (Math.abs(right.floatValue() - 0) < 0.00000001) {
                    throw new ArithmeticException("/ by zero");
                }
                return left.floatValue() / right.floatValue();
            }
        }
    }
}
