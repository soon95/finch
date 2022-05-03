// Generated from /Users/soon/MyFiles/Code/finch/finch-parse/src/main/java/org/leon/finch/parse/math/Math.g4 by ANTLR 4.9.2
package org.leon.finch.parse.math;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MathParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, T__1 = 2, MUL = 3, DIV = 4, ADD = 5, SUB = 6, INT = 7, DEC = 8, BLANK = 9;
    public static final int
            RULE_prog = 0, RULE_formula = 1;

    private static String[] makeRuleNames() {
        return new String[]{
                "prog", "formula"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'('", "')'", "'*'", "'/'", "'+'", "'-'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, "MUL", "DIV", "ADD", "SUB", "INT", "DEC", "BLANK"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Math.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public MathParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class ProgContext extends ParserRuleContext {
        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public ProgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_prog;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterProg(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitProg(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitProg(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ProgContext prog() throws RecognitionException {
        ProgContext _localctx = new ProgContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_prog);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(4);
                formula(0);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormulaContext extends ParserRuleContext {
        public FormulaContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formula;
        }

        public FormulaContext() {
        }

        public void copyFrom(FormulaContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class ParensContext extends FormulaContext {
        public FormulaContext formula() {
            return getRuleContext(FormulaContext.class, 0);
        }

        public ParensContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterParens(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitParens(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitParens(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class DecContext extends FormulaContext {
        public TerminalNode DEC() {
            return getToken(MathParser.DEC, 0);
        }

        public DecContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterDec(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitDec(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitDec(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class BlankContext extends FormulaContext {
        public TerminalNode BLANK() {
            return getToken(MathParser.BLANK, 0);
        }

        public BlankContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterBlank(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitBlank(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitBlank(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class AddSubContext extends FormulaContext {
        public Token op;

        public List<FormulaContext> formula() {
            return getRuleContexts(FormulaContext.class);
        }

        public FormulaContext formula(int i) {
            return getRuleContext(FormulaContext.class, i);
        }

        public TerminalNode ADD() {
            return getToken(MathParser.ADD, 0);
        }

        public TerminalNode SUB() {
            return getToken(MathParser.SUB, 0);
        }

        public AddSubContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterAddSub(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitAddSub(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitAddSub(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class IntContext extends FormulaContext {
        public TerminalNode INT() {
            return getToken(MathParser.INT, 0);
        }

        public IntContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterInt(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitInt(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitInt(this);
            else return visitor.visitChildren(this);
        }
    }

    public static class MulDivContext extends FormulaContext {
        public Token op;

        public List<FormulaContext> formula() {
            return getRuleContexts(FormulaContext.class);
        }

        public FormulaContext formula(int i) {
            return getRuleContext(FormulaContext.class, i);
        }

        public TerminalNode MUL() {
            return getToken(MathParser.MUL, 0);
        }

        public TerminalNode DIV() {
            return getToken(MathParser.DIV, 0);
        }

        public MulDivContext(FormulaContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).enterMulDiv(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MathListener) ((MathListener) listener).exitMulDiv(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MathVisitor) return ((MathVisitor<? extends T>) visitor).visitMulDiv(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FormulaContext formula() throws RecognitionException {
        return formula(0);
    }

    private FormulaContext formula(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
        FormulaContext _prevctx = _localctx;
        int _startState = 2;
        enterRecursionRule(_localctx, 2, RULE_formula, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(14);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case INT: {
                        _localctx = new IntContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(7);
                        match(INT);
                    }
                    break;
                    case DEC: {
                        _localctx = new DecContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(8);
                        match(DEC);
                    }
                    break;
                    case T__0: {
                        _localctx = new ParensContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(9);
                        match(T__0);
                        setState(10);
                        formula(0);
                        setState(11);
                        match(T__1);
                    }
                    break;
                    case BLANK: {
                        _localctx = new BlankContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(13);
                        match(BLANK);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(24);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(22);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
                                case 1: {
                                    _localctx = new MulDivContext(new FormulaContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_formula);
                                    setState(16);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    setState(17);
                                    ((MulDivContext) _localctx).op = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == MUL || _la == DIV)) {
                                        ((MulDivContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(18);
                                    formula(7);
                                }
                                break;
                                case 2: {
                                    _localctx = new AddSubContext(new FormulaContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_formula);
                                    setState(19);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(20);
                                    ((AddSubContext) _localctx).op = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == ADD || _la == SUB)) {
                                        ((AddSubContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(21);
                                    formula(6);
                                }
                                break;
                            }
                        }
                    }
                    setState(26);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 1:
                return formula_sempred((FormulaContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 6);
            case 1:
                return precpred(_ctx, 5);
        }
        return true;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\36\4\2\t\2\4\3" +
                    "\t\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\21\n\3\3\3\3\3\3\3\3" +
                    "\3\3\3\3\3\7\3\31\n\3\f\3\16\3\34\13\3\3\3\2\3\4\4\2\4\2\4\3\2\5\6\3\2" +
                    "\7\b\2 \2\6\3\2\2\2\4\20\3\2\2\2\6\7\5\4\3\2\7\3\3\2\2\2\b\t\b\3\1\2\t" +
                    "\21\7\t\2\2\n\21\7\n\2\2\13\f\7\3\2\2\f\r\5\4\3\2\r\16\7\4\2\2\16\21\3" +
                    "\2\2\2\17\21\7\13\2\2\20\b\3\2\2\2\20\n\3\2\2\2\20\13\3\2\2\2\20\17\3" +
                    "\2\2\2\21\32\3\2\2\2\22\23\f\b\2\2\23\24\t\2\2\2\24\31\5\4\3\t\25\26\f" +
                    "\7\2\2\26\27\t\3\2\2\27\31\5\4\3\b\30\22\3\2\2\2\30\25\3\2\2\2\31\34\3" +
                    "\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\5\3\2\2\2\34\32\3\2\2\2\5\20\30" +
                    "\32";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}