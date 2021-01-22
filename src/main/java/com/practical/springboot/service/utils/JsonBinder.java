package com.practical.springboot.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Objects;
import org.jooq.Binding;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingGetSQLInputContext;
import org.jooq.BindingGetStatementContext;
import org.jooq.BindingRegisterContext;
import org.jooq.BindingSQLContext;
import org.jooq.BindingSetSQLOutputContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Converter;
import org.jooq.RenderContext;
import org.jooq.impl.DSL;

public class JsonBinder implements Binding<Object, JsonNode> {
    private static final long serialVersionUID = -3537514888695302019L;

    public JsonBinder() {
    }

    public Converter<Object, JsonNode> converter() {
        return new JsonNodeConverter();
    }

    public void sql(BindingSQLContext<JsonNode> ctx) {
        ((RenderContext)ctx.render().visit(DSL.val(ctx.convert(this.converter()).value()))).sql("::json");
    }

    public void register(BindingRegisterContext<JsonNode> ctx) throws SQLException {
        ctx.statement().registerOutParameter(ctx.index(), 12);
    }

    public void set(BindingSetStatementContext<JsonNode> ctx) throws SQLException {
        ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(this.converter()).value(), (String)null));
    }

    public void set(BindingSetSQLOutputContext<JsonNode> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    public void get(BindingGetResultSetContext<JsonNode> ctx) throws SQLException {
        ctx.convert(this.converter()).value(ctx.resultSet().getString(ctx.index()));
    }

    public void get(BindingGetStatementContext<JsonNode> ctx) throws SQLException {
        ctx.convert(this.converter()).value(ctx.statement().getString(ctx.index()));
    }

    public void get(BindingGetSQLInputContext<JsonNode> ctx) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
