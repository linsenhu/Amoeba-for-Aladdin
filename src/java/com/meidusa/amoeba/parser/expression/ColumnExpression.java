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

import com.meidusa.amoeba.parser.dbobject.Column;

/**
 * 
 * @author <a href=mailto:piratebase@sina.com>Struct chen</a>
 *
 */
public class ColumnExpression extends Expression {
	private Column column;
	private ComparisonExpression expression;
	
	//�õ��У���
	public Column getColumn() {
		return column;
	}
	/**
	 * ����column��ֵ
	 * @param column
	 */
	public void setColumn(Column column) {
		this.column = column;
	}
	/**
	 * ������expression��ֵ
	 * @param expression
	 */
	public void setExpression(ComparisonExpression expression){
		this.expression = expression;
	}
	
	/***
	 * ����ʽȡ��
	 */
	public Expression reverse(){
		expression.reverse();
		return this;
	}
	/**
	 * �õ�����ʽ(expression)
	 * @return
	 */
	public Expression getExpression(){
		return expression;
	}
	/**
	 * �Ƿ���Ҫʵʱ����
	 */
	public boolean isRealtime(){
		return expression.isRealtime();
	}
	/* ����ʽ����
	 * (non-Javadoc)
	 * @see com.meidusa.amoeba.parser.expression.Expression#evaluate(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Comparable evaluate(Object[] parameters) {
		return expression.evaluate(parameters);
	}
	
	/**
	 * ת�����ַ���
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	@Override
	protected void toString(StringBuilder builder) {
		if(column == null){
			return;
		}else{
			builder.append(" ");
			builder.append(column.getSql());
			if(expression != null){
				expression.toString(builder);
			}
		}
	}

}