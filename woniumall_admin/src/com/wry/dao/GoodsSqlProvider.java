package com.wry.dao;

import com.wry.domain.Goods;
import com.wry.domain.GoodsExample.Criteria;
import com.wry.domain.GoodsExample.Criterion;
import com.wry.domain.GoodsExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class GoodsSqlProvider {
    public String countByExample(GoodsExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mall_goods");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(GoodsExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mall_goods");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Goods record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mall_goods");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsno() != null) {
            sql.VALUES("goodsno", "#{goodsno,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.VALUES("publisher", "#{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getPubtime() != null) {
            sql.VALUES("pubtime", "#{pubtime,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryid() != null) {
            sql.VALUES("categoryid", "#{categoryid,jdbcType=INTEGER}");
        }
        
        if (record.getImage() != null) {
            sql.VALUES("image", "#{image,jdbcType=VARCHAR}");
        }
        
        if (record.getStock() != null) {
            sql.VALUES("stock", "#{stock,jdbcType=INTEGER}");
        }
        
        if (record.getMarketprice() != null) {
            sql.VALUES("marketprice", "#{marketprice,jdbcType=DECIMAL}");
        }
        
        if (record.getSalesprice() != null) {
            sql.VALUES("salesprice", "#{salesprice,jdbcType=DECIMAL}");
        }
        
        if (record.getUptime() != null) {
            sql.VALUES("uptime", "#{uptime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDowntime() != null) {
            sql.VALUES("downtime", "#{downtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(GoodsExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("name");
        sql.SELECT("goodsno");
        sql.SELECT("author");
        sql.SELECT("publisher");
        sql.SELECT("pubtime");
        sql.SELECT("categoryid");
        sql.SELECT("image");
        sql.SELECT("stock");
        sql.SELECT("marketprice");
        sql.SELECT("salesprice");
        sql.SELECT("uptime");
        sql.SELECT("downtime");
        sql.SELECT("status");
        sql.SELECT("description");
        sql.FROM("mall_goods");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(GoodsExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("name");
        sql.SELECT("goodsno");
        sql.SELECT("author");
        sql.SELECT("publisher");
        sql.SELECT("pubtime");
        sql.SELECT("categoryid");
        sql.SELECT("image");
        sql.SELECT("stock");
        sql.SELECT("marketprice");
        sql.SELECT("salesprice");
        sql.SELECT("uptime");
        sql.SELECT("downtime");
        sql.SELECT("status");
        sql.FROM("mall_goods");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Goods record = (Goods) parameter.get("record");
        GoodsExample example = (GoodsExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("mall_goods");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsno() != null) {
            sql.SET("goodsno = #{record.goodsno,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{record.author,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.SET("publisher = #{record.publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getPubtime() != null) {
            sql.SET("pubtime = #{record.pubtime,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryid() != null) {
            sql.SET("categoryid = #{record.categoryid,jdbcType=INTEGER}");
        }
        
        if (record.getImage() != null) {
            sql.SET("image = #{record.image,jdbcType=VARCHAR}");
        }
        
        if (record.getStock() != null) {
            sql.SET("stock = #{record.stock,jdbcType=INTEGER}");
        }
        
        if (record.getMarketprice() != null) {
            sql.SET("marketprice = #{record.marketprice,jdbcType=DECIMAL}");
        }
        
        if (record.getSalesprice() != null) {
            sql.SET("salesprice = #{record.salesprice,jdbcType=DECIMAL}");
        }
        
        if (record.getUptime() != null) {
            sql.SET("uptime = #{record.uptime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDowntime() != null) {
            sql.SET("downtime = #{record.downtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{record.description,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mall_goods");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("goodsno = #{record.goodsno,jdbcType=VARCHAR}");
        sql.SET("author = #{record.author,jdbcType=VARCHAR}");
        sql.SET("publisher = #{record.publisher,jdbcType=VARCHAR}");
        sql.SET("pubtime = #{record.pubtime,jdbcType=VARCHAR}");
        sql.SET("categoryid = #{record.categoryid,jdbcType=INTEGER}");
        sql.SET("image = #{record.image,jdbcType=VARCHAR}");
        sql.SET("stock = #{record.stock,jdbcType=INTEGER}");
        sql.SET("marketprice = #{record.marketprice,jdbcType=DECIMAL}");
        sql.SET("salesprice = #{record.salesprice,jdbcType=DECIMAL}");
        sql.SET("uptime = #{record.uptime,jdbcType=TIMESTAMP}");
        sql.SET("downtime = #{record.downtime,jdbcType=TIMESTAMP}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("description = #{record.description,jdbcType=LONGVARCHAR}");
        
        GoodsExample example = (GoodsExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("mall_goods");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("goodsno = #{record.goodsno,jdbcType=VARCHAR}");
        sql.SET("author = #{record.author,jdbcType=VARCHAR}");
        sql.SET("publisher = #{record.publisher,jdbcType=VARCHAR}");
        sql.SET("pubtime = #{record.pubtime,jdbcType=VARCHAR}");
        sql.SET("categoryid = #{record.categoryid,jdbcType=INTEGER}");
        sql.SET("image = #{record.image,jdbcType=VARCHAR}");
        sql.SET("stock = #{record.stock,jdbcType=INTEGER}");
        sql.SET("marketprice = #{record.marketprice,jdbcType=DECIMAL}");
        sql.SET("salesprice = #{record.salesprice,jdbcType=DECIMAL}");
        sql.SET("uptime = #{record.uptime,jdbcType=TIMESTAMP}");
        sql.SET("downtime = #{record.downtime,jdbcType=TIMESTAMP}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        
        GoodsExample example = (GoodsExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Goods record) {
        SQL sql = new SQL();
        sql.UPDATE("mall_goods");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsno() != null) {
            sql.SET("goodsno = #{goodsno,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.SET("publisher = #{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getPubtime() != null) {
            sql.SET("pubtime = #{pubtime,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryid() != null) {
            sql.SET("categoryid = #{categoryid,jdbcType=INTEGER}");
        }
        
        if (record.getImage() != null) {
            sql.SET("image = #{image,jdbcType=VARCHAR}");
        }
        
        if (record.getStock() != null) {
            sql.SET("stock = #{stock,jdbcType=INTEGER}");
        }
        
        if (record.getMarketprice() != null) {
            sql.SET("marketprice = #{marketprice,jdbcType=DECIMAL}");
        }
        
        if (record.getSalesprice() != null) {
            sql.SET("salesprice = #{salesprice,jdbcType=DECIMAL}");
        }
        
        if (record.getUptime() != null) {
            sql.SET("uptime = #{uptime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDowntime() != null) {
            sql.SET("downtime = #{downtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, GoodsExample example, boolean includeExamplePhrase) {
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