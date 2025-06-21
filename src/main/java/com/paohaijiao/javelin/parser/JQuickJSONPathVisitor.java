package com.paohaijiao.javelin.parser;// Generated from D:/idea/jthornruleGrammer/JSONPath/JQuickJSONPath.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JQuickJSONPathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JQuickJSONPathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(JQuickJSONPathParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(JQuickJSONPathParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierSegment(JQuickJSONPathParser.IdentifierSegmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptSegment(JQuickJSONPathParser.SubscriptSegmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code childIdentifierSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildIdentifierSegment(JQuickJSONPathParser.ChildIdentifierSegmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code childSubscriptSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildSubscriptSegment(JQuickJSONPathParser.ChildSubscriptSegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(JQuickJSONPathParser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#filterExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpression(JQuickJSONPathParser.FilterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#slice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlice(JQuickJSONPathParser.SliceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(JQuickJSONPathParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(JQuickJSONPathParser.EndContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#step}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStep(JQuickJSONPathParser.StepContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#scriptExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptExpression(JQuickJSONPathParser.ScriptExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negationExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegationExpression(JQuickJSONPathParser.NegationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(JQuickJSONPathParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(JQuickJSONPathParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(JQuickJSONPathParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(JQuickJSONPathParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(JQuickJSONPathParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(JQuickJSONPathParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code netestDotExpr}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNetestDotExpr(JQuickJSONPathParser.NetestDotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bracketExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketExpression(JQuickJSONPathParser.BracketExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rootExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRootExpression(JQuickJSONPathParser.RootExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInExpression(JQuickJSONPathParser.InExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(JQuickJSONPathParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentExpression(JQuickJSONPathParser.CurrentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(JQuickJSONPathParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(JQuickJSONPathParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(JQuickJSONPathParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code regexExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexExpression(JQuickJSONPathParser.RegexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(JQuickJSONPathParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code chainedDotExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#dotExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChainedDotExpression(JQuickJSONPathParser.ChainedDotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code directDotExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#dotExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectDotExpression(JQuickJSONPathParser.DirectDotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#leftDotExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftDotExpr(JQuickJSONPathParser.LeftDotExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#rightDotExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightDotExpr(JQuickJSONPathParser.RightDotExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#valueList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueList(JQuickJSONPathParser.ValueListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#regexLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexLiteral(JQuickJSONPathParser.RegexLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(JQuickJSONPathParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(JQuickJSONPathParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(JQuickJSONPathParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(JQuickJSONPathParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JQuickJSONPathParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONPathParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(JQuickJSONPathParser.VariableContext ctx);
}