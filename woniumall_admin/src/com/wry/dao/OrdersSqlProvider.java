package com.wry.dao;

import com.wry.domain.Orders;
import com.wry.domain.OrdersExample.Criteria;
import com.wry.domain.OrdersExample.Criterion;
import com.wry.domain.OrdersExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OrdersSqlProvider {
    public String countByExample(OrdersExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mall_order");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(OrdersExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mall_order");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Orders record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mall_order");
        
        if (record.getOrderno() != null) {
            sql.VALUES("orderno", "#{orderno,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.VALUES("userid", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getOrdertime() != null) {
            sql.VALUES("ordertime", "#{ordertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccept() != null) {
            sql.VALUES("accept", "#{accept,jdbcType=VARCHAR}");
        }
        
        if (record.getTelphone() != null) {
            sql.VALUES("telphone", "#{telphone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            sql.VALUES("money", "#{money,jdbcType=DECIMAL}");
        }
        
        if (record.getPaytype() != null) {
            sql.VALUES("paytype", "#{paytype,jdbcType=INTEGER}");
        }
        
        if (record.getPaytime() != null) {
            sql.VALUES("paytime", "#{paytime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelivertime() != null) {
            sql.VALUES("delivertime", "#{delivertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getReceivetime() != null) {
            sql.VALUES("receivetime", "#{receivetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getIsdel() != null) {
            sql.VALUES("isdel", "#{isdel,jdbcType=CHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(OrdersExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("orderno");
        sql.SELECT("userid");
        sql.SELECT("ordertime");
        sql.SELECT("accept");
        sql.SELECT("telphone");
        sql.SELECT("address");
        sql.SELECT("money");
        sql.SELECT("paytype");
        sql.SELECT("paytime");
        sql.SELECT("delivertime");
        sql.SELECT("receivetime");
        sql.SELECT("status");
        sql.SELECT("isdel");
        sql.FROM("mall_order");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Orders record = (Orders) parameter.get("record");
        OrdersExample example = (OrdersExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("mall_order");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getOrderno() != null) {
            sql.SET("orderno = #{record.orderno,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.SET("userid = #{record.userid,jdbcType=INTEGER}");
        }
        
        if (record.getOrdertime() != null) {
            sql.SET("ordertime = #{record.ordertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccept() != null) {
            sql.SET("accept = #{record.accept,jdbcType=VARCHAR}");
        }
        
        if (record.getTelphone() != null) {
            sql.SET("telphone = #{record.telphone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            sql.SET("money = #{record.money,jdbcType=DECIMAL}");
        }
        
        if (record.getPaytype() != null) {
            sql.SET("paytype = #{record.paytype,jdbcType=INTEGER}");
        }
        
        if (record.getPaytime() != null) {
            sql.SET("paytime = #{record.paytime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelivertime() != null) {
            sql.SET("delivertime = #{record.delivertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getReceivetime() != null) {
            sql.SET("receivetime = #{record.receivetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getIsdel() != null) {
            sql.SET("isdel = #{record.isdel,jdbcType=CHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mall_order");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("orderno = #{record.orderno,jdbcType=VARCHAR}");
        sql.SET("userid = #{record.userid,jdbcType=INTEGER}");
        sql.SET("ordertime = #{record.ordertime,jdbcType=TIMESTAMP}");
        sql.SET("accept = #{record.accept,jdbcType=VARCHAR}");
        sql.SET("telphone = #{record.telphone,jdbcType=VARCHAR}");
        sql.SET("address = #{record.address,jdbcType=VARCHAR}");
        sql.SET("money = #{record.money,jdbcType=DECIMAL}");
        sql.SET("paytype = #{record.paytype,jdbcType=INTEGER}");
        sql.SET("paytime = #{record.paytime,jdbcType=TIMESTAMP}");
        sql.SET("delivertime = #{record.delivertime,jdbcType=TIMESTAMP}");
        sql.SET("receivetime = #{record.receivetime,jdbcType=TIMESTAMP}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("isdel = #{record.isdel,jdbcType=CHAR}");
        
        OrdersExample example = (OrdersExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Orders record) {
        SQL sql = new SQL();
        sql.UPDATE("mall_order");
        
        if (record.getOrderno() != null) {
            sql.SET("orderno = #{orderno,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.SET("userid = #{userid,jdbcType=INTEGER}");
        }
        
        if (record.getOrdertime() != null) {
            sql.SET("ordertime = #{ordertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccept() != null) {
            sql.SET("accept = #{accept,jdbcType=VARCHAR}");
        }
        
        if (record.getTelphone() != null) {
            sql.SET("telphone = #{telphone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getMoney() != null) {
            sql.SET("money = #{money,jdbcType=DECIMAL}");
        }
        
        if (record.getPaytype() != null) {
            sql.SET("paytype = #{paytype,jdbcType=INTEGER}");
        }
        
        if (record.getPaytime() != null) {
            sql.SET("paytime = #{paytime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelivertime() != null) {
            sql.SET("delivertime = #{delivertime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getReceivetime() != null) {
            sql.SET("receivetime = #{receivetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getIsdel() != null) {
            sql.SET("isdel = #{isdel,jdbcType=CHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, OrdersExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}