package com.paohaijiao.javelin.parser;// Generated from D:/idea/jthornruleGrammer/JSONPath/JQuickJSONPath.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JQuickJSONPathParser}.
 */
public interface JQuickJSONPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(JQuickJSONPathParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(JQuickJSONPathParser.PathContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(JQuickJSONPathParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(JQuickJSONPathParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierSegment(JQuickJSONPathParser.IdentifierSegmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierSegment(JQuickJSONPathParser.IdentifierSegmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptSegment(JQuickJSONPathParser.SubscriptSegmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptSegment(JQuickJSONPathParser.SubscriptSegmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code childIdentifierSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterChildIdentifierSegment(JQuickJSONPathParser.ChildIdentifierSegmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code childIdentifierSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitChildIdentifierSegment(JQuickJSONPathParser.ChildIdentifierSegmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code childSubscriptSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterChildSubscriptSegment(JQuickJSONPathParser.ChildSubscriptSegmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code childSubscriptSegment}
	 * labeled alternative in {@link JQuickJSONPathParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitChildSubscriptSegment(JQuickJSONPathParser.ChildSubscriptSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(JQuickJSONPathParser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(JQuickJSONPathParser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpression(JQuickJSONPathParser.FilterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpression(JQuickJSONPathParser.FilterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#slice}.
	 * @param ctx the parse tree
	 */
	void enterSlice(JQuickJSONPathParser.SliceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#slice}.
	 * @param ctx the parse tree
	 */
	void exitSlice(JQuickJSONPathParser.SliceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(JQuickJSONPathParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(JQuickJSONPathParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(JQuickJSONPathParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(JQuickJSONPathParser.EndContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#step}.
	 * @param ctx the parse tree
	 */
	void enterStep(JQuickJSONPathParser.StepContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#step}.
	 * @param ctx the parse tree
	 */
	void exitStep(JQuickJSONPathParser.StepContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#scriptExpression}.
	 * @param ctx the parse tree
	 */
	void enterScriptExpression(JQuickJSONPathParser.ScriptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#scriptExpression}.
	 * @param ctx the parse tree
	 */
	void exitScriptExpression(JQuickJSONPathParser.ScriptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negationExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegationExpression(JQuickJSONPathParser.NegationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negationExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegationExpression(JQuickJSONPathParser.NegationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(JQuickJSONPathParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(JQuickJSONPathParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(JQuickJSONPathParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(JQuickJSONPathParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(JQuickJSONPathParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(JQuickJSONPathParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(JQuickJSONPathParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(JQuickJSONPathParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(JQuickJSONPathParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(JQuickJSONPathParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(JQuickJSONPathParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(JQuickJSONPathParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code netestDotExpr}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNetestDotExpr(JQuickJSONPathParser.NetestDotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code netestDotExpr}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNetestDotExpr(JQuickJSONPathParser.NetestDotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bracketExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpression(JQuickJSONPathParser.BracketExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bracketExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpression(JQuickJSONPathParser.BracketExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rootExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRootExpression(JQuickJSONPathParser.RootExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rootExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRootExpression(JQuickJSONPathParser.RootExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInExpression(JQuickJSONPathParser.InExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInExpression(JQuickJSONPathParser.InExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(JQuickJSONPathParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(JQuickJSONPathParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCurrentExpression(JQuickJSONPathParser.CurrentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCurrentExpression(JQuickJSONPathParser.CurrentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(JQuickJSONPathParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(JQuickJSONPathParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(JQuickJSONPathParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(JQuickJSONPathParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(JQuickJSONPathParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(JQuickJSONPathParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code regexExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRegexExpression(JQuickJSONPathParser.RegexExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code regexExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRegexExpression(JQuickJSONPathParser.RegexExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(JQuickJSONPathParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(JQuickJSONPathParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code chainedDotExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#dotExpr}.
	 * @param ctx the parse tree
	 */
	void enterChainedDotExpression(JQuickJSONPathParser.ChainedDotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code chainedDotExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#dotExpr}.
	 * @param ctx the parse tree
	 */
	void exitChainedDotExpression(JQuickJSONPathParser.ChainedDotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code directDotExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#dotExpr}.
	 * @param ctx the parse tree
	 */
	void enterDirectDotExpression(JQuickJSONPathParser.DirectDotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directDotExpression}
	 * labeled alternative in {@link JQuickJSONPathParser#dotExpr}.
	 * @param ctx the parse tree
	 */
	void exitDirectDotExpression(JQuickJSONPathParser.DirectDotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#leftDotExpr}.
	 * @param ctx the parse tree
	 */
	void enterLeftDotExpr(JQuickJSONPathParser.LeftDotExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#leftDotExpr}.
	 * @param ctx the parse tree
	 */
	void exitLeftDotExpr(JQuickJSONPathParser.LeftDotExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#rightDotExpr}.
	 * @param ctx the parse tree
	 */
	void enterRightDotExpr(JQuickJSONPathParser.RightDotExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#rightDotExpr}.
	 * @param ctx the parse tree
	 */
	void exitRightDotExpr(JQuickJSONPathParser.RightDotExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#valueList}.
	 * @param ctx the parse tree
	 */
	void enterValueList(JQuickJSONPathParser.ValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#valueList}.
	 * @param ctx the parse tree
	 */
	void exitValueList(JQuickJSONPathParser.ValueListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#regexLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRegexLiteral(JQuickJSONPathParser.RegexLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#regexLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRegexLiteral(JQuickJSONPathParser.RegexLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(JQuickJSONPathParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(JQuickJSONPathParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(JQuickJSONPathParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(JQuickJSONPathParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JQuickJSONPathParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JQuickJSONPathParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(JQuickJSONPathParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(JQuickJSONPathParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(JQuickJSONPathParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(JQuickJSONPathParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickJSONPathParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(JQuickJSONPathParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickJSONPathParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(JQuickJSONPathParser.VariableContext ctx);
}