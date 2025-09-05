package io.dataease.mybatisplus;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "tenant")
public class TenantFilterProperties {

    /**
     * 租户字段名
     */
    private String tenantIdColumn;

    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 租户需要过滤的表名
     */
    private List<String> tableFilter;

    /**
     * 租户需要过滤的方法
     */
    private List<String> tenantFilterMethod;

    public String getTenantIdColumn() {
        return tenantIdColumn;
    }

    public void setTenantIdColumn(String tenantIdColumn) {
        this.tenantIdColumn = tenantIdColumn;
    }

    public List<String> getTableFilter() {
        return tableFilter;
    }

    public void setTableFilter(List<String> tableFilter) {
        this.tableFilter = tableFilter;
    }

    public List<String> getTenantFilterMethod() {
        return tenantFilterMethod;
    }

    public void setTenantFilterMethod(List<String> tenantFilterMethod) {
        this.tenantFilterMethod = tenantFilterMethod;
    }
}
