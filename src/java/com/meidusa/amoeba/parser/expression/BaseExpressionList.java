/*
 * 	This program is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, 
 * or (at your option) any later version. 
 * 
 * 	This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details. 
 * 	You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.meidusa.amoeba.parser.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href=mailto:piratebase@sina.com>Struct chen</a>
 *
 */
public abstract class BaseExpressionList extends Expression {

    protected List<Expression> eList = new ArrayList<Expression>();

    public BaseExpressionList(){
    }

    public BaseExpressionList(Expression expression){
        if (expression != null) {
            addExpression(expression);
        }
    }

    public List<Expression> getAllExpression() {
        return eList;
    }

    /**
     * 表达式是否需要实时计算
     */
    public boolean isRealtime() {
        for (Expression e : eList) {
            if (e.isRealtime()) {
                return true;
            }
        }
        return false;
    }

    protected abstract BaseExpressionList getReverseObject();

    /**
     * 表达式取反
     */
    @Override
    public Expression reverse() {
        BaseExpressionList list = getReverseObject();
        for (Expression e : this.eList) {
            Expression reversedExpression = e.reverse();
            list.addExpression(reversedExpression);
        }
        return list;
    }
    /**
     * 把表达式expression添加到eList中
     * @param expression
     */
    public void addExpression(Expression expression) {
        eList.add(expression);
    }
    /**
     * 得到eList的大小（size）
     * @return
     */
    public int getSize() {
        if (eList == null){
            return 0;
        }
        return eList.size();
    }

    /***
     * 目前还不是很清楚
     * @param builder
     * @param concatString
     */
    protected void toString(StringBuilder builder, String concatString) {
        int current = 0;
        if (eList.size() >= 2) {
            builder.append("(");
        }
        for (Expression e : eList) {
            e.toString(builder);
            current++;
            if (current != eList.size()) {
                builder.append(concatString);
            }
        }
        if (eList.size() >= 2) {
            builder.append(")");
        }
    }
}
