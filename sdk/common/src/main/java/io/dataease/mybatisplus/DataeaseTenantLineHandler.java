package io.dataease.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import io.dataease.utils.AuthUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@Slf4j
@Component
public class DataeaseTenantLineHandler implements TenantLineHandler {

    @Resource
    private TenantFilterProperties tenantFilterProperties;

    private static ThreadLocal<Long> TENANT_TL = new ThreadLocal<>();

    /**
     * 设置线程租户，使用后必须调用remove方法
     * @param tenantId
     */
    public static void setTenant(Long tenantId) {
        TENANT_TL.set(tenantId);
    }

    public static void removeTenant() {
        TENANT_TL.remove();
    }

    public static Long getTenant() {
        return TENANT_TL.get();
    }

    @Override
    public Expression getTenantId() {
        Long tenantId = null;
        tenantId = getTenant();
        if (Objects.isNull(tenantId) && Objects.nonNull(RequestContextHolder.getRequestAttributes())) {
            tenantId = AuthUtils.getUser().getUserId();
        }

        log.debug("租户id为:{}", tenantId);
        if (Objects.nonNull(tenantId)) {
            return new LongValue(tenantId);
        }
        return new NullValue();
    }

    /**
     * 设置租户列
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return tenantFilterProperties.getTenantIdColumn();
    }

    /**
     * 需要过滤的表
     * @param tableName 表名
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
        return tenantFilterProperties.getTableFilter().stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }

}
